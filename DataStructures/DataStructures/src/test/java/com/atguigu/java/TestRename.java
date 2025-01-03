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
        String directoryPath = "D:\\video_production\\命运石之门\\命运石之门4K 58G\\命运石之门 0";
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("指定的目录不存在或不是一个目录: " + directoryPath);
            return;
        }

        File[] mkvFiles = directory.listFiles(pathname -> pathname.getName().endsWith(".mkv") && pathname.getName().startsWith("[Bcat] Steins;Gate 0 ["));
        File[] assFiles = directory.listFiles(pathname -> pathname.getName().endsWith(".ass") && pathname.getName().startsWith("[KissSub][Steins;Gate0]"));

        if (mkvFiles == null || assFiles == null) {
            System.err.println("读取文件列表失败。");
            return;
        }

        for (File mkvFile : mkvFiles) {
            String mkvFileName = mkvFile.getName();
            Pattern pattern = Pattern.compile("\\[Bcat\\] Steins;Gate 0 \\[([0-9]+)\\]");
            Matcher matcher = pattern.matcher(mkvFileName);

            if (matcher.find()) {
                String episodeNumber = matcher.group(1);
                String paddedEpisodeNumber = String.format("%02d", Integer.parseInt(episodeNumber));

                for (File assFile : assFiles) {
                    String assFileName = assFile.getName();
                    String expectedMkvPrefix = "[KissSub][Steins;Gate0][" + paddedEpisodeNumber + "]";
                    if (assFileName.startsWith(expectedMkvPrefix)) {
                        String newMkvFileName = assFileName.replace(".ass", ".mkv");
                        File newMkvFile = new File(directory, newMkvFileName);

                        try {
                            Files.move(mkvFile.toPath(), newMkvFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("将 '" + mkvFileName + "' 重命名为 '" + newMkvFileName + "'");
                            break; // 找到匹配的字幕文件后，跳出字幕文件循环
                        } catch (IOException e) {
                            System.err.println("重命名文件 '" + mkvFileName + "' 时出错: " + e.getMessage());
                        }
                    }
                }
            } else {
                System.out.println("无法从文件名中提取剧集编号: " + mkvFileName);
            }
        }

        System.out.println("重命名完成。");
    }
}