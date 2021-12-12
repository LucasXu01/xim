package com.lucas.xim.ximcore.nettyimcore.protocal.request;


import com.lucas.xim.ximcore.nettyimcore.protocal.Packet;

import static com.lucas.xim.ximcore.nettyimcore.protocal.command.Command.HEARTBEAT_REQUEST;

public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
