package com.lucas.xim.ximcore.nettyimcore.protocal.response;


import com.lucas.xim.ximcore.nettyimcore.protocal.Packet;

import static com.lucas.xim.ximcore.nettyimcore.protocal.command.Command.HEARTBEAT_RESPONSE;

public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
