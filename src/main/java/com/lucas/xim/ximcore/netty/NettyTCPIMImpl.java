package com.lucas.xim.ximcore.netty;

import com.lucas.xim.ximcore.bean.IMMsg;
import com.lucas.xim.ximcore.config.IMOptions;
import com.lucas.xim.ximcore.interf.IMInterface;
import com.lucas.xim.ximcore.listener.IMMsgReceivedListener;
import com.lucas.xim.ximcore.nettyimcore.codec.PacketCodecHandler;
import com.lucas.xim.ximcore.nettyimcore.codec.Spliter;
import com.lucas.xim.ximcore.nettyimcore.handler.IMIdleStateHandler;
import com.lucas.xim.ximcore.nettyimcore.server.handler.AuthHandler;
import com.lucas.xim.ximcore.nettyimcore.server.handler.HeartBeatRequestHandler;
import com.lucas.xim.ximcore.nettyimcore.server.handler.IMHandler;
import com.lucas.xim.ximcore.nettyimcore.server.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.util.Date;


/**
 * @Description:基于Netty实现的TCP协议客户端
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/09/16:35 下午
 */
public class NettyTCPIMImpl implements IMInterface {

    private static final Logger logger = LoggerFactory.getLogger(NettyTCPIMImpl.class);
    private ServerBootstrap bootstrap;
    private Channel channel;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private volatile boolean isClosed;
    private boolean initialized;
    private IMOptions mIMSOptions;
    private IMMsgReceivedListener mIMSMsgReceivedListener;

    private NettyTCPIMImpl() {
    }

    public static NettyTCPIMImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final NettyTCPIMImpl INSTANCE = new NettyTCPIMImpl();
    }

    @Override
    public boolean init(IMOptions options, IMMsgReceivedListener msgReceivedListener) {
        if (options == null) {
            initialized = false;
            return false;
        }

        this.mIMSOptions = options;
        this.mIMSMsgReceivedListener = msgReceivedListener;
        initialized = true;
        isClosed = false;
        return true;
    }

    @Override
    public void start() {
        if (!initialized) {
            logger.warn("NettyTCPIMImpl启动失败：初始化失败");
            return;
        }

        try {
            initServerBootstrap();
            ChannelFuture future = bind(bootstrap, mIMSOptions.getPort());
            channel = future.channel();
            if (channel != null && channel.isOpen() && channel.isActive() && channel.isRegistered() && channel.isWritable()) {
                logger.debug(String.format("NettyTCPIMImpl启动成功，ip【%1$s】\tport【%2$d】", Inet4Address.getLocalHost().getHostAddress(), mIMSOptions.getPort()));
            } else {
                logger.debug("NettyTCPIMImpl启动失败");
            }

//            future.awaitUninterruptibly();
//            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            try {
//                bossGroup.shutdownGracefully();
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                bossGroup = null;
//            }
//
//            try {
//                workGroup.shutdownGracefully();
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                workGroup = null;
//            }
        }
    }

    @Override
    public void sendMsg(IMMsg msg) {

    }

    @Override
    public void sendMsg(IMMsg msg, boolean isJoinResendManager) {

    }

    @Override
    public void release() {
        closeChannel();
        closeServerBootstrap();
        isClosed = true;
    }

    /**
     * 初始化ServerBootstrap
     */
    private void initServerBootstrap() {
        try {
            closeServerBootstrap();// 先关闭之前的bootstrap
            // boss线程池用于处理TCP连接，通常服务端开启的都是一个端口，所以线程数指定为1即可
            bossGroup = new NioEventLoopGroup(1);
            // work线程用于处理IO事件，需要多线程处理，不指定线程数，默认就是CPU核心数*2
            workGroup = new NioEventLoopGroup();
            bootstrap = new ServerBootstrap();
            bootstrap
                    .group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    // 设置TCP接收缓冲区大小（字节数）
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    // 服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝。默认值，Windows为200，其他为128
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 设置禁用nagle算法，如果要求高实时性，有数据发送时就马上发送，就将该选项设置为true关闭Nagle算法；如果要减少发送次数减少网络交互，就设置为false等累积一定大小后再发送。默认为false
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        protected void initChannel(NioSocketChannel ch) {
                            ch.pipeline().addLast(new IMIdleStateHandler());
                            ch.pipeline().addLast(new Spliter());
                            ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                            ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                            ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                            ch.pipeline().addLast(AuthHandler.INSTANCE);
                            ch.pipeline().addLast(IMHandler.INSTANCE);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ChannelFuture bind(final ServerBootstrap serverBootstrap, final int port) {
        return serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                logger.debug(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                logger.debug("端口[" + port + "]绑定失败!");
            }
        });
    }

    private void closeServerBootstrap() {
        try {
            if (bootstrap != null) {
                bootstrap.config().group().shutdownGracefully();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bootstrap = null;
        }
    }

    private void closeChannel() {
        try {
            if (channel != null) {
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    channel.eventLoop().shutdownGracefully();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel = null;
        }
    }
}
