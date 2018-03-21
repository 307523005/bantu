package com.bantu.ssm.controller.ocr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bantu.ssm.entity.wxuser.Wxuser;
import com.bantu.ssm.service.ocr.OCRService;
import com.bantu.ssm.util.Result;

@Controller
@RequestMapping("/bantuOCR")
public class OCRController {
	@Resource
	private OCRService ocrService;
	/**
	 * 接收名片图片，返回文字信息
	 * 因为百度返回的数据中文为text/plain;charset=ISO-8859-1，所有要 用   produces="text/html;charset=UTF-8"  转换编码格式
	 * @param wxuser
	 * @param pic
	 * @param session
	 * @return
	 * @throws Exception 
	 */
		@ResponseBody
		@RequestMapping(value ="/getTextByOrc", method = RequestMethod.POST)
		public 	Map getTextByOrc(String mycard_uid, @RequestParam(required = false) MultipartFile[] pic,HttpSession session) throws Exception {
			System.out.println("-----mycard_uid-------mycard_uid*----"+mycard_uid);
			Wxuser wxuser = new Wxuser();
			wxuser.setUser_uid(mycard_uid);
			Map textByOrc = ocrService.getTextByOrc( wxuser,pic, session);
			return textByOrc;
	}
		/**
		 * 百度语音识别
		 * @param wxuser
		 * @param pSpeech
		 * @param session
		 * @return
		 * @throws Exception
		 */
		@ResponseBody
		@RequestMapping(value ="/getTextBypSpeech", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
		public Object getTextBypSpeech(Wxuser wxuser, @RequestParam(required = false) MultipartFile[] pSpeech,HttpSession session) throws Exception {
			
			Object textByOrc = ocrService.getTextBypSpeech(wxuser, pSpeech, session);
			return textByOrc;
		}
		
}
