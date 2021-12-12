package com.lucas.xim.ximcore.nettyimcore.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.lucas.xim.ximcore.nettyimcore.serialize.Serializer;
import com.lucas.xim.ximcore.nettyimcore.serialize.SerializerAlgorithm;

public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}