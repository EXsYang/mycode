package com.hspedu.web.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author yangda
 * @create 2023-10-13-23:12
 * @description: 处理文件上传的handler
 */
@Controller
public class FileUploadHandler {


    //编写方法，处理文件上传的请求 返回String类型 表示 如果上传成功跳转到成功页面
    //使用MultipartFile类型 来接收前端传过来的文件类型的数据
    //如果前端一次上传多个图片/文件，就按照数组的形式进行接收，如MultipartFile[] files
    @RequestMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file,
                             HttpServletRequest request,
                             String introduce) throws IOException {
        // 按照默认的机制接收表单提交的introduce 注意名称要保持一致
        System.out.println("文件介绍introduce= " + introduce);


        // 接收到提交的文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println("你上传的文件名= " + originalFilename);
        // 得到要把上传文件保存到哪个路径
        //servletContext.getRealPath()得到的是运行目录的路径 即out下的
        String fileFullPath = request.getServletContext().getRealPath("/img/" + originalFilename);
        //创建文件 刚创建的文件是一个空文件
        File saveToFile = new File(fileFullPath);
        //将上传的文件，转存到saveToFile
        file.transferTo(saveToFile);

        //请求转发到success.jsp页面
        return "success";
    }
}
