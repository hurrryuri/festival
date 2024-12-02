package com.example.festival.entity;

import com.example.festival.constant.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @Column(name = "member_mno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(unique = true , nullable = false, length = 20)
    private String memberID;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    private Boolean approve;

    @Enumerated(EnumType.STRING)
    private Role role;
}

