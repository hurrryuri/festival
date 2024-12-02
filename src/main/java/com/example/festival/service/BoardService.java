package com.example.festival.service;

import com.example.festival.dto.BoardDTO;
import com.example.festival.entity.Board;
import com.example.festival.repository.BoardRepository;
import jakarta.persistence.EntityExistsException;
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
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    private final BoardImgService boardImgService;


    public Long saveBoard(BoardDTO boardDTO, List<MultipartFile> multipartFiles) throws IOException {

        Board board = modelMapper.map(boardDTO, Board.class);

        board = boardRepository.save(board);

        boardImgService.saveImg(board.getBno(), multipartFiles);

        return board.getBno();

    }


    public BoardDTO register(BoardDTO boardDTO){
        Board board = modelMapper.map(boardDTO, Board.class);
        boardRepository.save(board);

        return boardDTO;
    }

    public List<BoardDTO> list(){
        List<Board> boardList =
                boardRepository.findAll();
        boardList.forEach(board -> log.info(board));

        List<BoardDTO> boardDTOList =
                boardList.stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());

        boardList.forEach(boardDTO -> log.info(boardDTO));

        return boardDTOList;


    }
    public BoardDTO read(Long bno){

        Board board =
                boardRepository.findById(bno).orElseThrow(EntityExistsException::new);

        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
//                .setBoardImgDTOList(board.getBoardImgList());

     return boardDTO;
    }

    public BoardDTO read(Long bno, String memberID){

        Board board =
                boardRepository.findById(bno).orElseThrow(EntityExistsException::new);

        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
//                .setBoardImgDTOList(board.getBoardImgList());

        return boardDTO;
    }

}
