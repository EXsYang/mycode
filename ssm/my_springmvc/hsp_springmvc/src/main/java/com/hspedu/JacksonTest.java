package com.hspedu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hspedu.entity.Monster;

import java.util.ArrayList;

/**
 * @author yangda
 * @create 2023-10-05-22:19
 * @description:
 */
public class JacksonTest {
    public static void main(String[] args) {

        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(100,"牛魔王","牛魔王拳",400));
        monsters.add(new Monster(200,"蜘蛛精","吐口水",200));

        //注意引入的是 com.fasterxml.jackson.databind.ObjectMapper;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //把monsters转成json
            String  monstersJson = objectMapper.writeValueAsString(monsters);

            System.out.println("monstersJson= " + monstersJson);
            //monstersJson=
            // [{"id":100,"name":"牛魔王","skill":"牛魔王拳","age":400},
            // {"id":200,"name":"蜘蛛精","skill":"吐口水","age":200}]
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
