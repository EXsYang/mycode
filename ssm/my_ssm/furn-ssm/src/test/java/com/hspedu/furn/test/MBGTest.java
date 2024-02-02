package com.hspedu.furn.test;

import com.hspedu.furn.bean.Furn;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangda
 * @create 2023-11-08-19:50
 * @description: 用于 使用一段Java代码 生成 在mbg.xml配置好的 mybatisGenerator逆向工程文件
 */
public class MBGTest {

    @Test
    public void generator() throws Exception {

        // 注意 使用逆向工程生成的 Furn.java 文件中 没有全参构造器 需要手动添加

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //这里要指定 自己配置 mybatisGeneratorConfig(mybatis逆向工程配置) 文件 mbg.xml
        // File configFile = new File("generatorConfig.xml");
        // 如果这里这样访问=> new File("mbg.xml");
        // 需要将 mbg.xml 文件直接放在项目下
        File configFile = new File("mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        System.out.println("逆向生成 OK");

    }

    @Test
    public void fileReadPath() {

        // 如果这里这样访问=> new File("mbg.xml");
        // 总结: 在maven生成的项目的test环境下的junit测试环境下
        // 使用 new File("mbg2.xml"); 不带斜杠 默认读取的路径是在项目下
        File file = new File("mbg2.xml");


        if (file.exists()){
            if (file.delete()){
                System.out.println("test删除成功");
            }else {
                System.out.println("test删除失败");
            }
        }else {
            System.out.println("test该文件不存在");
            try {
                file.createNewFile();
                System.out.println("test文件绝对路径：" + file.getAbsolutePath());
                //文件绝对路径：D:\testFile
                System.out.println("test文件创建成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //调用相应的方法，得到对应的信息
        System.out.println("文件名：" + file.getName()); // 文件名：news1.txt
        System.out.println("文件绝对路径：" + file.getAbsolutePath());
        System.out.println("文件父级目录：" + file.getParent());
        System.out.println("文件大小(字节)：" + file.length());
        System.out.println("文件是否存在：" + file.exists());
        System.out.println("是否为文件：" + file.isFile());
        System.out.println("是否是一个目录:" + file.isDirectory());//文件夹



    }



}
