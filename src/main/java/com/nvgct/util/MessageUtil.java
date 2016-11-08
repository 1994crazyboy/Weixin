package com.nvgct.util;

import com.nvgct.po.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gxgc on 2016/11/7 0007.
 */
public class MessageUtil {
    public static final String MESSAGE_TEXT="text";
    public static final String MESSAGE_IMAGE="image";
    public static final String MESSAGE_VOICE="voice";
    public static final String MESSAGE_VIDEO="video";
    public static final String MESSAGE_SHORTVIDEO="shortvideo";
    public static final String MESSAGE_LOCATION="location";
    public static final String MESSAGE_LINK="link";
    public static final String MESSAGE_EVENT="event";


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




}
