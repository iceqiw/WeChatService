package org.qiwei.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/10.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ArticleList {
    List<Article> item;

    public ArticleList() {
        item = new ArrayList<>();
    }

    public void addItem(Article article) {
        this.item.add(article);
    }

}
