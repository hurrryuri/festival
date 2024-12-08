package com.example.festival.controller;

import com.example.festival.dto.BoardCategoryDTO;
import com.example.festival.dto.CategoryDTO;
import com.example.festival.dto.SubCategoryDTO;
import com.example.festival.repository.SubCategoryRepository;
import com.example.festival.service.BoardCategoryService;
import com.example.festival.service.CategoryService;
import com.example.festival.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CategoryController {

    private final BoardCategoryService boardCategoryService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    @GetMapping("/category/category")
    private void list(Model model, BoardCategoryDTO boardCategoryDTO){

        model.addAttribute("categorylist", categoryService.list());
        model.addAttribute("subcategorylist", subCategoryService.list2());
        model.addAttribute("boardcategorylist", boardCategoryService.list(boardCategoryDTO));



    }

    @PostMapping("/category/register")
    public String save(CategoryDTO categoryDTO){

        categoryService.savecategory(categoryDTO);

        return "redirect:/category/category";
    }

    @PostMapping("/subcategory/register")
    public String savesubcategory(SubCategoryDTO subCategoryDTO){

        subCategoryService.savecategory(subCategoryDTO);

        return "redirect:/category/category";
    }

    @PostMapping("/category/boardnew")
    public String save(BoardCategoryDTO boardCategoryDTO){

        boardCategoryService.savecategory(boardCategoryDTO);

        return "redirect:/category/category";
    }
}
