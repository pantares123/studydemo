package com.zch.graduationdesign.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Base64;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/upload")
public class FileContronller {

    @PostMapping("/uploadimg")
    public String uploadFile(@RequestBody String img)throws IOException{

        Matcher matcher =Pattern.compile("^data%3Aimage%2Fjpeg%3Bbase64%2C").matcher(img);
        if (matcher.find()){
            String text = img.replace(matcher.group(),"");
            System.out.println(text);
        }

        System.out.println(img);

        return "上传成功";

    }

    private void saveFile(MultipartFile photo) throws IOException {
        File dir=new File("C:\\Users\\12169\\AppData\\Local\\Temp\\GraDesignImg");
        if(!dir.exists()){
            dir.mkdir();
        }
        File file=new File("C:\\Users\\12169\\AppData\\Local\\Temp\\GraDesignImg\\"+photo.getOriginalFilename());
        photo.transferTo(file);

    }

}
