package com.bantu.ssm.util.ocr;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.map.LinkedMap;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * ocr返回文字数据分类
 * 
 * @author Administrator
 * 
 */
public class TextCategorizationUtil {

	public static Map TextCategorization(JSONObject OCRText) {
		Map map = new LinkedMap();
		JSONArray object = (JSONArray) OCRText.get("words_result");
		String mycard_name = "";// 姓名
		String mycard_position = "";// 职位
		String mycard_mobile = "";// 手机号
		String mycard_companyname = "";// 公司名称
		String mycard_phone = "";// 电话
		String mycard_mail = "";// 邮箱
		String mycard_add = "";// 地址
		String mycard_website = "";// 公司网址
		String exceptions = "";
		String object4 = "";
		for (int i = 0; i < object.length(); i++) {
			String object3 = (String) ((JSONObject) object.get(i)).get("words");

			// 去除所有空格,全小写
			String object2 = object3.replaceAll("\\s*", "").replaceAll(":", "")
					.replaceAll("-", "").replaceAll("\\(", "")
					.replaceAll("\\)", "").replaceAll("·", "")
					.replaceAll("・", "").toLowerCase();// ·・

			object4 += "+++++++++---------" + object2;
			// Map map = new TreeMap();
			/*
			 * if(object2.indexOf("1") != -1){ }else{ }
			 */
			// 是否包含职位字段
			String isPosition = screenPosition(object2);
			// 是否包含公司字段
			String isCompanyname = screenCompany(object2);
			// 是否存在手机号
			String isMobile = screenMobile(object2);
			// 是否存在电话号
			String isPhone = screenPhone(object2);
			// 死否存在地址字段
			String isAdd = screenSite(object2);
			// 筛选姓名，职位
			// 判断第一个字是否是单姓氏，同时不包含职位字段，长度小于4，
			if (screenSingleName(object2) && 1 < object2.length()
					&& object2.length() < 4 && isPosition == "") {// &&
				try {
					if (isPosition == "") {
						object2 = object2.replaceAll(isPosition, "");
						System.out.println(object2 + "object2" + isPosition
								+ "aaa---------aa");
						// map.put("mycard_name", object2);
						if (mycard_name.equals("")) {
							mycard_name += object2;
						}
					}

				} catch (Exception e) {
					exceptions = "姓氏出现异常";
					System.out.println("-*-*--*-*-Exception-*-*-姓氏");
				}
			}
			// 包含职位字段
			if (screenSingleName(object2) && isPosition != ""
					&& object2.length() > 3) {
				try {
					object2 = object2.replaceAll(isPosition, "");
					System.out.println(object2 + "object2" + isPosition
							+ "aaaaa+++screenSingleName+++++++++aaaa");
					// map.put("mycard_name", object2);
					// map.put("mycard_position", a);
					if (mycard_name.equals("")) {
						mycard_name += object2;
					}
					if (mycard_position.equals("")) {
						mycard_position += isPosition;
					}
				} catch (Exception e) {
					exceptions = "包含职位字段出现异常";
					System.out.println("-*-*--*-*-Exception-*-*-包含职位字段");
				}
			}
			/*
			 * // 包含职位字段开始
			 */if (isPosition != "") {// &&
				object2 = object2.replaceAll(isPosition, "");
				System.out.println(object2 + "object2" + isPosition
						+ "aaaaa*****isPosition********aaaa");
				// map.put("mycard_position", a);
				if (mycard_position.equals("")) {
					mycard_position += isPosition;
				}

			}
			/* // 职位字段结束 */
			/* // 包含公司字段开始，不存在楼，号 */
			if (isCompanyname != "" && isAdd == "") {// &&
				// object2.matches("^.*\\d{6}.*$")
				// object2 = object2.replaceAll(companyname, "");
				System.out.println(object2 + "object2" + isCompanyname
						+ "aaaaa*****sCompanyname********aaaa");
				// map.put("mycard_position", a);、
				if (mycard_companyname.equals("")) {
					mycard_companyname += object2;
				}
			}
			/* 公司名字段结束 */
			/* 地址字段开始 */
			if (isAdd != "") {
				String add = mycard_add;
				mycard_add += object2;
				if (mycard_add.length() < 51) {
					mycard_add = mycard_add;
				} else {
					mycard_add = add;
				}
				System.out.println(mycard_add
						+ "--------------isAdd--------isAdd");
			}
			/* 地址字段结束 */
			/* 网址字段开始 */
			try {
				if (object2.contains("网址") || object2.contains("www")
						|| object2.contains("com") || object2.contains(".cn")
						|| object2.contains("web") || object2.contains("net")
						|| object2.contains(".hk")) {
					// www开头
					if (object2.contains("www")) {
						// 得到第一个w的位置
						int wBegin = object2.indexOf("www");
						if (object2.contains(".cn")) {
							// 得到从第一个w开始在此字符串中最后一次出现的com的索引
							int mEnd = object2.indexOf(".cn");
							System.out.println(object2
									+ "------Add-----wBegin-" + wBegin
									+ "***mEnd*" + mEnd);
							if (mycard_website.equals("")) {
								mycard_website += object2.substring(wBegin,
										mEnd + 3);
							}
						}
						if (object2.contains("net")) {
							// 得到从第一个w开始在此字符串中最后一次出现的com的索引
							int mEnd = object2.indexOf("net");
							System.out.println(object2
									+ "------net-----wBegin-" + wBegin
									+ "***mEnd*" + mEnd);
							if (mycard_website.equals("")) {
								mycard_website += object2.substring(wBegin,
										mEnd + 3);
							}
						}
						if (object2.contains(".hk")) {
							// 得到从第一个w开始在此字符串中最后一次出现的com的索引
							int mEnd = object2.indexOf(".hk");
							System.out.println(object2
									+ "------net-----wBegin-" + wBegin
									+ "***mEnd*" + mEnd);
							if (mycard_website.equals("")) {
								mycard_website += object2.substring(wBegin,
										mEnd + 3);
							}
						}
						if (object2.contains("com")) {
							// 得到从第一个w开始在此字符串中最后一次出现的com的索引
							int mEnd = object2.indexOf("com");
							System.out.println(object2
									+ "--------Add---wBegin-" + wBegin
									+ "***mEnd*" + mEnd);
							if (mycard_website.equals("")) {
								mycard_website += object2.substring(wBegin,
										mEnd + 3);
							}
						}

					} else if (object2.indexOf("@") == -1) {// 不是www开头
						// 得到第一个数字或字母的位置
						Pattern pattern = Pattern.compile("[a-zA-Z0-9]");
						Matcher matcher = pattern.matcher(object2);
						int wBegin = 0;
						if (matcher.find()) {
							// 得到第一个
							wBegin = object2.indexOf(matcher.group());
						}
						if (mycard_website.equals("")) {
							mycard_website += object2.substring(wBegin);
						}

					}
				}
			} catch (Exception e) {
				exceptions = "包含网址字段,不存在@出现异常";
				System.out.println("-*-*--*-*-Exception-*-*-包含网址字段,不存在@");
			}
			/* 网址字段结束 */
			/* 邮箱字段开始 */
			if (object2.contains("@") || object2.contains("邮箱")
					|| object2.contains("email") || object2.contains("emai1")) {
				try {
					// 得到第一个数字或字母的位置
					Pattern pattern = Pattern.compile("箱");
					Matcher matcher = pattern.matcher(object2);
					int wBegin = 0;
					if (matcher.find()) {
						// 得到第一个
						wBegin = object2.indexOf(matcher.group());
					}
					if (object2.contains("com")) {
						int mEnd = object2.indexOf("com");
						if (mycard_mail.equals("")) {
							mycard_mail += object2.substring(wBegin + 1,
									mEnd + 3);
						}
					} else {
						// 得到第一个
						if (mycard_mail.equals("")) {
							mycard_mail += object2.substring(wBegin + 1,
									object2.length());
						}
					}
				} catch (Exception e) {
					exceptions = "箱字段出现异常";
					System.out.println("-*-*--*-*-箱");
				}
				try {
					// 得到第一个数字或字母的位置
					Pattern pattern = Pattern.compile("[a-zA-Z0-9]");
					Matcher matcher = pattern.matcher(object2);
					int wBegin = 0;
					if (matcher.find()) {
						// 得到第一个
						wBegin = object2.indexOf(matcher.group());
					}
					if (object2.contains("com")) {
						int mEnd = object2.indexOf("com");
						if (mycard_mail.equals("")) {
							mycard_mail += object2.substring(wBegin, mEnd + 3);
						}
					} else {
						// 得到第一个
						if (mycard_mail.equals("")) {
							mycard_mail += object2.substring(wBegin,
									object2.length());
						}
					}
				} catch (Exception e) {
					exceptions = "包含邮箱字段出现异常";
					System.out.println("-*-*--*-*-Exception-*-*-包含邮箱字段");
				}
			}
			/* 邮箱字段结束 */
			/* 手机字段开始 */
			if (isMobile != "") {
				try {
					System.out.println(object2
							+ "---------------mobile---123456789--*-*---*---");
					// 得到第一个1的位置
					Pattern patternOne = Pattern.compile("1");
					Matcher matcherOne = patternOne.matcher(object2);
					int wBeginOne = 0;
					if (matcherOne.find()) {
						// 得到第一个
						wBeginOne = object2.indexOf(matcherOne.group());
					}
					object2 = object2.substring(wBeginOne, wBeginOne + 11);
					System.out
							.println("-------object2----------object2--"
									+ object2);
					Pattern pattern = Pattern
							.compile("(?<!\\d)(?:(?:1[3578]\\d{8,9})|(?:861[3578]\\d{8,9}))(?!\\d)");
					Matcher matcher = pattern.matcher(object2);
					StringBuffer bf = new StringBuffer(64);
					while (matcher.find()) {
						bf.append(matcher.group()).append(",");
					}
					int len = bf.length();
					if (len > 0) {
						bf.deleteCharAt(len - 1);
					}
					if (mycard_mobile.equals("")) {
						mycard_mobile += bf.toString();
						System.out.println(object2
								+ "---------mycard_mobile*-*---*---"
								+ mycard_mobile);
					}
				} catch (Exception e) {
					exceptions = "是否包含手机字段出现异常";
					System.out.println("-*-*--*-*-Exception-*-*-是否包含手机字段");
				}
			}
			/* 手机字段结束 */
			/* 电话字段开始 */
			if (isPhone != "" || object2.contains("电话")) {
				try {
					// 得到第一个010的位置
					if (object2.contains("010")) {
						Pattern oneNum = Pattern.compile("010");
						Matcher oneMatcher = oneNum.matcher(object2);

						int phoneBegin = 0;
						if (oneMatcher.find()) {
							phoneBegin = object2.indexOf(oneMatcher.group());
						}
						object2 = object2
								.substring(phoneBegin, phoneBegin + 10);
						int phoneLength = 0;
						System.out.println(phoneBegin
								+ "----------010-phoneBegin---------"
								+ object2.length());
						// 如果总长度减去第一个出现010的
						if ((object2.length() - phoneBegin) > 10) {//
							phoneLength = phoneBegin + 11;
							object2 = object2
									.substring(phoneBegin, phoneLength);
							System.out.println(object2
									+ "---------010101010101++"
									+ phoneBegin + "*---*---" + phoneLength);
						} else if ((object2.length() - phoneBegin) > 9) {//
							phoneLength = phoneBegin + 10;
							object2 = object2
									.substring(phoneBegin, phoneLength);
							System.out.println(object2
									+ "----------01010101-++"
									+ phoneBegin + "*---*---" + phoneLength);
						} else {
							if ((object2.length() - phoneBegin) > 3) {
								phoneLength = object2.length() - phoneBegin;
								object2 = object2.substring(phoneBegin,
										phoneLength);
								System.out
										.println(object2
												+ "---------isPhone0101010---*---"
												+ phoneBegin + phoneLength);
							}
						}
					}
				} catch (Exception e) {
					exceptions = "是否包含电话字段010出现异常";
					System.out.println("-*-*--*-*-Exception-*-*是否包含电话字段");
				}
				try {
					// 得到第一个数字的位置
					if (object2.contains("03")) {
						Pattern oneNum = Pattern.compile("03");
						Matcher oneMatcher = oneNum.matcher(object2);
						int phoneBegin = 0;
						if (oneMatcher.find()) {
							phoneBegin = object2.indexOf(oneMatcher.group());
						}
						int phoneLength = 0;
						System.out.println(phoneBegin
								+ "-----------phoneBegin---------"
								+ object2.length());
						// 如果总长度减去第一个出现03的
						if ((object2.length() - phoneBegin) > 11) {// 4+8
							phoneLength = phoneBegin + 12;
							object2 = object2
									.substring(phoneBegin, phoneLength);
							System.out
									.println(object2
											+ "-----------------in)>11-03030*111-++"
											+ phoneBegin + "*---*---"
											+ phoneLength);
						} else if ((object2.length() - phoneBegin) > 10) {// 4+7
							phoneLength = phoneBegin + 11;
							object2 = object2
									.substring(phoneBegin, phoneLength);
							System.out
									.println(object2
											+ "------------in)>11-0303030*101010-++"
											+ phoneBegin + "*---*---"
											+ phoneLength);
						} else {
							if ((object2.length() - phoneBegin) > 2) {
								phoneLength = object2.length() - phoneBegin;
								object2 = object2.substring(phoneBegin,
										phoneLength);
								System.out
										.println(object2
												+ "-------------isPhone03030--*-*---*---"
												+ phoneBegin + phoneLength);
							}
						}
					}
				} catch (Exception e) {
					exceptions = "是否包含电话字段0303030303出现异常";
					System.out.println("-*-*--*-*-Exception-*-*是否包含电话字段");
				}
				try {
					if (object2.contains("86")) {
						Pattern oneNum = Pattern.compile("86");
						Matcher oneMatcher = oneNum.matcher(object2);
						int phoneBegin = 0;
						if (oneMatcher.find()) {
							phoneBegin = object2.indexOf(oneMatcher.group());
						}
						int phoneLength = 0;
						// 如果总长度减去第一个出现03的
						if ((object2.length() - phoneBegin) > 11) {
							phoneLength = phoneBegin + 12;
							object2 = object2
									.substring(phoneBegin, phoneLength);
							System.out.println(object2
									+ "---------in)>11-*8686-*---*---"
									+ phoneBegin);
						} else if ((object2.length() - phoneBegin) > 10) {
							phoneLength = phoneBegin + 11;
							object2 = object2
									.substring(phoneBegin, phoneLength);
							System.out.println(object2
									+ "------------in)>11-*8686-*---*---"
									+ phoneBegin);
						} else {
							if ((object2.length() - phoneBegin) > 2) {
								phoneLength = object2.length() - phoneBegin;
								object2 = object2.substring(phoneBegin,
										phoneLength);
								System.out
										.println(object2
												+ "-------------isPhone86868--*-*---*---"
												+ phoneBegin);
							}
						}
					}
				} catch (Exception e) {
					exceptions = "是否包含电话字段868686出现异常";
				}

				Pattern pattern = Pattern
						.compile("(?<!\\d)(?:(?:0[321]\\d{9,10})|(?:86[02][123]\\d{8,9}))(?!\\d)");
				Matcher matcher = pattern.matcher(object2);
				StringBuffer bf = new StringBuffer(64);
				while (matcher.find()) {
					bf.append(matcher.group()).append(",");
				}
				int len = bf.length();
				if (len > 0) {
					bf.deleteCharAt(len - 1);
				}
				if (mycard_phone.equals("")) {
					mycard_phone += bf.toString();
					System.out.println(object2
							+ "-----------ycard_phone*-*---*---"
							+ mycard_phone);

				}

			}
			/* 电话字段结束 */
		}
		map.put("mycard_name", mycard_name);
		map.put("mycard_position", mycard_position);
		map.put("mycard_mobile", mycard_mobile);
		map.put("mycard_companyname", mycard_companyname);
		map.put("mycard_phone", mycard_phone);
		map.put("mycard_mail",
				mycard_mail.replaceAll("邮箱", "").replaceAll("email", "")
						.replaceAll("emai1", ""));
		map.put("mycard_add",
				mycard_add.replaceAll("地址", "").replaceAll("addr", "")
						.replaceAll("add", ""));
		map.put("mycard_website", mycard_website.replaceAll("网址", "")
				.replaceAll("web", ""));
		map.put("exceptions", "**************" + exceptions);
		map.put("object4",
				"-----------------"
						+ object4);

		return map;
	}

