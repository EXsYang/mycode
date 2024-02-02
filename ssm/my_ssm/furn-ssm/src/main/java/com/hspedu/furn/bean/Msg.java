package com.hspedu.furn.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangda
 * @create 2023-11-14-13:11
 * @description: 用来返回 json 的数据的通用类
 * Msg: 后端程序返回给前端的json数据的Msg对象=> 本质就是数据规则
 * 约定好的 协议
 */
public class Msg {

    // 状态码 200-成功 400-失败
    private int code;
    // 信息-说明
    private String msg;

    // 返回给客户端/浏览器的数据-Map集合
    private Map<String, Object> extend =
            new HashMap<>();

    public Msg() {
    }

    public Msg(int code, String msg, Map<String, Object> extend) {
        this.code = code;
        this.msg = msg;
        this.extend = extend;
    }

    // 编写几个常用的方法-封装好Msg
    // 返回success对应的 mag
    public static Msg success() {

        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMsg("success");
        return msg;

    }
    // 返回 fail 对应的 mag
    public static Msg fail() {

        Msg msg = new Msg();
        msg.setCode(400);
        msg.setMsg("fail");

        return msg;

    }

    // 编写几个常用的方法-封装好Msg
    // 给返回的msg设置数据
    public Msg add(String key, Object value) {

        extend.put(key, value);

        return this;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", extend=" + extend +
                '}';
    }
}
