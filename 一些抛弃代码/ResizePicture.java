package manager.util;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
 
public class ResizePicture {
	
	 public static Image makeColorTransparent(Image im, final Color color) 
	 {
	        ImageFilter filter = new RGBImageFilter() {
	            // the color we are looking for... Alpha bits are set to opaque
	            public int markerRGB = color.getRGB() | 0xFF000000;
	 
	            @Override
	            public final int filterRGB(int x, int y, int rgb) {
	                if ((rgb | 0xFF000000) == markerRGB) {
	                    // Mark the alpha bits as zero - transparent
	                    return 0x00FFFFFF & rgb;
	                } else {
	                    // nothing to do
	                    return rgb;
	                }
	            }
	        };
	 
	        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
	        return Toolkit.getDefaultToolkit().createImage(ip);
	    }
	 
	 public static BufferedImage toBufferedImage(Image image) {
		    if (image instanceof BufferedImage) {
		        return (BufferedImage)image;
		     }
		 
		    // This code ensures that all the pixels in the image are loaded
		     image = new ImageIcon(image).getImage();
		 
		    // Determine if the image has transparent pixels; for this method's
		    // implementation, see e661 Determining If an Image Has Transparent Pixels
		    //boolean hasAlpha = hasAlpha(image);
		 
		    // Create a buffered image with a format that's compatible with the screen
		     BufferedImage bimage = null;
		     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    try {
		        // Determine the type of transparency of the new buffered image
		        int transparency = Transparency.OPAQUE;
		       /* if (hasAlpha) {
		         transparency = Transparency.BITMASK;
		         }*/
		 
		        // Create the buffered image
		         GraphicsDevice gs = ge.getDefaultScreenDevice();
		         GraphicsConfiguration gc = gs.getDefaultConfiguration();
		         bimage = gc.createCompatibleImage(
		         image.getWidth(null), image.getHeight(null), transparency);
		     } catch (HeadlessException e) {
		        // The system does not have a screen
		     }
		 
		    if (bimage == null) {
		        // Create a buffered image using the default color model
		        int type = BufferedImage.TYPE_INT_RGB;
		        //int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
		        /*if (hasAlpha) {
		         type = BufferedImage.TYPE_INT_ARGB;
		         }*/
		         bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		     }
		 
//		    // Copy image to buffered image
//		     Graphics g = bimage.createGraphics();
//		 
//		    // Paint the image onto the buffered image
//		     g.drawImage(image, 0, 0, null);
//		     g.dispose();
		 
		    return bimage;
		}
	 
    public static Icon changeImage(Icon icon, double n) {
       Image img = ((ImageIcon)icon).getImage();
       // 构造Image对象
       int wideth = img.getWidth(null); // 得到源图宽
       int height = img.getHeight(null); // 得到源图长
       BufferedImage tag = new BufferedImage((int)(n * wideth), (int)(n * height),
              BufferedImage.TYPE_INT_RGB);
       tag.getGraphics().drawImage(img, 0, 0, (int)(n * wideth), (int)(n * height), null); 
       Image image = (Image)tag;
       image =makeColorTransparent(image,Color.white);
       int widethtmp = image.getWidth(null); // 得到源图宽
       int heighttmp = image.getHeight(null); // 得到源图长
       tag.getGraphics().drawImage(image, 0, 0, widethtmp, heighttmp, null); 
       Icon returnIcon = new ImageIcon(tag);
      
       return returnIcon;
    }
}