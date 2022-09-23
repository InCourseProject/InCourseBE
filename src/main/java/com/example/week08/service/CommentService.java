package com.example.week08.service;
import com.example.week08.domain.Comment;
import com.example.week08.domain.Member;
import com.example.week08.domain.Post;
import com.example.week08.dto.request.CommentRequestDto;
import com.example.week08.errorhandler.BusinessException;
import com.example.week08.repository.CommentRepository;
import com.example.week08.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.week08.errorhandler.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void CommentCreate(CommentRequestDto commentRequestDto, Member member) {
        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(
                () -> new BusinessException("포스트가 존재하지 않습니다.", POST_NOT_EXIST)
        );
        Comment comment = new Comment(commentRequestDto, member, post);
        commentRepository.save(comment);
    }

    @Transactional
    public void CommentDelete(Long commentId, Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new BusinessException("코맨트가 존재하지 않습니다.", COMMENT_NOT_EXIST)
        );
        if (!comment.getAuthor().equals(member.getEmail())) {
            throw new BusinessException("아이디가 일치하지 않습니다.", MEMBER_NOT_EXIST);
        }

        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void CommentUpdate(Long commentId, CommentRequestDto commentRequestDto, Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new BusinessException("코맨트가 존재하지 않습니다.", COMMENT_NOT_EXIST)
        );
        if (!comment.getAuthor().equals(member.getEmail())) {
            throw new BusinessException("아이디가 일치하지 않습니다.", MEMBER_NOT_EXIST);
        }
        comment.update(commentRequestDto);
    }
}
