package com.hsp.upload;

import java.io.*;

/**
 * @author yangda
 * @description:
 * @create 2022-12-03-10:23
 */
public class StreamUtils {

    /*
     * @description:将输入流转换成byte[],既可以把文件的内容读入到byte[]
     * @author: yangda
     * @date: 2022/12/3 10:27
     * @param: [inputStream]
     * @return: byte[]
     **/
    public static byte[] streamToByteArray(InputStream inputStream) throws Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1){
            bos.write(buf,0,readLen);
        }
        byte[] array = bos.toByteArray();

        return array;
    }
    
    /*
     * @description:将输入流转换成String
     * @author: yangda 
     * @date: 2022/12/3 10:36
     * @param: [inputStream]
     * @return: java.lang.String
     **/
    public static String streamToString(InputStream inputStream) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null){
           stringBuilder.append(line + "\r\n");
        }

        return stringBuilder.toString();
    }



}
