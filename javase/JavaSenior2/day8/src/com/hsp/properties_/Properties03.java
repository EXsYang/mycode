package com.hsp.properties_;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author yangda
 * @description:
 * @create 2022-11-29-11:18
 */
public class Properties03 {
    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        properties.setProperty("charSet","utf-8");
        properties.setProperty("user","杨达");
        properties.setProperty("pwd","12345");
        properties.setProperty("pwd","11111");

        /*
         public synchronized Object setProperty(String key, String value) {
            return put(key, value);
        }
         */

        /*
        走的是Hashtable中的put()
         public synchronized V put(K key, V value) {
            // Make sure the value is not null
            if (value == null) {
                throw new NullPointerException();
            }

            // Makes sure the key is not already in the hashtable.
            Entry<?,?> tab[] = table;
            int hash = key.hashCode();
            int index = (hash & 0x7FFFFFFF) % tab.length;
            @SuppressWarnings("unchecked")
            Entry<K,V> entry = (Entry<K,V>)tab[index];
            for(; entry != null ; entry = entry.next) {
                if ((entry.hash == hash) && entry.key.equals(key)) {
                    V old = entry.value;
                    entry.value = value;//key 相同相当于替换操作
                    return old; //把旧的值返回
                }
            }

            addEntry(hash, key, value, index); //key 是新的 直接加进去
            return null;
        }
         */

        properties.store(new FileOutputStream("src/mysql01.properties"),null);//字节流 中文保存为对应的Unicode值
        properties.store(new FileWriter("src/mysql02.properties"),null);//字符流 中文保存的是中文
        properties.store(new FileWriter("src/mysql03.properties"),"我是注释:comments");

//        private void store0(BufferedWriter bw, String comments, boolean escUnicode)
//        throws IOException
//        {
//            if (comments != null) {
//                writeComments(bw, comments);
//            }
//            bw.write("#" + new Date().toString());
//            bw.newLine();
//            synchronized (this) {
//                for (Enumeration<?> e = keys(); e.hasMoreElements();) {
//                    String key = (String)e.nextElement();
//                    String val = (String)get(key);
//                    key = saveConvert(key, true, escUnicode);
//                    /* No need to escape embedded and trailing spaces for value, hence
//                     * pass false to flag.
//                     */
//                    val = saveConvert(val, false, escUnicode);
//                    bw.write(key + "=" + val);
//                    bw.newLine();
//                }
//            }
//            bw.flush(); //这里进行了刷新，写入了
//        }

        System.out.println("生成文件");


    }
}
