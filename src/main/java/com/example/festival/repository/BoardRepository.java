package com.example.festival.repository;

import com.example.festival.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {


    @Query("select c.categoryname , s.categoryname , b.tagtitle  from Board b " +
            "                  join Category  c " +
            "                       on b.category.id = c.id" +
            "                join  SubCategory s " +
            "                        on b.subCategory.id = s.id" +
            " " +
            "where  b.tagtitle like  concat('%',:keyword,'%' ) " +
            "    or c.categoryname = '계절축제'  or  s.categoryname = '여름' " +
            " ")
    public List<Board> asdf(String keyword);


    public List<Board> findByCategoryId (Long caid);
    public List<Board> findBySubCategoryId (Long suid);

}
