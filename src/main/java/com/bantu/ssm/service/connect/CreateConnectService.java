package com.bantu.ssm.service.connect;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.bantu.ssm.entity.specificCode.SpecificCode;

public interface CreateConnectService {
	/**
	 * 生成名片连接
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String clickShare(String user_uid,String mycard_cardid,String redPacket, String mycard_templateid,HttpSession session) throws Exception;
	/**
	 * 得到所有信息
	 * @param map
	 * @return
	 */
	public List<SpecificCode>  getList(Map map)throws Exception ;
	/**
	 * 添加新链接
	 * @param wxuser
	 * @return
	 */
	public int add(SpecificCode specificCode)throws Exception ;
	/**
	 * 根据specificCode_code(唯一钥匙)获取用户信息
	 * @param openid
	 * @return
	 */
	public List<SpecificCode> getBySpecificCode_code(String specificCode_code)throws Exception ;
	/**
	 * 得到名片信息，是否有红包
	 * @param k
	 * @param uid
	 * @param cid
	 * @return
	 * @throws Exception
	 */
	public Map getCardAadRedPacket(String k, String uid, String cid) throws Exception ;
}
