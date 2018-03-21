package com.bantu.ssm.service.impl.wxuser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bantu.ssm.dao.accesstoken.AccessTokenDao;
import com.bantu.ssm.dao.mycard.MycardDao;
import com.bantu.ssm.dao.templatemessage.TemplateMessageDao;
import com.bantu.ssm.dao.wxuser.WxuserDao;
import com.bantu.ssm.entity.feedback.Feedback;
import com.bantu.ssm.entity.mycard.Mycard;
import com.bantu.ssm.entity.templatemessage.TemplateMessage;
import com.bantu.ssm.entity.wx.AccessToken;
import com.bantu.ssm.entity.wxuser.Wxuser;
import com.bantu.ssm.service.wxuser.WxuserService;
import com.bantu.ssm.util.PageUtil;
import com.bantu.ssm.util.Result;
import com.bantu.ssm.util.RoundUtil;
import com.bantu.ssm.util.wx.AesCbcUtil;
import com.bantu.ssm.util.wx.DefinedChars;
import com.bantu.ssm.util.wx.GetAccess_token;
import com.bantu.ssm.util.wx.SendTemplateUtil;

/**
 * 公众号登陆获取openid
 * 
 * @author Administrator
 * 
 */
@Service
public class WxuserServiceImpl implements WxuserService {
	@Autowired
	private WxuserDao wxuserDao;
	@Autowired
	private MycardDao mycardDao;
	@Autowired
	private AccessTokenDao accessTokenDao;
	@Autowired
	private TemplateMessageDao templateMessageDao;

