package com.bantu.ssm.util.ocr;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
/**
 * 取得给定汉字串的首字母串
 * @author Administrator
 *
 */

public class ChineseInitalTwo {
	public static final Map<String, String> name = new HashMap<String, String>();
	static {
		name.put("重", "c");
		name.put("区", "o");
		name.put("仇", "q");
		name.put("秘", "b");
		name.put("冼", "x");
		name.put("解", "x");
		name.put("折", "s");
		name.put("单", "s");
		name.put("朴", "p");
		name.put("翟", "z");
		name.put("查", "z");
		name.put("盖", "g");
		name.put("万俟", "mq");
		name.put("单于", "cy");
		name.put("尉迟", "yc");
		name.put("妞", "n");
		name.put("嗯", "n");
	}
	/**
	 * 取得给定汉字串的首字母串
	 * 
	 * @param str
	 *            给定汉字串
	 * @return 
	 */
	public static String getFirstOne(String str) {
		String _str = "";
		String specialNames = specialName(str);
			// 判断是不是特殊形式
			if (!specialNames.equals("")) {
				System.out.println("特殊形式特殊形式特殊形式特殊形式特殊形式"+str);
				str = str.replaceAll(specialNames, name.get(specialNames));
			}
			System.out.println("抓憨厚转换后转换后转换后转换后转换后v"+str);
			for (int i = 0; i < str.length(); i++) {
				String strs = str.substring(i, i + 1);
				// 判断是不是字母
				if (EnglishText(strs)) {
					//全小写
					_str = _str + strs.toLowerCase();
				}else {
					_str=_str + converterToFirstSpell(strs);
				}
			}
	
		return _str;
	}
	/**
	 * 判断是不是特殊姓氏
	 * 
	 * @param name
	 * @return
	 */
	public static String specialName(String name) {
		String str = "";
		/*
		 * 重：Chóng 音崇； 区：ōu 音欧； 仇：Qiú 音求； 秘：Bì 音闭； 冼：Xiǎn 音显； 解：Xiè 音谢； 折：Shè
		 * 音舌； 单：Shàn 音善； 朴：Piáo 音瓢； 翟：Zhá 音宅； 查：Zhā 音渣； 盖：gě； 万俟：Mò qí 音莫奇；
		 * 尉迟：Yù chí 音玉迟；等等。
		 */
		String[] surName = {  "尉迟", "单于","重", "区", "仇", "秘", "冼", "解", "折","单", "朴",
				"翟", "查", "盖" ,"妞","嗯",};

		for (String s : surName) {
			String s1 = s.substring(0, 1);
			if (name.contains(s)) {
				return str = s;
			}
		}
		return str ;

	}

	// 判断一个字符串的首字符是否为字母
	public static boolean EnglishText(String s) {
		char c = s.charAt(0);
		int i = (int) c;
		if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122)) {
			return true;
		} else {
			return false;
		}
	}
	public static void main(String[] args) {
	String c="向蓬";
	char b;
	int a=c.length();
	for(int k=0;k<a;k++){
	b=c.charAt(k);
	String d = String.valueOf(b);
	String str = converterToFirstSpell(d);
	String s = str.toUpperCase();
	String g=s;
	char h;
	int j=g.length();
	for(int y=0;y<=0;y++){
	h=g.charAt(0);
	System.out.println(h);
	}
	}
	/*System.out.println(converterToFirstSpell("向蓬"));
	String str = converterToFirstSpell("向蓬");
	String s = str.toUpperCase();
	System.out.println(s);*/
	}
	public static String converterToFirstSpell(String chines) { 
	String pinyinName = ""; 
	char[] nameChar = chines.toCharArray(); 
	HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat(); 
	defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); 
	defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 
	for (int i = 0; i < nameChar.length; i++) { 
	String s = String.valueOf(nameChar[i]); 
	if (s.matches("[\\u4e00-\\u9fa5]")) { 
	try { 
	String[] mPinyinArray = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat); 
	pinyinName += mPinyinArray[0]; 
	} catch (BadHanyuPinyinOutputFormatCombination e) { 
	e.printStackTrace(); 
	} 
	} else { 
	pinyinName += nameChar[i]; 
	} 
	} 
	return pinyinName.substring(0, 1);
	}
	}