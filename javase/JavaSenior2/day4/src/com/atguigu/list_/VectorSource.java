package com.atguigu.list_;

import java.util.Arrays;
import java.util.Vector;

/**
 * @author yangda
 * @description:
 * @create 2022-11-14-20:06
 */
public class VectorSource {
    public static void main(String[] args) {

        Vector vector = new Vector();//调用的还是有参构造器


//         public Vector() {
//            this(10);
//        }

//           public Vector(int initialCapacity) {
//            this(initialCapacity, 0);//开始的，最初的容量
//        }


//         public Vector(int initialCapacity, int capacityIncrement) {
//            super();
//            if (initialCapacity < 0)
//                throw new IllegalArgumentException("Illegal Capacity: "+
//                        initialCapacity);
//            this.elementData = new Object[initialCapacity];
//            this.capacityIncrement = capacityIncrement;//容量定量
//        }


        Vector vector1 = new Vector(8);


//         public Vector(int initialCapacity) {
//            this(initialCapacity, 0);
//        }
//


//        public Vector(int initialCapacity, int capacityIncrement) {
//            super();
//            if (initialCapacity < 0)
//                throw new IllegalArgumentException("Illegal Capacity: "+
//                        initialCapacity);
//            this.elementData = new Object[initialCapacity];
//            this.capacityIncrement = capacityIncrement;
//        }


        for (int i = 1; i <= 10; i++) {
            vector.add(i);
//            public synchronized boolean add(E e) {
//                modCount++;
//                ensureCapacityHelper(elementCount + 1);
//                elementData[elementCount++] = e;
//                return true;
//            }

//            private void ensureCapacityHelper(int minCapacity) {
//                // overflow-conscious code
//                if (minCapacity - elementData.length > 0)
//                    grow(minCapacity);
//            }

//            private void grow(int minCapacity) {
//                // overflow-conscious code    意识到溢出的代码
//                int oldCapacity = elementData.length;
//                int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
//                        capacityIncrement : oldCapacity);
//                if (newCapacity - minCapacity < 0)
//                    newCapacity = minCapacity;
//                if (newCapacity - MAX_ARRAY_SIZE > 0)
//                    newCapacity = hugeCapacity(minCapacity);
//                elementData = Arrays.copyOf(elementData, newCapacity);
//            }


        }
          vector.add(199);
        for (int i = 1; i <= 10; i++) {
            vector1.add(i);
        }
        vector1.add(199);

    }
}
