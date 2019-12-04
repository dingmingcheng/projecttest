package com.dmc.DesignPatterns.ChainOfResponsibility;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 下午2:45 2018/2/9
 * @Modifyed By:
 */
public class Filter3 implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        System.out.println("get into filter3....");
        filterChain.doFilter(request, response, filterChain);
    }
}
