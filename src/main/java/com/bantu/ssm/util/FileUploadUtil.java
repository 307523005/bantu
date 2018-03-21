package com.bantu.ssm.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
/**
 * 文件通用上传方法
 * @author Administrator
 *
 */
public class FileUploadUtil {
	
	public static String uploadFile(MultipartFile file, HttpSession session,
			String savePath) {

		// springmvc上传，首先要在服务端创建上传文件
		// 创建文件涉及到文件file的使用，必须是绝对路径转换为相对路径的方法，过时方法request.getRealPath("");
		// 获取绝对路径的不过时方法
		String realPath = session.getServletContext().getRealPath(
				"uploadfilesimages/" + savePath);
		// 获取原文件名称
		String oldFileName = file.getOriginalFilename();
		// 获取原文件后缀
		String suffix = oldFileName.substring(oldFileName.lastIndexOf("."));
		// 创建新的文件名称UUID。readomUUID(),获取一个36位的随机数，并且不会重复
		String newFileName = UUID.randomUUID() + suffix;
		// 在服务端创建文件
		File files = new File(realPath, newFileName);
		// 判断文件目录是否存在，不存在创建所有父目录
		if (!files.exists()) {
			files.mkdirs();
		}
		// 利用文件读写把本地文件读取文件内容到服务新建文件里面
		try {
			file.transferTo(files);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "uploadfilesimages/" + savePath + "/" + newFileName;
	}

	/**
	 * 生成二维码图片文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static String newQRcodeFile(String fileName, String userId,
			HttpSession session) {
		// 根据系统的实际情况选择目录分隔符（windows下是，linux下是/）
		String separator = File.separator;
		// springmvc上传，首先要在服务端创建上传文件
		// 创建文件涉及到文件file的使用，必须是绝对路径转换为相对路径的方法，过时方法request.getRealPath("");
		// 获取绝对路径的不过时方法
		String directory = session.getServletContext().getRealPath(
				"QRcode/" + userId);
		// 以下这句的效果等同于上面两句，windows下正斜杠/和反斜杠都是可以的
		// linux下只认正斜杠，为了保证跨平台性，不建议使用反斜杠（在java程序中是<a
		// href="https://www.baidu.com/s?wd=%E8%BD%AC%E4%B9%89%E5%AD%97%E7%AC%A6&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1d9uW0LP1nzrHb3rHcsuWPh0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3En1cdn1fvPjck"
		// target="_blank" class="baidu-highlight">转义字符</a>，用\来表示反斜杠）
		String fileNames = fileName + ".png";
		// 在内存中创建一个文件对象，注意：此时还没有在硬盘对应目录下创建实实在在的文件
		File f = new File(directory, fileNames);
		if (f.exists()) {
			return f.getAbsolutePath();
			// 文件已经存在，输出文件的相关信息
			/*System.out.println(f.getAbsolutePath());
			System.out.println(f.getName());
			System.out.println(f.length());*/
		} else {
			// 先创建文件所在的目录
			f.getParentFile().mkdirs();
			try {
				// 创建新文件
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
		return directory + "/" + fileNames;
	}
	
}
