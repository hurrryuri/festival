package com.example.festival.service;

import com.example.festival.entity.Board;
import com.example.festival.entity.BoardImg;
import com.example.festival.repository.BoardImgRepository;
import com.example.festival.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class BoardImgService {

    private final BoardImgRepository boardImgRepository;
    private final FileService fileService;
    private final BoardRepository boardRepository;


    public void saveImg(Long bno, List<MultipartFile> multipartFile) throws IOException {


        log.info("이미지서비스로 들어온 bno" + bno);
        if(multipartFile != null){
            for (MultipartFile img : multipartFile) {
                if(!img.isEmpty()){
                    log.info("이미지서비스로 들어온 이미지" + img.getOriginalFilename());
                    //물리적인 저장
                    String savedFileName =      //uuid가 포함된 물리적인 파일이름
                            fileService.uploadFile(img);


                    // db저장
                    //엔티티를 가져왔다면 중복코드를 사용할 필요가 없어진다.해볼것
                    Board board =
                            boardRepository.findById(bno).get();

                    String imgUrl = "/images/board/" + savedFileName;

                    BoardImg boardImg = new BoardImg();
                    boardImg.setBoard(board);      //본문 // 이미지가 달릴 아이템
                    boardImg.setImgName(savedFileName);       //uuid포함 저장될 이름
                    boardImg.setImgUrl(imgUrl);        //경로
                    //대표이미지 여부 확인
                    if(multipartFile.indexOf(img) == 0){
                        boardImg.setRepimgYn("Y");      //대표이미지
                    }else {
                        boardImg.setRepimgYn("N");
                    }
                    boardImgRepository.save(boardImg);

                }
            }
        }

    }

    public void update(Long bno, List<MultipartFile> multipartFile, Long mainbno) throws IOException {


        log.info("아이템이미지서비스로 들어온 bno" + bno);
        if(multipartFile != null){
            for (MultipartFile img : multipartFile) {
                if(!img.isEmpty()){
                    log.info("아이템이미지서비스로 들어온 이미지" + img.getOriginalFilename());
                    //물리적인 저장
                    String savedFileName =      //uuid가 포함된 물리적인 파일이름
                            fileService.uploadFile(img);

                    Board board =
                            boardRepository.findById(bno).get();

                    String imgUrl = "/images/board/" + savedFileName;

                    BoardImg boardImg = new BoardImg();
                    boardImg.setBoard(board);      //본문 // 이미지가 달릴 아이템
                    boardImg.setImgName(savedFileName);       //uuid포함 저장될 이름
                    boardImg.setImgUrl(imgUrl);        //경로
                    //대표이미지 여부 확인
                    if (mainbno == null) {
                        if(multipartFile.indexOf(img) == 0){

                            boardImg =
                                    boardImgRepository.findByBoardBnoAndRepimgYn(bno, "Y");
                            boardImg.setBoard(board);      //본문 // 이미지가 달릴 아이템
                            boardImg.setImgName(savedFileName);       //uuid포함 저장될 이름
                            boardImg.setImgUrl(imgUrl);        //경로


//                            itemImg.setRepimgYn("Y");      //대표이미지
                        }else {
                            boardImg.setRepimgYn("N");
                        }


                    }else {
                        boardImg.setRepimgYn("N");
                    }

                    boardImgRepository.save(boardImg);

                }
            }
        }








    }



    public void removeimg(Long id){
        //물리적파일을 삭제하기위해서 데이터를 가져온다.
        BoardImg boardImg =
                boardImgRepository.findById(id).get();

        //저장경로 value 위에 있는 value 이미 이건 파일service안에 있음


        //물리적파일 삭제
        // 삭제하려면 경로 및 사진파일명 필요
        fileService.removefile(boardImg.getImgName());
        //반환값에 따라 물리파일이 삭제가 되었다면 안되었다면에 따라
        // db를 지우거나 지우지 않거나

        //db에서 값을지운다
        boardImgRepository.deleteById(id);

    }



}
