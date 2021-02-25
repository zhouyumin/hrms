package com.fwwb.hrms.utils;

import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: 周余民
 * @Date: Created in 14:38 2021/2/25
 * @Description: 添加图片水印
 */
public class WaterMarkUtil {
    private static String watermark_content;

    @Value("${water-mark.content}")
    public void setWatermark_content(String watermark_content) {
        WaterMarkUtil.watermark_content = watermark_content;
    }

    /**
     * 添加水印
     * @param image 图片输入流
     * @return 添加水印的图片输入流
     * @throws IOException IO异常
     */
    public static InputStream setWaterMark(InputStream image) throws IOException {
        // 原图位置, 输出图片位置, 水印文字颜色, 水印文字
        // 读取原图片信息
        Image srcImg = ImageIO.read(image);
        int srcImgWidth = srcImg.getWidth(null);
        int srcImgHeight = srcImg.getHeight(null);
        // 加水印
        BufferedImage bufImg = new BufferedImage(srcImgWidth,
                srcImgHeight,
                BufferedImage.TYPE_INT_RGB);
        //获取 Graphics2D 对象
        Graphics2D g = bufImg.createGraphics();
        //消除文字锯齿
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //设置绘图区域
        g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
        //设置字体
        Font font = new Font("宋体", Font.PLAIN, srcImgWidth/30);
        // 根据图片的背景设置水印颜色
        g.setColor(Color.orange);
        g.setFont(font);
        //获取文字长度
        int len = g.getFontMetrics(
                g.getFont()).charsWidth(watermark_content.toCharArray(),
                0,
                watermark_content.length());
        int x = (srcImgWidth - len) / 2;
        int y = (int) (srcImgHeight*0.9);
        g.drawString(watermark_content, x, y);
        g.dispose();
        // 输出图片
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufImg, "jpg", os);
        return new ByteArrayInputStream(os.toByteArray());
    }
}
