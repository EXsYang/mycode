package com.hspedu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yangda
 * @create 2024-01-10-0:24
 * @description:
 */
@Mapper
public interface AccountDao {
    void reduce(@Param("userId") Long userId, @Param("money") Integer money);
}
