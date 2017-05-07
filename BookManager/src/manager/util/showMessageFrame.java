package manager.util;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class showMessageFrame extends javax.swing.JFrame {
	/**
	 * ������ʽѡ����
	 */
    public static final int NORMAL=0;
    public static final int NOTE=1;
	
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screensize = tk.getScreenSize();
    int height = (int)screensize.getHeight();
    int width = (int)screensize.getWidth();
    private double enlargement_x=width/1920;
    private double enlargement_y=height/1080;
    
    private String str = null;
    private JLabel text;
    
    public showMessageFrame(Component c,String str,int color) {
        this.str = str;
        new Thread(new Runnable() {
            @Override
            public void run() {
                initGUI(c,color);
            }
        }).start();
    }

    private void initGUI(Component c,int color) {
        try {
            setUndecorated(true);
            setLocationRelativeTo(c);//���ô��������ָ�������λ��
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            {
                text = new JLabel("<html>" + str + "</html>", SwingConstants.CENTER);
                text.setFont(new Font("����",Font.BOLD, (int)(40*enlargement_x)));
                getContentPane().add(text, BorderLayout.CENTER);
                text.setBackground(new java.awt.Color(255, 251, 240));
                switch(color)
                {
                  case NORMAL:
                    break;
                  case NOTE:
                	  getContentPane().setBackground(Color.YELLOW);break;
                }
            }
            pack();
            setBounds(width / 2,height/2,
            		(int)(500*enlargement_x),(int)(100*enlargement_y));
            pack();
            try {
                Thread.sleep(3000);//����3000ms
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            dispose();//�ͷ��ɴ� Window�������������ӵ�е������������ʹ�õ����б�����Ļ��Դ
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}