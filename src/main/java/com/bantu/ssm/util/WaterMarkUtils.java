package com.bantu.ssm.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 利用Java代码给图片加水印
 */
public class WaterMarkUtils {

	/**
	 * 
	 * @param souchFilePath
	 *            ：源图片路径
	 * @param targetFilePath
	 *            ：生成后的目标图片路径
	 * @param markContent
	 *            :要加的文字
	 * @param markContentColor
	 *            :文字颜色
	 * @param qualNum
	 *            :质量数字
	 * @param fontType
	 *            :字体类型
	 * @param fontSize
	 *            :字体大小
	 * @return
	 */
	public static void createMark(String souchFilePath, String targetFilePath,
			String markContent, Color markContentColor, float qualNum,
			String fontType, int fontSize, int w, int h, Integer degree,
			Color color) {
		markContentColor = color;
		/* 构建要处理的源图片 */
		ImageIcon imageIcon = new ImageIcon(souchFilePath);
		/* 获取要处理的图片 */
		Image image = imageIcon.getImage();
		// Image可以获得图片的属性信息
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		// 为画出与源图片的相同大小的图片（可以自己定义）
		BufferedImage bImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 构建2D画笔
		Graphics2D g = bImage.createGraphics();
		/* 设置2D画笔的画出的文字颜色 */
		g.setColor(markContentColor);
		/* 设置2D画笔的画出的文字背景色 */
		g.setBackground(Color.white);
		/* 画出图片 */
		g.drawImage(image, 0, 0, null);
		/* --------对要显示的文字进行处理-------------- */
		AttributedString ats = new AttributedString(markContent);
		Font font = new Font(fontType, Font.BOLD, fontSize);
		g.setFont(font);
		// 4、设置水印旋转
		if (null != degree) {
			g.rotate(Math.toRadians(degree), (double) bImage.getWidth() / 2,
					(double) bImage.getHeight() / 2);
		}
		/* 消除java.awt.Font字体的锯齿 */
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		/* 消除java.awt.Font字体的锯齿 */
		// font = g.getFont().deriveFont(30.0f);
		// 更多实例请访问 http://www.walkerjava.com/forum.php?mod=viewthread&tid=135
		ats.addAttribute(TextAttribute.FONT, font, 0, markContent.length());
		AttributedCharacterIterator iter = ats.getIterator();
		/* 添加水印的文字和设置水印文字出现的内容 ----位置 */
		g.drawString(iter, width - w, height - h);
		/* --------对要显示的文字进行处理-------------- www.it165.net */
		g.dispose();// 画笔结束
		try {
			// 输出 文件 到指定的路径
			FileOutputStream out = new FileOutputStream(targetFilePath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bImage);
			param.setQuality(qualNum, true);
			encoder.encode(bImage, param);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// 得到二维码路径,生成二维码图片前要先创建空白文件
		Color color = new Color(100, 100, 100, 200);
		// 图片质量1-100,字形，字大小，距离右边距离，距离下边距离,文字倾斜
		createMark("E:\\tomcat\\apache-tomcat-7.0.78\\webapps\\bantu\\images\\logo.png", "E:\\tomcat\\apache-tomcat-7.0.78\\webapps\\bantu\\1.png", "bantu文字水印", color, 1, "方正楷体简体",
				26, 490, -70, -45, color);
	}
}