package com.example.festival.controller;

import com.example.festival.dto.MemberDTO;
import com.example.festival.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/adminRegister")
    public String adminRegister(Model model) {
        log.info("진입");

        model.addAttribute("memberDTO", new MemberDTO());

        log.info("반환");
        return "member/adminRegister";

    }

    @PostMapping("/adminregister")
    public String adminRegisterPost(@Valid MemberDTO memberDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            log.info((bindingResult.getAllErrors()));

            return "adminRegister";

        }

        try {
            memberService.saveMember(memberDTO);
        } catch (IllegalStateException e) {

            model.addAttribute("msg", e.getMessage());

            return "admin/adminRegister";
        }

        return null;
    }

    @GetMapping("/login")
    public String loginAdmin(){

        log.info("로그인");

        return "/member/login";}
}