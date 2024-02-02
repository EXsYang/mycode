package com.hspedu.springboot.controller;

import com.hspedu.springboot.bean.Car;
import com.hspedu.springboot.bean.Monster;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author yangda
 * @create 2023-12-06-20:27
 * @description: 返回json格式数据
 */
@Controller
public class ResponseController {

    //返回Monster对象-以json格式返回

    /**
     * 底层会走到 AbstractJackson2HttpMessageConverter转换器类的 writeInternal方法
     * 根据前端http Headers 设置的 Accept 获取到的类型进行内容协商 决定要返回的数据格式
     * , 默认是json格式返回, 如客户端设置了 Accept: ※/※ 表示默认接收所有格式的数据， 这里※即星号* 防止编译报错进行了替换
     * 使用postman将Accept改为 application/xml 发送请求，会报406的错误
     * , 原因是后端少一个解析xml的依赖,
     * spring-boot-starter-web web场景启动器 默认有处理json格式的依赖
     * , 但是没有处理返回xml格式的依赖,需要我们手动添加支持处理xml数据格式返回的依赖
     */
    @GetMapping("/get/monster")
    @ResponseBody
    public Monster getMonster(){

        //开发中，monster对象是从DB中获取的,这里模拟monster数据
        Monster monster = new Monster();
        monster.setId(100);
        monster.setName("孙悟空");
        monster.setAge(10000);
        monster.setBirth(new Date());
        monster.setIsMarried(false);
        Car car = new Car();
        car.setName("奔驰");
        car.setPrice(200000.0);
        monster.setCar(car);

        return monster;
    }

    /**
     * 火狐浏览器的Accept如下
     * Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,星/星;q=0.8
     * 在没有引入jackson-dataformat-xml依赖时,默认返回json格式的数据,为什么?
     * 因为，前面的xml格式后端处理不了，但是Accept又有一个 星/星 , 星/星可以接收所有格式的数据
     * ,当然也包括json格式, 后端默认返回json格式的数据,因此浏览器接受到的是json格式，
     * application/xml;q=0.9    中的q=0.9 是代表权重，值越大优先级越高， 星/星;q=0.8 优先级越低
     * 如果后端既可以处理xml,又可以处理json格式的数据，按照这个权重 应该优先返回xml格式的数据
     *
     *
     * 1. Postman 可以通过修改 Accept 的值，来返回不同的数据格式
     * 2. 如果后端即支持xml, 又支持json格式 浏览器不能修改Accept的值,但是我们就想要返回指定格式的数据怎么办？
     * 如下的 Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,星/星;q=0.8
     * 就想要返回json格式如何指定?
     *  对于浏览器，我们无法修改其 Accept 的值，怎么办? 解决方案: 开启支持基于请求参数
     * 的内容协商功能
     * 1) 修改 application.yml, 开启基于请求参数的内容协商功能
     * spring:
     *   mvc:
     *     contentnegotiation:
     *       favor-parameter: true #开启基于请求参数的内容协商功能
     *
     *  favor-parameter对应 WebMvcProperties.java 中的属性 favorParameter
     * 		 * Whether a request parameter ("format" by default) should be used to determine
     * 		 * the requested media type.
     * 		 * 默认情况下按照"format"接收客户端需要返回的媒体类型的,也就是说在请求的时候带一个"format"
     * 		 * 在后面带上要返回的类型就能够进行请求参数的内容协商  format:格式
     * 		private boolean favorParameter = false;
     *  具体用法: http://localhost:8080/get/monster?format=json
     *      format=json 优先级大于Accept中的内容协商
     *      虽然     Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,星/星;q=0.8
     *      虽然 Accept 不能修改，但是因为在请求的时候，带了一个请求参数 format 就按照指定的格式进行返回
     *      即返回json格式
     *
     *
     *  注意，参数 format 是规定好的 ， 在开启请求参数的内容协商功能后，SpringBoot 底层
     * ParameterContentNegotiationStrategy 会通过 format 来接收参数，然后返回对应的媒体类型/
     * 数据格式 , 当然 format=xx 这个 xx 媒体类型/数据格式 是 SpringBoot 可以处理的才行，不
     * 能乱写
         *public class ParameterContentNegotiationStrategy extends AbstractMappingContentNegotiationStrategy {
         * 	private String parameterName = "format";
     * 	该值可以在配置文件application.yml中进行修改
     * 	通过
     * 	 contentnegotiation:
     *       parameter-name: hspformat # 指定一个内容协商的参数名，该参数是通过url带给后端
     *
     *
     */

