package com.bantu.ssm.entity.wx;
/**
 * 红包表
 * @author Administrator
 *
 */
public class RedPacket {
	private Integer redpacket_id;//自增id
	private String redpacket_code;//红包唯一标示
	private String redpacket_payuid; //发放uid
	private String	redpacket_money;//红包金额
	private String redpacket_receiveopenId;//领取人openId
	private Integer redpacket_effectivity;//是否领取过
	private String redpacket_paytime;//红包生成时间
	private String redpacket_receivetime;//领取时间
	public Integer getRedpacket_id() {
		return redpacket_id;
	}
	public void setRedpacket_id(Integer redpacket_id) {
		this.redpacket_id = redpacket_id;
	}
	public String getRedpacket_code() {
		return redpacket_code;
	}
	public void setRedpacket_code(String redpacket_code) {
		this.redpacket_code = redpacket_code;
	}
	public String getRedpacket_payuid() {
		return redpacket_payuid;
	}
	public void setRedpacket_payuid(String redpacket_payuid) {
		this.redpacket_payuid = redpacket_payuid;
	}
	public String getRedpacket_money() {
		return redpacket_money;
	}
	public void setRedpacket_money(String redpacket_money) {
		this.redpacket_money = redpacket_money;
	}
	public String getRedpacket_receiveopenId() {
		return redpacket_receiveopenId;
	}
	public void setRedpacket_receiveopenId(String redpacket_receiveopenId) {
		this.redpacket_receiveopenId = redpacket_receiveopenId;
	}
	public Integer getRedpacket_effectivity() {
		return redpacket_effectivity;
	}
	public void setRedpacket_effectivity(Integer redpacket_effectivity) {
		this.redpacket_effectivity = redpacket_effectivity;
	}
	public String getRedpacket_paytime() {
		return redpacket_paytime;
	}
	public void setRedpacket_paytime(String redpacket_paytime) {
		this.redpacket_paytime = redpacket_paytime;
	}
	public String getRedpacket_receivetime() {
		return redpacket_receivetime;
	}
	public void setRedpacket_receivetime(String redpacket_receivetime) {
		this.redpacket_receivetime = redpacket_receivetime;
	}
	

}
