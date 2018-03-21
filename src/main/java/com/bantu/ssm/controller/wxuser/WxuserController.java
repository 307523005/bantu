package com.bantu.ssm.controller.wxuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.bantu.ssm.entity.templatemessage.TemplateMessage;
import com.bantu.ssm.entity.wx.AccessToken;
import com.bantu.ssm.entity.wxuser.Wxuser;
import com.bantu.ssm.service.wxuser.WxuserService;
import com.bantu.ssm.util.IpUtils;
import com.bantu.ssm.util.PageUtil;
import com.bantu.ssm.util.ParamsUtil;
import com.bantu.ssm.util.Result;
import com.bantu.ssm.util.wx.CheckoutUtil;
import com.bantu.ssm.util.wx.SendTemplateUtil;

/**
 * 2017-12-25 微信授权登陆
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/wxuser")
public class WxuserController {
	@Resource
	private WxuserService wxuserService;

	/**
	 * 用户进入h5
	 * 
	 * @param redirectUrl
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/H5login")
	public Map<String, Object> H5login(String code) throws Exception {
		Map<String, Object> h5login = wxuserService.H5login(code);
		return h5login;
	}
/**
 * 根据uid得到用户头像
 * @param user_uid
 * @return
 * @throws Exception
 */
	@ResponseBody
	@RequestMapping(value = "/getUserPic")
	public Map<String, Object> getUserPic(String user_uid) throws Exception {
		Map resObjectMap = new HashMap();
		Wxuser wxuser = new Wxuser();
		wxuser.setUser_uid(user_uid);
		Map list = wxuserService.getList(wxuser);
		return list;
	}

	/**
	 * 微信用户授权进入小程序，后台获取openid，返回前台userid和openid 获取客户端ip 得到用户自己的的名片信息
	 * 
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(String appid, String secret, String code,
			String user_nickname, String user_gender, String user_province,
			String user_city, String user_country, String user_deptpic,
			HttpServletRequest request, String user_phonebrand,
			String user_phonemodel, String user_wxversion,
			String user_pixelRatio, String user_screenWidth,
			String user_screenHeight, String user_windowWidth,
			String user_windowHeight, String user_language, String user_system,
			String user_platform, String user_fontSizeSetting,
			String user_SDKVersion, String encryptedData, String iv)
			throws Exception {
		System.out.println("**********--------------***********--------"
				+ user_SDKVersion + user_fontSizeSetting);
		Map map = new HashMap();
		Map resObjectMap = new HashMap();
		if (code != null) {// 获取openid成功
			map = wxuserService.login(code, user_nickname, user_gender,
					user_province, user_city, user_country, user_deptpic,
					user_phonebrand, user_phonemodel, user_wxversion,
					user_pixelRatio, user_screenWidth, user_screenHeight,
					user_windowWidth, user_windowHeight, user_language,
					user_system, user_platform, user_fontSizeSetting,
					user_SDKVersion, encryptedData, iv);
			resObjectMap.put("res", Result.OK_MAP);
			resObjectMap.put("resObject", map);
		} else {// 获取openid错误返回
			resObjectMap.put("res", Result.Not_Found);
			resObjectMap.put("resObject", "缺少code");
		}
		// 获取客户端ip
		String ipAddr = IpUtils.getIpNetwork(request);
		String ipAddr2 = IpUtils.getIpAddr(request);
		System.out.println(ipAddr2 + "ipipipipipipipipipipiu----" + ipAddr
				+ "resObjectMap" + resObjectMap + "encryptedData"
				+ encryptedData + "iv" + iv);
		return resObjectMap;
	}

	/**
	 * 测试接口 获取微信端传送的用户数据
	 * 
	 * @param nickName
	 * @param avatarUrl
	 * @param gender
	 * @param province
	 * @param city
	 * @param country
	 * @return
	 * @throws IOException
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/getSessionKeyOropenid", method =
	 * RequestMethod.POST) public Map getSessionKeyOropenid(String nickName,
	 * String avatarUrl, String gender, String province, String city, String
	 * country) throws Exception { System.out.println("测试数据:" + nickName +
	 * avatarUrl + gender + province + city + country); String cc = "你好";
	 * JSONArray jsonArray = new JSONArray(); jsonArray.add(cc); Map map = new
	 * HashMap(); map.put("aaa", "啦啦"); map.put("bbb", "剌剌"); return map;
	 * 
	 * }
	 */
	/**
	 * 得到AccessToken
	 * 
	 * @param accessToken
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getAccessToken")
	public List<AccessToken> getAccessToken(AccessToken accessToken)
			throws Exception {
		Map map = new HashMap();
		List<AccessToken> accessToken2 = wxuserService
				.getAccessToken(accessToken);
		System.out.println(accessToken2.get(0).getGZHaccess_token());
		return accessToken2;

	}

	/**
	 * 发送小程序模板通知
	 * 
	 * @param templatemessage
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/gettemplatemessage")
	public String gettemplatemessage(TemplateMessage templatemessage)
			throws Exception {
		Map map = new HashMap();
		templatemessage.setTemplateMessage_id(1);
		String templatemessageList = wxuserService
				.gettemplatemessage(templatemessage);
		return templatemessageList;

		/*	// 根据uid得到openid
	List<Wxuser> byUser_uid = wxuserDao.getByUser_uid(user_uid);
	if (byUser_uid.size() > 0) {
		// 发送模板消息
		List<TemplateMessage> byId = templateMessageDao
				.getById("1");
		SendTemplateUtil.SendTemplates(byUser_uid.get(0)
				.getUser_openid(), user_form_id, accessTokenDao
				.getList().get(0).getXCXaccess_token(), byId);

	}*/
	}

	/**
	 * 用户信息分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getList")
	// method = RequestMethod.POST, produces = "text/html;charset=UTF-8"
	public Map getList(ParamsUtil paramsUtil) throws Exception {
		PageUtil pageUtil = wxuserService.getPage(paramsUtil.getParamsMap());
		return paramsUtil.getResultMap(pageUtil.getList(), pageUtil.getCount());
	}

	/**
	 *公众号绑定域名， 微信消息接收和token验证
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/hello")
	public void hello(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean isGet = request.getMethod().toLowerCase().equals("get");
		PrintWriter print;
		System.out.println("-------------------");
		if (isGet) {
			// 微信加密签名
			String signature = request.getParameter("signature");
			// 时间戳
			String timestamp = request.getParameter("timestamp");
			// 随机数
			String nonce = request.getParameter("nonce");
			// 随机字符串
			String echostr = request.getParameter("echostr");
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (signature != null
					&& CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
				try {
					print = response.getWriter();
					print.write(echostr);
					print.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
