package com.hspedu.springboot.controller;

import com.hspedu.springboot.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author yangda
 * @create 2023-12-09-22:03
 * @description:
 */
@Controller
@Slf4j
public class UploadController {

    /**
     * 处理转发到用户注册-可以完成文件上传页面
     * http://localhost:8080/upload.html
     */
    @GetMapping("/upload.html")
    public String uploadPage() {
        return "upload"; // 视图解析，转发到 templates/upload.html页面
    }

    /**
     * 处理用户的注册请求-包括文件上传
     *
     * @
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam(value = "name") String name,
                         @RequestParam(value = "email") String email,
                         @RequestParam(value = "age") Integer age,
                         @RequestParam(value = "job") String job,
                         @RequestParam(value = "header") MultipartFile header,
                         @RequestParam(value = "photos") MultipartFile[] photos
    ) throws IOException {

        //输出获取到的信息
        // header.getSize()文件的大小
        // photos.length 上传的photos文件数量
        log.info("name={} email={} age={} job={} header={} photos={}"
                , name, email, age, job, header.getSize(), photos.length);

        //将文件保存到指定目录
        //如果信息都成功得到，我们就将文件保存到指定的目录,比如d:\\temp_upload
        //1. 我们先将文件保存到指定的目录 比如d:\\temp_upload
        //2. 后面我们在演示把文件保存到动态创建的目录.

        //得到类路径(运行时候的)
        String path = ResourceUtils.getURL("classpath:").getPath();
        // log.info("path={}", path);
        // path=/D:/Java_developer_tools/springboot/springboot-usersys/target/classes/

        //动态创建指定目录

        // File file = new File(path + "static/images/upload/");
        //解决文件分目录存放 如 一天上传的文件，统一放到一个文件夹 年/月/日, 比如 2022/11/11 目录
        // File file = new File(path + "static/images/upload/" +  WebUtils.getYearMonthDay());
        // File file = new File(path + WebUtils.UPLOAD_FILE_DIRECTORY +  WebUtils.getYearMonthDay());
        File file = new File(path + WebUtils.getUploadFileDirectory());
        // new File() 创建File对象时会拿掉最后一个斜杠！！
        log.info("file.getAbsolutePath()@@@={}",file.getAbsolutePath());



        //判断文件是否存在，其实就是判断目录是否存在，目录也是特殊的文件
        if (!file.exists()) {  //如果目录不存在，就创建目录 io的知识
            //java.io.File.mkdir()：只能创建一级目录，且父目录必须存在，否则无法成功创建一个目录。
            //java.io.File.mkdirs()：可以创建多级目录，父目录不一定存在。
            // file.mkdir(); //这个也可以，因为这里是创建单级目录，但是如果创建多级目录就需要用下面的
            file.mkdirs(); // 这个方法可以创建一个或者多个目录
        }

        if (!header.isEmpty()) { //处理头像
            //获取上传的文件的名字
            String originalFilename = header.getOriginalFilename();
            System.out.println("获取上传的头像的名字" + originalFilename);

            //解决文件覆盖问题, 如果文件名相同,会出现覆盖问题
            //对上传的文件名进行处理，保证该文件名唯一
            originalFilename = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "_" + originalFilename;
            log.info("处理后的头像名={}",originalFilename);

            //这里指定要保存的文件的绝对路径
            // header.transferTo(new File("d:\\temp_upload\\" + originalFilename));
            // 反斜杠或正斜杠都可以
            // header.transferTo(new File("d:\\temp_upload/" +   header.getOriginalFilename()));

            // log.info("file.getPath()={}" + file.getPath());
            // log.info("保存文件的绝对路径file.getPath()={}" , file.getPath() + originalFilename);


            // log.info("保存文件的绝对路径file.getAbsolutePath()={}", file.getAbsolutePath() + "/" + originalFilename);
            //
            // log.info("保存文件的绝对路径file.getAbsolutePath()={}", file.getAbsolutePath() + "\\" + originalFilename);

            //保存到动态创建的目录
            header.transferTo(new File(file.getAbsolutePath() + "\\" + originalFilename));
            //使用一个正斜杠也可以，等价
            // header.transferTo(new File(file.getAbsolutePath() + "/" + originalFilename));
        }

        //处理宠物图片
        if (photos.length > 0) {
            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()) {
                    String originalFilename = photo.getOriginalFilename();
                    //解决文件覆盖问题, 如果文件名相同,会出现覆盖问题
                    //对上传的文件名进行处理，保证该文件名唯一
                    originalFilename = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "_" + originalFilename;
                    log.info("处理后的photo名={}",originalFilename);

                    // photo.transferTo(new File("d:\\temp_upload\\" + originalFilename));
                    // 创建文件时 路径中的正斜杠"/"和两个反斜杠"\\"效果是一样的，都可以创建成功！！
                    File uploadPhoto = new File(file.getAbsolutePath() + "/" + originalFilename);
                    // File uploadPhoto = new File(file.getAbsolutePath() + "\\" + originalFilename);
                    // File uploadPhoto2 = new File(file.getAbsolutePath() + "/" + originalFilename);
                    // log.info("uploadPhoto 绝对路径={}", uploadPhoto.getAbsolutePath());
                    // log.info("uploadPhoto2 绝对路径={}", uploadPhoto2.getAbsolutePath());
                    photo.transferTo(uploadPhoto);
                }
            }
        }


        return "注册用户成功/文件上传成功";
    }


}
