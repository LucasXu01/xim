package com.lucas.xim.ximcore.bean;

/**
 * @Description:
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/09/15:59 下午
 */
public class IMMsg {

    public static int MESSAGE_TYPE_C2C = 0x1;
    public static int MESSAGE_TYPE_GROUP = 0x2;

    private String msgId;// 消息唯一标识
    private int msgType; // 消息类型
    private String sender;// 发送者标识
    private String receiver;// 接收者标识
    private long timestamp;// 消息发送时间，单位：毫秒
    private int report;// 消息发送状态报告
    private String content;// 消息内容
    private int contentType;// 消息内容类型
    private String data; // 扩展字段，以key/value形式存储的json字符串

    public IMMsg(Builder builder) {
        if (builder == null) {
            return;
        }

        this.msgId = builder.msgId;
        this.msgType = builder.msgType;
        this.sender = builder.sender;
        this.receiver = builder.receiver;
        this.timestamp = builder.timestamp;
        this.report = builder.report;
        this.content = builder.content;
        this.contentType = builder.contentType;
        this.data = builder.data;
    }

    public String getMsgId() {
        return msgId;
    }

    public int getMsgType() {
        return msgType;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getReport() {
        return report;
    }

    public String getContent() {
        return content;
    }

    public int getContentType() {
        return contentType;
    }

    public String getData() {
        return data;
    }

    public static class Builder {
        private String msgId;// 消息唯一标识
        private int msgType; // 消息类型
        private String sender;// 发送者标识
        private String receiver;// 接收者标识
        private long timestamp;// 消息发送时间，单位：毫秒
        private int report;// 消息发送状态报告
        private String content;// 消息内容
        private int contentType;// 消息内容类型
        private String data; // 扩展字段，以key/value形式存储的json字符串

        public Builder() {
        }

        public Builder setMsgType(int msgType) {
            this.msgType = msgType;
            return this;
        }

        public Builder setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder setReceiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        public Builder setTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setReport(int report) {
            this.report = report;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setContentType(int contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder setData(String data) {
            this.data = data;
            return this;
        }

        public IMMsg build() {
            return new IMMsg(this);
        }
    }
}