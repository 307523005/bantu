package com.bantu.ssm.controller.mycard;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bantu.ssm.entity.mycard.Cardcase;
import com.bantu.ssm.entity.mycard.Mycard;
import com.bantu.ssm.service.mycard.MycardService;

@Controller
@RequestMapping("/Mycard")
public class MycardController {
	@Resource
	private MycardService mycardService;

	/**
	 * 根据用户id得到用户自己的名片
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getMycardByUid")
	public Map getMycardByUid(String mycard_uid) throws Exception {
		Map mycardByUid = mycardService.getMycardByUid(mycard_uid);
		System.err.println(mycard_uid
				+ "userIduserIduserIduserIdvuserIduserId++++++++++");
		return mycardByUid;
	}

	/**
	 * 根据用户id得到名片夹名片
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getMyCardcaseByUid")
	public Map getMyCardcaseByUid(String mycard_uid) throws Exception {
		System.out.println(mycard_uid + "mycard_uidmycard_uidmycard_uid----");
		Map myCardcaseByUid = mycardService.getMyCardcaseByUid(mycard_uid);
		System.out.println(myCardcaseByUid + "---------------");
		return myCardcaseByUid;
	}

	/**
	 * 根据名片id和uid获取名片信息 ，查询用户名片夹或者待接受夹是否已有改名片
	 * 
	 * @param cardId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getCardBycardId")
	public Map getCardBycardId(Mycard mycard) throws Exception {
		System.out
				.println(mycard.getMycard_cardid()
						+ "0222//////mycard_cardidmycard_cardi---"+mycard.getMycard_uid()+"--Mycard_uid*****************");
		Map cardByCardId = mycardService.getCardByCardId(mycard);
		System.out.println(cardByCardId + "aaaaaaaaaaa");
		return cardByCardId;
	}
	/**
	 * 分享页面根据名片id查询用户名片夹是否已有改名
	 * 
	 * @param cardId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getShareCardBycardId")
	public Map getShareCardBycardId(Mycard mycard) throws Exception {
		System.out
				.println(mycard.getMycard_cardid()
						+ "-11111///mycard_cardid+----"+mycard.getMycard_uid()+"----+mycard_cardid****");
		Map cardByCardId = mycardService.getShareCardBycardId(mycard);
		System.out.println(cardByCardId + "aaaaaaaaaaa");
		return cardByCardId;
	}
	/**
	 * 手动添加名片,我的，他人的,
	 * 如果有Mycard_uid则为修改
	 * 
	 * @param mycard
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addMyCard")
	public Map addMyCard(Mycard mycard) throws Exception {

		Map addMyCard = null;
		System.out.println(mycard.getMycard_cardid() + ""
				+ mycard.getMycard_uid()
				+ "..............cardid...................");
		if (!mycard.getMycard_cardid().equals("")
				&& mycard.getMycard_cardid() != null) {
			addMyCard = mycardService.update(mycard);
		} else {

			addMyCard = mycardService.addOthersCard(mycard);
		}
		return addMyCard;
	}

	/**
	 * H5点保存自动添加名片夹名片
	 * 
	 * @param cardcase
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addCardcaseTwoH5")
	public Map addCardcaseTwoH5(Cardcase cardcase) throws Exception {
		System.out.println(cardcase.getCardcase_cardid()+"---h5--etCardcase_cardid()------h5------");
		Map addCardcaseTwo = mycardService.addCardcaseTwo(cardcase,"","");
		return addCardcaseTwo;
	}
	/**
	 * 点分享自动添加名片夹名片
	 * 
	 * @param cardcase
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addCardcaseTwo")
	public Map addCardcaseTwo(Cardcase cardcase,String user_uid,String user_form_id) throws Exception {
		System.out.println(cardcase.getCardcase_cardid()+"-----etCardcase_cardid()------------"+user_form_id+"---*-"+user_uid);
		Map addCardcaseTwo = mycardService.addCardcaseTwo(cardcase,user_uid,user_form_id);
		return addCardcaseTwo;
	}
	/**
	 * 删除名片夹名片
	 * 
	 * @param mycard
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	public Map delete(Cardcase cardcase) throws Exception {
		Map delete = mycardService.delete(cardcase);
		return delete;
	}
	/**
	 * 删除待保存名片
	 * 
	 * @param mycard
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAwaitAddCard")
	public Map deleteAwaitAddCard(Cardcase cardcase) throws Exception {
		System.out.println(cardcase.getCardcase_uid()+"--*-*-*---delete--*-*---"+cardcase.getCardcase_cardid());
		Map deleteAwaitAddCard = mycardService.deleteAwaitAddCard(cardcase);
		return deleteAwaitAddCard;
	}
	/**
	 * 将自己的名片发送给他人为待保存名片
	 * @param cardcase
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/awaitAddCard")
	public Map awaitAddCard(Cardcase cardcase) throws Exception {
		System.out.println("addaa-*-*-*-*-*-*-dd"+cardcase.getCardcase_uid()+cardcase.getCardcase_cardid());
		Map awaitAddCard = mycardService.awaitAddCard(cardcase);
		return awaitAddCard;
	}
	/**
	 * 保存，待保存名片
	 * @param cardcase
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/saveAwaitAddCard")
	public Map saveAwaitAddCard(Cardcase cardcase) throws Exception {
		Map saveAwaitAddCard = mycardService.saveAwaitAddCard(cardcase);
		return saveAwaitAddCard;
	}
	/**
	 * 根据uid查询待保存名片
	 * @param mycard_uid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getAwaitAddCard")
	public Map getAwaitAddCard(String mycard_uid) throws Exception {
		Map getAwaitAddCard = mycardService.getAwaitAddCard(mycard_uid);
		return getAwaitAddCard;
	}
	/**
	 * 根据用户uid获取用户自己的待保存名片数量
	 * @param mycard_uid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getAwaitAddCardCount")
	public Map getAwaitAddCardCount(String mycard_uid) throws Exception {
		Map getAwaitAddCardCount = mycardService.getAwaitAddCardCount(mycard_uid);
		return getAwaitAddCardCount;
	}
	/**
	 * 根据cardcase_cardid  得到名片原始主人cardcase_uid  
	 * @param cardcase_cardid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getPrimitiveUid")
	public Map getPrimitiveUid(String cardcase_cardid) throws Exception {
		System.out.println(cardcase_cardid+"------cardcase_cardid----------");
		Map getAwaitAddCardCount = mycardService.getPrimitiveUid(cardcase_cardid  );
		System.out.println(getAwaitAddCardCount.get(""));
		return getAwaitAddCardCount;
	}
}
