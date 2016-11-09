package com.nvgct.util;

import com.nvgct.po.*;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by gxgc on 2016/11/7 0007.
 */
public class MessageUtil {
    public static final String MESSAGE_TEXT="text";
    public static final String MESSAGE_NEWS = "news";
    public static final String MESSAGE_IMAGE="image";
    public static final String MESSAGE_VOICE="voice";
    public static final String MESSAGE_VIDEO="video";
    public static final String MESSAGE_SHORTVIDEO="shortvideo";
    public static final String MESSAGE_LOCATION="location";
    public static final String MESSAGE_LINK="link";
    public static final String MESSAGE_EVENT="event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";


    /***
     * xml消息转换为map类型
     * @param request
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request) throws DocumentException, IOException {
        Map<String,String> map=new HashMap<String, String>();
        SAXReader reader=new SAXReader();

        InputStream ins=request.getInputStream();
        Document doc=reader.read(ins);

        Element root=doc.getRootElement();

        List<Element> list=root.elements();
        for (Element e:list){
            map.put(e.getName(),e.getText());
        }
        ins.close();
        return map;
    }

    /***
     * 将消息转换为xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        XStream xStream=new XStream();
        xStream.alias("xml",textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    /**
     * 将NewsMessage转换为xml类型的信息
     *
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", newsMessage.getClass());
        xStream.alias("item", new News().getClass());
        return xStream.toXML(newsMessage);
    }

    /**
     * 将ImageMessage转换为xml类型的信息
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", imageMessage.getClass());
        return xStream.toXML(imageMessage);
    }

    /**
     * 获取主菜单
     *
     * @return
     */
    public static String getMainMenu() {
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎使用出行天地图微信服务号（回复以下数字获得相应菜单）：\n\n");
        sb.append("1.查看地图。\n");
        sb.append("2.查看当前位置。\n");
        sb.append("3.查看帮助。\n");
        sb.append("tw.查看图文消息。\n");
        sb.append("回复0，返回主菜单！");

        return sb.toString();
    }

    public static String getMenu1() {
        StringBuffer sb = new StringBuffer();
        sb.append("菜单1：获取当前地图了哦！！！");
        return sb.toString();
    }

    public static String getMenu2() {
        StringBuffer sb = new StringBuffer();
        sb.append("菜单2：您当前的位置在中国的。");
        return sb.toString();
    }

    public static String getMenu3() {
        StringBuffer sb = new StringBuffer();
        sb.append("菜单3：这个公众号很好玩的哦！");
        return sb.toString();
    }

    /**
     * 初始化要发送的消息
     *
     * @param toUserName
     * @param fromUserName
     * @param message
     * @return
     */
    public static String initTextMessage(String toUserName, String fromUserName, String message) {
        TextMessage textMessage = new TextMessage();

        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType("text");
        textMessage.setContent("您发送的内容是：" + message);

        return MessageUtil.textMessageToXml(textMessage);
    }

    /**
     * 初始化图文消息成xml格式
     *
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initNewsMessage(String toUserName, String fromUserName) {
        String message = null;

        NewsMessage newsMessage = new NewsMessage();
        List<News> newses = new ArrayList<News>();
        News news = new News();

        news.setTitle("天地图介绍");
        news.setDescription("天地图是我国测绘地理信息局......（此处省略一万字）。");
        news.setPicUrl("http://nvgct.com/Weixin/image/img01.jpg");
        news.setUrl("http://www.hyn123.cn");

        newses.add(news);

        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setArticles(newses);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticleCount(newses.size());

        message = newsMessageToXml(newsMessage);

        return message;
    }

    /**
     * 初始化图片消息
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initImageMessage(String toUserName, String fromUserName){
        String message=null;

        ImageMessage imageMessage=new ImageMessage();
        Image image=new Image();
        image.setMediaId("SwEny8aMl0j-BO45sEu-k5hfHOkuCIZRbvxjDd-nMvNxbxPbQ6v4AApu83F4cbu3");
        imageMessage.setImage(image);
        imageMessage.setCreateTime(new Date().getTime());
        imageMessage.setFromUserName(toUserName);
        imageMessage.setMsgType(MessageUtil.MESSAGE_IMAGE);
        imageMessage.setToUserName(fromUserName);

        message=imageMessageToXml(imageMessage);
        return message;
    }





}
