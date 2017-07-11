package org.qiwei.controller;

import com.google.inject.Inject;
import org.qiwei.service.WechatService;
import org.qiwei.vo.WeChatMsgType;
import org.qiwei.vo.WeChatRepMsg;
import org.qiwei.xml.XMLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by admin on 2017/7/4.
 */

@Path("/wechat")
public class WechatController {

    @Inject
    WechatService wechatService;

    private final static Logger logger = LoggerFactory.getLogger(WechatController.class);
    private static final String token = "xxxxx";

    private static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String checkToken(@QueryParam("signature") String signature, @QueryParam("timestamp") String timestamp, @QueryParam("echostr") String echostr, @QueryParam("nonce") String nonce) {

        logger.info("==============checkToken=========enter=========" + echostr);
        if (checkSignature(token, signature, timestamp, nonce)) {
            logger.info("==========checkToken==========true============" + echostr);
            return echostr;
        }
        logger.info("==============checkToken=========false=========" + echostr);
        return echostr;
    }

    @POST
    public String doHandle(@Context HttpServletRequest req) throws IOException {
        logger.info("================================ok=================");
        HashMap<String, String> requestMap = new HashMap<>();
        try (ServletInputStream inputStream = req.getInputStream();) {
            requestMap.putAll(XMLUtils.XMLToMap(inputStream));
        } catch (Exception e) {
            logger.error("parse xml", e);
            return "error";
        }
        String msgType = requestMap.get("MsgType");
        String openid = requestMap.get("FromUserName");
        String serviceid = requestMap.get("ToUserName");
        String event = requestMap.get("Event");
        String eventKey = requestMap.get("EventKey");
        String content = requestMap.get("Content");

        WeChatRepMsg textMsg = null;
        if (WeChatMsgType.EVENT.getValue().equals(msgType)) {
            textMsg = wechatService.doHandlerEvent(openid, serviceid, event, eventKey, requestMap);
        } else if (WeChatMsgType.TEXT.getValue().equals(msgType)) {
            textMsg = wechatService.doHandlerText(openid, serviceid, content);
        } else {
            textMsg = wechatService.doHandlerText(openid, serviceid, content);
        }
        logger.info(XMLUtils.ObjectToXML(textMsg));
        return XMLUtils.ObjectToXML(textMsg);
    }


    public static boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        String[] arr = new String[]{token, timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        String tmpStr = null;

        // 将三个参数字符串拼接成一个字符串进行sha1加密
        byte[] digest = md.digest(content.toString().getBytes());
        tmpStr = byteToStr(digest);
        logger.info("=================" + tmpStr + "===========================");
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }


    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }
}
