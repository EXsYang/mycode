package com.hspedu.furn.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author yangda
 * @create 2023-12-22-19:47
 * @description:
 * 前端测试数据 前端如果发送的是json格式 需要指定Content-Type:application/json
 * 前端测试数据 前端如果以表单格式发送的数据 需要指定Content-Type:multipart/form-data
 *
 * {
 *     "id":"100",
 *     "name":"顺平沙发",
 *     "maker":"顺平家具",
 *     "price":"200.2",
 *     "sales":"2",
 *     "stock":"100"
 * }
 *
 * 如果Furn类名和表 furn名字不能对应,可以通过@TableName 指定
 * 如果可以对应上,可以不写
 */
@Data
@TableName("furn")
public class Furn {

    //这里我们使用@TableId: 表主键标识
    //当我们在 private Integer id; 上标识了@TableId
    //说明id 对应的就是表的id字段,而且是主键
    //(type = IdType.AUTO) 主键类型是自增长的 该类型请确保数据库设置了 ID自增 否则无效
    //这时即使前端不给id字段的值,即id=null如
    /* 本应该给所有的字段的值,如果不设置@TableId 发送第二组这样的数据 就会失败报错
    , 前端发送第二组的数据,后端通过@RequestBody注解封装后拿到的数据为 furn=Furn(id=null, name=顺平沙发, maker=顺平家具, price=200.2, sales=2, stock=100)
    ,因为是按照默认id=null,封装的,保存到数据库时argument type mismatch
    即
    如果设置了下面的@TableId,前端就可以不给id的值 可以发送第二组这样的数据
    ,后端通过@RequestBody注解封装后拿到的数据还是为 furn=Furn(id=null, name=顺平沙发, maker=顺平家具, price=200.2, sales=2, stock=100)
    但因为@TableId(type = IdType.AUTO)做了处理可以保存成功

         --@TableId(type = IdType.AUTO)
         --private Integer id;
    第一组:
    {
    "id":"100",
    "name":"顺平沙发",
    "maker":"顺平家具",
    "price":"200.2",
    "sales":"2",
    "stock":"100"
    }
    第二组:
    {
    "name":"顺平沙发",
    "maker":"顺平家具",
    "price":"200.2",
    "sales":"2",
    "stock":"100"
    }
     */
    // 也可以正常将前端发过来的json数据完成封装
    @TableId(type = IdType.AUTO)
    private Integer id;

    //如果是对String进行非空校验，我们首选/应该使用@NotEmpty
    @NotEmpty(message = "请输入家居名")
    private String name;
    @NotEmpty(message = "请输入厂商名")
    private String maker;

    //range:范围
    @NotNull(message = "请输入数字")
    @Range(min = 0,message = "价格不能小于0")
    private Double price;
    @NotNull(message = "请输入数字")
    @Range(min = 0,message = "销量不能小于0")
    private Integer sales;
    @NotNull(message = "请输入数字")
    @Range(min = 0,message = "库存不能小于0")
    private Integer stock;



}
