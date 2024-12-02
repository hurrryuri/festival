package com.example.festival.dto;

import com.example.festival.constant.Role;
import com.example.festival.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    //어차피 등록시에는 안씀
    private Long mno;

    @NotBlank
    @Size(min = 4, max = 20, message = "ID는 4자 이상 20자 이하로 작성해주세요")
    private String memberID;

    @NotBlank
    @Size(min = 4, max = 20)
    private String password;

    @NotBlank
    @Size(min = 2, max = 20, message = "이름은 2자 이상 20자 이하로 작성해주세요")
    private String name;

    @Email(message = "이메일 형식으로 작성해주세요")
    @NotBlank(message = "이메일을 작성해주세요")
    @Size(min = 2, max = 50, message = "이메일은 2자 이상 50자 이하로 작성해주세요")
    private String email;

    private Boolean approve;

    private Role role;

    public static Member dtoEntity(MemberDTO memberDTO) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Member member = new Member();
        member.setMemberID(memberDTO.memberID);
        member.setName(memberDTO.name);
        member.setEmail(memberDTO.email);

        member.setPassword(passwordEncoder.encode(memberDTO.password));
        member.setRole(Role.ADMIN);

        return member;
    }
}


