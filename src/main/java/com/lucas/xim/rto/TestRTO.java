package com.lucas.xim.rto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TestRTO {
    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "性别不能为空")
    private String sex;
}
