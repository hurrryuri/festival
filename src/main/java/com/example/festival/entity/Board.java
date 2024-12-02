package com.example.festival.entity;

import com.example.festival.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "board")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;


    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private LocalDate schedule;

    @Column(nullable = false, length = 50)
    private String location;

    @Column(nullable = false, length = 20)
    private int pay;

    @Column(length = 50)
    private String parking;

    @Lob
    @Column(nullable = false)
    private String detail;

    @Column(nullable = false, length = 255)
    private String content;

    @Column(length = 20)
    private int tel;

    private LocalDate updatedate;

    @Column(nullable = false, length = 255)
    private String tagtitle;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL,
    orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardImg> boardImgList;

}

