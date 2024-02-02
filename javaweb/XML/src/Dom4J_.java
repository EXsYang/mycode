import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-05-20-15:36
 */
public class Dom4J_ {

    @Test
    public void test01() throws DocumentException {
        //1.得到一个解析器
        SAXReader reader = new SAXReader();
        //2.指定解析哪个xml文件
        Document document = reader.read(new File("src/students.xml"));
        //直接写路径也可以
        //Document document = reader.read( "src/students.xml");

        File file = new File("src/students.xml");
        //直接输出file 对象 默认调用 toString()
        //File 的 toString() 重写过了
        /*
        public String toString() {
        return getPath();
        }
        */
        System.out.println("file=" + file);//file=src\students.xml
        System.out.println(document);

        //3.遍历所有的students元素
        Element rootElement = document.getRootElement();
        System.out.println("rootElement=" + rootElement);
        List<Element> elements = document.getRootElement().elements();
        System.out.println("elements=" + elements);
        List<Element> students = document.getRootElement().elements("student");
        System.out.println("students=" + students);

        //get(0) 是List接口的一个方法
        Element element = elements.get(0);

        for (Element student : students) {
            //取出student元素的所有信息
            Element name = student.element("name");
            Element age = student.element("age");
            Element gender = student.element("gender");
            String id = student.attributeValue("id");

            //getName返回此节点的名称
            String getName  = student.getName();//student
            //创建成Student对象
            Student student1 = new Student();
            student1.setId(Integer.parseInt(id));
            student1.setAge(Integer.parseInt(age.getText()));
            student1.setName(name.getText());
            student1.setGender(gender.getText());

            System.out.println("student1对象信息" + student1);
            System.out.println("student.getName()=" + getName);
            System.out.println("name=" + name);
            System.out.println("age=" + age);
            System.out.println("gender=" + gender);
            System.out.println("id=" + id);







        }

    }

}
