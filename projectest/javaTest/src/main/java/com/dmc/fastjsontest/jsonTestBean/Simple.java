package com.dmc.fastjsontest.jsonTestBean;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 上午10:56 2018/5/22
 * @Modified By:
 */
public class Simple implements Serializable {
    private String name;
    private String sex;
    private Integer age;

    public Simple() {
        try {
            Runtime.getRuntime().exec("pwd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Simple(String name, String sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Simple{" +
            "name='" + name + '\'' +
            ", sex='" + sex + '\'' +
            ", age=" + age +
            '}';
    }

}
