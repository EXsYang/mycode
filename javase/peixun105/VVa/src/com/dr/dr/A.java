package com.dr.dr;


public class A  {
    int maxElements;
   A()  {
        maxElements = 100;
        System.out.println(maxElements);
    }
   A(int i)  {
        maxElements = i;
        System.out.println(maxElements);
    }
    public static void main(String[] args) {
         A a = new A();
         A b = new A(999);
     }

 }