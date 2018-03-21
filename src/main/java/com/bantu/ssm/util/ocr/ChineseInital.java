package com.bantu.ssm.util.ocr;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**（弃用）
 * 取得给定汉字串的首字母串,即声母串 * 注：只支持GB2312字符集中的汉字
 * 
 */
public class ChineseInital {
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
	private final static int[] areaCode = { 1601, 1637, 1833, 2078, 2274, 2302,
			2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027,
			4086, 4390, 4558, 4684, 4925, 5249, 5590 };
	private final static String[] letters = { "a", "b", "c", "d", "e", "f",
			"g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
			"w", "x", "y", "z" };

	/**
	 * 取得给定汉字串的首字母串
	 * 
	 * @param str
	 *            给定汉字串
	 * @return 所有字符的首字母
	 */
	public static String getAllFirstLetter(String str) {
		if (str == null || str.trim().length() == 0) {
			return "";
		}

		String _str = "";
		for (int i = 0; i < str.length(); i++) {
			_str = _str + getFirstLetter(str.substring(i, i + 1));
		}

		return _str;
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
				// 判断是不是汉字
				if (ChineseText(strs)) {
					_str = _str + getFirstLetter(strs);
				}else if (EnglishText(strs)) {
					//全小写
					_str = _str + strs.toLowerCase();
				}else {
					_str="#";
				}
			}
	
		return _str;
	}

	/**
	 * 取得给定汉字的首字母,即声母
	 * 
	 * @param chinese
	 *            给定的汉字
	 * @return 给定汉字的声母
	 */
	public static String getFirstLetter(String chinese) {
		if (chinese == null || chinese.trim().length() == 0) {
			return "";
		}
		chinese = conversionStr(chinese, "GB2312", "ISO8859-1");

		if (chinese.length() > 1) // 判断是不是汉字
		{
			int li_SectorCode = (int) chinese.charAt(0); // 汉字区码
			int li_PositionCode = (int) chinese.charAt(1); // 汉字位码
			li_SectorCode = li_SectorCode - 160;
			li_PositionCode = li_PositionCode - 160;
			int li_SecPosCode = li_SectorCode * 100 + li_PositionCode; // 汉字区位码
			if (li_SecPosCode > 1600 && li_SecPosCode < 5590) {
				for (int i = 0; i < 23; i++) {
					if (li_SecPosCode >= areaCode[i]
							&& li_SecPosCode < areaCode[i + 1]) {
						chinese = letters[i];
						break;
					}
				}
			} else // 非汉字字符,如图形符号或ASCII码
			{
				chinese = conversionStr(chinese, "ISO8859-1", "GB2312");
				chinese = chinese.substring(0, 1);
			}
		}

		return chinese;
	}

	/**
	 * 字符串编码转换
	 * 
	 * @param str
	 *            要转换编码的字符串
	 * @param charsetName
	 *            原来的编码
	 * @param toCharsetName
	 *            转换后的编码
	 * @return 经过编码转换后的字符串
	 */
	private static String conversionStr(String str, String charsetName,
			String toCharsetName) {
		try {
			str = new String(str.getBytes(charsetName), toCharsetName);
		} catch (UnsupportedEncodingException ex) {
			System.out.println("字符串编码转换异常：" + ex.getMessage());
		}
		return str;
	}

	// 判断是否为汉字
	public static boolean ChineseText(String str) {

		char[] chars = str.toCharArray();
		boolean isGB2312 = false;
		for (int i = 0; i < chars.length; i++) {
			byte[] bytes = ("" + chars[i]).getBytes();
			if (bytes.length == 2) {
				int[] ints = new int[2];
				ints[0] = bytes[0] & 0xff;
				ints[1] = bytes[1] & 0xff;

				if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40
						&& ints[1] <= 0xFE) {
					isGB2312 = true;
					break;
				}
			}
		}
		return isGB2312;
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

	public static boolean check(String fstrData) {
		char c = fstrData.charAt(0);
		if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
			return true;
		} else {
			return false;
		}
	}
}