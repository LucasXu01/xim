package com.lucas.xim.ximcore.interf;


import com.lucas.xim.ximcore.bean.IMMsg;
import com.lucas.xim.ximcore.config.IMOptions;
import com.lucas.xim.ximcore.listener.IMMsgReceivedListener;

/**
 * @Description:XIMInterface抽象接口,不同的服务端协议实现此接口即可，例：
 * {@link com.freddy.kulachat.ims.netty.tcp.NettyTCPIMS}
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/09/15:45 下午
 */
public interface IMInterface {

    /**
     * 初始化
     *
     * @param options
     * @return
     */
    boolean init(IMOptions options, IMMsgReceivedListener msgReceivedListener);

    /**
     * 启动IMS
     */
    void start();

    /**
     * 发送消息
     *
     * @param msg
     */
    void sendMsg(IMMsg msg);

    /**
     * 发送消息
     * 重载
     *
     * @param msg
     * @param isJoinResendManager 是否加入消息重发管理器
     */
    void sendMsg(IMMsg msg, boolean isJoinResendManager);

    /**
     * 释放资源
     */
    void release();
}
