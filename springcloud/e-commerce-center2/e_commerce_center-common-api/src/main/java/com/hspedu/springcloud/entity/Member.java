package com.hspedu.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yangda
 * @create 2023-12-30-16:25
 * @description: 因为这里的对象在底层传输的过程中是以对象流的方式进行传输的
 * , 因此实现Serializable接口更好,支持可序列化 ,member对象是通过网络传递的，本质其实是流
 * 一个对象在网络传输的时候，基本上按照常规都需要进行序列化,这样在对方接收到流的时候
 * ，才能将其恢复成为一个对象
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
// public class Member {
// 测试发现这里即使不序列化也可以成功保存到数据库
/**
 * 您的观察是正确的，在某些情况下，即使Java类没有实现 Serializable 接口，也可能成功地在微服务架构中进行网络传输和数据库保存。这主要是因为Spring和相关的库（如Jackson）在处理HTTP请求和响应时，采用了自己的序列化和反序列化机制。下面我将解释为什么在这种情况下，即使没有实现 Serializable，也可以成功保存数据到数据库，以及为什么实现 Serializable 仍然是一个好的实践。
 *
 * 为什么没有实现 Serializable 也能成功保存数据
 * HTTP传输和JSON序列化：
 *
 * 当使用Spring的 RestTemplate 或者 @RequestBody 注解时，传输的通常是JSON格式的数据。在这种情况下，Spring框架使用像Jackson这样的库将Java对象转换为JSON字符串，然后再将JSON字符串转换回Java对象。这个过程并不依赖于Java的内置序列化机制。
 * 数据库操作：
 *
 * 将数据保存到数据库通常涉及将Java对象映射为数据库中的行。在使用JPA、Hibernate或MyBatis等ORM（对象关系映射）工具时，它们有自己的机制来处理Java对象和数据库之间的映射，并不要求Java类必须实现 Serializable 接口。
 * 为什么实现 Serializable 仍然是好的实践
 * Java标准序列化：
 *
 * 对于需要使用Java标准序列化机制的场景（如某些远程方法调用、Java原生序列化到文件等），实现 Serializable 接口是必要的。
 * 一致性和未来兼容性：
 *
 * 即使当前的应用场景不要求实现 Serializable，为了保持一致性和可能的未来兼容性，实现它仍然是一个好的做法。这样，如果将来您的应用需要使用到基于Java原生序列化的功能，您的类已经准备就绪。
 * 清晰的意图表达：
 *
 * 实现 Serializable 接口清楚地表明了您的类是可以被序列化和反序列化的，这对于维护代码和团队协作是有益的。
 * 结论
 * 虽然在您的当前场景中，即使不实现 Serializable 接口也能成功运行，但实现它是一种符合Java最佳实践的做法，特别是考虑到可能的未来用例和清晰的代码意图表达。同时，这也强调了Spring框架和现代Java开发中使用的序列化机制的灵活性和多样性。
 */
public class Member implements Serializable {
    private Long id;
    private String name;
    private String pwd;
    private String mobile;
    private String email;
    private Integer gender;
}
