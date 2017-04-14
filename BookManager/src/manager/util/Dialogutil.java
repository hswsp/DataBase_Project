package manager.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
/**
 * �Ի��򹤾���
 * @author asus-pc
 *
 */
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * ���ڵ�����ȷ����ʧ����ʾ��
 * @author asus-pc
 *
 */

public class Dialogutil extends JDialog implements ActionListener{
	Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screensize = tk.getScreenSize();
    int height = (int)screensize.getHeight();
    int width = (int)screensize.getWidth();
    private double enlargement_x=width/1920;
    private double enlargement_y=height/1080;
        // �ı������������ַ���
        // �Ի���ĸ����塣
        // ��ȷ������ť
        JButton setButton;    
        /**
         * ���캯��������Ϊ������ͶԻ���ı���
         */
      public  Dialogutil(JFrame prentFrame, String title,String inform) {
            //�ڶ�������Ϊ�Ի�����⣬����������Ϊ����ʾ����Ϣ
           // ���ø���Ĺ��캯����
            super(prentFrame, title, false); // ������������false��ʾ�������������塣Ϊtrue��ʾ���ܹ�������������          
          //�����м�����
            JPanel contentPane = new JPanel();
            setContentPane(contentPane);
            contentPane.setLayout(new BorderLayout());//��������borderlayout
            
            JPanel p1 = new JPanel();
            contentPane.add(p1, "Center");
            p1.setLayout(new FlowLayout(FlowLayout.CENTER)); //������p1��FlowLayout
 //           getContentPane().add("Center", p1);
             // ���Label�������ı���
            JLabel label = new JLabel(inform);
            label.setFont(new Font("����", Font.PLAIN, 35));
            p1.add(label);          
            setBounds((int)(width / 2-180* enlargement_x),(int)(height/2 ),
            		(int)(640*enlargement_x),(int)(320*enlargement_y));
            //��һ�п���
//            JPanel p3 = new JPanel();
//             p3.setLayout(new FlowLayout(FlowLayout.CENTER));
//            JLabel label2 = new JLabel(" ");
//            label2.setFont(new Font("����", Font.PLAIN, 35));          
//            p3.add(label2);
//            getContentPane().add(p3);
//            setBounds((int)(width / 2-180* enlargement_x),(int)(height/2+10*enlargement_y),
//            		(int)(640*enlargement_x),(int)(320*enlargement_y));
            // ���ȷ����ȡ����ť
            JPanel p2 = new JPanel();
            p2.setLayout(new FlowLayout(FlowLayout.CENTER));         
            setButton = new JButton("ȷ ��");
            setButton.setFont( new Font("����", Font.PLAIN,(int)(20*enlargement_x)) );
            setButton.addActionListener(this);//��������
            setButton.setPreferredSize(new Dimension((int)(85*enlargement_x),(int)(40*enlargement_y)));
            //   ʹ�þ��Բ���ʱ��p2.setLayout(null);
            //btn.setBounds(x,y,width,height);//���ô�С����λ          
            //setButton.setSize((int)(70*enlargement_x),(int)(40*enlargement_y));//���ô�С
            //btn.setLocation(x,y);//��λ
            p2.add(setButton);
            getContentPane().add("South", p2);          
            // �����Ի��򲼾ִ�С
            pack();
            setVisible(true);     
        }
        /**
         * �¼�����
         */
        public void actionPerformed(ActionEvent event) {    
            Object source = event.getSource();         
            if ((source == setButton)) {
            	setVisible(false);
            	dispose();
             return;
            }            
        }
    }
 
