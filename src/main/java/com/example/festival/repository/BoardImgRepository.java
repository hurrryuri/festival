package com.example.festival.repository;

import com.example.festival.entity.BoardImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardImgRepository extends JpaRepository<BoardImg, Long> {

    List<BoardImg> findByBoardBno ( Long id);

    public BoardImg findByBoardBnoAndRepimgYn (Long id, String val);
}
