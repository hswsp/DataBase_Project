package manager.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
/**
 * 对话框工具类
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
 * 用于弹出按确定消失的提示框
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
        // 文本框，用于输入字符串
        // 对话框的父窗体。
        // “确定”按钮
        JButton setButton;    
        /**
         * 构造函数，参数为父窗体和对话框的标题
         */
      public  Dialogutil(JFrame prentFrame, String title,String inform) {
            //第二个参数为对话框标题，第三个参数为所显示的信息
           // 调用父类的构造函数，
            super(prentFrame, title, false); // 第三个参数用false表示允许激活其他窗体。为true表示不能够激活其他窗体          
          //建立中间容器
            JPanel contentPane = new JPanel();
            setContentPane(contentPane);
            contentPane.setLayout(new BorderLayout());//主容器用borderlayout
            
            JPanel p1 = new JPanel();
            contentPane.add(p1, "Center");
            p1.setLayout(new FlowLayout(FlowLayout.CENTER)); //子容器p1用FlowLayout
 //           getContentPane().add("Center", p1);
             // 添加Label和输入文本框
            JLabel label = new JLabel(inform);
            label.setFont(new Font("宋体", Font.PLAIN, 35));
            p1.add(label);          
            setBounds((int)(width / 2-180* enlargement_x),(int)(height/2 ),
            		(int)(640*enlargement_x),(int)(320*enlargement_y));
            //加一行空行
//            JPanel p3 = new JPanel();
//             p3.setLayout(new FlowLayout(FlowLayout.CENTER));
//            JLabel label2 = new JLabel(" ");
//            label2.setFont(new Font("宋体", Font.PLAIN, 35));          
//            p3.add(label2);
//            getContentPane().add(p3);
//            setBounds((int)(width / 2-180* enlargement_x),(int)(height/2+10*enlargement_y),
//            		(int)(640*enlargement_x),(int)(320*enlargement_y));
            // 添加确定和取消按钮
            JPanel p2 = new JPanel();
            p2.setLayout(new FlowLayout(FlowLayout.CENTER));         
            setButton = new JButton("确 定");
            setButton.setFont( new Font("宋体", Font.PLAIN,(int)(20*enlargement_x)) );
            setButton.addActionListener(this);//关联动作
            setButton.setPreferredSize(new Dimension((int)(85*enlargement_x),(int)(40*enlargement_y)));
            //   使用绝对布局时候：p2.setLayout(null);
            //btn.setBounds(x,y,width,height);//设置大小并定位          
            //setButton.setSize((int)(70*enlargement_x),(int)(40*enlargement_y));//设置大小
            //btn.setLocation(x,y);//定位
            p2.add(setButton);
            getContentPane().add("South", p2);          
            // 调整对话框布局大小
            pack();
            setVisible(true);     
        }
        /**
         * 事件处理
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
 
