package com.dmc.rocketmq;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 5:09 PM 2019/4/24
 * @Modified By:
 */
public class readmsg {

    public static void main(String[] args) throws Exception{
//        byte[] b = new byte[100];
//        System.out.println(Arrays.toString(b));
//        readCommitlog(  10);
//        readConsumerQueue(5);
        readIndex();
//        readConsumerQueue(5);
    }

    private static void readCommitlog(int/*读取的消息数量*/ t) throws Exception{
        String file = "/Users/dingmc/store/commitlog/00000000000000000000";
        RandomAccessFile accessFile = new RandomAccessFile(file, "r");

        for (int j = 0; j < t; j++) {
            System.out.println("----------------第" + j + "条消息----------------");
            System.out.println("msgLength:" + accessFile.readInt());
            System.out.println("MESSAGE_MAGIC_CODE:" + accessFile.readInt());
            System.out.println("bodyCRC:" + accessFile.readInt());
            System.out.println("queueId:" + accessFile.readInt());
            System.out.println("flag:" + accessFile.readInt());

            System.out.println("QUEUEOFFSET:" + accessFile.readLong());
            System.out.println("PHYSICALOFFSET:" + accessFile.readLong());

            System.out.println("SYSFLAG:" + accessFile.readInt());
            System.out.println("BORNTIMESTAMP:" + accessFile.readLong());
            byte[] b = new byte[4];
            accessFile.read(b);
            System.out.print("born host:");
            for (int i = 0; i < 4; i++) {
                System.out.print(b[i] & 0xFF);
                if (i < 3) {
                    System.out.print(".");
                }
            }
            System.out.println();
            System.out.println("born port:" + accessFile.readInt());

            System.out.println("STORETIMESTAMP:" + accessFile.readLong());

            accessFile.read(b);
            System.out.print("store host:");
            for (int i = 0; i < 4; i++) {
                System.out.print(b[i] & 0xFF);
                if (i < 3) {
                    System.out.print(".");
                }
            }
            System.out.println();
            System.out.println("store port:" + accessFile.readInt());

            System.out.println("RECONSUMETIMES:" + accessFile.readInt());
            System.out.println("Prepared Transaction Offset：" + accessFile.readLong());
            //bodyLength
            int bodyLength;
            System.out.println("bodyLength:" + (bodyLength = accessFile.readInt()));

            //大于4k会进行压缩
            byte[] b1 = new byte[bodyLength];
            accessFile.read(b1);
            String bodyStr = new String(b1);
            System.out.println("body:" + bodyStr);

            //topiclength
            byte[] b2 = new byte[1];
            accessFile.read(b2);
            int topicLength = byteArrayToInt(b2);
            System.out.println("topic length:" + topicLength);

            //topic
            byte[] b3 = new byte[topicLength];
            accessFile.read(b3);
            System.out.println("topic name:" + new String(b3));

            //properties
            int propertiesLen = accessFile.readShort();
            System.out.println("PROPERTIESLen:" + propertiesLen);
            byte[] b4 = new byte[propertiesLen];
            accessFile.read(b4);
            System.out.println("PROPERTIES:" + new String(b4));
        }
    }

    public static int byteArrayToInt(byte[] b) {
        return b[0] & 0xFF;
    }

    public static int byteArrayToInt2(byte[] b) {
        return b[3] << 24 & 0xFF | b[2] << 16 & 0xFF | b[1] << 8 & 0xff | b[0] & 0xff;
    }

    public static void readIndex() throws Exception{
        String key = "test_topic#keys";
        int absSlotPos = 40 + 4 * (Math.abs(key.hashCode()) % 5000000);
        String file = "/Users/dingmc/store/index/20190304141342419";
        RandomAccessFile accessFile = new RandomAccessFile(file, "r");
        //MappedByteBuffer方式
//        FileChannel channel = accessFile.getChannel();
//        MappedByteBuffer mappedByteBuffer = channel.map(MapMode.READ_ONLY, 0, 20000000);
//        System.out.println(mappedByteBuffer.getInt(absSlotPos));

        System.out.println("keyHash:" + Math.abs(key.hashCode()));
        accessFile.skipBytes(absSlotPos);
        int index = accessFile.readInt();
        while (index != 0) {
            System.out.println("-----------------------");
            System.out.println("slot index:" + index);
            int absIndexPos = 40 + 5000000 * 4 + index * 20;

            RandomAccessFile accessFile2 = new RandomAccessFile(file, "r");
            accessFile2.skipBytes(absIndexPos);

            System.out.println("keyhash:" + accessFile2.readInt());
            System.out.println("commitLogOffset:" + accessFile2.readLong());
            System.out.println("timeDiff(storeTime - indexFileBegintime):" + accessFile2.readInt());
            index = accessFile2.readInt();
            System.out.println("lastSlotIndex:" + index);
        }
    }

    public static void readConsumerQueue(int sum) throws Exception{
        String file = "/Users/dingmc/store/consumequeue/test_topic/1/00000000000000000000";
        RandomAccessFile accessFile = new RandomAccessFile(file, "r");
        for (int i = 0; i < sum; i++) {
            System.out.println("------------------");
            System.out.println("offset:" + accessFile.readLong());
            System.out.println("size:" + accessFile.readInt());
            System.out.println("tagcode:" + accessFile.readLong());
        }
    }
}
