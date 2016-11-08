package com.nvgct.servlet;

import com.nvgct.po.TextMessage;
import com.nvgct.util.CheckUtil;
import com.nvgct.util.MessageUtil;
import javafx.scene.chart.PieChart;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
            String ToUserName=map.get("ToUserName");
            String FromUserName=map.get("FromUserName");
            String MsgType=map.get("MsgType");
            String Content=map.get("Content");

            String message=null;

            if(MsgType.equals(MessageUtil.MESSAGE_TEXT)){
                TextMessage textMessage=new TextMessage();

                textMessage.setToUserName(FromUserName);
                textMessage.setFromUserName(ToUserName);
                textMessage.setCreateTime(new Date().getTime());
                textMessage.setMsgType("text");
                textMessage.setContent("您发送的内容是："+Content);

                message=MessageUtil.textMessageToXml(textMessage);

                System.out.println(message);
            }else if(MsgType.equals(MessageUtil.MESSAGE_VOICE)){

            }else if(MsgType.equals(MessageUtil.MESSAGE_SHORTVIDEO)){

            }else if(MsgType.equals(MessageUtil.MESSAGE_EVENT)){


            }

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
