package com.hspedu.hspmybatis.sqlsession;

import com.hspedu.entity.Monster;
import com.hspedu.hspmybatis.config.Function;
import com.hspedu.hspmybatis.config.MapperBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author yangda
 * @create 2023-10-27-21:07
 * @description: 动态代理生成Mapper对象，调用HspExecutor
 */
public class HspMapperProxy implements InvocationHandler {


    // 被代理的接口的名字 即使用返回的代理对象是MonsterMapper接口类型
    // 在调用目标方法时 传进来一个clazz对象 获取到接口的名字
    // 用于读取 mapperFile 的 mapper.xml 文件
    private String mapperFile;

    // 真正要执行的目标方法 配置好的实现类 包含一个接口名，是xml文件内部配置的nameSpace
    //private MapperBean mapperBean;

    private HspConfiguration hspConfiguration;

    // 真正操作数据库的HspSqlSession
    private HspSqlSession hspSqlSession;


    // 构造器
    public HspMapperProxy(HspConfiguration hspConfiguration, HspSqlSession hspSqlSession, Class clazz) {
        this.hspConfiguration = hspConfiguration;
        this.hspSqlSession = hspSqlSession;
        //this.mapperBean = mapperBean;
        this.mapperFile = clazz.getSimpleName() + ".xml";

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        MapperBean mapperBean = hspConfiguration.readMapper(mapperFile);

        System.out.println("method.getDeclaringClass().getSimpleName()= " + method.getDeclaringClass().getSimpleName());
        System.out.println("method.getDeclaringClass().getName()= " + method.getDeclaringClass().getName());
        System.out.println("mapperBean.getInterfaceName()= " + mapperBean.getInterfaceName());
        //if (!method.getDeclaringClass().getSimpleName().equals(mapperBean.getInterfaceName())) {
        //注意这里要使用getDeclaringClass().getName() 拿到全类名 再进行equals比较！！！
        if (!method.getDeclaringClass().getName().equals(mapperBean.getInterfaceName())) {
            // 说明要执行的目标方法所在的类的类名
            // ,和 mapperBean对象中保存的接口名不一致
            System.out.println("没有返回代理对象哦");
            return null;

        }

        // 说明目标方法method所在的类名
        // 和要读取的mapper.xml文件中配置的接口名相同
        // 继续执行

        // 解析mapperBean
        List<Function> functions = mapperBean.getFunctions();
        //判断当前mapperBean解析对应MapperXML后 有方法
        if (null != functions && 0 != functions.size()) {
            for (Function function : functions) {

                // 判断目标方法的方法名 和 mapper.xml 中保存的方法id 是否相同
                if (method.getName().equals(function.getFuncName())) {
                    // 相同
                    // 判断要执行的语句的类型
                    if ("select".equalsIgnoreCase(function.getSqlType())) {
                        //执行查询语句 这里简化  认为只要是查询语句 就执行
                        //HspSession 中的selectOne  // 返回查询结果
                        return hspSqlSession.selectOne(function.getSql(), String.valueOf(args[0]));

                    }

                }


            }
        }


        return null;
    }
}
