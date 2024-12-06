package com.example.festival.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SmallCateDTO {

    private Long id;

    private Long categoryId;

    private String categoryname;

    private CategoryDTO categoryDTO;

    public SmallCateDTO setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
        return this;
    }
}
