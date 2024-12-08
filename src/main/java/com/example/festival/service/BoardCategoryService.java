package com.example.festival.service;

import com.example.festival.dto.BoardCategoryDTO;
import com.example.festival.entity.BoardCategory;
import com.example.festival.entity.Category;
import com.example.festival.entity.SubCategory;
import com.example.festival.repository.BoardCategoryRepository;
import com.example.festival.repository.CategoryRepository;
import com.example.festival.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.registry.Registry;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class BoardCategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final BoardCategoryRepository boardCategoryRepository;
    private final ModelMapper modelMapper;

    public void savecategory(BoardCategoryDTO boardCategoryDTO){

        log.info(boardCategoryDTO);
        if(boardCategoryDTO.getCategoryid()!=null && (boardCategoryDTO.getSubcategoryid() == null ||boardCategoryDTO.getSubcategoryid().equals(""))){

            Category category =
                    categoryRepository.findById(boardCategoryDTO.getCategoryid()).get();

            BoardCategory boardCategory = modelMapper.map(boardCategoryDTO,BoardCategory.class);

            boardCategory.setCategory(category);

            boardCategoryRepository.save(boardCategory);
        } else {

            SubCategory subCategory =
                    subCategoryRepository.findById(boardCategoryDTO.getSubcategoryid()).get();

            BoardCategory boardCategory = modelMapper.map(boardCategoryDTO, BoardCategory.class);

            boardCategory.setSubCategory(subCategory);
            boardCategoryRepository.save(boardCategory);
        }
    }

    public List<BoardCategoryDTO> list (BoardCategoryDTO boardCategoryDTO){

        if(boardCategoryDTO.getCategoryid() == null){
            return boardCategoryRepository.findAll().stream().map(boardCategory -> modelMapper.map(boardCategory, BoardCategoryDTO.class))
                    .collect(Collectors.toList());

        }else {
            return boardCategoryRepository.findBySubCategoryId(boardCategoryDTO.getCategoryid()).stream().map(boardCategory ->
                            modelMapper.map(boardCategory, BoardCategoryDTO.class))
                    .collect(Collectors.toList());
        }
    }
}
