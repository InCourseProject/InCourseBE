package com.example.week08.controller;



import com.example.week08.domain.Post;
import com.example.week08.domain.UserDetailsImpl;
import com.example.week08.dto.request.PlaceRequestDto;
import com.example.week08.dto.request.PostPlaceDto;
import com.example.week08.dto.request.PostPlacePutDto;
import com.example.week08.dto.request.PostRequestDto;
import com.example.week08.dto.response.PostResponseDto;
import com.example.week08.service.PostService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {


    private final PostService postService;

    // 게시물 상세내용 가져오기
    @GetMapping("api/post/{postId}")
    public PostResponseDto detailPost(@PathVariable(name = "postId") Long id) {
        return postService.findPostByID(id);
    }

    //게시물 생성
    @PostMapping("api/auth/post")
    public Post createPost(@Valid @RequestBody PostPlaceDto postPlaceDto,
//                           @RequestBody PlaceRequestDto placeRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return postService.postCreate( postPlaceDto,  userDetails.getMember());
    }

    //게시물 좋아요
    @PostMapping("api/auth/post/like")
    public Post likePost(PostRequestDto postRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.postlike( postRequestDto, userDetails.getMember());
    }

    // 게시물 수정
    @PutMapping("/api/auth/post/{postId}")
    public Post editPost(@PathVariable(name = "postId") Long id,
                         @Valid @RequestBody PostPlacePutDto postPlacePutDto,
                         @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return postService.postEdit(id, postPlacePutDto, userDetails.getMember());
    }

    // 게시물 삭제
    @DeleteMapping("/api/auth/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "postId") Long id,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.postDelete(id, userDetails.getMember());
        return ResponseEntity.ok("게시물 삭제 성공");
    }


    // 게시물 검색
    @GetMapping("/api/post/search")
    public List<PostResponseDto> searchPost(@RequestParam("query") String keyword) {
        return postService.postSearch(keyword);
    }

    //게시물 전체조회
    @GetMapping("/api/post")
    public Page<Post> listAllPost(Model model,
                                    @RequestParam(value = "category", required = false) String category,
                                  Pageable pageable) {

        return postService.postAllList(model,category, pageable);
    }

    //게시물 저장
    @GetMapping("api/auth/post/restore/{postId}")
    public ResponseEntity<String> restorePost(@PathVariable Long postId,
                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.postStore(postId, userDetails.getMember());
        return ResponseEntity.ok("성공적으로 저장됨");
    }
}

