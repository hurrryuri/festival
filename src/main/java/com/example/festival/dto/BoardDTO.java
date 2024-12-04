package com.example.festival.dto;

import com.example.festival.entity.BoardImg;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {

    private Long bno;


    @NotBlank
    @Size(max = 50)
    private String title;


    @NotNull
    @FutureOrPresent(message = "미래여야한다.")
    private LocalDate startschedule;

    @NotNull
    @FutureOrPresent(message = "미래여야한다.")
    private LocalDate endschedule;

    @NotBlank
    @Size(max = 50)
    private String location;

    @NotBlank
    private String pay;

    @Size(max = 50)
    private String parking;

    private String detail;

    @NotBlank
    @Size(max = 255)
    private String content;

    @Size(max = 20)
    private String tel;


    @Size(max = 255)
    private String tagtitle;

    private List<BoardImgDTO> boardImgDTOList;


    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    public BoardDTO setBoardImgDTOList(List<BoardImgDTO> boardImgDTOList) {
        this.boardImgDTOList = boardImgDTOList;

        return this;
    }
}
