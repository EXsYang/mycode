package com.hspedu.mhl.utils;

/**
 * @author yangda
 * @description:
 * @create 2023-04-29-2:07
 */
class Father {

    Father() {
        System.out.println("父类构造器");
    }

    public void doWork() {

        System.out.println("父类doWork方法");

    }

}

class son extends Father {

    son() {
        System.out.println("子类构造器");
    }

    public void doWork() {

        System.out.println("子类doWork方法");

    }

}

class MutipliedDemo {

    public static void main(String[] args) {

        Father child = new son();
        System.out.println(child);
        System.out.println(child.getClass());
        child.doWork();
    }

}
