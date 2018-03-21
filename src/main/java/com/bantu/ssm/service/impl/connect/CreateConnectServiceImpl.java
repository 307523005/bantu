package com.bantu.ssm.service.impl.connect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bantu.ssm.dao.accesstoken.AccessTokenDao;
import com.bantu.ssm.dao.connect.CreateConnectDao;
import com.bantu.ssm.dao.mycard.MycardDao;
import com.bantu.ssm.dao.wx.RedPacketDao;
import com.bantu.ssm.entity.mycard.Mycard;
import com.bantu.ssm.entity.specificCode.SpecificCode;
import com.bantu.ssm.service.connect.CreateConnectService;
import com.bantu.ssm.util.FileUploadUtil;
import com.bantu.ssm.util.Result;
import com.bantu.ssm.util.RoundUtil;
import com.bantu.ssm.util.QRCode.ColoursQRCodeUtil;
import com.bantu.ssm.util.QRCode.QRCodeUtil;
import com.bantu.ssm.util.wx.DefinedChars;
import com.bantu.ssm.util.wx.GetAccess_token;
import com.bantu.ssm.util.wx.ShortConnect;

/**
 * 生成用户名片连接
 * 
 * @author Administrator
 * 
 */
@Service
@Transactional
public class CreateConnectServiceImpl implements CreateConnectService {
	// public static final String HTML5PATH =
	// "http://192.168.2.115:8080/bantu/html/bantuH5/index.html?k="; // h5页面请求路径
	@Autowired
	private CreateConnectDao createConnectDao;
	@Autowired
	private MycardDao mycardDao;
	@Autowired
	private RedPacketDao redPacketDao;
	@Autowired
	private AccessTokenDao accessTokenDao;
	private Map resObjectMap = new HashMap();

