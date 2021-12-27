package com.lucas.xim.ximcore.nettyimcore.protocal.request;

import com.lucas.xim.ximcore.nettyimcore.protocal.Packet;
import lombok.Data;

import java.util.List;

import static com.lucas.xim.ximcore.nettyimcore.protocal.command.Command.CREATE_GROUP_REQUEST;
import static com.lucas.xim.ximcore.nettyimcore.protocal.command.Command.GET_ONLINE_MEMBERS_REQUEST;


@Data
public class GetOnlineMembersRequestPacket extends Packet {

    @Override
    public Byte getCommand() {

        return GET_ONLINE_MEMBERS_REQUEST;
    }
}
