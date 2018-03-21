package com.bantu.ssm.controller.wx;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bantu.ssm.service.wx.PaymentService;
import com.bantu.ssm.util.wx.PayUtils;
/**
 * 微信小程序支付 一
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/wxpays")
public class PaymentController {

	@Resource
	private PaymentService paymentService;
	 /**
	  * 返回通知方法
	  * @param request
	  * @param response
	  * @throws Exception
	  */
	@ResponseBody  
    @RequestMapping(value="/wxNotify")  
    public void wxNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{  
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));  
        String line = null;  
    	String key = "123";
        StringBuilder sb = new StringBuilder();  
        while((line = br.readLine()) != null){  
            sb.append(line);  
        }  
        //sb为微信返回的xml  
        String notityXml = sb.toString();  
        String resXml = "";  
        System.out.println("接收到的报文：" + notityXml);  
      
        Map map = PayUtils.doXMLParse(notityXml);  
          
        String returnCode = (String) map.get("return_code");  
        if("SUCCESS".equals(returnCode)){  
            //验证签名是否正确  
            if(PayUtils.verify(PayUtils.createLinkString(map), (String)map.get("sign"), key, "utf-8")){  
                /**此处添加自己的业务逻辑代码start**/  
                  
                  
                /**此处添加自己的业务逻辑代码end**/  
                //通知微信服务器已经支付成功  
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
            }  
        }else{  
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
            + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
        }  
        System.out.println(resXml);  
        System.out.println("微信支付回调数据结束");  
  
  
        BufferedOutputStream out = new BufferedOutputStream(  
                response.getOutputStream());  
        out.write(resXml.getBytes());  
        out.flush();  
        out.close();  
    }  
    /**
     * 支付方法
     * @param openid
     * @param appid
     * @param mch_id
     * @param request
     * @return
     */
    @ResponseBody  
 @RequestMapping(value="/wxPay")  
    public Map wxPay(String openid,String appId,String total_fee,HttpServletRequest request )throws Exception{
    	System.out.println("---------------进入wxPay:openid---"+openid+"appId---"+appId+"total_fee"+total_fee);
	 Map wxPay = paymentService.wxPay(openid, appId,total_fee,  request);

    	return wxPay;
    }
}
