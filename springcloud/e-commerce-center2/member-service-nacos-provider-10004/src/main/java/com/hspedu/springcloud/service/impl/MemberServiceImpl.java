package com.hspedu.springcloud.service.impl;

import com.hspedu.springcloud.dao.MemberDao;
import com.hspedu.springcloud.entity.Member;
import com.hspedu.springcloud.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangda
 * @create 2023-12-29-14:20
 * @description:
 */
@Service
public class MemberServiceImpl implements MemberService {

    //装配memberDao
    @Resource
    private MemberDao memberDao;

    @Override
    public Member queryMemberById(Long id) {
        return memberDao.queryMemberById(id);
    }

    @Override
    public int save(Member member) {
        return memberDao.save(member);
    }
}
