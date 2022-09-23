package com.example.week08.controller;

import com.example.week08.domain.UserDetailsImpl;
import com.example.week08.dto.request.SubCommentRequestDto;
import com.example.week08.service.SubCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SubCommentController {
    private final SubCommentService subCommentService;

    //서브코맨트 생성
    @PostMapping("api/auth/post/comment/subcomment")
    public ResponseEntity<String> createSubComment(@RequestBody SubCommentRequestDto subCommentRequestDto,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        subCommentService.SubCommentCreate(subCommentRequestDto, userDetails.getMember());
        return ResponseEntity.ok("서브코맨트 생성 성공");
    }

    //서브코맨트 삭제
    @DeleteMapping("api/auth/post/comment/subcomment/{subcommentId}")
    public ResponseEntity<String> DeleteSubComment(@PathVariable Long subcommentId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        subCommentService.SubCommentDelete(subcommentId, userDetails.getMember());
        return ResponseEntity.ok("서브코맨트 삭제 성공");
    }

    //서브코멘트 수정
    @PutMapping("api/auth/post/comment/subcomment/{subcommentId}")
    public ResponseEntity<String> UpdateSubComment(@RequestBody SubCommentRequestDto subCommentRequestDto,
                                                   @PathVariable Long subcommentId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        subCommentService.SubCommentUpdate(subCommentRequestDto,subcommentId, userDetails.getMember());
        return ResponseEntity.ok("서브코맨트 변경 성공");
    }
}
