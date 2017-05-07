package manager.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PictureUtil 
{
	/**
	  * 改变图片尺寸
	  * @param srcFileName 源图片路径
	  * @param tagFileName 目的图片路径
	  * @param width 修改后的宽度
	  * @param height 修改后的高度
	  */
	 public static void zoomImage(String srcFileName,String tagFileName,int width,int height)
	 {  
	  try {
	   BufferedImage bi = ImageIO.read(new File(srcFileName));
	   BufferedImage tag=new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
	   tag.getGraphics().drawImage(bi, 0, 0, width, height, null);
	   ImageIO.write(tag, "jpg", new File(tagFileName));
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	 }
}
