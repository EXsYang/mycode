package com.hspedu.hspmybatis.config;

import java.util.List;

/**
 * @author yangda
 * @create 2023-10-27-19:56
 * @description: MapperBean 将Mapper信息(接口和xml)，进行封装
 *
 *  从这里可以看出  mapperBean对象
 *  只是对mapper.xml文件 读取到的信息进行了封装
 *  当前 mapperBean对象 和MonsterMapper 接口还没有联系
 *  因为 mapperBean对象的 两个属性的值 都来自 mapper.xml 文件
 *
 */
public class MapperBean {

    private String interfaceName; //接口名

    // 接口下的所有方法-集合
    private List<Function> functions;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    @Override
    public String toString() {
        return "MapperBean{" +
                "interfaceName='" + interfaceName + '\'' +
                ", functions=" + functions +
                '}';
    }
}
