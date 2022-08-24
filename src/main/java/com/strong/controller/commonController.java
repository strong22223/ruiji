package com.strong.controller;


import com.strong.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.Filer;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("common")
public class commonController {
    @Value("${ruiji.basePath}")
    private String basePath;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
        log.info("文件->>{}", file);
        //获取传输带的文件名
        String originalFilename = file.getOriginalFilename();
        log.info(originalFilename);
        //截取filename的后缀名 ,获取字段
        int i = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(i);
        //使用UUID获取随机的用户名
        String fileName = UUID.randomUUID().toString() + suffix;

        //创建文件目录
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        file.transferTo(new File(basePath + fileName));

        return R.success(fileName);
    }

    @GetMapping("/download")
    public void downLoad(String name, HttpServletResponse response) {

        //首先获取到文件的名称 name

        //1.获取输入流
        try (
                FileInputStream inputStream = new FileInputStream(new File(basePath + name)); //2.获取输出流
                ServletOutputStream outputStream = response.getOutputStream();

        ) {
            //设置响应的文件类型
            response.setContentType("image/jpeg");
            log.info(basePath + name);
//            首先输出文件名称
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {


                outputStream.write(bytes, 0, len);
//                log.info("长度->>{}",len);
                outputStream.flush();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
