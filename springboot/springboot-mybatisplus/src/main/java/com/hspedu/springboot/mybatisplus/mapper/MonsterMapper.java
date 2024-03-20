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

// 在 Spring Boot 应用中，使用 @MapperScan 注解可以指定一个或多个包路径，
// Spring Boot 会自动扫描这些路径下的接口，为它们创建 MyBatis 的映射器(Mapper)
// 代理对象。因此，当你在启动类或任何配置类上使用了 @MapperScan 并指定了
// 包路径之后，你就不需要再在每个 Mapper 接口上单独使用 @Mapper 注解了。


/**
 * MyBatis Plus BaseMapper 接口说明：
 * 当一个 Mapper 接口继承自 BaseMapper<T>，它自动具备了针对泛型 T 所代表的数据库表的基本 CRUD 方法。
 * 这意味着无需为这些基本操作编写 SQL 语句或 mapper.xml 文件。
 *
 * 示例：
 * public interface MonsterMapper extends BaseMapper<Monster> {
 *     // 这里可以添加非 CRUD 的其他方法
 * }
 *
 * @Mapper 注解：
 * 用于标识一个接口为 MyBatis 的 Mapper 接口。在 Spring Boot 项目中，
 * 如果已经通过 @MapperScan 注解指定了 Mapper 接口所在的包，则该包下的所有接口
 * 都会自动被 Spring 管理，即它们的实现会被加入到 Spring IOC 容器中，
 * 因此不需要在每个 Mapper 接口上单独标注 @Mapper。
 *
 * 示例：
 * @SpringBootApplication
 * @MapperScan("com.hspedu.furn.mapper") // 指定扫描的 Mapper 接口所在的包
 * public class Application {
 *     public static void main(String[] args) {
 *         SpringApplication.run(Application.class, args);
 *     }
 * }
 *
 * 注意：
 * 使用 @MapperScan 后，Spring 将自动管理指定包路径下的所有 Mapper 接口，
 * 无需手动在每个 Mapper 接口上添加 @Mapper 注解。
 * 继承 BaseMapper<T> 的接口可以直接使用 MyBatis Plus 提供的各种 CRUD 方法，
 * 从而简化了数据访问层的代码。
 */

public interface MonsterMapper extends BaseMapper<Monster> {
    //自定义方法

    // 使用mybatisplusx插件自动生成的方法
    // 选择好要生成的方法'insertSelective'后，将光标放在方法名最后面alt+enter。 insertSelective alt+enter
    int insertSelective(Monster monster);

    int deleteByEmail(@Param("email") String email);

}
