package com.bantu.ssm.service.impl.mycard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bantu.ssm.dao.accesstoken.AccessTokenDao;
import com.bantu.ssm.dao.mycard.MycardDao;
import com.bantu.ssm.dao.templatemessage.TemplateMessageDao;
import com.bantu.ssm.dao.wxuser.WxuserDao;
import com.bantu.ssm.entity.mycard.Cardcase;
import com.bantu.ssm.entity.mycard.Mycard;
import com.bantu.ssm.entity.templatemessage.TemplateMessage;
import com.bantu.ssm.entity.wxuser.Wxuser;
import com.bantu.ssm.service.mycard.MycardService;
import com.bantu.ssm.util.Result;
import com.bantu.ssm.util.RoundUtil;
import com.bantu.ssm.util.ocr.ChineseInital;
import com.bantu.ssm.util.ocr.ChineseInitalTwo;
import com.bantu.ssm.util.wx.SendTemplateUtil;

@Service
@Transactional
public class MycardServiceImpl implements MycardService {
	@Autowired
	private MycardDao mycardDao;
	@Autowired
	private WxuserDao wxuserDao;
	@Autowired
	private AccessTokenDao accessTokenDao;
	@Autowired
	private TemplateMessageDao templateMessageDao;
	private Map resObjectMap = new HashMap();

	/**
	 * 修改名片信息
	 */
	@Override
	public Map update(Mycard mycard) {
		resObjectMap.clear();
		int mycardBycardId = 0;
		if (mycard.getMycard_cardid() != null
				&& !mycard.getMycard_cardid().equals("")) {
			if (mycard.getMycard_name() != null
					&& !mycard.getMycard_name().equals("")) {
				// 获取姓名的字符的首字母
				mycard.setMycard_initial(ChineseInitalTwo.getFirstOne(mycard
						.getMycard_name()));
			}
			mycardBycardId = mycardDao.update(mycard);
			if (mycardBycardId > 0) {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", mycard.getMycard_cardid());
			} else {
				resObjectMap.put("res", Result.Not_Found);
			}

		} else {
			resObjectMap.put("res", Result.Not_Found);
		}
		return resObjectMap;
	}

	/**
	 * 删除名片夹信息
	 */
	@Override
	public Map delete(Cardcase cardcase) throws Exception {
		resObjectMap.clear();
		int mycardBycardId = 0;
		if (cardcase.getCardcase_cardid() != null
				&& !cardcase.getCardcase_cardid().equals("")) {
			//先删除cardcase表
			mycardBycardId = mycardDao.delete(cardcase);
			Mycard mycard = new Mycard();
			mycard.setMycard_cardid(cardcase.getCardcase_cardid());
			mycard.setMycard_uid(cardcase.getCardcase_uid());
			List<Mycard> iscompile = mycardDao.getIscompile(mycard);
			//根据uid cid  查看是不是自己编辑的名片 
			if (iscompile.size() > 0) {
				mycardDao.deleteMycard(mycard);
			}
			if (mycardBycardId > 0) {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", mycardBycardId);
			} else {
				resObjectMap.put("res", Result.Not_Found);
			}
		} else {
			resObjectMap.put("res", Result.Not_Found);
		}
		return resObjectMap;
	}

	/**
	 * 根据cardId得到一张名片的详细信息，查询用户名片夹或者待接受夹是否已有改名片
	 */
	@Override
	public Map getCardByCardId(Mycard mycard) throws Exception {
		resObjectMap.clear();
		System.out.println(mycard.getMycard_cardid()
				+ "mycardBycardId-----------+" + mycard.getMycard_uid()
				+ "+mycardBycardId---****-----");
		List<Mycard> mycardBycardId = null;
		if (mycard.getMycard_cardid() != null
				&& !mycard.getMycard_cardid().equals("")
				&& !mycard.getMycard_cardid().equals("undefined")) {
			mycardBycardId = mycardDao.getCardByCardId(mycard);

			if (mycardBycardId.size() > 0) {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", mycardBycardId);
				System.err.println("*********---------------*-********"
						+ mycardBycardId.get(0).getMycard_name());
			} else {
				resObjectMap.put("res", Result.Not_Found);
			}

		} else {
			System.out.println("------------------------------"
					+ resObjectMap.get("mycard_mail"));
			resObjectMap.put("res", Result.Not_Found);
		}
		return resObjectMap;
	}

