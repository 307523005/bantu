package com.bantu.ssm.dao.mycard;

import java.util.List;
import java.util.Map;

import com.bantu.ssm.entity.mycard.Cardcase;
import com.bantu.ssm.entity.mycard.Mycard;
/**
 * 我的名片
 * @author Administrator
 *
 */
public interface MycardDao {
	/**
	 * 添加名片夹
	 * @param cardcase
	 * @return
	 */
	public int addCard(Cardcase cardcase);
	/**
	 * 删除名片夹名片
	 * @param mycard
	 * @return
	 */
	public int delete(Cardcase cardcase);
	/**
	 * 删除名片信息
	 * @param mycard
	 * @return
	 */
	public int deleteMycard(Mycard mycard);
	

	/**
	 * 手动添加名片
	 * @param mycard
	 * @return
	 */
	public int addCardcase(Mycard mycard);


	/**
	 * 修改名片
	 * @param mycard
	 * @return
	 */
	public int update(Mycard mycard);
	/**
	 * 根据名片id和用户uid获取名片
	 * @param id
	 * @return
	 */
	public List<Mycard> getCardByCardId(Mycard mycard);
	/**
	 * 判断是不是自己编辑的
	 * @param id
	 * @return
	 */
	public List<Mycard> getIscompile(Mycard mycard);
	/**
	 * 根据用户uid获取用户自己的名片
	 * @param id
	 * @return
	 */
	public List<Mycard> getMycardByUid(String uid);
	/**
	 * 根据用户uid获取用户自己的待保存名片
	 * @param uid
	 * @return
	 */
	public List<Mycard> getAwaitAddCard(String uid);
	/**
	 * 根据用户uid获取用户自己的待保存名片数量
	 * @param uid
	 * @return
	 */
	public int getAwaitAddCardCount(String uid);
	/**
	 * 根据用户uid获取用户自己的名片夹名片
	 * @param uid
	 * @return
	 */
	public List<Mycard> getMyCardcaseByUid(String uid);
	/**
	 * 将自己的名片发送给他人为待保存名片
	 * @param cardcase
	 * @return
	 */
	public int awaitAddCard(Cardcase cardcase);
	/**
	 * 保存，待保存名片
	 * @param cardcase
	 * @return
	 */
	public int saveAwaitAddCard(Cardcase cardcase);
	/**
	 * 根据cardcase_cardid  得到名片原始主人cardcase_uid  
	 * @param cid
	 * @return
	 */
	public List<Cardcase> getPrimitiveUid(String cid);
}
