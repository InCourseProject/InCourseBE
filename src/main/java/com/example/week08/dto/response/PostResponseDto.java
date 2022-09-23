package com.example.week08.dto.response;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.week08.domain.Comment;
import com.example.week08.domain.Member;
import com.example.week08.domain.Post;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String category;
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
    private Member member;

    private List<Comment> comment;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = post.getCategory();
        this.createdAt = post.getCreatedAt();
        this.comment = post.getComments();
        this.modifiedAt = post.getModifiedAt();
        this.member = post.getMember();
    }

}
