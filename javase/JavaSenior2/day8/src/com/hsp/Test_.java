package com.hsp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author yangda
 * @description:   模仿 修饰器模式
 * @create 2022-11-27-15:01
 */
public class Test_ {
    public static void main(String[] args) {
        BufferedReader_ bufferedReader_ = new BufferedReader_(new FileReader_());
        bufferedReader_.fileReader_();
        bufferedReader_.fileReaders_(55);

        BufferedReader_ bufferedReader_1 = new BufferedReader_(new StringReader_());
        bufferedReader_1.stringReader_();
    }
}
