package com.atguigu.set_;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author yangda
 * @description:
 * @create 2022-11-15-16:14
 */
public class HashSetSource {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();

        hashSet.add("java");

//        public boolean add(E e) {
//            return map.put(e, PRESENT)==null; //    private static final Object PRESENT = new Object();
//        }

        hashSet.add("java");


//        final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//        boolean evict) {
//            HashMap.Node<K,V>[] tab; HashMap.Node<K,V> p; int n, i;
//            if ((tab = table) == null || (n = tab.length) == 0)
//                n = (tab = resize()).length;
//            if ((p = tab[i = (n - 1) & hash]) == null)  //p是在这里进行赋值的，每次进来都会根据新进来对象对应table中
//                                                          的位置，在table表中的位置进行赋值，每次p都是table表的第一个结点
//                tab[i] = newNode(hash, key, value, null);
//            else {
//                HashMap.Node<K,V> e; K k;
//                if (p.hash == hash &&
//                        ((k = p.key) == key || (key != null && key.equals(k))))
//                    e = p;
//                else if (p instanceof TreeNode)
//                    e = ((HashMap.TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
//                else {
//                    for (int binCount = 0; ; ++binCount) {
//                        if ((e = p.next) == null) {  // e 在这里进行赋值操作，e指向p.next,即指向p的下一个元素
//                            p.next = newNode(hash, key, value, null);
//                            if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
//                                treeifyBin(tab, hash);
//                            break;
//                        }
//                        if (e.hash == hash &&
//                                ((k = e.key) == key || (key != null && key.equals(k))))
//                            break;
//                        p = e;
//                    }
//                }
//                if (e != null) { // existing mapping for key
//                    V oldValue = e.value;//value 是PRESENT = new Object();
//                    if (!onlyIfAbsent || oldValue == null)//这里注意取反了，是true onlyIfAbsent传进来时写死了false
//                        e.value = value;
//                    afterNodeAccess(e);
//                    return oldValue;
//                }
//            }
//            ++modCount;
//            if (++size > threshold)
//                resize();
//            afterNodeInsertion(evict);
//            return null;
//        }

        hashSet.add("jack");







    }
}
