package com.bantu.ssm.thread;

import org.springframework.transaction.annotation.Transactional;

import com.bantu.ssm.dao.accesstoken.AccessTokenDao;
import com.bantu.ssm.entity.wx.AccessToken;
import com.bantu.ssm.servlet.AllBean;
import com.bantu.ssm.util.wx.GetAccess_token;
@Transactional(readOnly=false)
public class TokenThread implements Runnable {
	/*
	 * @Resource private WxuserService wxuserService;
	 */
	AccessTokenDao accessTokenDao = (AccessTokenDao)AllBean
			.getBean("accessTokenDao");
	// 微信公众号的凭证和秘钥
	public static AccessToken access_token = null;
/*	@Autowired
	private AccessTokenDao accessTokenDao ;*/
	@Override
	public void run() {
		while (true) {
			try {
				// 调用工具类获取access_token(每日最多获取100000次，每次获取的有效期为7200秒)
				access_token = GetAccess_token.getAccessToken();
				try {
					accessTokenDao.update(access_token);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (null != access_token) {
					// 7000秒之后重新进行获取
					Thread.sleep((access_token.getExpires_in() - 200) * 1000);
				} else {
					// 获取失败时，1秒之后尝试重新获取
					Thread.sleep(1 * 1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
