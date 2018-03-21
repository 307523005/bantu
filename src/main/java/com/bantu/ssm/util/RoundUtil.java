package com.bantu.ssm.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import com.bantu.ssm.util.wx.PayUtil;

/**
 * @ClassName: RoundUtil 
 * @Description: 随机数生成工具类
 * @author bob_zhang
 */
public class RoundUtil {

/**
* @Title: getUUID32 
* @Description: 获取UUID 获取一个32位的随机数，并且不会重复
* @param @return 设定文件 
* @return String 返回类型 
* @throws
*/
public static String getUUID32() {
String uuid = UUID.randomUUID().toString(); 
//8+4+4+4+12
uuid = uuid.substring(0,8)+uuid.substring(9,13)+uuid.substring(14,18)+uuid.substring(19,23)+uuid.substring(24,36); 
        return uuid;
}
/**
* @Title: getUUID28 
* @Description: 获取UUID 获取一个32位的随机数，并且不会重复
* @param @return 设定文件 
* @return String 返回类型 
* @throws
*/
public static String getUUID28() {
String uuid = UUID.randomUUID().toString(); 
//8+4+4+4+8
uuid = uuid.substring(0,8)+uuid.substring(9,13)+uuid.substring(14,18)+uuid.substring(19,23)+uuid.substring(24,32); 
        return uuid;
}
/**
* @Title: getUUID16
* @Description: 获取UUID 获取一个32位的随机数，并且不会重复
* @param @return 设定文件 
* @return String 返回类型 
* @throws
*/
public static String getUUID16() {
String uuid = UUID.randomUUID().toString(); 
//8+4+4
uuid = uuid.substring(0,8)+uuid.substring(9,13)+uuid.substring(14,18); 
        return uuid;
}
/**
* @Title: getUUID12
* @Description: 获取UUID 获取一个12位的随机数，并且不会重复
* @param @return 设定文件 
* @return String 返回类型 
* @throws
*/
public static String getUUID12() {
String uuid = UUID.randomUUID().toString(); 
//8+4
uuid = uuid.substring(0,8)+uuid.substring(9,13); 
        return uuid;
}
/**
* @Title: getCode 
* @Description: 随机生成4位验证码
* @param @return 设定文件 
* @return String 返回类型 
* @throws
*/
public static String getCode() {
int i = (int) (Math.random() * 9000 + 1000);
return i + "";
}
/** 
 * 生成商户订单号 
 * @param mch_id  商户号 
 * @param userId  该用户的userID 
 * @return 
 */  
public static String orderNo28(String userId){  
    //组成： mch_id+yyyymmdd+10位一天内不能重复的数字  
    //因为每个用户绑定了userId,他们的userId不同,时间+随机数  
    String nowTime= new SimpleDateFormat("yyyyMMdd").format(new Date());  
    int length = 10 - userId.length();  
    return    userId +nowTime+ getCode();  
}  
/** 
 * 生成特定位数的随机数字 
 * @param length 
 * @return 
 */  
public static String getRandomNum(int length) {  
    String val = "";  
    Random random = new Random();  
    for (int i = 0; i < length; i++) {  
        val += String.valueOf(random.nextInt(10));  
    }  
    return val;  
}  

/**
 * 生成用户特定的随机码/分享名片用key=  +MD5运算生成签名
 * 28+userid
 */
public static String specificCode(String userId){  
	String aa =getUUID28()+userId;
	// MD5运算生成签名
    return    PayUtil.sign(aa,aa,"utf-8").toUpperCase();  
}  
public static void main(String[] args) {
String time= new SimpleDateFormat("yyMMddHHmmssSSSSSSSSSSS").format(new Date());
System.out.println(time);
String time2= new SimpleDateFormat("yyMMddHHmmssSSSS").format(new Date());
System.out.println(time2);
String time3= new SimpleDateFormat("yyMMddHHmmssSSSS").format(new Date());
System.out.println(time3);
String time4= new SimpleDateFormat("yyMMddHHmmssSSSS").format(new Date());
System.out.println(time4);
String time5= new SimpleDateFormat("yyMMddHHmmssSSSS").format(new Date());
System.out.println(time5);
String time6= new SimpleDateFormat("yyMMddHHmmssSSSS").format(new Date());
System.out.println(time6);
String time7= new SimpleDateFormat("yyMMddHHmmssSSSS").format(new Date());
System.out.println(time7);
String time8= new SimpleDateFormat("yyMMddHHmmssSSSS").format(new Date());
System.out.println(time8);
}
}