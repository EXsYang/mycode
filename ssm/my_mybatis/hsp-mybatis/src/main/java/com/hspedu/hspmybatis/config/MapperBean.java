package com.hspedu.hspmybatis.config;

import java.util.List;

/**
 * @author yangda
 * @create 2023-10-20-14:47
 * @description:
 * MapperBean 将Mapper信息(接口和xml)，进行封装
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
