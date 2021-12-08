package com.lucas.xim.core.system;

import lombok.Data;

/**
 */
@Data
public class User {
    private int _id;
    private String nickname;
    private String password;
    private long create_time;
}
