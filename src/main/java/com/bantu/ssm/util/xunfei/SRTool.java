package com.bantu.ssm.util.xunfei;

 
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.iflytek.cloud.speech.RecognizerListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechRecognizer;
import com.iflytek.cloud.speech.SpeechUtility;
 
/**
 * Created by songzs on 2017/12/4.
 */
public class SRTool {
 
    private int perWaitTime = 100;
 
    private StringBuffer mResult = new StringBuffer();
 
    static {
        SpeechUtility.createUtility("appid=5a4addc5");//申请的appid
    }
 
    public String voice2words(String fileName) throws InterruptedException, IOException {
        return to(fileName);
    }
 
    public String to(String fileName) throws InterruptedException, IOException {
 
        File file = new File(fileName);
        if(!file.exists()){
            throw new RuntimeException("要读取的文件不存在");
        }
        FileInputStream fis = new FileInputStream(file);
        int len = 0;
        byte[] buf = new byte[fis.available()];
        fis.read(buf);
        fis.close();
 
        //1.创建SpeechRecognizer对象
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer();
        //2.设置听写参数，详见《MSC Reference Manual》SpeechConstant类
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");
        mIat.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
        //3.开始听写
        mIat.startListening(mRecoListener);
 
        //voiceBuffer为音频数据流，splitBuffer为自定义分割接口，将其以4.8k字节分割成数组
        ArrayList<byte[]> buffers = splitBuffer(buf, buf.length, 4800);
        for (int i = 0; i < buffers.size(); i++) {
            // 每次写入msc数据4.8K,相当150ms录音数据
            mIat.writeAudio(buffers.get(i), 0, buffers.get(i).length);
        }
        mIat.stopListening();
 
        while (mIat.isListening()) {
            Thread.sleep(perWaitTime);
        }
        return mResult+"";
    }
 
    /**
     * 将字节缓冲区按照固定大小进行分割成数组
     *
     * @param buffer 缓冲区
     * @param length 缓冲区大小
     * @param spsize 切割块大小
     * @return
     */
    private ArrayList<byte[]> splitBuffer(byte[] buffer, int length, int spsize) {
        ArrayList<byte[]> array = new ArrayList<byte[]>();
        if (spsize <= 0 || length <= 0 || buffer == null
                || buffer.length < length)
            return array;
        int size = 0;
        while (size < length) {
            int left = length - size;
            if (spsize < left) {
                byte[] sdata = new byte[spsize];
                System.arraycopy(buffer, size, sdata, 0, spsize);
                array.add(sdata);
                size += spsize;
            } else {
                byte[] sdata = new byte[left];
                System.arraycopy(buffer, size, sdata, 0, left);
                array.add(sdata);
                size += left;
            }
        }
        return array;
    }
 
    //听写监听器
    private RecognizerListener mRecoListener = new RecognizerListener() {
		
		@Override
		public void onVolumeChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		 public void onResult(RecognizerResult results, boolean isLast) {
            System.out.println("Result:" + results.getResultString());
            mResult.append(results.getResultString());
        }
		
		@Override
		public void onEvent(int arg0, int arg1, int arg2, String arg3) {
			// TODO Auto-generated method stub
			
		}
        //会话发生错误回调接口
		@Override
		public void onError(SpeechError error) {
			// TODO Auto-generated method stub
		    System.out.println(error.getErrorCode()+"=========="+error.getErrorDesc());
            System.out.println(error);
		}
		
		@Override
		public void onEndOfSpeech() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onBeginOfSpeech() {
			// TODO Auto-generated method stub
			
		}
    };
}
