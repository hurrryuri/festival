package com.example.festival.repository;

import com.example.festival.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {

    public List<BoardCategory> findBySubCategoryId(Long id);
}
