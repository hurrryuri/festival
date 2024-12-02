package com.example.festival.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "board")
@Table(name = "board_img")
@NoArgsConstructor
public class BoardImg {

    @Id
    @Column(name = "board_img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgName;

    private String imgUrl;

    private String repimgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bno")
    private Board board;


}
