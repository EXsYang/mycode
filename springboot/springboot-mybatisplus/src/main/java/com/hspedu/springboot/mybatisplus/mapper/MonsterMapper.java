package com.hspedu.springboot.mybatisplus.mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hspedu.springboot.mybatisplus.bean.Monster;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangda
 * @create 2023-12-17-21:27
 * @description:
 * 1. BaseMapper已经默认提供了很多的crud方法，可以直接使用
 * 2. 如果BaseMapper 提供的方法不能满足业务需求，我们可以再开发新的方法,
 *    并在MonsterMapper.xml进行配置 =>使用插件开发 mybatisplusx
 *
 * Mapper 继承BaseMapper接口后，无需编写 mapper.xml 文件，即可获得CRUD功能，这里许多单表的操作已经写好了，直接拿来使用方便
 * 如果还有自定义的方法,则才需要在对应的Mapper.xml进行配置实现
 */
// @Mapper //单一注入 MonsterMapper ,将MonsterMapper 接口的实现，加入到 ioc 容器中
public interface MonsterMapper extends BaseMapper<Monster> {
    //自定义方法

    // 使用mybatisplusx插件自动生成的方法
    // 选择好要生成的方法'insertSelective'后，将光标放在方法名最后面alt+enter。 insertSelective alt+enter
    int insertSelective(Monster monster);

    int deleteByEmail(@Param("email") String email);

}
