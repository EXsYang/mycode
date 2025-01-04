package com.atguigu.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yangda
 * @create 2025-01-03-5:26
 * @description:
 */
public class TestRename {


    public static void main(String[] args) {
        String directoryPath = "D:\\Google_Drive\\动漫\\命运石之门\\命运石之门4K 67G";
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("目录未找到: " + directoryPath);
            return;
        }

        File[] files = directory.listFiles();
        if (files == null) {
            System.err.println("无法列出目录中的文件: " + directoryPath);
            return;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".mkv")) {
                String oldName = file.getName();
                String newName = oldName.substring(0, oldName.length() - 4) + ".ass"; // 将 .mkv 替换为 .ass
                File newFile = new File(directory, newName);

                if (file.renameTo(newFile)) {
                    System.out.println("已将 '" + oldName + "' 重命名为 '" + newName + "'");
                } else {
                    System.err.println("重命名 '" + oldName + "' 为 '" + newName + "' 失败");
                }
            }
        }
    }
}