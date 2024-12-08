package com.example.festival.service;

import com.example.festival.dto.CategoryDTO;
import com.example.festival.dto.SubCategoryDTO;
import com.example.festival.entity.Category;
import com.example.festival.entity.SubCategory;
import com.example.festival.repository.CategoryRepository;
import com.example.festival.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;


    public void savecategory(SubCategoryDTO subCategoryDTO){

        Category category =
                categoryRepository.findById(subCategoryDTO.getCategoryid()).get();

        SubCategory subCategory = modelMapper.map(subCategoryDTO, SubCategory.class);

        subCategory.setCategory(category);

        subCategoryRepository.save(subCategory);

    }

    public List<SubCategoryDTO> list2 (){

        return subCategoryRepository.findAll().stream().map(subCategory ->
                modelMapper.map(subCategory, SubCategoryDTO.class).setCategoryDTO(modelMapper.map(subCategory.getCategory(), CategoryDTO.class))
        ).collect(Collectors.toList());

    }
}
