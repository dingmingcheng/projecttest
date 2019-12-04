package com.dmc.DesignPatterns.ChainOfResponsibility;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 下午2:35 2018/2/9
 * @Modifyed By:
 */
public interface Filter {
    void doFilter(Request request, Response response, FilterChain filterChain);
}
