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
 * <CreateTime>12345678</CreateTime>
 * <MsgType><![CDATA[news]]></MsgType>
 * <ArticleCount>2</ArticleCount>
 * <Articles>
 * <item>
 * <Title><![CDATA[title1]]></Title>
 * <Description><![CDATA[description1]]></Description>
 * <PicUrl><![CDATA[picurl]]></PicUrl>
 * <Url><![CDATA[url]]></Url>
 * </item>
 * <item>
 * <Title><![CDATA[title]]></Title>
 * <Description><![CDATA[description]]></Description>
 * <PicUrl><![CDATA[picurl]]></PicUrl>
 * <Url><![CDATA[url]]></Url>
 * </item>
 * </Articles>
 * </xml>
 */

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeChatNewsMsg implements WeChatRepMsg {
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String ToUserName;
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    private Integer ArticleCount;
    private ArticleList Articles;

    public WeChatNewsMsg() {
    }

    public WeChatNewsMsg(String toUserName, String fromUserName, Integer articleCount, ArticleList articles) {
        this.ToUserName = toUserName;
        this.FromUserName = fromUserName;
        this.CreateTime = System.currentTimeMillis();
        this.MsgType = "news";
        this.ArticleCount = articleCount;
        this.Articles = articles;
    }
}
