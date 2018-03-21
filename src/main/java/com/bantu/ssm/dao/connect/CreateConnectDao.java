package com.bantu.ssm.dao.connect;

import java.util.List;
import java.util.Map;

import com.bantu.ssm.entity.specificCode.SpecificCode;

public interface CreateConnectDao {
	/**
	 * 生成名片连接
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String clickShare(String user_uid,String mycard_cardid,String redPacket) throws Exception;
	/**
	 * 得到所有信息
	 * @param map
	 * @return
	 */
	public List<SpecificCode>  getList(Map map);
	/**
	 * 添加新链接
	 * @param wxuser
	 * @return
	 */
	public int add(SpecificCode specificCode);
	/**
	 * 根据specificCode_code(唯一钥匙)获取用户信息
	 * @param openid
	 * @return
	 */
	public List<SpecificCode> getBySpecificCode_code(String specificCode_code);
}
