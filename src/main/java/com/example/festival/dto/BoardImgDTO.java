package com.example.festival.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardImgDTO {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repimgYn;

    private BoardDTO boardDTO;
}
