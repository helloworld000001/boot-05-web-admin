package com.atguigu.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @auther 陈彤琳
 * @Description $ 文件上传测试
 * 2023/10/26 9:42
 */
@Slf4j
@Controller
public class FormTestController {
    @GetMapping("/form_layouts")
    public String form_layouts(){
        return "form/form_layouts";
    }

    /* 文件获取
     * MultipartFile 会自动封装上传来的文件
     *               可以用于单文件获取，对于多文件获取只要设为数组即可MultipartFile[]
     * */
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username")String username,
                         @RequestPart("headerImg") MultipartFile headerImg,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {
        log.info("上传的信息是：email={}， username={}， headerImg={}， photos={}",
                email, username, headerImg.getSize(), photos.length);
        /* 上传的信息是：email=c2032461441@163.com， username=12， headerImg=2， photos=3 */
        // 保存文件到E:\interim\路径下
        if(!headerImg.isEmpty()){
            // 获取原始的文件名作为保存时使用的文件名
            String originalFilename = headerImg.getOriginalFilename();
            headerImg.transferTo(new File("E:\\interim\\" + originalFilename));
        }
        if(photos.length > 0){
            for (MultipartFile photo : photos) {
                if(!photo.isEmpty()){
                    String originalFilename = photo.getOriginalFilename();
                    photo.transferTo(new File("E:\\interim\\" + originalFilename));

                }
            }
        }

        return "index";
    }
}
