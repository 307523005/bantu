package com.bantu.ssm.service.xunfei;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface XunFeiService {//MultipartFile multi
	 public Map<String,String> speechRecognition()throws Exception;
}
