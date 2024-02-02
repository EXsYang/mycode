package com.hspedu.mapper;

import com.hspedu.entity.Monster;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangda
 * @create 2023-10-29-20:35
 * @description:
 */
public class MonsterMapperTest {

        //属性
        private SqlSession sqlSession;
        private MonsterMapper monsterMapper;

        //编写方法完成初始化


        @Before
        public void init() {

            //获取到sqlSession
            sqlSession = MyBatisUtils.getSqlSession();
            System.out.println("sqlSession= " + sqlSession);
            //获取到MonsterMapper对象 class com.sun.proxy.$Proxy7 是一个代理对象
            //,底层使用的动态代理机制，后面我们自己实现底层机制时会讲到
            monsterMapper = sqlSession.getMapper(MonsterMapper.class);
            System.out.println("代理对象monsterMapper 运行类型= " + monsterMapper.getClass());
        }


        @Test
        public void findMonsterByNameORId() {

            Monster monster = new Monster();
            monster.setId(6);
            monster.setName("老鼠精");
            List<Monster> monsters = monsterMapper.findMonsterByNameORId(monster);

            for (Monster m : monsters) {
                System.out.println("monster= " + m);

            }

            if (sqlSession != null){
                sqlSession.close();
            }


            System.out.println("操作成功...");
        }


        @Test
        public void findMonsterByName() {

            List<Monster> monsters = monsterMapper.findMonsterByName("牛魔王");

            for (Monster monster : monsters) {
                System.out.println("monster= " + monster);
            }

            if (sqlSession != null){
                sqlSession.close();
            }


            System.out.println("操作成功...");
        }

        @Test
        public void findMonsterByIdAndSalary_ParameterHashMap() {

            //Diamond types are not supported at language level '5'
            //如何解决 => 去pom.xml 文件中指定编译器的版本 因为默认生成的maven项目 编译器的版本是1.5
            /*  <!--这里指定maven编译器 和 jdk的版本  /source/target/java的版本为1.8 -->
                <properties>
                    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                    <maven.compiler.source>1.8</maven.compiler.source>
                    <maven.compiler.target>1.8</maven.compiler.target>
                    <java.version>1.8</java.version>
                </properties>
            */
            //比较灵活 不受POJO类属性的约束
            Map<String, Object> map = new HashMap<>();
            //这里设置的值 要和MonsterMapper.xml 文件中对应sql位置使用 #{key} 取出时使用的key值保持一致
            map.put("id",10 );
            map.put("salary",40);

            List<Monster> monsters = monsterMapper.findMonsterByIdAndSalary_ParameterHashMap(map);

            for (Monster monster : monsters) {
                System.out.println("monster= " + monster);
            }


            if (sqlSession != null){
                sqlSession.close();
            }


            System.out.println("操作成功...");
        }

        @Test
        public void findMonsterByIdAndSalary_ParameterHashMap_ReturnHashMap() {


            Map<String, Object> map = new HashMap<>();
            //这里设置的值 要和MonsterMapper.xml 文件中对应sql位置使用 #{key} 取出时使用的key值保持一致
            map.put("id",10 );
            map.put("salary",40);

            List<Map<String, Object>> monsterList = monsterMapper.findMonsterByIdAndSalary_ParameterHashMap_ReturnHashMap(map);
            //取出返回的结果 以map形式
            for (Map<String, Object> monsterMap : monsterList) {
                // System.out.println("monsterMap= " + monsterMap);
                // 遍历monsterMap(方式1) 取出属性和对应的值
                // for (String key : monsterMap.keySet()) {
                //     System.out.println(key + " => " + monsterMap.get(key));
                // }

                // 遍历monsterMap(方式2) 取出属性和对应的值
                for (Map.Entry<String, Object> entry : monsterMap.entrySet()) {
                    System.out.println("entry-key:" + entry.getKey() + "--------entry-value:" + entry.getValue());
                }

                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            }


            if (sqlSession != null){
                sqlSession.close();
            }


            System.out.println("操作成功...");
        }







}
