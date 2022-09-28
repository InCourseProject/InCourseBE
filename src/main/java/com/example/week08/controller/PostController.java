package com.example.week08.controller;

import com.example.week08.domain.Post;
import com.example.week08.dto.request.PostPlaceDto;
import com.example.week08.dto.request.PostPlacePutDto;
import com.example.week08.dto.request.PostRecommendedDto;
import com.example.week08.dto.request.PostRequestDto;
import com.example.week08.dto.response.PostResponseDto;
import com.example.week08.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;


    // 코스 게시글 작성
    @PostMapping( "/api/course")
    public Post createPost(@RequestPart(value = "data") PostPlaceDto requestDto,
                           @RequestPart(value = "image" ,required = false) MultipartFile image) throws IOException {
        return postService.postCreate(requestDto, image);
    }

    // 코스(게시글) 상세 조회
    @GetMapping( "/api/course/{courseId}")
    public PostResponseDto getPost(@PathVariable Long courseId) {
        return postService.getPost(courseId);
    }

    // 코스(게시글) 전체 조회
    @GetMapping("/api/course")
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPost();
    }

    // 코스(게시글) 수정
    @PutMapping( "/api/course/{courseId}")
    public Post updatePost(@PathVariable Long courseId,
                           @RequestPart(value = "data") PostPlacePutDto requestDto,
                           @RequestPart(value = "image" ,required = false) MultipartFile image) throws IOException {
        return postService.postUpdate(courseId, requestDto, image);
    }

    // 코스(게시글) 삭제
    @DeleteMapping( "/api/course/{courseId}")
    public ResponseEntity<String> deletePost(@PathVariable Long courseId) {
        postService.postDelete(courseId);
        return ResponseEntity.ok("게시물 삭제 성공");
    }

    // 코스(게시글) 카테고리 조회
    @GetMapping("/api/course/category")
    public List<PostResponseDto> findAll(@RequestBody(required = false) PostRequestDto requestDto){
//        if (requestDto == null) return postService.findAll();
        return postService.findPost(requestDto);
    }
    //카테고리는 카테고리 목록버튼을 누르면 요청이 와서 그에 맞는 목록을 보여준다
    //추천은 카테고리도 이용할 뿐 카테고리 목록만 보여주는것이 아니다
    //추천은 현재 날씨/ 현재 지역/ 현재 계절을 바탕으로 만들어야 한다.

    //메인 날씨/지역/계절/평점 기반 추천
    @GetMapping("/api/recommended")
    public List<PostResponseDto> recommendedGet(@RequestBody(required = false) PostRecommendedDto requestDto){
        return postService.getRecommended(requestDto);
    }



}