	private static int lastIndexOf(String string, int wBegin) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static boolean screenSingleName(String name) {
		String[] surName = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈",
				"肖", "苑", "未", "楮", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤",
				"许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶",
				"姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘",
				"葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花",
				"方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑",
				"薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安",
				"常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元",
				"卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚", "邵", "湛",
				"汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成",
				"戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董",
				"梁", "杜", "阮", "蓝", "闽", "席", "季", "麻", "强", "贾", "路", "娄",
				"危", "江", "童", "颜", "郭", "梅", "盛", "林", "刁", "锺", "徐", "丘",
				"骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支",
				"柯", "昝", "管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应",
				"宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪", "包", "诸", "左",
				"石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣",
				"翁", "荀", "羊", "於", "惠", "甄", "麹", "家", "封", "芮", "羿", "储",
				"靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴",
				"弓", "牧", "隗", "山", "谷", "车", "侯", "宓", "蓬", "全", "郗", "班",
				"仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "斜", "厉",
				"戎", "祖", "武", "符", "刘", "景", "詹", "束", "龙", "叶", "幸", "司",
				"韶", "郜", "黎", "蓟", "薄", "印", "宿", "白", "怀", "蒲", "邰", "从",
				"鄂", "索", "咸", "籍", "赖", "卓", "蔺", "屠", "蒙", "池", "乔", "阴",
				"郁", "胥", "能", "苍", "双", "闻", "莘", "党", "翟", "谭", "贡", "劳",
				"逄", "姬", "申", "扶", "堵", "冉", "宰", "郦", "雍", "郤", "璩", "桑",
				"桂", "濮", "牛", "寿", "通", "边", "扈", "燕", "冀", "郏", "浦", "尚",
				"农", "温", "别", "庄", "晏", "柴", "瞿", "阎", "充", "慕", "连", "茹",
				"习", "宦", "艾", "鱼", "容", "向", "古", "易", "慎", "戈", "廖", "庾",
				"终", "暨", "居", "衡", "步", "都", "耿", "满", "弘", "匡", "国", "文",
				"寇", "广", "禄", "阙", "东", "欧", "殳", "沃", "利", "蔚", "越", "夔",
				"隆", "师", "巩", "厍", "聂", "晁", "勾", "敖", "融", "冷", "訾", "辛",
				"阚", "那", "简", "饶", "空", "曾", "毋", "沙", "乜", "养", "鞠", "须",
				"丰", "巢", "关", "蒯", "相", "查", "后", "荆", "红", "游", "竺", "权",
				"逑", "盖", "益", "桓", "公", "仉", "督", "晋", "楚", "阎", "法", "汝",
				"鄢", "涂", "钦", "归", "海", "岳", "帅", "缑", "亢", "况", "后", "有",
				"琴", "商", "牟", "佘", "佴", "伯", "赏", "墨", "哈", "谯", "笪", "年",
				"爱", "阳", "佟", };

