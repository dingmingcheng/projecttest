package com.dmc;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 11:23 AM 2019/5/20
 * @Modified By:
 */
public class asdf {

    public static void main(String[] args) {
        a1 t = new a2();
        t.main1();
    }

    static abstract class a1 {
        public void main1() {
            test();
        }

        protected void test() {
            System.out.println("Adsfasf");
        }
    }

    static class a2 extends a1 {
        @Override
        protected void test() {
            System.out.println("asasfaf");
        }
    }
}
