package com.example.week08.dto.response;
import com.example.week08.dto.TokenDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private boolean ok;
    private String message;
    private String Authorization;
    private String RefreshToken;

    public MemberResponseDto(boolean ok, String message) {
        this.ok = ok;
        this.message =message;
    }

    public MemberResponseDto(TokenDto tokenDto) {
        this.ok = true;
        this.message ="로그인 성공";
        this.Authorization =tokenDto.getAccessToken();
        this.RefreshToken = tokenDto.getRefreshToken();

    }
}

//게시물
//레포 세이브
//게시물 아이디 불러오기
