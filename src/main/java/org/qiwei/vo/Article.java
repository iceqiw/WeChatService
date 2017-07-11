package org.qiwei.vo;

import org.qiwei.xml.AdaptorCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by admin on 2017/7/10.
 * <item>
 * <Title><![CDATA[title1]]></Title>
 * <Description><![CDATA[description1]]></Description>
 * <PicUrl><![CDATA[picurl]]></PicUrl>
 * <Url><![CDATA[url]]></Url>
 * </item>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Article {
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String Title;
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String Description;
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String PicUrl;
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String Url;

    public Article() {
    }

    public Article(String title, String description, String picUrl, String url) {
        this.Title = title;
        this.Description = description;
        this.PicUrl = picUrl;
        this.Url = url;
    }
}
