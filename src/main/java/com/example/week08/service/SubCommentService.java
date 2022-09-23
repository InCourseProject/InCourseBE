package com.example.week08.service;

import com.example.week08.domain.Comment;
import com.example.week08.domain.Member;
import com.example.week08.domain.SubComment;
import com.example.week08.dto.request.SubCommentRequestDto;
import com.example.week08.errorhandler.BusinessException;
import com.example.week08.repository.CommentRepository;
import com.example.week08.repository.SubCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static com.example.week08.errorhandler.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class SubCommentService {
    private final CommentRepository commentRepository;
    private final SubCommentRepository subCommentRepository;

    @Transactional
    public void SubCommentCreate(SubCommentRequestDto subCommentRequestDto, Member member) {
        Comment comment = commentRepository.findById(subCommentRequestDto.getCommentId()).orElseThrow(
                () -> new BusinessException("코맨트가 존재하지 않습니다.", COMMENT_NOT_EXIST)
        );
        SubComment subComment = new SubComment(subCommentRequestDto, member, comment);
        List<SubComment> subComments = comment.getSubComments();
        subComments.add(subComment);
        subCommentRepository.save(subComment);
    }

    @Transactional
    public void SubCommentDelete(Long subcommentId, Member member) {
        SubComment subComment = subCommentRepository.findById(subcommentId).orElseThrow(() ->
                new BusinessException("서브 코맨트가 존재하지 않습니다. ", SUBCOMMENT_NOT_EXIST));
        if (!subComment.getAuthor().equals(member.getEmail())) {
            throw new BusinessException("아이디가 일치하지 않습니다.", MEMBER_NOT_EXIST);
        }

        subCommentRepository.deleteById(subcommentId);
    }

    @Transactional
    public void SubCommentUpdate(SubCommentRequestDto subCommentRequestDto, Long subcommentId, Member member) {
        SubComment subComment = subCommentRepository.findById(subcommentId).orElseThrow(() ->
                new BusinessException("서브 코맨트가 존재하지 않습니다. ", SUBCOMMENT_NOT_EXIST));
        if (!subComment.getAuthor().equals(member.getEmail())) {
            throw new BusinessException("아이디가 일치하지 않습니다.", MEMBER_NOT_EXIST);
        }
        subComment.update(subCommentRequestDto);
    }
}
