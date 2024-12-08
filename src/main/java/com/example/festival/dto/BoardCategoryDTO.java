package com.example.festival.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
@NoArgsConstructor
public class BoardCategoryDTO {

    private Long id;


    private String title;


    private Long categoryid;

    private Long subcategoryid;

    private CategoryDTO categoryDTO;

}
