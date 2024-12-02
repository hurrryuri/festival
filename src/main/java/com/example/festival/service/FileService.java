package com.example.festival.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Value("C:/festival/board")
    String boardImgLocation;

    public String  uploadFile(MultipartFile multipartFile) throws IOException {

        UUID uuid = UUID.randomUUID();

        String extension = multipartFile.getOriginalFilename()
                .substring(multipartFile.getOriginalFilename().lastIndexOf("."));

        //물리적인 파일이름
        String savedFileName = uuid.toString()+extension;


        //경로
        String fileUploadFullUrl= boardImgLocation+"/"+savedFileName;

        //물리적인 저장
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(multipartFile.getBytes());
        fos.close();

        return savedFileName;
    }

    public void removefile(String imgName){
        String delFileurl = boardImgLocation + "/" + imgName;
        System.out.println(delFileurl);
        System.out.println(delFileurl);
        System.out.println(delFileurl);
        System.out.println(delFileurl);
        File file = new File(delFileurl);

        if (file.exists()){//파일존재여부확인
            file.delete();
        }
    }


}
