package com.lucas.xim.ximcore.nettyimcore.protocal.response;

import com.lucas.xim.ximcore.nettyimcore.protocal.Packet;
import lombok.Data;

import static com.lucas.xim.ximcore.nettyimcore.protocal.command.Command.QUIT_GROUP_RESPONSE;


@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_RESPONSE;
    }
}
