package com.hspedu.springcloud.service;

import com.hspedu.springcloud.entity.Member;

/**
 * @author yangda
 * @create 2023-12-29-14:19
 * @description:
 */
public interface MemberService {

    //定义方法 根据id返回Member数据
    public Member queryMemberById(Long id);

    //添加Member
    public int save(Member member);

}
