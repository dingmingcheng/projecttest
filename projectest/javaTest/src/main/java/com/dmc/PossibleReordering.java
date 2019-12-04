package com.dmc;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 下午6:16 2018/2/14
 * @Modifyed By:
 */
public class PossibleReordering {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static boolean test() throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            public void run() {
                a = 1;
                x = b;
            }
        });

        Thread other = new Thread(new Runnable() {
            public void run() {
                b = 1;
                y = a;
            }
        });
        one.start();
        other.start();
        one.join();
        other.join();
        System.out.println("(" + x + "," + y + ")");
        if (x == 0 && y == 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        int time = 0;
        while (test()) {
            System.out.println(time++);
            x = 0;
            y = 0;
            a = 0;
            b = 0;
        }
    }

}
