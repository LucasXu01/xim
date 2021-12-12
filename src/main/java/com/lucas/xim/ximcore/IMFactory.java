package com.lucas.xim.ximcore;


import com.lucas.xim.ximcore.config.CommunicationProtocol;
import com.lucas.xim.ximcore.config.ImplementationMode;
import com.lucas.xim.ximcore.interf.IMInterface;
import com.lucas.xim.ximcore.netty.NettyTCPIMImpl;

/**
 * @Description: 分层，可切换IM底层实现逻辑，暂只使用netty-tcp
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/09/16:36 下午
 */
public class IMFactory {

    public static IMInterface getIMS(ImplementationMode implementationMode, CommunicationProtocol communicationProtocol) {
        switch (implementationMode) {
            case Netty:
            default:
                switch (communicationProtocol) {
                    case TCP:
                        return NettyTCPIMImpl.getInstance();
                    case WebSocket:
                    default:
                        break;
                }
                break;
        }

        return null;
    }
}
