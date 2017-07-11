package org.qiwei.vo;


import org.qiwei.xml.AdaptorCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by admin on 2017/7/9.
 * <p>
 * <p>
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>1348831860</CreateTime>
 * <MsgType><![CDATA[text]]></MsgType>
 * <Content><![CDATA[this is a test]]></Content>
 * <MsgId>1234567890123456</MsgId>
 * </xml>
 */

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeChatTextMsg implements WeChatRepMsg {
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String ToUserName;
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String Content;

    public WeChatTextMsg() {
    }

    public WeChatTextMsg(String toUserName, String fromUserName, String content) {
        this.ToUserName = toUserName;
        this.FromUserName = fromUserName;
        this.CreateTime = System.currentTimeMillis();
        this.MsgType = "text";
        this.Content = content;
    }
}
