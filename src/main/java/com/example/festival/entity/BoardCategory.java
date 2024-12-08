package com.example.festival.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardCategory {

    @Id
    @Column(name = "boardcategory_id")       //테이블에서 매핑될 컬럼
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id") //매핑할 외래키지정
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id") //매핑할 외래키지정
    private Category category;
}
