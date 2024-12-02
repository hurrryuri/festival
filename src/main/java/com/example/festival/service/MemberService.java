package com.example.festival.service;

import com.example.festival.constant.Role;
import com.example.festival.dto.MemberDTO;
import com.example.festival.entity.Member;
import com.example.festival.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {


    private final MemberRepository memberRepository;

       @Override
    public UserDetails loadUserByUsername(String memberID) throws UsernameNotFoundException {


        Member member =
                memberRepository.findByMemberID(memberID);

        if(member == null){
            throw new UsernameNotFoundException(memberID);
        }

        return User.builder()
                .username(member.getMemberID())
                .password(member.getPassword())
                .roles(member.getRole().name())
                .build();
    }

    public Member saveMember(MemberDTO memberDTO) {
        //회원가입여부 확인
        validateDuplicateMember(memberDTO.getMemberID());


        Member member =
                MemberDTO.dtoEntity(memberDTO);

        member =
                memberRepository.save(member);

        return member;
    }

    public Member saveMemberSu(MemberDTO memberDTO) {
        //회원가입여부 확인
        validateDuplicateMember(memberDTO.getMemberID());


        Member member =
                MemberDTO.dtoEntity(memberDTO);
        member.setRole(Role.SUPERADMIN);

        member =
                memberRepository.save(member);

        return member;
    }

    private void validateDuplicateMember(String memberID){

        Member member =
                memberRepository.findByMemberID(memberID);

        if(member != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }


    }

    }