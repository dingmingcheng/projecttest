package com.dmc.xxljobtest;

import com.alibaba.druid.pool.DruidDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 4:57 PM 2019/12/31
 * @Modified By:
 */
public class asdf {

    private static volatile List<String> connections;

    private static ReentrantLock lock;

    private static Condition empty;

    private static Condition notEmpty;

    public static void main(String[] args) {
        lock = new ReentrantLock();
        empty = lock.newCondition();
        notEmpty = lock.newCondition();
        connections = new ArrayList<>();
        //生产者线程
        new Thread(() -> {
            for (;;) {
                try {
                    lock.lock();
                    if (connections.size() == 10) {
                        empty.await();
                        notEmpty.signal();
                    }
                    connections.add(new String("11234123"));
                    System.out.println("produce successful....." + connections.size());
                    lock.unlock();
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //消费者线程
        new Thread(() -> {
            for (;;) {
                try {
                    lock.lock();
                    if (connections.size() == 0) {
                        empty.signal();
                        notEmpty.await();
                    }
                    connections.remove(connections.size() - 1);
                    System.out.println("consume successful....." + connections.size());
                    lock.unlock();
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
