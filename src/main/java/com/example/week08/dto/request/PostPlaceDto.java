package com.example.week08.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostPlaceDto {
    private PostRequestDto postRequestDto;
//    private PlaceRequestDto placeRequestDto;
    private ArrayList<PlaceRequestDto> placeRequestDtoList;

}
