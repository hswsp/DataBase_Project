package manager.util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
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
      private JTextField MassageTXt;
      private JButton confirmJb = new JButton();
      
        /**
         * 构造函数，参数为父窗体和对话框的标题
         */
      public  Dialogutil(JFrame prentFrame, String title,String inform) {
            //第二个参数为对话框标题，第三个参数为所显示的信息
           // 调用父类的构造函数，   	    
            super(prentFrame, title, false);
            int Txtsize=inform.length();
            setTitle(title);
          //建立中间容器
            JPanel contentPane = new JPanel();
            setContentPane(contentPane);
            
            MassageTXt = new JTextField();
            MassageTXt.setEditable(false);
            MassageTXt.setBackground(UIManager.getColor("menu"));
            MassageTXt.setFont(new Font("华文行楷", Font.PLAIN, 35));
            MassageTXt.setText(inform);
            MassageTXt.setColumns(10);
            
            confirmJb = new JButton("\u786E\u8BA4");
            confirmJb.addActionListener(this);
            confirmJb.setFont(new Font("宋体", Font.PLAIN, 35));
            GroupLayout gl_contentPane = new GroupLayout(contentPane);
            gl_contentPane.setHorizontalGroup(
            	gl_contentPane.createParallelGroup(Alignment.LEADING)
            		.addGroup(gl_contentPane.createSequentialGroup()
            			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
            				.addGroup(gl_contentPane.createSequentialGroup()
            					.addGap(123)
            					.addComponent(MassageTXt, GroupLayout.PREFERRED_SIZE,(int)(Txtsize*35*enlargement_x), GroupLayout.PREFERRED_SIZE))
            				.addGroup(gl_contentPane.createSequentialGroup()
            					.addGap(123+(int)(Txtsize/2.7*35*enlargement_x))//置于中间
            					.addComponent(confirmJb)))
            			.addContainerGap(123, Short.MAX_VALUE))
            );
            gl_contentPane.setVerticalGroup(
            	gl_contentPane.createParallelGroup(Alignment.LEADING)
            		.addGroup(gl_contentPane.createSequentialGroup()
            			.addGap(55)
            			.addComponent(MassageTXt, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
            			.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
            			.addComponent(confirmJb)
            			.addContainerGap())
            );
            //MassageTXt.setBorder(new LineBorder(new Color(127, 157, 185)));
            MassageTXt.setBorder(new EmptyBorder(0,0,0,0));
            contentPane.setLayout(gl_contentPane);
            setBounds((int)(width / 2-180* enlargement_x),height/2,558,318);
            // 调整对话框布局大小
            pack();    
            setVisible(true);     
        }
        /**
         * 事件处理
         */
        @Override
		public void actionPerformed(ActionEvent event)
        {    
            Object source = event.getSource();         
            if ((source == confirmJb))
            {
            	setVisible(false);
            	dispose();
             return;
            }            
        }
    }
 
