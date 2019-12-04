package com.dmc.DesignPatterns.ChainOfResponsibility;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 下午2:35 2018/2/9
 * @Modifyed By:
 */
public class Request<T> {
    T t;

    public Request(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
