package com.lucas.xim;

import com.lucas.xim.ximcore.XIMKit;
import com.lucas.xim.ximcore.config.CommunicationProtocol;
import com.lucas.xim.ximcore.config.IMOptions;
import com.lucas.xim.ximcore.config.ImplementationMode;
import com.lucas.xim.ximcore.config.TransportProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XimApplication {
    private static final Logger logger = LoggerFactory.getLogger(XimApplication.class);

    public static void main(String[] args) {
        logger.debug("Xim server starting...");
        SpringApplication.run(XimApplication.class, args);
        startXIM();
        logger.debug("Xim server started.");
    }


    private static void startXIM() {
        IMOptions options = new IMOptions.Builder()
                .setImplementationMode(ImplementationMode.Netty)
                .setCommunicationProtocol(CommunicationProtocol.TCP)
                .setTransportProtocol(TransportProtocol.Json)
                .build();
        boolean initialized = XIMKit.getInstance().init(options, null);
        if(!initialized) {
            logger.warn("XIMKit启动失败，请查看XIMKit相关日志");
            return;
        }
        XIMKit.getInstance().start();
    }

}
