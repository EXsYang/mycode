package com.atguigu.list_;

import java.util.LinkedList;

/**
 * @author yangda
 * @description:
 * @create 2022-11-14-21:28
 */
public class LinkedListSource {
    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        // public LinkedList() {//进行了初始化
        // }


        linkedList.add(1);
        // 第一步：public boolean add(E e) {
            //        linkLast(e);
            //        return true;
            //    }

        //第二步：
//        void linkLast(E e) {
//            final LinkedList.Node<E> l = last;//第一次添加时l、last 为空
//            final LinkedList.Node<E> newNode = new LinkedList.Node<>(l, e, null);
//            last = newNode;
//            if (l == null)
//                first = newNode;
//            else
//                l.next = newNode;
//            size++;
//            modCount++;
//        }



        linkedList.add(2);
        //第三步：
//        void linkLast(E e) {
//            final LinkedList.Node<E> l = last;//第二次添加时last,l 不为空，都指向第一个对象
//            final LinkedList.Node<E> newNode = new LinkedList.Node<>(l, e, null);
//            last = newNode;
//            if (l == null)
//                first = newNode;
//            else
//                l.next = newNode;
//            size++;
//            modCount++;
//        }

        linkedList.add(3);

        linkedList.set(1,4);

//        public E set(int index, E element) {
//            checkElementIndex(index);
//            LinkedList.Node<E> x = node(index);
//            E oldVal = x.item;    //
//            x.item = element;     //把原来双向链表index索引位置的 存的内容 item 改了
//            return oldVal;
//        }



//        LinkedList.Node<E> node(int index) {
//            // assert isElementIndex(index);
//
//            if (index < (size >> 1)) {
//                LinkedList.Node<E> x = first;
//                for (int i = 0; i < index; i++)
//                    x = x.next;
//                return x;
//            } else {
//                LinkedList.Node<E> x = last;
//                for (int i = size - 1; i > index; i--)
//                    x = x.prev;
//                return x;     返回的是目标索引index 的结点
//            }
//        }



        linkedList.remove();



        linkedList.remove();






        linkedList.add(3);


    }
}
