package com.hspedu.furns.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

/**
 * @author yangda
 * @description: 就是购物车 包含多个CartItem
 * @create 2023-08-02-19:16
 */
public class Cart {

    //public class HashMap<K,V>  只可以指定两项泛型k,v
    // 写多了会报错 Wrong number of type arguments: 3; required: 2
    // 不可以指定为基本数据类型 Type argument cannot be of primitive type
    // 使用HashMap 存放 CartItem 对象
    //
    private HashMap<Integer, CartItem> items = new HashMap();

    //购物车总商品数
    //private Integer totalCount;
    //这里注销掉了！没有totalCount属性 前端在el表达式中是否还可以通过cart.totalCount
    // 结论：设置在session域对象中 cart对象的没有totalCount属性
    // 前端在el表达式中 依然可以通过 ${sessionScope.cart.totalCount} 进行获取
    // 因为el表达式 这种方式 找的是cart对象的get方法 只要对象中有get方法 就可以这样获取！！


    //public HashMap getItems() {
    //    return items;
    //}


    public boolean isEmpty(){
        return items.size() == 0;
    }

    public HashMap<Integer, CartItem> getItems() {
        return items;
    }

    /**
     * 向购物车中添加 cartItem
     */
    public void addItem(CartItem cartItem) {

        // 通过商品id 查看items 集合中是否已经存在该id的家居商品项
        CartItem item = items.get(cartItem.getId());

        // 下面这种写法也可以 没有用到else
        //if (item == null) {
        //    // 说明item中不存在该商品项 可以直接添加到购物车
        //    items.put(cartItem.getId(), cartItem);
        //    // 直接放到集合中不往下走了！
        //    return;
        //}


        if (item == null) { //说明当前购物车 还没有这个 cartItem
            // 说明item中不存在该商品项 可以直接添加到购物车
            items.put(cartItem.getId(), cartItem);

        } else {
            /* 上面的if语句相当于： Absent 不存在
             *  items.putIfAbsent(cartItem.getId(), cartItem);
             */
            // 走到这里 说明item不为空 即在items中有该id的商品项
            // 不需要添加新的商品项 修改该id商品项item的商品个数和总价格即可
            // 下面这种写法 要保证传进来的cartItem
            // 的count属性值 为1 即要保证只添加一个商品！才可以这样写
            // 否则按照注释掉的写法更加准确
            //item.setCount(item.getCount() + cartItem.getCount());
            item.setCount(item.getCount() + 1); //数量加1

            // Operator '*' cannot be applied to 'java.lang.Integer', 'java.math.BigDecimal'
            // BigDecimal 乘法不可以使用'*' 有专门的方法multiply
            //item.setTotalPrice(item.getCount() * item.getPrice());
            //item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));

            // 修改总价
            // 下面这种写法 该id的 item 的总价加上一次单价就是最新的价格
            // 要保证传进来的cartItem
            // 的count属性值 为1 即要保证只添加一个商品！才可以这样写
            // 否则按照 单价乘以实际的数量更加准确！
            item.setTotalPrice(item.getTotalPrice().add(item.getPrice()));
        }
    }

