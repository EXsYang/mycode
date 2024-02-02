package com.hspedu.web.datavalid.entity;

import org.hibernate.validator.constraints.Range;
import org.springframework.util.StringUtils;

// import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Furn {
    //id 在前端的add()方法中 没有传id的值，id是自增长的 因此这里不需要验证注解
    private Integer id;

    // String类型 验证为空首选@NotEmpty
    // @NotEmpty(message = "家居名不能为空,请输入家居名")
    private String name;

    // @NotEmpty(message = "制造商名不能为空,请输入制造商名")
    private String maker;

    //@NotNull注解可以用在任何类型上
    @NotNull(message = "请输入价格")
    @Range(min = 0,message = "价格不能小于0")
    private BigDecimal price;

    @NotNull(message = "请输入数字")
    @Range(min = 0,message = "销量不能小于0")
    private Integer sales;

    //增加新的校验规则 类型不能输入非数字 否则报错
    @NotNull(message = "请输入数字")
    // @Min(value = 0,message = "填入的值必须是数字")
    @Range(min = 0,message = "库存不能小于0")
    private Integer stock;

    // imgPath 属性 是通过逆向工程 自动生成的字段
    // 在逆向工程生成该字段时自动指定了映射关系 不用人为干预
    private String imgPath = "assets/images/product-image/1.jpg";

    // 当创建 Furn 对象 imgPath 为 null 时, imgPath 给默认值
    // 注意 使用逆向工程生成的 Furn.java 文件中 没有全参构造器 需要手动添加全参构造器
    public Furn(Integer id, String name, String maker, BigDecimal price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.sales = sales;
        this.stock = stock;

        // 做一个判断
        // 如果 imgPath 有数据 就设置给this.imgPath
        // 如果 null 或 空串"" 或 都是空格"    " 就使用默认的图片路径

        //使用spring框架提供的工具类 StringUtils hasText()方法
        //解决
        // import org.springframework.util.StringUtils;
        /**
         *  public static boolean hasText(@Nullable String str) {
         *         return str != null && !str.isEmpty() && containsText(str);
         *  }
         *
         *  StringUtils.hasText() 会经常使用!!
         *  该方法 如果传入的是 null 或 空串"" 或 都是空格"    "   返回false 即
         *  如果是Text 有数据 ,才会返回true
         */
        if (StringUtils.hasText(imgPath)) {
            this.imgPath = imgPath;
        }
    }

    // ,同时将 被覆盖掉的无参构造器 显示的声明一下
    public Furn() {
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
        this.name = name == null ? null : name.trim();
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker == null ? null : maker.trim();
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
        this.imgPath = imgPath == null ? null : imgPath.trim();
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