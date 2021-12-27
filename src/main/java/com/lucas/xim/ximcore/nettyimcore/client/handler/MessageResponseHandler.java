package com.lucas.xim.ximcore.nettyimcore.client.handler;

import com.lucas.xim.ximcore.nettyimcore.protocal.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {
        String fromUserId = messageResponsePacket.getImMsg().getSender();
        String fromUserName = messageResponsePacket.getImMsg().getSender();
        System.out.println(fromUserId + ":" + fromUserName + " -> " + messageResponsePacket
                .getImMsg().getSender());
    }
}
