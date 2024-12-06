package com.example.festival.controller;

import com.example.festival.dto.BoardDTO;
import com.example.festival.dto.CategoryDTO;
import com.example.festival.repository.CategoryRepository;
import com.example.festival.repository.SmallCateRepository;
import com.example.festival.service.BoardService;
import com.example.festival.service.CategoryService;
import com.example.festival.service.SmallCateService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BoardService boardService;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final SmallCateService smallCateService;
    private final SmallCateRepository smallCateRepository;

    @GetMapping("/admin/board/new")
    public String registerGet(Model model, Principal principal) {
        if (principal == null) {

        }
        if (principal != null) {
        }
        model.addAttribute("boardDTO", new BoardDTO());
        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("smallCate", smallCateRepository.findAll());

        return "board/register";
    }

    @PostMapping("/admin/board/new")
    public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult,
                               List<MultipartFile> multipartFile, Model model) {

        log.info("저장됐는지 보여줘" + boardDTO);

        if (multipartFile.get(0).isEmpty()) {
            model.addAttribute("msg", "이미지 미등록됨");
            return "/board/register";

        }

        if (multipartFile != null) {
            for (MultipartFile img : multipartFile) {
                if (!img.getOriginalFilename().equals("")) {
                    log.info(img.getOriginalFilename());
                }
            }
        }

        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors());

            return "/board/register";
        }
        try {

            Long saveBoardBno =
                    boardService.saveBoard(boardDTO, multipartFile);

            log.info("사진은 등록되는지 보여줘");

            return "redirect:/admin/board/read?bno=" + saveBoardBno;

        } catch (Exception e) {
            e.printStackTrace();
            log.info("파일등록간 문제가 발생했습니다.");
            model.addAttribute("msg", "파일등록에 문제발생");
            return "/board/register";
        }
    }


    @GetMapping("/admin/board/list")

    public String adminlist(Model model, Principal principal) {

        List<BoardDTO> boardDTOList =
                boardService.list();
        model.addAttribute("boardDTOList", boardDTOList);

        return "board/list";

    }

    @GetMapping("/admin/board/read")
    public String adminread(Long bno, Model model, RedirectAttributes redirectAttributes) {

        try {
            BoardDTO boardDTO =
                    boardService.read(bno);

            model.addAttribute("boardDTO", boardDTO);

            return "/board/read";

        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("msg", "존재하지 않는 페이지입니다.");
            return "redirect:/admin/board/list";

        }


    }

    @GetMapping("/admin/board/update")
    public String adminupdateGet(Long id, Model model, Principal principal){

        BoardDTO boardDTO = boardService.read(id, principal.getName());
        if(boardDTO != null){
            model.addAttribute("boardDTO", boardDTO);
            return "board/update";
        }else {
            return "redirect:/admin/board/list";
        }
    }

    @PostMapping("/admin/board/update")
    public String boardupdate(@Valid BoardDTO boardDTO, BindingResult bindingResult, List<MultipartFile> multipartFiles, Integer[] delbno, Long mainbno){

        if(bindingResult.hasErrors()){
            log.info("유효성검사 에러");
            log.info(bindingResult.getAllErrors());

            return "/board/update";
        }

        boardService.update(boardDTO, boardDTO.getBno(), multipartFiles, delbno, mainbno);

        return null;
    }

    @PostMapping("/admin/board/del")
    public String delboard(Long bno){

        log.info("삭제할 아이템번호 : " + bno);

        boardService.remove(bno);


        return "redirect:/admin/board/list";
    }


    @GetMapping("/festival/list")
    public String aaaa() {

       return null;
    }

    @PostMapping("/category/register")
    public String save(CategoryDTO categoryDTO){

        categoryService.savecategory(categoryDTO);

        return "redirect:/category/category";
    }
}