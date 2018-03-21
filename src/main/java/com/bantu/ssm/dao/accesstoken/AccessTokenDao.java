package com.bantu.ssm.dao.accesstoken;

import java.util.List;

import com.bantu.ssm.entity.wx.AccessToken;

public interface AccessTokenDao {
	/**
	 * 
	 * @return
	 */
	public List<AccessToken>  getList();
	/**
	 * 
	 * @param accessToken
	 * @return
	 */
	public int update(AccessToken accessToken);
}