		String firstName = name.substring(0, 1);
		for (String s : surName) {
			String s1 = s.substring(0, 1);
			if (s.contains(firstName)) {
				return true;
			}
		}
		return false;

	}

	public static boolean screenBothName(String name) {
		String[] surName = { "万俟", "司马", "上官", "欧阳", "夏侯", "诸葛", "闻人", "东方",
				"赫连", "皇甫", "尉迟", "公羊", "澹台", "公冶", "宗政", "濮阳", "淳于", "单于",
				"太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐", "锺离", "宇文", "长孙", "慕容",
				"鲜于", "闾丘", "司徒", "司空", "丌官", "司寇", "南宫", "子车", "颛孙", "端木",
				"巫马", "公西", "漆雕", "乐正", "壤驷", "公良", "拓拔", "夹谷", "宰父", "谷梁",
				"段干", "百里", "东郭", "南门", "呼延", "羊舌", "微生", "梁丘", "左丘", "东门",
				"西门", };

		String firstName = name.substring(0, 1);
		for (String s : surName) {
			String s1 = s.substring(0, 1);
			if (s.contains(firstName)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 筛选职位字段
	 * 
	 * @param name
	 * @return
	 */
	public static String screenPosition(String name) {
		String[] surName = { "CTO", "CFO", "CMO", "客座教授", "教授", "总经理助理",
				"人力资源助理 ", "主任助理", "市场策划", "策划", "投资顾问", "顾问", "主任", "人力资源专员 ",
				"培训专员 ", "员工培训与发展主管 ", "绩效考核主管 ", "薪资福利主管 ", "融资主管", "招聘主管",
				"主管", "董事长", "事长", "薪酬分析师 ", "培训师 ", "人力资源信息系统经理 ", "高层管理职位",
				"总裁", "人力资源总监 ", "客服部总监", "财务总监", "生产总监 ", "运营总监 ", "投资总监",
				"技术总监", "营销总监", "市场总监", "销售总监 ", "总监", "员工记录经理 ", "执行总经理",
				"高级经理", "客户经理", "项目经理", "业务经理", "理财经理", "副总经理", "总经理", "经理",
				"研究员", };
		String i = "";
		int j = 0;
		for (String s : surName) {
			if (name.contains(s)) {// (str.indexOf("ABC")!=-1

				return i = s;
			}
		}
		return i;

	}

	/**
	 * 筛选公司字段
	 * 
	 * @param name
	 * @return
	 */
	public static String screenCompany(String name) {
		String[] surName = { "有眼公司", "有限", "事务所", "期货", "资本", "证券", "中心",
	 " 无限", "集团", "合作", "会社", "合伙", "企业"
		, "厂", "公司", "行", "社", "中心", "网吧", "院", "部", "处", "股", "队", "委员会",
				"合作", " 库", "局", "村", "团", " 站", "店", "所", "段", " 厅", " 组",
				"工作室", "研究中心", "办公室", "商场", "大学"
				, "Co.", "Limited", "LIMITED", "LTD.", "INC.", "LLC."
				, " 委派代表",
				"财政部", "国务院", "国资委",
				"国家股", "人股", "流通股", "公众股", "工股", "股东", "A股", "H股", "B股", "上市股",
				"基本社员 ", "集体股", "合作股", "募集股",

		};
		String i = "";
		int j = 0;
		for (String s : surName) {
			if (name.contains(s)) {// (str.indexOf("ABC")!=-1

				return i = s;
			}
		}
		return i;

	}

	/**
	 * 筛选地址字段
	 * 
	 * @param name
	 * @return
	 */
	public static String screenSite(String add) {
		// if ( object2.indexOf("楼")!=-1&& object2.indexOf("号")!=-1&&
		// object2.indexOf("层")!=-1&& object2.indexOf("路")!=-1&&
		// object2.indexOf("街")==-1) {// &&
		String[] surName = { "大厦", "路", "楼", "街", "层", "号", "室", "座", "弄", "栋" };
		String i = "";
		int j = 0;
		for (String s : surName) {
			if (add.contains(s)) {// (str.indexOf("ABC")!=-1
				return i = s;
			}
		}
		return i;
	}

	/**
	 * 筛选手机字段
	 * 
	 * @param name
	 * @return
	 */
	public static String screenMobile(String num) {
		String reg = "^.*\\d{10,11}.*$";
		String res = "";
		if (num.matches(reg)) {
			return res = num;
		}
		return res;
	}

	/**
	 * 筛选电话字段
	 * 
	 * @param name
	 * @return
	 */
	public static String screenPhone(String num) {
		String reg = "^.*\\d{11,12}.*$";
		String res = "";
		if (num.matches(reg)) {
			return res = num;
		}
		return res;

	}

	/*
	 * public static void main(String[] args) { String name = "张三";
	 * System.out.println(screenName(name)); }
	 */
	public static void main(String[] args) {
		String reg = "^.*\\d{7}.*$";
		String s = "aa2aa11ab15896863368aa11";
		if (s.matches(reg)) {
			System.out.println(111);

		} else {
			System.out.println(0);
		}
		int length = "ad:86511515555545665df4656".indexOf("1") + 1;// 因为是从开始计数，所以加1

		int end = length + "bc".length() - 1;// 取得结束位置
		System.err.println(length + "efa" + end);

	}
}