	/**
	 * h5登陆
	 */
	@Override
	public Map<String, Object> H5login(String code) throws Exception {
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());// new Date()为获取当前系统时间
		System.out.println("-----redirectUrl----" + code);
		Map map = new HashMap();
		// 根据code得到access_token,openid
		String getH5OpenidUrl = DefinedChars.getH5Openid(code);
		String rec = httpGet(getH5OpenidUrl);
		JSONObject json = JSON.parseObject(rec);
		System.out.println("------------json------" + json);
		// 获取回执的openid
		// 获取access_token
		String gzhopenid = json.getString("openid");
		String access_token = json.getString("access_token");
		// 获取微信用户详细信息
		String uidMessageUrl = DefinedChars.getUidMessage(access_token);
		String recTwo = httpGet(uidMessageUrl);
		JSONObject jsonTwo = JSON.parseObject(recTwo);
		String nickname = jsonTwo.getString("nickname").toString();
		String sex = jsonTwo.getString("sex").toString();
		String province = jsonTwo.getString("province").toString();
		String city = jsonTwo.getString("city").toString();
		String country = jsonTwo.getString("country").toString();
		String headimgurl = jsonTwo.getString("headimgurl").toString();
		String unionid = jsonTwo.getString("unionid").toString();
		if (unionid != null) {
			// 根据openid获取用户信息
			List<Wxuser> wxuser = wxuserDao.getByOpenid(unionid);
			if (wxuser.size() > 0) {
				map.put("userid", wxuser.get(0).getUser_uid());
				// 修改用户信息
				Wxuser wxuserUpdate = new Wxuser();
				wxuserUpdate.setUser_nickname(nickname);
				wxuserUpdate.setUser_gender(sex);
				wxuserUpdate.setUser_province(province);
				wxuserUpdate.setUser_city(city);
				wxuserUpdate.setUser_country(country);
				wxuserUpdate.setUser_deptpic(headimgurl);
				wxuserUpdate.setUser_logindtime(nowTime);
				wxuserUpdate.setUser_unionid(unionid);
				wxuserUpdate.setUser_gzhopenid(gzhopenid);
				wxuserDao.update(wxuserUpdate);
			} else {
				// 随机生成uid
				String user_uid = RoundUtil.getUUID16();
				/*
				 * nickname, sex, province, city, country, openid, headimgurl,
				 * nowTime, nowTime, "", "", "", user_uid,"", "","", "","",
				 * "","", "","", "",unionid
				 */
				Wxuser wxusers = new Wxuser();
				wxusers.setUser_uid(user_uid);
				wxusers.setUser_unionid(unionid);
				wxusers.setUser_gzhopenid(gzhopenid);
				wxusers.setUser_registeredtime(nowTime);
				wxusers.setUser_logindtime(nowTime);
				// 添加新用户信息
				wxuserDao.add(wxusers);
				map.put("userid", user_uid);
			}
		}
		return map;
	}

	/**
	 * 小程序登陆后台接口使用框架为Springboot+maven+JPA 需要将前端code 发送到该处，使用code 换取openid 和
	 * session_key 根据用户id获取用户自己名片信息,有自己的名片返回ret1，没有为0
	 * 
	 * @param code
	 *            前台通过wx.login函数success微信服务器回传的code（有效期5min） 本例中前台格式为 code:
	 *            res.code
	 * @return json格式数据 如果成功返回值为{ userid:XX}
	 */

	public Map login(String code, String user_nickname, String user_gender,
			String user_province, String user_city, String user_country,
			String user_deptpic, String user_phonebrand,
			String user_phonemodel, String user_wxversion,
			String user_pixelRatio, String user_screenWidth,
			String user_screenHeight, String user_windowWidth,
			String user_windowHeight, String user_language, String user_system,
			String user_platform, String user_fontSizeSetting,
			String user_SDKVersion, String encryptedData, String iv)
			throws Exception {
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());// new Date()为获取当前系统时间
		Map map = new HashMap();
		System.out.println(">>>微信小程序登录，请求数据为[ code:" + code);
		// 获取用户openid的实际网址
		String getOpenidUrl = DefinedChars.getOpenidXCX(code);
		System.out.println("-----------------token---------------"
				+ getOpenidUrl);
		// 通过HttpGet方法将token发送至微信服务器并获得其回执
		String rec = httpGet(getOpenidUrl);
		System.out.println("微信回执为：/n" + rec);
		// 使用json类解析结果
		// 导json，jsonobject包依赖为
		/*
		 * <dependency> <groupId>com.alibaba</groupId>
		 * <artifactId>fastjson</artifactId> <version>1.2.4</version>
		 * </dependency>
		 */
		JSONObject json = JSON.parseObject(rec);
		System.out.println("------------json------" + json);
		// 获取回执的openid
		// 获取openid
		String openid = json.getString("openid");
		String session_key = json.getString("session_key").toString();
		// 得到app，公众号，小程序唯一标示unionid
		String unionid = json.getString("unionid");
		String result="";
		if (unionid == null || unionid.equals("")) {
			//如果解码出现异常，直接返回状态2
			try {
				result = AesCbcUtil.decrypt(encryptedData, session_key, iv,
						"UTF-8");
				if (null == result) {
					map.put("ret", 2);
					return map;

				}
			} catch (Exception e) {
				// TODO: handle exception
				map.put("ret", 2);
				return map;
			}
			if (null != result && result.length() > 0) {
				net.sf.json.JSONObject userInfoJSON = net.sf.json.JSONObject
						.fromObject(result);
				/*
				 * Map userInfo = new HashMap(); userInfo.put("openId",
				 * userInfoJSON.get("openId")); userInfo.put("nickName",
				 * userInfoJSON.get("nickName")); userInfo.put("gender",
				 * userInfoJSON.get("gender")); userInfo.put("city",
				 * userInfoJSON.get("city")); userInfo.put("province",
				 * userInfoJSON.get("province")); userInfo.put("country",
				 * userInfoJSON.get("country")); userInfo.put("avatarUrl",
				 * userInfoJSON.get("avatarUrl")); userInfo.put("unionId",
				 * userInfoJSON.get("unionId")); map.put("userInfo", userInfo);
				 */
				unionid = userInfoJSON.get("unionId").toString();
			}
			
		}
		System.out.println(openid + "-----------enidopenidopenid-----------"
				+ unionid);
		if (unionid != null) {
			// 根据openid获取用户信息
			List<Wxuser> wxuser = wxuserDao.getByOpenid(unionid);
			if (wxuser.size() > 0) {
				map.put("userid", wxuser.get(0).getUser_uid());
				// 修改用户信息
				Wxuser wxuserUpdate = new Wxuser(openid, user_nickname,
						user_gender, user_province, user_city, user_country,
						unionid, user_deptpic, nowTime, user_phonebrand,
						user_phonemodel, user_wxversion, user_pixelRatio,
						user_screenWidth, user_screenHeight, user_windowWidth,
						user_windowHeight, user_language, user_system,
						user_platform, user_fontSizeSetting, user_SDKVersion);
				wxuserDao.update(wxuserUpdate);
				// 根据用户id获取用户自己名片信息
				List<Mycard> mycardByUid = mycardDao.getMycardByUid(wxuser.get(
						0).getUser_uid());
				System.out.println(mycardByUid.size()
						+ "------------ByUidmycardByUidmycardByUid-----------");
				map.put("mycard", mycardByUid);
				if (mycardByUid.size() > 0) {
					map.put("ret", 1);
				} else {
					map.put("ret", 0);
				}

			} else {
				// 随机生成uid
				String user_uid = RoundUtil.getUUID16();
				Wxuser wxusers = new Wxuser(user_nickname, user_gender,
						user_province, user_city, user_country, openid,
						user_deptpic, nowTime, nowTime, user_phonebrand,
						user_phonemodel, user_wxversion, user_uid,
						user_pixelRatio, user_screenWidth, user_screenHeight,
						user_windowWidth, user_windowHeight, user_language,
						user_system, user_platform, user_fontSizeSetting,
						user_SDKVersion, unionid);
				// 添加新用户信息
				wxuserDao.add(wxusers);
				map.put("userid", user_uid);
				// 根据用户id获取用户自己名片信息
				List<Mycard> mycardByUid = mycardDao.getMycardByUid(user_uid);
				map.put("mycard", mycardByUid);
				// 如果用户有我的名片返回ret=1
				if (mycardByUid.size() > 0) {
					map.put("ret", 1);
				} else {
					map.put("ret", 0);
				}
			}
		}
		return map;
	}

	/**
	 * 通过HttpGet类发送GET请求并获取返回信息
	 * 
	 * @param path
	 *            发送至的网址
	 * @return
	 */
	public String httpGet(String path) throws Exception {
		if (path == null) {
			return null;
		}
		String rec = null;
		HttpGet get = new HttpGet(path);
		try {
			HttpResponse response = HttpClients.createDefault().execute(get);
			HttpEntity entity = response.getEntity();
			rec = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rec;
	}

	@Override
	public int getCount(Map map) {
		// TODO Auto-generated method stub
		return wxuserDao.getCount(map);
	}

	@Override
	public Map getList(Wxuser wxuser) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		List<Wxuser> list = wxuserDao.getList(wxuser);
		if (list.size()>0) {
			map.put("user_deptpic", list.get(0).getUser_deptpic());
		}else {
			map.put("user_deptpic", Result.No_Content);
		}
		return map;
	}

	@Override
	public List<Wxuser> getByOpenid(String openid) {
		// TODO Auto-generated method stub
		return wxuserDao.getByOpenid(openid);
	}

	@Override
	public int update(Wxuser wxuser) {
		// TODO Auto-generated method stub
		return wxuserDao.update(wxuser);
	}

	@Override
	public int add(Wxuser wxuser) throws Exception {
		// TODO Auto-generated method stub
		return wxuserDao.add(wxuser);
	}

	@Override
	public List<AccessToken> getAccessToken(AccessToken accessToken)
			throws Exception {
		// TODO Auto-generated method stub
		return accessTokenDao.getList();
	}

	@Override
	public String gettemplatemessage(TemplateMessage templateMessage)
			throws Exception {
		// TODO Auto-generated method stub、
		List<TemplateMessage> byId = templateMessageDao.getById(String
				.valueOf(templateMessage.getTemplateMessage_id()));
		SendTemplateUtil.SendTemplates("ow1c95cNcEuukFbO5Q6sXtYjA50Y",
				"1517553958810", accessTokenDao.getList().get(0)
						.getXCXaccess_token(), byId);
		return "";
	}

	@Override
	public List<Wxuser> getPageList(Map map) throws Exception {
		// TODO Auto-generated method stub
		return wxuserDao.getPageList(map);
	}

	@Override
	public PageUtil getPage(Map map) throws Exception {
		// TODO Auto-generated method stub
		List<Wxuser> pageList = wxuserDao.getPageList(map);
		Object object = map.get("rows");
		Object object2 = map.get("page");
		return new PageUtil(pageList, wxuserDao.getCount(map));
	}

}
