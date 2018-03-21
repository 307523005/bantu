package com.bantu.ssm.util.wx;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.bantu.ssm.dao.accesstoken.AccessTokenDao;
import com.bantu.ssm.entity.wx.AccessToken;

/**
 * 获取accessToken，(生成短连接用)
 * 
 * @author Administrator
 * 
 */
public class GetAccess_token {
	/**
	 * 获取公众号appid的token
	 * @return
	 */
	public static AccessToken getAccessToken() {
		AccessToken token =getGZHToken();
		AccessToken toketwo = getXCXToken();
		token.setGZHaccess_token(token.getAccess_token());
		token.setXCXaccess_token(toketwo.getAccess_token());
		return token;
	}
	public static AccessToken getGZHToken() {
		AccessToken token = getToken(DefinedChars.WXGZAPPID, DefinedChars.WXGZSECRET);
		token.setGZHaccess_token(token.getAccess_token());
		
		return token;
	}
	/**
	 *小程序appid的token
	 * @return
	 */
	public static AccessToken getXCXToken() {
		return getToken(DefinedChars.XCXAPPID, DefinedChars.XCXSECRET);
	}
	/**
	 * 获取access_token
	 * @param ZAPPID
	 * @param SECRET
	 * @return
	 */
	public static AccessToken getToken(String ZAPPID,String SECRET) {
		AccessToken accessToken = new AccessToken();
		String access_token = null;
		int expires_in = 0;
		String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ ZAPPID + "&secret=" + SECRET;
		try {

			URL urlGet = new URL(GET_TOKEN_URL);

			HttpURLConnection http = (HttpURLConnection) urlGet
					.openConnection();

			http.setRequestMethod("GET"); // 必须是get方式请求

			http.setRequestProperty("Content-Type",

			"application/x-www-form-urlencoded");

			http.setDoOutput(true);

			http.setDoInput(true);

			http.connect();

			InputStream is = http.getInputStream();

			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			JSONObject demoJson = JSONObject.parseObject(message);
			access_token = demoJson.getString("access_token");
			expires_in = demoJson.getIntValue("expires_in");
			accessToken.setAccess_token(access_token);
			accessToken.setExpires_in(expires_in);
			is.close();
			System.out.println(demoJson);
			System.out.println(accessToken.getAccess_token()+"*----*"+accessToken.getExpires_in());

		} catch (Exception e) {

			e.printStackTrace();

		}

		return accessToken;

	}
	public static void main(String[] args) {
		getXCXToken();
	}

}
