package com.hsp;

/**
 * @author yangda
 * @description:
 * @create 2022-11-27-15:00
 */
public class BufferedReader_ extends Reader_{
    Reader_ reader_;

    public BufferedReader_(Reader_ reader_) {
        this.reader_ = reader_;
    }


    public void fileReader_(){//封装一层
            reader_.fileReader_();
    }
    //让方法更灵活，多次读取文件，或者加缓冲char[] ....
    public void fileReaders_(int num){
        for (int i = 0; i < num; i++) {
            reader_.fileReader_();
        }
    }
    public void stringReader_(){
            reader_.stringReader_();
    }

    //扩展 readString ,批量处理字符串数据
    public void stringReaders_(int num){
        for (int i = 0; i < num; i++) {
            reader_.stringReader_();
        }
    }



}
