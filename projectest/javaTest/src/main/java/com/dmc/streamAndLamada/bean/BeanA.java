package com.dmc.streamAndLamada.bean;


/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 下午8:39 2018/5/29
 * @Modified By:
 */
public class BeanA {
    private Integer num;
    private String name;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BeanA(Integer num, String name) {
        this.num = num;
        this.name = name;
    }

}