	/**
	 * 生成名片连接,二维码
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String clickShare(String user_uid, String mycard_cardid,
			String redPacket, String mycard_templateid, HttpSession session)
			throws Exception {
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());// 获取当前时间
		// ?key= +特定码//对应红包唯一识别码生效一次
		// String specificCodea = RoundUtil.specificCode(user_uid);
		// 对应红包唯一识别码,红包商户订单号28位
		String specificCode = RoundUtil.orderNo28(user_uid);
		// 得到二维码路径,生成二维码图片前要先创建空白文件
		String newQRcodeFile = FileUploadUtil.newQRcodeFile(specificCode,
				user_uid, session);
		// 获取 公众号Access_token
		String token = accessTokenDao.getList().get(0).getGZHaccess_token();
		// 名片页面路径url+key
		String longurl = "";
		System.out.println(mycard_templateid + "666666666");
		// 根据模板id选择h5页面
		if (mycard_templateid.equals("0")) {
			longurl = DefinedChars.HTML5PATH0 + specificCode + "&uid="
					+ user_uid + "&cid=" + mycard_cardid;
		} else if (mycard_templateid.equals("1")) {
			longurl = DefinedChars.HTML5PATH1 + specificCode + "&uid="
					+ user_uid + "&cid=" + mycard_cardid;
		} else if (mycard_templateid.equals("2")) {
			longurl = DefinedChars.HTML5PATH2 + specificCode + "&uid="
					+ user_uid + "&cid=" + mycard_cardid;
		} else if (mycard_templateid.equals("3")) {
			longurl = DefinedChars.HTML5PATH3 + specificCode + "&uid="
					+ user_uid + "&cid=" + mycard_cardid;
		} else if (mycard_templateid.equals("4")) {
			longurl = DefinedChars.HTML5PATH4 + specificCode + "&uid="
					+ user_uid + "&cid=" + mycard_cardid;
		} else if (mycard_templateid.equals("5")) {
			longurl = DefinedChars.HTML5PATH5 + specificCode + "&uid="
					+ user_uid + "&cid=" + mycard_cardid;
		}

		// 生成给微信的h5链接
		String requestCodeUrl = DefinedChars.getRequestCodeUrl(longurl);
		// 生成短连接
		String shorts = ShortConnect.Long2Short(requestCodeUrl, token)
				.getString("short_url");

		/*
		 * https://open.weixin.qq.com/connect/oauth2/authorize?
		 * appid=wx520c15f417810387
		 * &redirect_uri=https%3A%2F%2Fchong.qq.com%2Fphp%2
		 * Findex.php%3Fd%3D%26c
		 * %3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag
		 * %3D4_2030_5_1194_60
		 * &response_type=code&scope=snsapi_base&state=123#wechat_redirect
		 */
		// 图片外网访问全路径
		String QRcodePathOne = DefinedChars.QRcodePathOne + user_uid + "/"
				+ specificCode + ".png";
		if (newQRcodeFile != null) {
			// 生成二维码图片
			/*
			 * //彩色 ColoursQRCodeUtil.encode(shorts, 400,
			 * 400,DefinedChars.QRcodePathTwo, newQRcodeFile);
			 */
			QRCodeUtil.encode(shorts, DefinedChars.QRcodePathTwo,
					newQRcodeFile, true);
			// 将生成的长短连接，二维码，数据添加到数据库
			SpecificCode sCodes = new SpecificCode();
			sCodes.setSpecificCode_userid(user_uid);
			sCodes.setSpecificCode_code(specificCode);
			sCodes.setSpecificCode_longurl(requestCodeUrl);
			sCodes.setSpecificCode_shorturl(shorts);
			sCodes.setSpecificCode_picpath(QRcodePathOne);
			sCodes.setSpecificCode_addtime(nowTime);
			add(sCodes);
		}
		// 判断此次生成二维码是否带红包 1是，0否
		if (redPacket == "1") {
			// 添加红包表specificCode，user_uid，nowTime
			/*
			 * RedPacket redPackets = new RedPacket();
			 * redPackets.setRedpacket_code(specificCode);
			 * redPackets.setRedpacket_payuid(user_uid);
			 * redPackets.setRedpacket_money("");
			 * redPacketDao.addRedPacket(redPackets);
			 */
		}
		return QRcodePathOne;
	}

	@Override
	public List<SpecificCode> getList(Map map) throws Exception {
		// TODO Auto-generated method stub
		return createConnectDao.getList(map);
	}

	@Override
	public int add(SpecificCode specificCode) throws Exception {
		// TODO Auto-generated method stub
		return createConnectDao.add(specificCode);
	}

	@Override
	public List<SpecificCode> getBySpecificCode_code(String specificCode_code)
			throws Exception {
		// TODO Auto-generated method stub
		return createConnectDao.getBySpecificCode_code(specificCode_code);
	}

	/**
	 * 根据 得到是否有红包，名片信息
	 */
	@Override
	public Map getCardAadRedPacket(String k, String uid, String cid)
			throws Exception {
		System.out.println("k*****" + k + "uid-****" + uid + "------cid" + cid);
		resObjectMap.clear();
		Map map = new HashMap();
		/*
		 * List<SpecificCode> bySpecificCode_code =
		 * createConnectDao.getBySpecificCode_code(k); if
		 * (bySpecificCode_code.size()>0) { String specificCode_addtime =
		 * bySpecificCode_code.get(0).getSpecificCode_addtime();
		 */
		// 根据k查看是否有红包

		// map.put("", value)
		Mycard mycard = new Mycard();
		mycard.setMycard_cardid(cid);
		mycard.setMycard_uid(uid);
		List<Mycard> cardByCardId = mycardDao.getCardByCardId(mycard);
		// }

		if (cardByCardId.size() > 0) {
			resObjectMap.put("res", Result.OK_MAP);
			resObjectMap.put("resObject", cardByCardId);

		} else {
			resObjectMap.put("res", Result.Not_Found);
			return resObjectMap;
		}
		return resObjectMap;
	}
}
