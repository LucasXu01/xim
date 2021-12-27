package com.lucas.xim.ximcore.nettyimcore.session;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Session {
    // 用户唯一性标识
    private String userId;
    private String mobile;
    private String UserName;

    public Session(String userId, String mobile) {
        this.userId = userId;
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return userId + ":" + mobile;
    }


}
