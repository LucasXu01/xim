package com.lucas.xim.ximcore.nettyimcore.client.console;

import com.lucas.xim.ximcore.bean.IMMsg;
import com.lucas.xim.ximcore.nettyimcore.protocal.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个用户：");

        String toUserId = scanner.next();
        String message = scanner.next();
        IMMsg imMsg = new IMMsg.Builder()
                .setSender(toUserId)
                .setContent(message)
                .build();
        channel.writeAndFlush(new MessageRequestPacket(imMsg));
    }
}
