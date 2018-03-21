package com.bantu.ssm.service.mycard;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bantu.ssm.entity.mycard.Cardcase;
import com.bantu.ssm.entity.mycard.Mycard;


public interface MycardService {


	/**
	 * 点开分享添加名片
	 * @param mycard
	 * @return
	 */
	public Map addCardcaseTwo(Cardcase cardcase,String user_uid,String user_form_id)throws Exception ;
	/**
	 * 修改名片
	 * @param mycard
	 * @return
	 */
	public Map update(Mycard mycard)throws Exception ;

	/**
	 * 删除名片夹名片
	 * @param mycard
	 * @return
	 */
	public Map delete(Cardcase cardcase)throws Exception ;
	/**
	 * 删除待保存名片
	 * @param mycard
	 * @return
	 */
	public Map deleteAwaitAddCard(Cardcase cardcase)throws Exception ;
	/**
	 * 将自己的名片发送给他人为待保存名片
	 * @param cardcase
	 * @return
	 */
	public Map awaitAddCard(Cardcase cardcase)throws Exception;
	/**
	 * 根据名片id获取用户名片
	 * @param id
	 * @return
	 */
	public Map getCardByCardId(Mycard mycard)throws Exception ;
	/**
	 * 分享页面根据名片id查询用户是否已有改名
	 * @param id
	 * @return
	 */
	public Map getShareCardBycardId(Mycard mycard)throws Exception ;
	/**
	 * 根据用户uid获取用户自己的名片
	 * @param id
	 * @return
	 */
	public Map getMycardByUid(String uid)throws Exception ;
	/**
	 * 根据用户uid获取用户自己的名片夹名片
	 * @param id
	 * @return
	 */
	public Map getMyCardcaseByUid(String uid)throws Exception ;
	/**
	 * 添加名片信息
	 * @param mycard
	 * @return
	 * @throws Exception
	 */
	public Map addOthersCard(Mycard mycard ) throws Exception;
	/**
	 * 保存，待保存名片
	 * @param cardcase
	 * @return
	 */
	public Map saveAwaitAddCard(Cardcase cardcase)throws Exception;
	/**
	 * 根据用户uid获取用户自己的待保存名片
	 * @param id
	 * @return
	 */
	public Map getAwaitAddCard(String uid)throws Exception;
	/**
	 * 根据用户uid获取用户自己的待保存名片数量
	 * @param uid
	 * @return
	 */
	public Map getAwaitAddCardCount(String uid)throws Exception;
	/**
	 * 根据cardcase_cardid  得到名片原始主人cardcase_uid  
	 * @param cid
	 * @return
	 * @throws Exception
	 */
	public Map getPrimitiveUid(String cid)throws Exception;
}
