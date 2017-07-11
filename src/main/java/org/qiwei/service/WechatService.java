package org.qiwei.service;

import org.qiwei.vo.*;

import java.util.HashMap;

/**
 * Created by admin on 2017/7/10.
 */
public class WechatService {


    public WeChatRepMsg doHandlerText(String openid, String serviceid, String content) {
        return new WeChatTextMsg(openid, serviceid, content);
    }

    public WeChatRepMsg doHandlerEvent(String openid, String serviceid, String event, String eventKey, HashMap<String, String> requestMap) {
        WeChatRepMsg msg = null;
        switch (event) {
            case "subscribe":
                msg = doSubscribe(openid, serviceid);
                break;
            case "unsubscribe":
                msg = doUnSubscribe(openid, serviceid);
                break;
            case "CLICK":
                msg = doClick(openid, serviceid, eventKey, requestMap);
                break;
            case "SCAN":
                msg = doScan(openid, serviceid, eventKey, requestMap);
                break;
            case "scancode_waitmsg":
                break;
            default:
                msg = doSubscribe(openid, serviceid);
                break;
        }
        return msg;
    }

    private WeChatRepMsg doSubscribe(String openid, String serviceid) {
        ArticleList a = new ArticleList();
        a.addItem(new Article("test", "etst", "3", "https://mp.weixin.qq.com?openid=" + openid));
        return new WeChatNewsMsg(openid, serviceid, 1, a);
    }

    private WeChatRepMsg doUnSubscribe(String openid, String serviceid) {
        return new WeChatTextMsg(openid, serviceid, "你好");
    }

    private WeChatRepMsg doClick(String openid, String serviceid, String eventKey, HashMap<String, String> requestMap) {
        return new WeChatTextMsg(openid, serviceid, "你好");
    }

    private WeChatRepMsg doScan(String openid, String serviceid, String eventKey, HashMap<String, String> requestMap) {
        return new WeChatTextMsg(openid, serviceid, "你好");
    }

}
