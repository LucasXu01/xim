package com.lucas.xim.ximcore.nettyimcore.attribute;

import com.lucas.xim.ximcore.nettyimcore.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}

