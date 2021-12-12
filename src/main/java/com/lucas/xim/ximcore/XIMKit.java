package com.lucas.xim.ximcore;

import com.lucas.xim.ximcore.config.CommunicationProtocol;
import com.lucas.xim.ximcore.config.IMOptions;
import com.lucas.xim.ximcore.config.ImplementationMode;
import com.lucas.xim.ximcore.config.TransportProtocol;
import com.lucas.xim.ximcore.interf.IMInterface;
import com.lucas.xim.ximcore.listener.IMMsgReceivedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/09/15:35 下午
 */
public class XIMKit {

    private static final Logger logger = LoggerFactory.getLogger(XIMKit.class);
    private IMInterface ims;

    private XIMKit() {
    }

    public static XIMKit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final XIMKit INSTANCE = new XIMKit();
    }

    public boolean init(IMOptions options, IMMsgReceivedListener msgReceivedListener) {
        logger.debug("XIMKit初始化开始");
        if(options == null) {
            logger.warn("XIMKit初始化失败：IMSOptions 为 null");
            return false;
        }

        ImplementationMode implementationMode = options.getImplementationMode();
        if (implementationMode == null) {
            logger.warn("XIMKit初始化失败：ImplementationMode 为 null");
            return false;
        }

        CommunicationProtocol communicationProtocol = options.getCommunicationProtocol();
        if (communicationProtocol == null) {
            logger.warn("XIMKit初始化失败：CommunicationProtocol 为 null");
            return false;
        }

        TransportProtocol transportProtocol = options.getTransportProtocol();
        if(transportProtocol == null) {
            logger.warn("XIMKit初始化失败：TransportProtocol 为 null");
            return false;
        }

        ims = IMFactory.getIMS(implementationMode, communicationProtocol);
        if (ims == null) {
            logger.warn("XIMKit初始化失败：ims 为 null");
            return false;
        }

        boolean initialized = ims.init(options, msgReceivedListener);
        if (!initialized) {
            logger.warn("XIMKit初始化失败：请查看 " + ims.getClass().getSimpleName() + " 相关的日志");
            return false;
        }

        logger.warn("XIMKit初始化完成\nims = " + ims.getClass().getSimpleName() + "\noptions = " + options);
        return true;
    }

    public void start() {
        if(ims == null) {
            logger.warn("XIMKit启动失败");
            return;
        }

        ims.start();
    }
}
