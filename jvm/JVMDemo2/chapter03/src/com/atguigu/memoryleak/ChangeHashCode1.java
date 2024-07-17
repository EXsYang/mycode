package com.atguigu.memoryleak;

import java.util.HashSet;

/**
 * 演示内存泄漏
 * @author shkstart
 * @create 14:47
 */
public class ChangeHashCode1 {
    public static void main(String[] args) {
        HashSet<Point> hs = new HashSet<Point>();
        Point cc = new Point();
        cc.setX(10);//hashCode = 41
        hs.add(cc);

        cc.setX(20);//hashCode = 51 ,这里是直接修改了hash值, 此行为导致了内存的泄漏
        // cc.setX(20); 并不会直接导致hash值改变，
        // 而是下面的 hs.remove(cc);方法时才会直接修改了hash值,
        // 因为cc的hash值是基于Point的x属性的。
        // 此时 cc 的 hashCode 应更新为 51，
        // 但这种变化实际上在 hs.remove(cc) 调用时才被考虑，
        // 因为 HashSet 在尝试删除时会重新计算 hashCode。

        System.out.println("hs.remove = " + hs.remove(cc));//false ,
        // remove()方法会在底层调用cc对象的hashCode()方法,导致cc对象生成新的hashCode值,
        // 与之前的HashCode值不一样了，因此删除失败。
        // 删除失败，因为 HashSet 正在查找 hashCode 为 51 的元素，而在添加时 cc 的 hashCode 是 41。因此，HashSet 中没有元素匹配当前的 hashCode。
        hs.add(cc); //尽管 cc 的实例已经在集合中，但由于其 hashCode 已经改变，HashSet 将其视为不同的元素，所以再次添加成功。
        System.out.println("hs.size = " + hs.size());//size = 2

        System.out.println(hs);
    }

}

class Point {
    int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Point other = (Point) obj;
        if (x != other.x) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                '}';
    }
}
