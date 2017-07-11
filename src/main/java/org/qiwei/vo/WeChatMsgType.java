package org.qiwei.vo;

/**
 * Created by admin on 2017/7/10.
 */
public enum WeChatMsgType {
    TEXT("text"),
    EVENT("event");
    private final String value;

    WeChatMsgType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
