package com.dmc.DesignPatterns.ChainOfResponsibility;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 下午2:36 2018/2/9
 * @Modifyed By:
 */
public class FilterChain implements Filter {

    private List<Filter> filters;

    private int index;

    /**
     * 0 mains get in,1 mains get out;
     */
    private int flag;

    public FilterChain() {
        filters = new ArrayList<Filter>();
        index = 0;
        flag = 0;
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        if (index == filters.size()) {
            flag = 1;
            index --;
            System.out.println("do service ..........");
            //do service, asyn or syn;
            //...
            //...
            //...
            //...
            doFilter(request, response, filterChain);//get out
            return;
        } else if (index == -1 && flag == 1){
            System.out.println("leave the filterChain...");
            return;
        }
        if (flag == 0) {
            Filter filter = filters.get(index++);
            filter.doFilter(request, response, filterChain);
        } else if (flag == 1){
            Filter filter = filters.get(index--);
            filter.doFilter(request, response, filterChain);
        }
    }
}
