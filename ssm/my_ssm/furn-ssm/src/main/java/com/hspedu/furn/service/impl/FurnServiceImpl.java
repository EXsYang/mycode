package com.hspedu.furn.service.impl;

import com.hspedu.furn.bean.Furn;
import com.hspedu.furn.bean.FurnExample;
import com.hspedu.furn.bean.Msg;
import com.hspedu.furn.dao.FurnMapper;
import com.hspedu.furn.service.FurnService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * @author yangda
 * @create 2023-11-14-12:37
 * @description: service 层 使用 spring框架来接管
 */
@Service
public class FurnServiceImpl implements FurnService {

    // 需要用到FurnMapper实现子类的对象
    // 使用注解注入FurnMapper 因为在spring配置文件
    // applicationContext.xml 配置了 扫描器 MapperScannerConfigurer
    // 会把 mybatis在dao层 接口的实现加入到 ioc 容器中
    // 因此可以使用注解 @Resource 自动装配/注入
    // 注意该注解最后面没有s import javax.annotation.Resource;
    // 注入/装配FurnMapper接口对象(代理对象)
    @Resource
    private FurnMapper furnMapper;


    @Override
    public void save(Furn furn) {
        //1. 这里使用的是 insertSelective()
        //2. 因为我们的furn表的id是自增长的,不用指定,就使用insertSelective()
        //3. 如果某个字段没有设置值,使用insert() 会默认给一个null ,
        // 如果表的字段不允许为null,会报错
        //这里可以进行处理 insertSelective() 方法会返回一个int值
        furnMapper.insertSelective(furn);

    }

    @Override
    public List<Furn> findAll() {
        // 返回所有家居数据，传入 null 即可
        List<Furn> furns = furnMapper.selectByExample(null);
        // select id, name, maker, price, sales, stock, img_path from furn

        return furns;
    }

    //更新家居信息
    @Override
    public int update(Furn furn) {

        //可以根据方法返回值判断是否更新成功 这里暂不处理
        // furnMapper.updateByPrimaryKey(furn);
        //因为传入的 furn 的字段不一定是完整的，所以使用 updateByPrimaryKeySelective
        int affected = furnMapper.updateByPrimaryKeySelective(furn);

        return affected;

    }

    // @Override
    // public void del(Integer id) {
    //
    //     furnMapper.deleteByPrimaryKey(id);
    //
    // }

    @Override
    public int del(Integer id) {

        int affected = furnMapper.deleteByPrimaryKey(id);

        return affected;

    }

    @Override
    public Furn findFurnById(Integer id) {

        Furn furn = furnMapper.selectByPrimaryKey(id);

        return furn;
    }



    @Override
    public List<Furn> findByCondition(String name) {

        //furnExample是可以带条件的
        FurnExample furnExample = new FurnExample();
        //通过Criteria对象可以设置查询条件
        FurnExample.Criteria criteria = furnExample.createCriteria();

        //判断name是有具体内容的
        if (StringUtils.hasText(name)){
            // 没有执行这句话 就没有条件，就会查询所有的记录
            criteria.andNameLike("%"+name+"%");
        }

        // 老师说明:如果name没有传值null,"","    ",依然是查询所有的记录
        return furnMapper.selectByExample(furnExample);
    }


}
