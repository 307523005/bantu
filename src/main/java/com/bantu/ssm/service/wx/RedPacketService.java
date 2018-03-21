package com.bantu.ssm.service.wx;


public interface RedPacketService {
	public int sendRedPacket(String openId, String money,String orderNo,String userId)  throws Exception;
	
}
