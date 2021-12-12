package com.lucas.xim.core.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 */
@Data
public class User {
    private String _id;
    private String nickname;
    private String password;
    private String mobile;
    private long create_time;
    private String token;
}
