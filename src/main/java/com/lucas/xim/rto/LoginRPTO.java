package com.lucas.xim.rto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginRPTO {
    private long user_id;
    private String nickname;
}