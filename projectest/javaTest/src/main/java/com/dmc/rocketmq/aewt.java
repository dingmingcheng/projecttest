package com.dmc.rocketmq;

import java.nio.ByteBuffer;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 7:44 PM 2019/6/16
 * @Modified By:
 */
public class aewt {

    public static void main(String[] args) {
        ByteBuffer byteBufferz = ByteBuffer.allocate(100);
        byte[] a = new byte[10];
        for (int i = 0;i < 10; i++) {
            a[i] = (byte) i;
        }

        byte[] array = byteBufferz.array();

        //byteBufferz的position和limit根本就没有改变过！！！！！
        ByteBuffer byteBuffer = byteBufferz.slice();
        byteBuffer.position(0);
        byteBuffer.put(a, 0, 10);

        ByteBuffer byteBuffer1 = byteBufferz.slice();
        byteBuffer1.position(10);
        byteBuffer1.put(a, 0, 10);
//        System.out.println(byteBuffer.limit());
//        System.out.println(byteBuffer1.limit());
//        System.out.println(byteBuffer.position());
//        System.out.println(byteBuffer1.position());
//        System.out.println(byteBuffer.capacity());
//        System.out.println(byteBuffer1.capacity());
//        byteBuffer1.position(0);
//        byteBuffer1.limit(10);
//        while (byteBuffer1.hasRemaining()) {
//            System.out.println(byteBuffer1.get());
//        }

//        byte[] t = new byte[1];
//        t[0] = 14;
//        byteBuffer1.put(t,0, 1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
