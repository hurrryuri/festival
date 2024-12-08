package com.example.festival.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SubCategoryDTO {

    private Long id;

    private Long categoryid;

    private String categoryname;

    private CategoryDTO categoryDTO;

    public SubCategoryDTO setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
        return this;
    }
}
