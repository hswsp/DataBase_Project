package manager.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PictureUtil 
{
	/**
	  * �ı�ͼƬ�ߴ�
	  * @param srcFileName ԴͼƬ·��
	  * @param tagFileName Ŀ��ͼƬ·��
	  * @param width �޸ĺ�Ŀ��
	  * @param height �޸ĺ�ĸ߶�
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
