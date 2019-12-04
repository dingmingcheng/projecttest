package com.dmc.common;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author: dingmingcheng
 * @Description
 * @Date: Created in 下午4:46 2018/1/11
 * @Modifyed By:
 */
public class AsysWriter {
    public static void doWrite(SocketChannel channel, String response) throws IOException {
        byte[] bytes = response.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        channel.write(writeBuffer);
    }
}
