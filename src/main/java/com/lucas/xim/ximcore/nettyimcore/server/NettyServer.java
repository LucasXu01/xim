package com.lucas.xim.ximcore.nettyimcore.server;

import com.lucas.xim.ximcore.nettyimcore.codec.PacketCodecHandler;
import com.lucas.xim.ximcore.nettyimcore.codec.Spliter;
import com.lucas.xim.ximcore.nettyimcore.handler.IMIdleStateHandler;
import com.lucas.xim.ximcore.nettyimcore.server.handler.AuthHandler;
import com.lucas.xim.ximcore.nettyimcore.server.handler.HeartBeatRequestHandler;
import com.lucas.xim.ximcore.nettyimcore.server.handler.IMHandler;
import com.lucas.xim.ximcore.nettyimcore.server.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

public class NettyServer {

    private static final int PORT = 8000;

    public static void main(String[] args) {

    }

    public void startServer() {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
//                        ch.pipeline().addLast(new ServerHandler());
//                        // inBound，处理读数据的逻辑链
//                        ch.pipeline().addLast(new InBoundHandlerA());
//                        ch.pipeline().addLast(new InBoundHandlerB());
//                        ch.pipeline().addLast(new InBoundHandlerC());
//
//                        // outBound，处理写数据的逻辑链
//                        ch.pipeline().addLast(new OutBoundHandlerA());
//                        ch.pipeline().addLast(new OutBoundHandlerB());
//                        ch.pipeline().addLast(new OutBoundHandlerC());

                        // 单聊
//                        ch.pipeline().addLast(new Spliter());
//                        ch.pipeline().addLast(new PacketDecoder());
//                        ch.pipeline().addLast(new LoginRequestHandler());
//                        ch.pipeline().addLast(new AuthHandler());
//                        ch.pipeline().addLast(new MessageRequestHandler());
//                        ch.pipeline().addLast(new PacketEncoder());

                        // 群聊
//                        ch.pipeline().addLast(new Spliter());
//                        ch.pipeline().addLast(new PacketDecoder());
//                        ch.pipeline().addLast(new LoginRequestHandler());
//                        ch.pipeline().addLast(new AuthHandler());
//                        ch.pipeline().addLast(new MessageRequestHandler());
//                        ch.pipeline().addLast(new CreateGroupRequestHandler());
//                        ch.pipeline().addLast(new LogoutRequestHandler());
//                        ch.pipeline().addLast(new PacketEncoder());

//                        // 群聊 拉人
//                        ch.pipeline().addLast(new Spliter());
//                        ch.pipeline().addLast(new PacketDecoder());
//                        // 登录请求处理器
//                        ch.pipeline().addLast(new LoginRequestHandler());
//                        ch.pipeline().addLast(new AuthHandler());
//                        // 单聊消息请求处理器
//                        ch.pipeline().addLast(new MessageRequestHandler());
//                        // 创建群请求处理器
//                        ch.pipeline().addLast(new CreateGroupRequestHandler());
//                        // 加群请求处理器
//                        ch.pipeline().addLast(new JoinGroupRequestHandler());
//                        // 退群请求处理器
//                        ch.pipeline().addLast(new QuitGroupRequestHandler());
//                        // 获取群成员请求处理器
//                        ch.pipeline().addLast(new ListGroupMembersRequestHandler());
//                        // 登出请求处理器
//                        ch.pipeline().addLast(new LogoutRequestHandler());
//                        ch.pipeline().addLast(new PacketEncoder());


                        // 空闲检测
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        ch.pipeline().addLast(IMHandler.INSTANCE);
                    }
                });


        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}