	/**
	 * 分享页面根据名片id查询用户名片夹是否已有改名片，1 是 0否
	 */
	@Override
	public Map getShareCardBycardId(Mycard mycard) throws Exception {
		resObjectMap.clear();
		List<Mycard> mycardBycardId = null;
		if (mycard.getMycard_cardid() != null && mycard.getMycard_uid() != null
				&& !mycard.getMycard_cardid().equals("")
				&& !mycard.getMycard_cardid().equals("undefined")) {

			mycardBycardId = mycardDao.getCardByCardId(mycard);
			if (mycardBycardId.size() > 0) {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", 1);
			} else {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", 0);
			}

		} else {
			resObjectMap.put("res", Result.Not_Found);
		}
		return resObjectMap;
	}

	/**
	 * 根据mycard_uid得到自己的名片
	 */
	@Override
	public Map getMycardByUid(String mycard_uid) throws Exception {
		resObjectMap.clear();
		List<Mycard> mycardByUid = null;
		if (mycard_uid != null) {
			mycardByUid = mycardDao.getMycardByUid(mycard_uid);
			if (mycardByUid.size() > 0) {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", mycardByUid);
			} else {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", 0);
			}
		} else {
			resObjectMap.put("res", Result.Not_Found);
		}
		return resObjectMap;
	}

	/**
	 * 根据mycard_uid得到自己的名片夹名片
	 */
	@Override
	public Map getMyCardcaseByUid(String mycard_uid) throws Exception {
		resObjectMap.clear();
		List<Mycard> mycardByUid = null;
		if (mycard_uid != null) {
			System.out.println(mycard_uid + "//////////--------------------//");
			mycardByUid = mycardDao.getMyCardcaseByUid(mycard_uid);
			if (mycardByUid.size() > 0) {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", mycardByUid);
			} else {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", 0);
			}
		} else {
			resObjectMap.put("res", Result.Not_Found);
		}
		System.out
				.println("---------------------------*******************------------");
		for (int i = 0; i < mycardByUid.size(); i++) {
			System.out.println(mycardByUid.get(i).getMycard_initial());
		}
		return resObjectMap;
	}

