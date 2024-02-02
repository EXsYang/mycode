package com.hspedu.springcloud.dao;

import com.hspedu.springcloud.entity.Member;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangda
 * @create 2023-12-28-20:40
 * @description:
 */
@Mapper
public interface MemberDao {

    //定义方法 根据id返回Member数据
    public Member queryMemberById(Long id);

    //添加Member
    public int save(Member member);



}
