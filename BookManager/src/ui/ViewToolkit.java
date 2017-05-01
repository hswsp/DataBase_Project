/**
 * 
 */
package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import values.ValuesMgr;

/**
 * ��ͼ������
 */
public class ViewToolkit {

	/**
	 * ��ȡͼ�����
	 */
	public static ImageIcon createImageIcon(String resTitle) {
		return new ImageIcon(ViewToolkit.class.getClass().getResource(ValuesMgr.SYS_VALUES.RESOURCES_PATH + resTitle));
	}

	/**
	 * ���ñ���ɫ new Color(240, 240, 240)
	 */
	public static void setBackColor(Color color, Container con) {
		for (int i = 0; i < con.getComponentCount(); i++) {
			Component temp = con.getComponent(i);
			if (!(temp instanceof JButton) && !(temp instanceof JComboBox)) {
				temp.setBackground(color);
				if (temp instanceof Container) {
					Container sub = (Container) temp;
					setBackColor(color, sub);
				}
			}
		}
	}

	/**
	 * ��ʾ��ʾ��Ϣ�Ի���
	 */
	public static void showMsgInfo(Component comp, String tipInfo) {
		JOptionPane pane = new JOptionPane("  " + tipInfo);
		pane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = pane.createDialog(comp, ValuesMgr.SYS_VALUES.ABOUT_DIALOG_TITLE);
		setBackColor(new Color(240, 240, 240), dialog);
		dialog.show();
	}

	/**
	 * ��ʾȷ����Ϣ�Ի���
	 */
	public static int showConfInfo(Component comp, String tipInfo) {
		JOptionPane pane = new JOptionPane("  " + tipInfo, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
		JDialog dialog = pane.createDialog(comp, ValuesMgr.SYS_VALUES.ABOUT_DIALOG_TITLE);
		setBackColor(new Color(240, 240, 240), dialog);
		dialog.show();
		Object rtnValue = pane.getValue();
		if (null != rtnValue && rtnValue instanceof Integer) {
			return ((Integer) rtnValue).intValue();
		} else {
			return JOptionPane.CLOSED_OPTION;
		}
	}

	/**
	 * �����ı������
	 */
	public static JTextField buildTxtField(String title, int colSize) {
		JTextField tmpTxtField = new JTextField(colSize);
		tmpTxtField.setToolTipText(title);
		return tmpTxtField;
	}

	/**
	 * buildTxtField ��������ѡ������
	 */
	public static JComboBox buildComBox(String title, String[] values, boolean isEdit) {
		JComboBox tmpComBox = new JComboBox(values);
		tmpComBox.setToolTipText(title);
		tmpComBox.setEditable(isEdit);
		return tmpComBox;
	}

	/**
	 * ��JLabel�ϻ�������
	 */
	public static ImageIcon createTxtImageIcon(String resTitle, String txtInfo) {
		ImageIcon imageIcon = ViewToolkit.createImageIcon(resTitle);
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(ViewToolkit.class.getClass().getResource(ValuesMgr.SYS_VALUES.RESOURCES_PATH + resTitle)); // ��File����ת����BufferedImage����
		} catch (IOException e) {
			e.printStackTrace();
		}

		Graphics2D g = bufferedImage.createGraphics();
		g.setColor(Color.white); // ���û�����ɫ
		g.setFont(new Font("����", Font.PLAIN, 13));

		g.drawString(txtInfo, 8, imageIcon.getIconHeight() - 10);// �򻺳�ͼ����д����,
		// 6,11������Ķ�

		Image image = Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());// ��BufferedImage����ת����Image����

		return new ImageIcon(image);
	}
}
