package com.hsp.exer;

/**
 * @author yangda
 * @description:
 * @create 2022-12-13-11:44
 */
public class PrivateTest {
    public String publicField = "publicField";
    String defaultField = "defaultField";
    private String privateField = "helloKitty";

    public String getName() {
        return privateField;
    }
}
