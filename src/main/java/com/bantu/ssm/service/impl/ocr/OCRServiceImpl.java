package com.bantu.ssm.service.impl.ocr;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.map.LinkedMap;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.baidu.aip.ocr.AipOcr;
import com.bantu.ssm.entity.wxuser.Wxuser;
import com.bantu.ssm.service.ocr.OCRService;
import com.bantu.ssm.util.FileUploadUtil;
import com.bantu.ssm.util.Result;
import com.bantu.ssm.util.ocr.TwoTextCategorizationUtil;
import com.bantu.ssm.util.wx.DefinedChars;

@Service
public class OCRServiceImpl implements OCRService {

	// 设置APPID/AK/SK 获取图片文字用*（帅账号）
	
	
	/*  public static final String APP_ID = "10579212"; 
	  public static final  String API_KEY = "r12asEFpeSssg9kQ7LOE7Ioy";
	  public static final String SECRET_KEY = "rjxGShGFjFrTAPaEA4TFGwH9hcKm7Rdt";*/
	 
	//(亚飞账号)

	/*  public static final String APP_ID = "10740728"; 
	  public static final  String API_KEY = "4T1PpwYFCwslh8kMM82ipHWd";
	  public static final String SECRET_KEY = "1ElaSPWqdhgSAIRMEHdRNSCLhyKnxbXa";*/
	// 设置APPID/AK/SK 获取语音文字用(钮豪账号)
/*	public static final String APP_ID = "10601988";
	public static final String API_KEY = "8Xu4qyjFbQEKktGX87i7ApVO";
	public static final String SECRET_KEY = "mQfy3qwgYoalOLPeQePhugcjxQeSsUQT";*/

	/**
	 * 接收名片图片，返回文字信息 因为百度返回的数据中文为text/plain;charset=ISO-8859-1，所有要 用
	 * produces="text/html;charset=UTF-8" 转换编码格式
	 * 
	 * @param wxuser
	 * @param pic
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public Map getTextByOrc(Wxuser wxuser, MultipartFile[] pic,
			HttpSession session) throws Exception {
		Map resObjectMap = new HashMap();

		// 返回结果参数
		String results = null;
		JSONObject res = null;
		// List<Map> lists = new ArrayList();
		Map map = new LinkedMap();
		// 如果有图片
		if (pic != null && pic.length > 0) {
			// 如果多张，循环
			for (MultipartFile multipartFile : pic) {
				if (multipartFile != null && !multipartFile.isEmpty()) {
					// 将图片保存在本地文件夹
					// E:/tomcat/apache-tomcat-7.0.78/webapps/bantu/uploadfilesimages/wxuser
					// 下
					String uploadFile = FileUploadUtil.uploadFile(
							multipartFile, session, "wxuser");
					AipOcr client = new AipOcr(DefinedChars.OCRAPP_ID, DefinedChars.OCRAPI_KEY, DefinedChars.OCRSECRET_KEY);
					// 传入可选参数调用接口
					HashMap<String, String> options = new HashMap<String, String>();
					options.put("language_type", "CHN_ENG");
					options.put("detect_direction", "true");
					options.put("detect_language", "true");
					options.put("probability", "true");
					// 参数为本地图片路径
					String image = DefinedChars.OCRimageURL
							+ uploadFile;
					// 通用文字识别接口, 用户向服务请求识别某张图中的所有文字
					 //res = client.basicGeneral(image, options);
					// 通用文字识别（高精度版）接口
					// 用户向服务请求识别某张图中的所有文字，相对于通用文字识别该产品精度更高，但是识别耗时会稍长。
					res = client.basicAccurateGeneral(image, options);
					System.out.println(res.toString(2)
							+ "---------ocr返回数据---------");
					// ORC返回数据分类
					Map textCategorization = TwoTextCategorizationUtil
							.TextCategorization(res);
					if (!(res.get("words_result")).equals("")) {
						resObjectMap.put("res", Result.OK_MAP);
						resObjectMap.put("resObject", textCategorization);
					}
					
					return resObjectMap;
				}
			}
		}
		return map;

	}

	/**
	 * 百度语音识别
	 */
	@Override
	public String getTextBypSpeech(Wxuser wxuser, MultipartFile[] pSpeech,
			HttpSession session) throws Exception {
		/*JSONObject asrRes = null;
		for (MultipartFile multipartFile : pSpeech) {
			if (multipartFile != null && !multipartFile.isEmpty()) {
				// 将图片保存在本地文件夹
				// E:/tomcat/apache-tomcat-7.0.78/webapps/bantu/uploadfilesimages/pSpeech
				// 下
				String uploadFile = FileUploadUtil.uploadFile(multipartFile,
						session, "pSpeech");
				System.out.println("pSpeech--------" + uploadFile);
				// 初始化一个AipSpeech
				AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
				// 调用接口
				String path = "E:/tomcat/apache-tomcat-7.0.78/webapps/bantu/"
						+ uploadFile;
				// String pcm
				// ="F:\\pSpeech\\"+uploadFile.replace(".silk",".pcm").replace("/","\\");
				String pcm = "F:/pSpeech/uploadfilesimages/pSpeech/d024bf8c-fea9-4ab9-9c06-a95f1904f17e.pcm";
				// getPcm(path.replace("/","\\"), pcm);
				asrRes = client.asr(pcm, "pcm", 16000, null);
				return asrRes.toString(2);
			}
		}*/

		return null;
	}

	/**
	 * F:\\silk_v3_decoder.exe E:\\tomcat\\apache-tomcat-7.0.78\\webapps\\bantu\\uploadfilesimages\\pSpeech\\626f4f00-c099-4d39-b244-e968d9438fd5.silk
	 * F:\\pSpeech\\uploadfilesimages\\pSpeech\\626f4f00-c099-4d39-b244-e968d9438fd5.pcm-quiet
	 * 解码为pcm格式
	 * @param silk
	 *            源silk文件,需要绝对路径!! 例:F:\zhuanma\vg2ub41omgipvrmur1fnssd3tq.silk
	 * @param pcm
	 *            目标pcm文件,需要绝对路径!! 例:F:\zhuanma\vg2ub41omgipvrmur1fnssd3tq.pcm
	 * @return
	 */
	/*public static String getPcm(String silk, String pcm) {
		boolean flag = true;
		String cmd = "cmd.exe /c F:\\silk_v3_decoder.exe " + silk + " " + pcm
				+ " -quiet";
		try {
			StringBuilder msg = Lang.execOutput(cmd, Encoding.CHARSET_GBK);
		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
		}
		return pcm;
	}*/

}
