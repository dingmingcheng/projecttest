package com.dmc.netty.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 7:19 PM 2019/1/28
 * @Modified By:
 */
@Slf4j
public class ClientMain {

    public static void main(String[] args) throws Exception {
        NioEventLoopGroup workers = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(workers)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.TCP_NODELAY, true)
            .option(ChannelOption.SO_KEEPALIVE, false)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
            .option(ChannelOption.SO_SNDBUF, 65535)
            .option(ChannelOption.SO_RCVBUF, 65535)
            .handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //编码通道处理
                    ch.pipeline().addLast(new StringDecoder());
                    //转码通道处理
                    ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new TestHandle());
                }
            });

        Channel channel = bootstrap.connect("127.0.0.1", 12345).sync().channel();
        while (true) {
            //向服务端发送内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String content = reader.readLine();
            if (StringUtils.isNotEmpty(content)) {
                if (StringUtils.equalsIgnoreCase(content, "q")) {
                    System.exit(1);
                }
                channel.writeAndFlush(content).addListener(f -> {
                    if (f.isSuccess()) {
                        System.out.println("success");
                    } else {
                        System.out.println("success");
                    }
                });
            }
        }
    }

    static class TestHandle extends SimpleChannelInboundHandler {

//        @Override
//        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//            log.info("......channelRegistered......");
//            ctx.fireChannelRegistered();
//        }
//
//        @Override
//        public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//            log.info("......channelUnregistered......");
//            ctx.fireChannelUnregistered();
//        }
//
//        @Override
//        public void channelActive(ChannelHandlerContext ctx) throws Exception {
//            log.info("......channelActive......");
//            ctx.fireChannelActive();
//        }
//
//        @Override
//        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//            log.info("......channelInactive......");
//            ctx.fireChannelInactive();
//        }
//
//        @Override
//        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//            log.info("......channelRead......");
//            ctx.fireChannelRead(msg);
//        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info(".......channelRead0......{}", msg);
        }
//
//        @Override
//        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//            log.info("......channelReadComplete......");
//            ctx.fireChannelReadComplete();
//        }
//
//        @Override
//        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//            log.info("......userEventTriggered......");
//            ctx.fireUserEventTriggered(evt);
//        }
//
//        @Override
//        public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
//            log.info("......channelWritabilityChanged......");
//            ctx.fireChannelWritabilityChanged();
//        }
//
//        @Override
//        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//            log.info("......handlerAdded......");
//        }
//
//        @Override
//        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//            log.info("......handlerRemoved......");
//        }
//
//        @Override
//        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//            log.info("......exceptionCaught......");
//            ctx.fireExceptionCaught(cause);
//        }
    }
}
