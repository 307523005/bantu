package com.bantu.ssm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *className:ParamsUtil.java
  *author:钮豪
 *createTime:2018-2-5
 *version:1.0.0
 */
public class ParamsUtil {

	private Integer page;	//ҳ��
	private Integer rows;	//����
	private String sort;	//����
	private String order;	//������ߵ���
	private String parama;	//����a
	private String paramb;	//����b
	private String paramc;
	private String paramd;
	private String parame;
	private String paramf;
	private String paramg;
	Map map =null;
	/**
	 * @return
	 */
	public Map getParamsMap(){
		map=new HashMap();
		if(page==null){
			page=1;
		}
		if(rows==null){
			rows=10;
		}
/*		map.put("begin", (page -1)*rows);
		map.put("end", page*rows+1);*/
		map.put("rows", rows);
		map.put("page", (page-1)*rows);
		map.put("sort", sort);
		map.put("order", order);
		map.put("parama", parama);
		map.put("paramb", paramb);
		map.put("paramc", paramc);
		map.put("paramd", paramd);
		map.put("parame", parame);
		map.put("paramf",paramf);
		map.put("paramg",paramg);
		return map;
	}
	/**
	 * ��װ����ֵ
	 * @param list
	 * @param totalCount
	 * @return
	 */
	public Map getResultMap(List list,Integer totalCount){
		map=new HashMap();
		map.put("total", totalCount);
		map.put("rows", list);
		return map;
	}
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getParama() {
		return parama;
	}
	public void setParama(String parama) {
		this.parama = parama;
	}
	public String getParamb() {
		return paramb;
	}
	public void setParamb(String paramb) {
		this.paramb = paramb;
	}
	public String getParamc() {
		return paramc;
	}
	public void setParamc(String paramc) {
		this.paramc = paramc;
	}
	public String getParamd() {
		return paramd;
	}
	public void setParamd(String paramd) {
		this.paramd = paramd;
	}
	public String getParame() {
		return parame;
	}
	public void setParame(String parame) {
		this.parame = parame;
	}
	public String getParamf() {
		return paramf;
	}
	public void setParamf(String paramf) {
		this.paramf = paramf;
	}
	public String getParamg() {
		return paramg;
	}
	public void setParamg(String paramg) {
		this.paramg = paramg;
	}
	
	
}