	/**
	 *添加名片信息
	 */
	@Override
	public Map addOthersCard(Mycard mycard) throws Exception {
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());// 获取当前时间
		resObjectMap.clear();
		String cardid = RoundUtil.specificCode(mycard.getMycard_uid());
		int mycardBycardId = 0;
		if (!mycard.getMycard_uid().equals("")) {
			// 名片唯一标识
			mycard.setMycard_cardid(cardid);

			// 获取姓名的第一个字符的首字母
			mycard.setMycard_initial(ChineseInitalTwo.getFirstOne(mycard
					.getMycard_name()));
			System.out.println(mycard.getMycard_initial()
					+ "asfasdgfadsfhdsfgdsfgfffffffffffffffffffffff");
			// 添加名片信息
			mycardBycardId = mycardDao.addCardcase(mycard);
			Cardcase cardcase = new Cardcase();
			cardcase.setCardcase_cardid(cardid);
			cardcase.setCardcase_uid(mycard.getMycard_uid());
			// 自己编写的名片（可以换模板）
			cardcase.setCardcase_iscompile(1);
			// 是我的还是他人的
			cardcase.setCardcase_ismy(mycard.getMycard_ismy());
			// 名片创建时间
			cardcase.setCardcase_addtime(nowTime);
			// 将名片添加入名片夹表
			mycardDao.addCard(cardcase);
			if (mycardBycardId > 0) {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", cardid);
			} else {
				resObjectMap.put("res", Result.Not_Found);
			}
		} else {
			resObjectMap.put("res", Result.Not_Found);
		}
		return resObjectMap;
	}

	/**
	 * 打开分享，h5，添加名片
	 */
	@Override
	public Map addCardcaseTwo(Cardcase cardcase, String user_uid,
			String user_form_id) throws Exception {
		// TODO Auto-generated method stub
		Mycard mycard = new Mycard();
		int mycardBycardId = 0;
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());// 获取当前时间
		resObjectMap.clear();
		// 添加时间
		cardcase.setCardcase_addtime(nowTime);
		// 不是手动编写的（不能换模板）
		cardcase.setCardcase_iscompile(0);
		// 是他人的
		cardcase.setCardcase_ismy("0");
		/*// 将uid，cid放入mycard
		mycard.setMycard_cardid(cardcase.getCardcase_cardid());
		mycard.setMycard_uid(cardcase.getCardcase_uid());
		List<Mycard> cardByCardId = mycardDao.getCardByCardId(mycard);
		System.out.println(cardByCardId.size()
				+ "cardByCardIdcardByCardId------------*-*-**--*'");
		// 如果查询到0条，添加，否则返回已经添加过
		if (cardByCardId.size() == 0) {*/
			int addCardcaseTwo = mycardDao.addCard(cardcase);
			if (mycardBycardId > 0) {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", addCardcaseTwo);
			} else {
				resObjectMap.put("res", Result.Not_Found);
			}

	/*	} else {
			resObjectMap.put("res", Result.Not_Found);
		}*/

		return resObjectMap;
	}

	/**
	 * 将自己的名片发送给他人为待保存名片
	 */
	@Override
	public Map awaitAddCard(Cardcase cardcases) {
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		.format(new Date());// 获取当前时间
		resObjectMap.clear();
		Cardcase cardcase = new Cardcase();
		cardcase.setCardcase_cardid(cardcases.getCardcase_cardid());
		cardcase.setCardcase_uid(cardcases.getCardcase_uid());
		// 名片创建时间
		cardcase.setCardcase_addtime(nowTime);
		// 将名片添加入名片夹表
		int awaitAddCard = mycardDao.awaitAddCard(cardcase);
		if (awaitAddCard > 0) {
			resObjectMap.put("res", Result.OK_MAP);
		} else {
			resObjectMap.put("res", Result.Not_Found);
		}
		return resObjectMap;
	}

	/**
	 * 保存，待保存名片
	 */
	@Override
	public Map saveAwaitAddCard(Cardcase cardcase) throws Exception {
		resObjectMap.clear();
		int saveAwaitAddCard = mycardDao.saveAwaitAddCard(cardcase);
		if (saveAwaitAddCard > 0) {
			resObjectMap.put("res", Result.OK_MAP);
		} else {
			resObjectMap.put("res", Result.Not_Found);
		}
		return resObjectMap;
	}

	/**
	 * 删除待保存名片
	 */
	@Override
	public Map deleteAwaitAddCard(Cardcase cardcase) throws Exception {
		resObjectMap.clear();
		if (cardcase.getCardcase_cardid() != null
				&& !cardcase.getCardcase_cardid().equals("")) {
			// 先删除cardcase表
			 int delete = mycardDao.delete(cardcase);
			if (delete > 0) {
				resObjectMap.put("res", Result.OK_MAP);
			} else {
				resObjectMap.put("res", Result.Not_Found);
			}
		} else {
			resObjectMap.put("res", Result.Not_Found);
		}
		return resObjectMap;
	}
/**
 * 根据用户uid获取用户自己的待保存名片
 */
	@Override
	public Map getAwaitAddCard(String uid) throws Exception {
		resObjectMap.clear();
		List<Mycard> getAwaitAddCard = null;
		if (uid != null) {
			getAwaitAddCard = mycardDao.getAwaitAddCard(uid);
			if (getAwaitAddCard.size() > 0) {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", getAwaitAddCard);
			} else {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", 0);
			}
		} else {
			resObjectMap.put("res", Result.Not_Found);
		}
		return resObjectMap;
	}

	/**
	 * 根据用户uid获取用户自己的待保存名片数量
	 */
	@Override
	public Map getAwaitAddCardCount(String uid) throws Exception {
		resObjectMap.clear();
		if (uid != null) {
			int awaitAddCardCount = mycardDao.getAwaitAddCard(uid).size();
			resObjectMap.put("res", Result.OK_MAP);
			resObjectMap.put("resObject", awaitAddCardCount);
		} else {
			resObjectMap.put("res", Result.Not_Found);
		}
		return resObjectMap;
	}

	/**
	 * 根据cardcase_cardid 得到名片原始主人cardcase_uid
	 */
	@Override
	public Map getPrimitiveUid(String cid) throws Exception {
		resObjectMap.clear();
		if (cid != null) {
			List<Cardcase> primitiveUid = mycardDao.getPrimitiveUid(cid);
			if (primitiveUid.size()>0) {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", primitiveUid.get(0).getCardcase_uid());
			}else {
				resObjectMap.put("res", Result.Not_Found);
			}
		} else {
			resObjectMap.put("res", Result.Not_Found);
		}
		return resObjectMap;
	}

}
