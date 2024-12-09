package com.example.festival.service;

import com.example.festival.dto.BoardDTO;
import com.example.festival.dto.BoardImgDTO;
import com.example.festival.entity.Board;
import com.example.festival.entity.Category;
import com.example.festival.entity.SubCategory;
import com.example.festival.repository.BoardRepository;
import com.example.festival.repository.CategoryRepository;
import com.example.festival.repository.SubCategoryRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codehaus.groovy.tools.shell.IO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    private final BoardImgService boardImgService;

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public Long saveBoard(BoardDTO boardDTO, List<MultipartFile> multipartFiles) throws IOException {

        //카테고리 대 , 소   String tag
        log.info(boardDTO);
        Board board = modelMapper.map(boardDTO, Board.class);

        if(boardDTO.getCategoryid() !=null && !boardDTO.getCategoryid().equals("")){
            Optional<Category> categoryOptional =
            categoryRepository.findById(boardDTO.getCategoryid());

            Category category =
            categoryOptional.orElseThrow(EntityNotFoundException::new);

            board.setCategory(category);
        }

        if(boardDTO.getSubcategory_id() !=null && !boardDTO.getSubcategory_id().equals("")){
            Optional<SubCategory> subCategoryOptional =
                    subCategoryRepository.findById(boardDTO.getSubcategory_id());

            SubCategory subCategory =
                    subCategoryOptional.orElseThrow(EntityNotFoundException::new);

            board.setSubCategory(subCategory);
        }



        board = boardRepository.save(board);

        boardImgService.saveImg(board.getBno(), multipartFiles);

        return board.getBno();

    }


    public BoardDTO register(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        boardRepository.save(board);

        return boardDTO;
    }

    public List<BoardDTO> list() {
        List<Board> boardList =
                boardRepository.findAll();
        boardList.forEach(board -> log.info(board));

        List<BoardDTO> boardDTOList =
                boardList.stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());

        boardList.forEach(boardDTO -> log.info(boardDTO));

        return boardDTOList;


    }

    public BoardDTO read(Long bno) {

        Board board =
                boardRepository.findById(bno).orElseThrow(EntityExistsException::new);

        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        boardDTO.setBoardImgDTOList(board.getBoardImgList().stream().map(boardImg -> modelMapper.map(boardImg, BoardImgDTO.class)).collect(Collectors.toList()));
//                .setBoardImgDTOList(board.getBoardImgList());

        return boardDTO;
    }

    public BoardDTO read(Long bno, String memberID) {

        Board board =
                boardRepository.findById(bno).orElseThrow(EntityExistsException::new);

        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
//                .setBoardImgDTOList(board.getBoardImgList());

        return boardDTO;
    }

    public BoardDTO update(BoardDTO boardDTO, Long bno, List<MultipartFile> multipartFiles, Integer[] delbno, Long mainbno) {

        Board board =
                boardRepository.findById(boardDTO.getBno())
                        .orElseThrow(EntityNotFoundException::new);

        board.setTitle(boardDTO.getTitle());
        board.setStartschedule(boardDTO.getStartschedule());
        board.setEndschedule(boardDTO.getEndschedule());
        board.setLocation(boardDTO.getLocation());
        board.setPay(boardDTO.getPay());
        board.setParking(boardDTO.getParking());
        board.setDetail(boardDTO.getDetail());
        board.setContent(boardDTO.getContent());
        board.setTel(boardDTO.getTel());

        if (delbno != null) {
            for (Integer bnoInteger : delbno) {

                if (bno != null && !bno.equals("")) {
                    log.info("삭제할 번호는 bno" + bno);
                    boardImgService.removeimg(bno.longValue());

                }

            }
        }

        try {
            boardImgService.update(bno, multipartFiles, mainbno);
        } catch (IOException e) {

        }
        return null;
    }

    public void remove(Long bno) {
        log.info("서비스로 들어온 삭제할 보드넘버 : " + bno);


        boardRepository.deleteById(bno);


    }
}
