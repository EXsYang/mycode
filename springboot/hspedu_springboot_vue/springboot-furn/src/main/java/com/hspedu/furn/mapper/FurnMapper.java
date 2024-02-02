package com.hspedu.furn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hspedu.furn.bean.Furn;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangda
 * @create 2023-12-22-20:30
 * @description:
 * Mapper 继承BaseMapper接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
 * 如果还有自定义的方法,则才需要在对应的Mapper.xml进行配置实现
 * // 自定义的方法可以使用mybatisplusx插件自动生成 
 * 如果是mybatis-plus 我们Mapper接口可以通过extends 接口BaseMapper<T> 扩展功能
 */
// @Mapper
public interface FurnMapper extends BaseMapper<Furn> {
    //如果有其他的方法，可以在该接口声明
    //并在对应的Mapper.xml进行配置实现


}
