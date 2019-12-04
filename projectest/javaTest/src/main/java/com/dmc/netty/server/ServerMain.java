package com.dmc.netty.server;

import java.net.InetSocketAddress;
import java.util.Scanner;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import sun.nio.ch.sctp.SctpNet;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 7:21 PM 2019/1/28
 * @Modified By:
 */
@Slf4j
public class ServerMain {

    public static void main(String[] args) throws Exception {
        try {
            NioEventLoopGroup workers = new NioEventLoopGroup();
            NioEventLoopGroup boss = new NioEventLoopGroup();
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, workers)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                //打开地址复用
                .option(ChannelOption.SO_REUSEADDR, true)
                //关闭心跳
                .option(ChannelOption.SO_KEEPALIVE, false)
                .childOption(ChannelOption.TCP_NODELAY, true)
                //64k
                .childOption(ChannelOption.SO_SNDBUF, 65535)
                //64k
                .childOption(ChannelOption.SO_RCVBUF, 65535)
                .localAddress(new InetSocketAddress(12345))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder(), new StringEncoder(), new TestHandle());
                    }
                });
            Channel channel = serverBootstrap.bind().sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class TestHandle extends SimpleChannelInboundHandler {

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            log.info("......channelRegistered......");
            ctx.channel().writeAndFlush("whoami\n");
            ctx.fireChannelRegistered();
        }
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
//            log.info(".....processMessage.......{}", msg);
            System.out.println(msg);
            Channel channel = ctx.channel();
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLine()) {
                String t = scanner.nextLine();
                channel.writeAndFlush(t + "\n");
            }
//            log.info("send ok");
        }

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

    static class TestHandle2 extends ChannelOutboundHandlerAdapter {

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            ctx.write(msg, promise);
        }//
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

//        @Override
//        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//            log.info(".....processMessage.......{}", msg);
//            Channel channel = ctx.channel();
//            channel.writeAndFlush("ls");
//            log.info("send ok");
//        }

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
