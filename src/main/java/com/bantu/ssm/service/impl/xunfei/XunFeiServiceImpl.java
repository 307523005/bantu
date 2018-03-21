package com.bantu.ssm.service.impl.xunfei;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bantu.ssm.service.xunfei.XunFeiService;
import com.bantu.ssm.util.xunfei.SR2Words;
import com.bantu.ssm.util.xunfei.SRTool;
import com.iflytek.cloud.speech.DataUploader;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechListener;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.cloud.speech.UserWords;

@Service
public class XunFeiServiceImpl implements XunFeiService{

	@Override//MultipartFile multi
	public Map<String, String> speechRecognition() throws Exception {
		String aa = "单,继,坤";
		uploadUserWords(aa);
		// TODO Auto-generated method stub
		  Map<String,String> map =new HashMap();
	        UUID uuid = UUID.randomUUID();
	      /*  String path = "/home/workspace/audio";
	        String fileName = uuid.toString()+".silk";
	        //临时silk文件
	        String tempFile = "/home/workspace/audio/"+uuid.toString()+".silk";
	        //中间过渡pcm文件
	        String pcmFile = "/home/workspace/audio/"+uuid.toString()+".pcm";
	        //可识别的wav文件
	        String wavFile = "/home/workspace/audio/"+uuid.toString()+".wav";
	        File file = new File(path,fileName);
	        try {
	            multi.transferTo(file);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }*/
	        /*移除临时silk文件首字节start*/
	        //标准silk文件
	     /*   String silkFile = FFMPEGUtil.silk_remove_word(tempFile);
	        移除临时silk文件首字节end
	        //silk文件转换成pcm文件
	        String silk2Pcm = FFMPEGUtil.silk2Pcm(silkFile, pcmFile);
	        //pcm文件转换成wav文件
	        String pcm2Wav = FFMPEGUtil.pcm2Wav(silk2Pcm, wavFile);*/
	        
	        String pcm = "F:/pSpeech/uploadfilesimages/pSpeech/626f4f00-c099-4d39-b244-e968d9438fd5.pcm";
	        
	        
	        //讯飞语音识别接口识别wav音频文件，转成文字返回
	        SRTool sr = new SRTool();
	        String words = null;
	        try {
	            words = sr.voice2words(pcm);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        System.out.println("讯飞识别的语音结果："+words);
	        if("".equals(words)){
	            System.out.println("讯飞识别的语音结果：null");
	            map.put("status","error");
	            map.put("content","对不起，请您在描述一遍！");
	            return map;
	        }
	        String result = SR2Words.sr2words(words);
	        System.out.println("讯飞识别的语音结果："+result);
	        map.put("status","success");
	        map.put("content",result);
	        return map;
	}
	private void uploadUserWords(String aa) throws Exception {
		SpeechUtility.createUtility("appid=5a4addc5");//申请的appid
DataUploader  dataUploader = new DataUploader();

		UserWords userwords = new UserWords(aa);
		byte[] datas = userwords.toString().getBytes("utf-8");
		
		dataUploader.setParameter(SpeechConstant.DATA_TYPE, "userword");
		dataUploader.uploadData(speechListener, "userwords", datas);
		System.out.println(dataUploader.getParameter("userwords")+"+++++++++++++++++++++++++");
	}
	/**
	 * 词表上传监听器
	 */
	SpeechListener speechListener = new SpeechListener() {
		
		@Override
		public void onEvent(int arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onCompleted(SpeechError arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onBufferReceived(byte[] arg0) {
			// TODO Auto-generated method stub
			
		}
	};
}