    /**
     * AbstractJackson2HttpMessageConverter转换器类核心代码
     * @Override
     * protected void writeInternal(Object object, @Nullable Type type, HttpOutputMessage outputMessage)
     *       throws IOException, HttpMessageNotWritableException {
     *
     *  // contentType 其实就是http协议中请求头设置的 Accept的值
     *  // 根据contentType的值进行协商,来决定要给浏览器/客户端返回的数据格式 [json/xml...]
     *    MediaType contentType = outputMessage.getHeaders().getContentType();
     *
     *
     *
     *    JsonEncoding encoding = getJsonEncoding(contentType);
     *
     *    Class<?> clazz = (object instanceof MappingJacksonValue ?
     *          ((MappingJacksonValue) object).getValue().getClass() : object.getClass());
     *
     *    //objectMapper 对象是Jackson的一个对象,之前在hsp-springmvc用过 进行对象转换的 如 ArrayList->json
     *    ObjectMapper objectMapper = selectObjectMapper(clazz, contentType);
     *
     *    Assert.state(objectMapper != null, "No ObjectMapper for " + clazz.getName());
     *
     *    OutputStream outputStream = StreamUtils.nonClosing(outputMessage.getBody());
     *    try (JsonGenerator generator = objectMapper.getFactory().createGenerator(outputStream, encoding)) {
     * 	   // generator:生成器
     * 	   JsonGenerator 容易产生误解  根据contentType不同，使用工厂模式返回的 generator也不同
     * 	   这里的generator用来将Monster对象转换为json、xml...返回给客户端
     *        // 并不是只可以处理Json格式的数据，xml也可以处理，只不过
     *        // 这里统一使用JsonGenerator进行处理
     *        // 根据contentType不一样，产生的generator不一样
     *        // 如果返回的是json格式这里产生的 generator对象 是 UTF8JsonGenerator
     *        // 如果返回的是xml格式这里产生的 generator对象 是 ToXmlGenerator(前提是
     *        // 在pom.xml文件中引入了支持返回xml格式的依赖jackson-dataformat-xml)
     *
     *
     *        writePrefix(generator, object);
     *
     *       Object value = object;
     *       Class<?> serializationView = null;
     *       FilterProvider filters = null;
     *       JavaType javaType = null;
     *
     *       if (object instanceof MappingJacksonValue) {
     *          MappingJacksonValue container = (MappingJacksonValue) object;
     *          value = container.getValue();
     *          serializationView = container.getSerializationView();
     *          filters = container.getFilters();
     *       }
     *       if (type != null && TypeUtils.isAssignable(type, value.getClass())) {
     *          javaType = getJavaType(type, null);
     *       }
     *
     *       ObjectWriter objectWriter = (serializationView != null ?
     *             objectMapper.writerWithView(serializationView) : objectMapper.writer());
     *       if (filters != null) {
     *          objectWriter = objectWriter.with(filters);
     *       }
     *       if (javaType != null && javaType.isContainerType()) {
     *          objectWriter = objectWriter.forType(javaType);
     *       }
     *       SerializationConfig config = objectWriter.getConfig();
     *       if (contentType != null && contentType.isCompatibleWith(MediaType.TEXT_EVENT_STREAM) &&
     *             config.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
     *          objectWriter = objectWriter.with(this.ssePrettyPrinter);
     *       }
     *       //重要的，在这里使用传进去的generator,对value值进行处理, 返回对应格式的数据
     *       objectWriter.writeValue(generator, value);
     *
     *
     *       writeSuffix(generator, object);
     *       generator.flush();
     *    }
     *    catch (InvalidDefinitionException ex) {
     *       throw new HttpMessageConversionException("Type definition error: " + ex.getType(), ex);
     *    }
     *    catch (JsonProcessingException ex) {
     *       throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getOriginalMessage(), ex);
     *    }
     * }
     */


}
