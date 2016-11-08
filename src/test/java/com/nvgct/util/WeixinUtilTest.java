package com.nvgct.util;

import com.nvgct.po.AccessToken;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class WeixinUtilTest {

    @Test
    public void testGetAccessToken() throws Exception {
        AccessToken accessToken = WeixinUtil.getAccessToken();
        System.out.println(accessToken.getAccess_token());
        System.out.println(accessToken.getExpires_in());
    }
}

