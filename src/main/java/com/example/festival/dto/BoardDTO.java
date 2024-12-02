package com.example.festival.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate schedule;

    @NotBlank
    @Size(max = 50)
    private String location;

    @NotNull
    private int pay;

    @Size(max = 50)
    private String parking;

    private String detail;

    @NotBlank
    @Size(max = 255)
    private String content;

    @Size(max = 20)
    private String tel;

    private LocalDate updatedate;


    @Size(max = 255)
    private String tagtitle;


}
