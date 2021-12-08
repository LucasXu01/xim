package com.lucas.xim.rqto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 */
@Data
public class LoginRQTO {

    @NotEmpty(message = "昵称不能为空")
    @Length(max = 12,message = "昵称最大长度12")
    private String nickname;

    @NotEmpty(message = "密码不能为空")
    @Length(max = 20,message = "密码最大长度20")
    private String password;

}
