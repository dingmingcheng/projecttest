package com.dmc.service;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 上午9:52 2017/12/29
 * @Modifyed By:
 */
public interface transferMoneyService {

    String none(Long id, Integer money);

    Boolean optimistic(Long id, Integer money);

    Boolean redis(Long id, Integer money);

    Boolean index(Long id, Integer money);
}
