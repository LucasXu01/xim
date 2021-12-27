package com.lucas.xim.ximcore.nettyimcore.protocal.response;

import com.lucas.xim.ximcore.nettyimcore.protocal.Packet;
import lombok.Data;

import static com.lucas.xim.ximcore.nettyimcore.protocal.command.Command.LOGIN_RESPONSE;


@Data
public class LoginResponsePacket extends Packet {
    private String userId;

    private String userName;

    private String mobile;

    private boolean success;

    private String reason;

    private String token;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
