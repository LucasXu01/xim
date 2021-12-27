package com.lucas.xim.ximcore.nettyimcore.protocal.request;

import com.lucas.xim.ximcore.nettyimcore.protocal.Packet;
import lombok.Data;

import static com.lucas.xim.ximcore.nettyimcore.protocal.command.Command.LOGIN_REQUEST;


@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String mobile;

    private String password;

    private String token;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}

