package com.hspedu.spring.jdbctemplate.dao;

import com.hspedu.spring.bean.Monster;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author yangda
 * @description:
 * @create 2023-09-18-22:47
 */

@Repository
public class MonsterDao {

    //注入一个属性
    @Resource
    private JdbcTemplate jdbcTemplate;

    // 完成保存任务
    public void save(Monster monster){

        // 构建sql insert
        String sql = "insert into monster values(?,?,?)";
        int affected = jdbcTemplate.update(sql, monster.getMonsterId(), monster.getName(), monster.getSkill());
        if (affected > 0){
            System.out.println("保存monster对象成功");
        }
    }

}
