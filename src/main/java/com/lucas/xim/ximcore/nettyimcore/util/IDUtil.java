package com.lucas.xim.ximcore.nettyimcore.util;

import java.util.UUID;

public class IDUtil {
    public static String randomId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
