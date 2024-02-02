package com.hspedu.furns.entity;

import java.math.BigDecimal;

/**
 * @author yangda
 * @description: Furn javaben 和 表 furn映射
 * @create 2023-07-17-11:05
 */
//public class Furn {
//            //`id` INT PRIMARY KEY AUTO_INCREMENT,
//            //`product_name` VARCHAR(32) NOT NULL UNIQUE,
//            //`merchant_name` VARCHAR(32) NOT NULL DEFAULT '', -- 或者 business name
//            //-- `price` int NOT NULL DEFAULT 0,
//            //`price` DOUBLE NOT NULL DEFAULT 0.0,
//            //`sales_volume` INT NOT NULL DEFAULT 0,
//            //`inventory` INT NOT NULL DEFAULT 0,
//            //-- `operate` tinyint 1:'修改操作',2:'删除操作' NOT NULL DEFAULT 1,	-- 有语法错误
//            //`operate` TINYINT NOT NULL DEFAULT 0
//
//    private Integer id;
//    //private String imgPath;
//    private String product_name; //商品名、家居名
//    private String merchant_name; //商家名
//    private Double price;
//    private Integer sales_volume; //销量
//    private Integer inventory;  //库存
//    private Integer operate;
//
//    public Furn() {
//    }
//
//    public Furn(Integer id, String product_name, String merchant_name, Double price, Integer sales_volume, Integer inventory, Integer operate) {
//        this.id = id;
//        this.product_name = product_name;
//        this.merchant_name = merchant_name;
//        this.price = price;
//        this.sales_volume = sales_volume;
//        this.inventory = inventory;
//        this.operate = operate;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getProduct_name() {
//        return product_name;
//    }
//
//    public void setProduct_name(String product_name) {
//        this.product_name = product_name;
//    }
//
//    public String getMerchant_name() {
//        return merchant_name;
//    }
//
//    public void setMerchant_name(String merchant_name) {
//        this.merchant_name = merchant_name;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public Integer getSales_volume() {
//        return sales_volume;
//    }
//
//    public void setSales_volume(Integer sales_volume) {
//        this.sales_volume = sales_volume;
//    }
//
//    public Integer getInventory() {
//        return inventory;
//    }
//
//    public void setInventory(Integer inventory) {
//        this.inventory = inventory;
//    }
//
//    public Integer getOperate() {
//        return operate;
//    }
//
//    public void setOperate(Integer operate) {
//        this.operate = operate;
//    }
//
//    @Override
//    public String toString() {
//        return "Furn{" +
//                "id=" + id +
//                ", product_name='" + product_name + '\'' +
//                ", merchant_name='" + merchant_name + '\'' +
//                ", price=" + price +
//                ", sales_volume=" + sales_volume +
//                ", inventory=" + inventory +
//                ", operate=" + operate +
//                '}';
//    }
//}
public class Furn {
    // CREATE TABLE `furn`(
    //`id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, #id
    //`name` VARCHAR(64) NOT NULL, #家居名
    //`maker` VARCHAR(64) NOT NULL, #制造商
    //`price` DECIMAL(11,2) NOT NULL, #价格 定点数
    //`sales` INT UNSIGNED NOT NULL, #销量
    //`stock` INT UNSIGNED NOT NULL, #库存
    //`img_path` VARCHAR(256) NOT NULL #存放图片的路径
    //)CHARSET utf8 ENGINE INNODB;

    //表的字段如何和 JavaBean映射
    private Integer id; // 防止null
    private String name; //商品名、家居名
    private String maker; //商家名
    private BigDecimal price;  //价格 BigDecimal 对应数据库中的 decimal(m,d) 字段类型
    private Integer sales; //销量
    private Integer stock;  //库存
    // 这里和数据库的img_path 字段名不一致，有坑，注意！
    private String imgPath = "assets/images/product-image/default.jpg"; //图片的路径 第一次创建时 没有给默认值
    //有一个隐藏的坑, 注意一会我说解决方案
    //表的字段是 img_path , 而老师用的是 imgPath
    //private String imgPath = "assets/images/product-image/default.jpg";
    public Furn() {
    }
    public Furn(Integer id, String name, String maker, BigDecimal price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        // 测试BeanUtils  反射使用的是带参构造器吗？ 还是set方法？
        // 测试方法：前端页面添加一个input 用于提交imgPath
        //前端 发送了一个空值/空字符串 后端BeanUtils反射 的imgPath后也是空
        //说明反射走的是空参构造器的set方法！
        if (!("".equals(imgPath) || imgPath == null)){
            //传入的imgPath不为空或不为空串 即imgPath有值时，才把该值重新初始化
            this.imgPath = imgPath;
        }

    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Furn{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maker='" + maker + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}