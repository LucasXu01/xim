package com.lucas.xim.ximcore.nettyimcore.protocal.request;

import com.lucas.xim.ximcore.bean.IMMsg;
import com.lucas.xim.ximcore.nettyimcore.protocal.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.lucas.xim.ximcore.nettyimcore.protocal.command.Command.MESSAGE_REQUEST;


@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    private IMMsg imMsg;

    public MessageRequestPacket(IMMsg imMsg) {
        this.imMsg = imMsg;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
