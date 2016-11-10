package com.nvgct.servlet;

import com.nvgct.util.CheckUtil;
import com.nvgct.util.MessageUtil;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by gxgc on 2016/11/7 0007.
 */
public class WeixinServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out=response.getWriter();

        try {
            Map<String,String> map= MessageUtil.xmlToMap(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");

            String message=null;

            if (msgType.equals(MessageUtil.MESSAGE_TEXT)) {
                if (content.equals("1")) {
                    message = MessageUtil.initTextMessage(toUserName, fromUserName, MessageUtil.getMenu1());
                } else if (content.equals("2")) {
                    message = MessageUtil.initTextMessage(toUserName, fromUserName, MessageUtil.getMenu2());
                } else if (content.equals("3")) {
                    message = MessageUtil.initTextMessage(toUserName, fromUserName, MessageUtil.getMenu3());
                } else if (content.equals("0")) {
                    message = MessageUtil.initTextMessage(toUserName, fromUserName, MessageUtil.getMainMenu());
                } else if (content.equals("tw")) {
                    message = MessageUtil.initNewsMessage(toUserName, fromUserName);
                } else if(content.equals("img")){
                    message = MessageUtil.initImageMessage(toUserName,fromUserName);
                } else if(content.equals("music")){
                    message=MessageUtil.initMusicMessage(toUserName,fromUserName);
                } else {
                    message = MessageUtil.initTextMessage(toUserName, fromUserName, "你大爷的不按常理出牌，老实点，我可是知道你在干嘛的！");
                }

            } else if (msgType.equals(MessageUtil.MESSAGE_VOICE)) {
                message = MessageUtil.initTextMessage(toUserName, fromUserName, "您发送的是声音文件，我听不懂你在说什么，泥为什么要说射种话，香菇蓝瘦");
            } else if (msgType.equals(MessageUtil.MESSAGE_SHORTVIDEO)) {
                message = MessageUtil.initTextMessage(toUserName, fromUserName, "您发送的是小视频文件，但是我看不见啊，拍的是什么鬼啊！");
            } else if (msgType.equals(MessageUtil.MESSAGE_EVENT)) {
                String eventType = map.get("Event");
                if (eventType.equals(MessageUtil.MESSAGE_SUBSCRIBE)) {
                    message = MessageUtil.initTextMessage(toUserName, fromUserName, MessageUtil.getMainMenu());
                }else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
                    message=MessageUtil.initTextMessage(toUserName,fromUserName,MessageUtil.getMainMenu());
                }else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
//                    String url=map.get("EventKey");
                    message=MessageUtil.initTextMessage(toUserName,fromUserName,"扫码事件：\n"+map.toString());
                }else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
                    String url=map.get("EventKey");
                    message=MessageUtil.initTextMessage(toUserName,fromUserName,url);
                }
            } else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){
                message=MessageUtil.initTextMessage(toUserName,fromUserName,"地理位置：\n"+map.toString());
            }

//            System.out.println(message);

            out.print(message);

        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }

    }


    /**
     * 验证微信
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws IOException
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String signature=request.getParameter("signature");
        String timestamp=request.getParameter("timestamp");
        String nonce=request.getParameter("nonce");
        String echostr=request.getParameter("echostr");

        PrintWriter out=response.getWriter();
        if(CheckUtil.checkSignature(signature, timestamp, nonce)){
            out.print(echostr);
        }
    }
}
