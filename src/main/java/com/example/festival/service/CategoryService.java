package com.example.festival.service;

import com.example.festival.dto.CategoryDTO;
import com.example.festival.entity.Category;
import com.example.festival.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;


    public void savecategory(CategoryDTO categoryDTO){

        Category category = modelMapper.map(categoryDTO, Category.class);

        categoryRepository.save(category);


    }
    public List<CategoryDTO> list (){

        return categoryRepository.findAll().stream().map(category ->
                modelMapper.map(category , CategoryDTO.class)
        ).collect(Collectors.toList());

    }






}
