package com.lucas.xim.ximcore.nettyimcore.protocal.response;

import com.lucas.xim.ximcore.bean.IMMsg;
import com.lucas.xim.ximcore.nettyimcore.protocal.Packet;
import lombok.Data;

import static com.lucas.xim.ximcore.nettyimcore.protocal.command.Command.MESSAGE_RESPONSE;


@Data
public class MessageResponsePacket extends Packet {

    private IMMsg imMsg;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