    /**
     * 更新购物车
     *
     * @param id
     * @param update 用于判断是如何更新
     */
    public void updateItem(Integer id, String update) {


        // 通过商品id 查看items 集合中是否已经存在该id的家居商品项
        CartItem item = items.get(id);
        // 接收前端传过来的 增加或者是减少的标记
        // 下面这种写法也可以 没有用到else
        //if (item == null) {
        //    // 说明item中不存在该商品项 可以直接添加到购物车
        //    items.put(cartItem.getId(), cartItem);
        //    // 直接放到集合中不往下走了！
        //    return;
        //}

        if (item == null) { //说明当前购物车 还没有这个 cartItem
            // 说明item中不存在该商品项
            System.out.println("购物车中不存在id= " + id + " 的商品项");
        } else {

            // 走到这里 说明item不为空 即在items中有该id的商品项
            // 不需要添加新的商品项 修改该id商品项item的商品个数和总价格即可
            // 下面这种写法 要保证传进来的cartItem
            // 的count属性值 为1 即要保证只添加一个商品！才可以这样写
            // 否则按照注释掉的写法更加准确
            //item.setCount(item.getCount() + cartItem.getCount());
            if ("add".equals(update)) {
                // 说明是加操作
                item.setCount(item.getCount() + 1); //数量加1
                // Operator '*' cannot be applied to 'java.lang.Integer', 'java.math.BigDecimal'
                // BigDecimal 乘法不可以使用'*' 有专门的方法multiply
                //item.setTotalPrice(item.getCount() * item.getPrice());
                //item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));

                // 修改总价
                // 下面这种写法 该id的 item 的总价加上一次单价就是最新的价格
                // 要保证传进来的cartItem
                // 的count属性值 为1 即要保证只添加一个商品！才可以这样写
                // 否则按照 单价乘以实际的数量更加准确！
                item.setTotalPrice(item.getTotalPrice().add(item.getPrice()));
            } else {
                // 说明是减操作
                // 判断当前的数量是否大于1 如果大于1那么可以减1
                // 如果小于等于1 将该商品项直接移除
                if (item.getCount() > 1) {
                    item.setCount(item.getCount() - 1); //数量减1
                    item.setTotalPrice(item.getTotalPrice().subtract(item.getPrice()));
                }else {
                    // 直接移除该商品项
                    items.remove(id);

                }
            }
        }
    }


    /**
     * 修改指定的CartItem的数量和总价, 根据传入的id 和 count
     *
     * @param id
     * @param count
     */
    public void updateCount(int id, int count) {

        CartItem item = items.get(id);
        if (null != item) {//如果得到CartItem
            //先更新数量
            item.setCount(count);
            //再更新总价 这里的参数填item.getCount() 从item对象来取更加合理
            // 更符合oop的原则 前面使用set 后面一般使用get进行获取
            //因为直接传进来的count可能不是我们最终要的数据 可能在setCount()方法中
            //进行数据的处理 比如边界的处理/数据格式的校验...
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }

    /**
     * 删除购物车的一项
     * @param id
     */
    public void deleteItem(Integer id){

        CartItem cartItem = items.get(id);

        if (cartItem != null){

            items.remove(id);
        }
    }

    /**
     * 根据传入的id ,删除指定的购物车项
     *
     * @param id
     */
    public void delItem(int id) {
        items.remove(id);
    }

    /**
     * 清空购物车的一项
     */
    public void clear(){
        if ( items != null){
            items.clear();
        }
    }

    /**
     * 返回购物车中的商品总数
     *
     * @return
     */
    public Integer getTotalCount() {
        Integer totalCount = 0;
        //遍历items集合
        Set<Integer> keyset = items.keySet();
        for (Integer id : keyset) {
            CartItem cartItem = items.get(id);
            totalCount += cartItem.getCount();
        }
        return totalCount;
    }

    /**
     * 返回购物车中所有商品总金额
     * 返回BigDecimal类型的参数 前端页面也可以使用el表达式取出
     *
     * @return
     */
    public BigDecimal getCartTotalPrice() {
        //public Integer getTotalPrice() {
        BigDecimal bigDecimalTotalPrice = new BigDecimal(0);
        //Integer totalPrice = 0;
        //遍历items集合
        Set<Integer> keyset = items.keySet();
        for (Integer id : keyset) {
            CartItem cartItem = items.get(id);
            //老师提醒, 一定要包add后的值, 重新赋给 cartTotalPrice, 这样才是累加.
            // bigDecimalTotalPrice.add(cartItem.getTotalPrice()); 只是返回的一个结果
            // 对原来的值bigDecimalTotalPrice 没有影响！！
            bigDecimalTotalPrice = bigDecimalTotalPrice.add(cartItem.getTotalPrice());
        }
        return bigDecimalTotalPrice;
        //return bigDecimalTotalPrice.intValue();
    }


    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
