package com.example.festival.entity;

import com.example.festival.entity.base.BaseEntity;
import com.example.festival.entity.base.BaseTimeEntity;
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
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;


    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private LocalDate startschedule;

    @Column(nullable = false, length = 50)
    private LocalDate endschedule;

    @Column(nullable = false, length = 50)
    private String location;

    @Column(nullable = false, length = 20)
    private String pay;

    @Column(length = 50)
    private String parking;

    @Lob
    @Column(nullable = false)
    private String detail;

    @Column(nullable = false, length = 255)
    private String content;

    @Column(length = 20)
    private String tel;


    @Column(nullable = false, length = 255)
    private String tagtitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL,
    orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardImg> boardImgList;

}

