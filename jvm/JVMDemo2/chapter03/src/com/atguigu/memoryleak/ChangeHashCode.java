package com.atguigu.memoryleak;

import java.util.HashSet;

/**
 * 演示内存泄漏
 * 演示因修改影响hashCode的字段导致的内存泄漏。
 * @author shkstart
 * @create 14:43
 */
public class ChangeHashCode {
    public static void main(String[] args) {
        HashSet set = new HashSet();
        Person p1 = new Person(1001, "AA"); //0x11
        Person p2 = new Person(1002, "BB"); //0x22

        set.add(p1);
        set.add(p2);

        p1.name = "CC";//这里是修改了name属性值, 导致了内存的泄漏。
        set.remove(p1); // 0x33 //删除失败,当执行 set.remove(p1); 时，
        // 才会更新p1方法的hashCode,
        // HashSet 将根据 p1 的当前(根据新的属性"CC"更新后的)哈希码
        // （此时已根据 "CC" 计算）来定位 p1
        // 应该位于的存储位置。由于这个位置并没有与 p1 相匹配的对象
        // （因为根据旧的hashCode存储的位置和当前新生成的hashCode不同），
        // 所以 remove 方法找不到要删除的对象，导致删除失败。

        System.out.println(set);

        set.add(new Person(1001, "CC")); //0x33 这里和之前的p1对象的存储地址不同。可以添加进去。
        System.out.println(set);

        set.add(new Person(1001, "AA")); //0x11 这里和之前的p1对象的存储地址相同,
        // 但是HashCode值不同,p1由于更新name为"CC"导致更新hashCode=0x33,
        // 但是p1对象还是存储在hashcode=0x11时计算得来的数组的位置上,由于p1当前的属性更新为了"CC",hashCode=0x33,
        // 和这里新添加的匿名对象Person对象做equals比较是不同的对象，属于两个不同的对象。可以添加进去。【当然hashcode值也是不同的】
        System.out.println(set);

    }
}

class Person {
    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}