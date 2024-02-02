package com.hsp.io;


import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author yangda
 * @create 2023-12-27-20:22
 * @description:
 */
public class DeleteFileByPath {

    @Test
    public void deleteFileByPath(){
        //判断 e:\\demo\\a\\b\\c 是否存在，如果存在就删除,否则提示不存在
        File file = new File("D:\\TyporaPhoto\\image-20240114010944487.png");//文件目录，也是特殊的文件
//        File file1 = new File("E:\\demo\\a\\b\\c\\c.txt");//文件目录，也是特殊的文件
//        try {
//            file1.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if (file.exists()){//判断文件是否存在
            System.out.println("file["+ file.getAbsolutePath() +"]存在");
            if (file.delete()){//删除空目录或文件,目录里有文件时，删除失败，没有文件时，删除最后一层目录(文件夹)
                //只要最后一层文件目录是空的，就可以删除成功
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        }else {
            System.out.println("该文件不存在");
//             file.mkdirs();//创建多级目录、返回一个布尔值，可以放在if(file.mkdirs)
// //                file.createNewFile();//没有后缀名也可以创建成功
//             System.out.println("目录创建成功");
        }
    }
}
