package com.dmc.DesignPatterns.ChainOfResponsibility;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 下午2:37 2018/2/9
 * @Modifyed By:
 */
public class StartApplication {

    public static void main(String[] args) {
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new Filter1());
        filterChain.addFilter(new Filter2());
        filterChain.addFilter(new Filter3());

        Request<String> request = new Request<String>("test request");
        Response<String> response = new Response<String>("test response");
        filterChain.doFilter(request, response, filterChain);
    }
}
