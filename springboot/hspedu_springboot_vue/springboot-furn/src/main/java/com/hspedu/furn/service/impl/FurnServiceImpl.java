package com.hspedu.furn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hspedu.furn.bean.Furn;
import com.hspedu.furn.mapper.FurnMapper;
import com.hspedu.furn.service.FurnService;
import org.springframework.stereotype.Service;

/**
 * @author yangda
 * @create 2023-12-22-20:47
 * @description:
 */
@Service //注意这个注解不要忘了写
public class FurnServiceImpl
        extends ServiceImpl<FurnMapper, Furn>
        implements FurnService {
}
