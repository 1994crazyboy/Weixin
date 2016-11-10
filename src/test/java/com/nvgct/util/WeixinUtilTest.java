package com.nvgct.util;

import com.alibaba.fastjson.JSONObject;
import com.nvgct.po.AccessToken;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class WeixinUtilTest {

//    @Test
    public void testGetAccessToken() throws Exception {
        AccessToken accessToken = WeixinUtil.getAccessToken();
//        System.out.println(accessToken.getAccess_token());
//        System.out.println(accessToken.getExpires_in());
        String filepath="F:/wyMusic/筷子兄弟 - 小苹果.mp3";
//        String mediaId=WeixinUtil.upload(filepath,accessToken.getAccess_token(),"image");
//        System.out.println(mediaId);

        String musicId=WeixinUtil.upload(filepath,accessToken.getAccess_token(),"music");
        System.out.println(musicId);


    }


    @Test
    public void testCreateMenu() throws Exception {
        AccessToken accessToken = WeixinUtil.getAccessToken();
        String menu = JSONObject.toJSON(WeixinUtil.initMenu()).toString();
        System.out.println(menu);
        int result=WeixinUtil.createMenu(accessToken.getAccess_token(),menu);
        if(result==0){
            System.out.println("创建成功");
        }else{
            System.out.println("创建失败，错误码为："+result);
        }



    }
}

