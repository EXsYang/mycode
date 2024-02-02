package com.hspedu.hspmybatis.sqlsession;

import com.hspedu.hspmybatis.config.Function;
import com.hspedu.hspmybatis.config.MapperBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author yangda
 * @create 2023-10-25-17:16
 * @description:
 * HspMapperProxy: 动态代理生成Mapper对象，调用HspExecutor
 */
public class HspMapperProxy implements InvocationHandler {

    //属性
    private HspSqlSession hspSqlSession;
    private String mapperFile;
    private HspConfiguration hspConfiguration;

    //构造器
    public HspMapperProxy( HspConfiguration hspConfiguration,HspSqlSession hspSqlSession,Class clazz) {
        this.hspConfiguration = hspConfiguration;
        this.hspSqlSession = hspSqlSession;
        // 简单类名+.xml 作为要读取的mapper.xml文件的文件名
        this.mapperFile = clazz.getSimpleName() + ".xml";
    }

    //前面讲解spring时，讲过动态代理机制=>老师提醒，如果小伙伴忘记了动态代理知识
    //去spring的笔记和代码回顾
    //老师提示：当执行Mapper接口的代理对象方法时，会执行到invoke方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //System.out.println("method.getDeclaringClass()= " + method.getDeclaringClass());
        //method.getDeclaringClass()= interface com.hspedu.mapper.MonsterMapper

        //System.out.println("method.getDeclaringClass().getSimpleName()= " + method.getDeclaringClass().getSimpleName());
        //method.getDeclaringClass().getSimpleName()= MonsterMapper

        MapperBean mapperBean = hspConfiguration.readMapper(this.mapperFile);

       /* if (!method.getDeclaringClass().getName().equals(mapperFile.split("\\.")[0])){
            // 可以使用这个方法进行判断
            // 判断要执行的目标方法所在的类 和想要获得mapperFile.split("\\.")[0] 接口的代理对象
            // 是否相同 如果返回的代理对象的接口类型 和目标方法所在的接口类不是同一个类 就直接返回null
            //
            System.out.println("没有返回monsterMapper的代理对象");
            return null;
        }*/


        System.out.println("method.getDeclaringClass().getName()= " + method.getDeclaringClass().getName());
        System.out.println("mapperBean.getInterfaceName()= " + mapperBean.getInterfaceName());

        //method.getDeclaringClass(): 返回 类表示声明该对象表示的可执行的类或接口对象。
        //判断是否是xml文件对应的接口
        // 判断目标方法所对应的类(接口) 和mapperBean中的接口名是否一致
        // this.mapperFile 要读取的xml文件的文件名
        // 通过 要读取的xml文件中映射的接口名 和目标方法执行的类
        if (!method.getDeclaringClass().getName().equals(mapperBean.getInterfaceName())){


            return null;
        }

        // 到这里说明 要调用的目标方法所对应的类 和读取的mapper.xml文件生成的mapperBean中的接口名是否一致
        // 继续往下走

        //取出mapperBean的functions
        List<Function> functions = mapperBean.getFunctions();
        //判断当前mapperBean解析对应MapperXML后 有方法
        if (null != functions && 0 != functions.size()){

            for (Function function : functions) {
                // 当前要执行的方法的方法名和Mapper.xml文件封装成的mapperBean中的function的方法名
                // ,function.getFuncName()一样
                // 说明我们可以从当前遍历的function对象中，取出相应的信息sql, 并执行方法
                if (method.getName().equals(function.getFuncName())){
                    //如果我们当前的function 要执行的sqlType 时select
                    //我们就去执行selectOne
                    /*
                    * 说明：
                    * 1. 如果要执行的方法是select，就对应执行selectOne
                    * 2. 因为老韩在HspSqlSession就写了一个selectOne
                    * 3. 实际上HspSqlSession 应该对应不同的方法(多个方法)
                    *    ,根据不同的匹配情况调用不同的方法，并且还需要考虑对参数解析处理(parameterType)
                    *    ,还有比较复杂的字符串处理，拼接sql,返回类型(resultType)等等工作
                    * 4. 因为老韩主要是讲 mybatis 生成mapper动态代理对象，调用方法机制，所以做了简化
                    *
                    * */
                    if ("select".equalsIgnoreCase(function.getSqlType())){
                        return hspSqlSession.selectOne(function.getSql(),String.valueOf(args[0]));
                    }

                }

            }


        }

        return null;
    }
}
