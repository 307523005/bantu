package com.bantu.ssm.service.ocr;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import com.bantu.ssm.entity.wxuser.Wxuser;
public interface OCRService {
	/**
	* 接收名片图片，返回文字信息
	* 因为百度返回的数据中文为text/plain;charset=ISO-8859-1，所有要 用   produces="text/html;charset=UTF-8"  转换编码格式
	* @param wxuser
	* @param pic
	* @param session
	* @return
	* @throws Exception 
	*/
		public Map getTextByOrc(Wxuser wxuser,  MultipartFile[] pic,HttpSession session) throws Exception;
		public Object getTextBypSpeech(Wxuser wxuser,  MultipartFile[] pSpeech,HttpSession session) throws Exception;
}
