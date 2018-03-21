package com.bantu.ssm.controller.connect;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bantu.ssm.service.connect.CreateConnectService;
import com.bantu.ssm.service.mycard.MycardService;

/**
 * 生成用户名片连接，二维码图片
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/CreateConnect")
public class CreateConnectController {
	@Resource
	private CreateConnectService createConnectService;
	@Resource
	private MycardService mycardService;

	@ResponseBody
	@RequestMapping(value = "/clickShare", produces = "text/html;charset=UTF-8")
	// method = RequestMethod.POST
	// 用户id，名片id，是否带红包 ,模板id
	public String clickShare(String user_uid, String mycard_cardid,
			String redPacket,String mycard_templateid, HttpSession session) throws Exception {
		System.out.println(mycard_templateid+"11111");
		String newQRcodeFile = null;
		if (user_uid != null&&mycard_cardid!=null&&!user_uid.equals("")&&!mycard_cardid.equals("")) {
			newQRcodeFile = createConnectService.clickShare(user_uid,
					mycard_cardid, redPacket,mycard_templateid, session);
		} else {
			newQRcodeFile = "user_uid-mycard_cardid为空";
		}
		return newQRcodeFile;
	}
	/**
	 * 根据  k  得到是否有红包，名片信息
	 * @param k
	 * @param uid
	 * @param cid
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getCardAadRedPacket")
	public Map getCardAadRedPacket(String k, String uid, String cid) throws Exception {
		Map cardAadRedPacket = createConnectService.getCardAadRedPacket(k, uid, cid);
		return cardAadRedPacket;
	}
}
