package com.bantu.ssm.dao.wx;

import com.bantu.ssm.entity.wx.RedPacket;

public interface RedPacketDao {
	/**
	 * 添加红包
	 * @param redPacket
	 * @return
	 */
	public int addRedPacket(RedPacket redPacket);
	/**
	 * 领取红包
	 * @param redPacket
	 * @return
	 */
	public int receiveRedPacket(RedPacket redPacket);
}
