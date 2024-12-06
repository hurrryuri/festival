package com.example.festival.service;

import com.example.festival.dto.CategoryDTO;
import com.example.festival.dto.SmallCateDTO;
import com.example.festival.entity.Category;
import com.example.festival.entity.SmallCate;
import com.example.festival.repository.CategoryRepository;
import com.example.festival.repository.SmallCateRepository;
import jdk.dynalink.linker.LinkerServices;
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
public class SmallCateService {

    private final SmallCateRepository smallCateRepository;

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;


    public void savecategory(SmallCateDTO smallCateDTO){

        Category category =
                categoryRepository.findById(smallCateDTO.getCategoryId()).get();

        SmallCate smallCate = modelMapper.map(smallCateDTO, SmallCate.class);

        smallCate.setCategory(category);

        smallCateRepository.save(smallCate);

    }

    public List<CategoryDTO> list2 (){

        return categoryRepository.findAll().stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());

    }
}
