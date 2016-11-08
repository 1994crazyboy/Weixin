package com.nvgct.util;

import java.util.Arrays;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CheckUtil {
	private static final String token="hynweixin";
	
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		//添加到数组中
		String[] arr=new String[]{token,timestamp,nonce};
		
		
		//排序
		Arrays.sort(arr);
		
		//排序完成后添加到字符串
		StringBuffer content=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			content.append(arr[i]);
		}
		
		//sha1加密
		String temp=getSha1(content.toString());
		
		//返回对比结果
		return temp.equals(signature);
		
	}
	
	//下面四个import放在类名前面 包名后面	 
	public static String getSha1(String str){
	    if (null == str || 0 == str.length()){
	        return null;
	    }
	    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
	            'a', 'b', 'c', 'd', 'e', 'f'};
	    try {
	        MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
	        mdTemp.update(str.getBytes("UTF-8"));
	         
	        byte[] md = mdTemp.digest();
	        int j = md.length;
	        char[] buf = new char[j * 2];
	        int k = 0;
	        for (int i = 0; i < j; i++) {
	            byte byte0 = md[i];
	            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
	            buf[k++] = hexDigits[byte0 & 0xf];
	        }
	        return new String(buf);
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	        return null;
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
