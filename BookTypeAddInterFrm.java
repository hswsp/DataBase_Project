package manager.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class BookTypeAddInterFrm extends JInternalFrame {
	
	//设置跟随分辨率变化窗口
			Toolkit kit = Toolkit.getDefaultToolkit();
		    Dimension screenSize = kit.getScreenSize();
			private int screenHeight = (int) screenSize.getHeight();
		    private int screenWidth = (int) screenSize.getWidth();
		    private double enlargement_x=screenWidth/1920;
		    private double enlargement_y=screenHeight/1080;
		    
		    private int windowWidth ; //获得窗口宽
		    private int windowHeight; //获得窗口高
	
	private JTextField BookTypeNameTxt;
	JTextArea BookTypeDescTxt = new JTextArea();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeAddInterFrm frame = new BookTypeAddInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookTypeAddInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u56FE\u4E66\u7C7B\u522B\u6DFB\u52A0");
		setBounds(100,100,(int)( 973*enlargement_x), (int)(647*enlargement_y));//设置初始位置（无所谓，后面重置），大小
		((BasicInternalFrameUI)getUI()).setNorthPane(null);
		windowWidth = this.getWidth(); //获得窗口宽
		windowHeight = this.getHeight(); //获得窗口高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		
		JLabel BookName = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0\uFF1A");
		BookName.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel BookDesc = new JLabel("\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0\uFF1A");
		BookDesc.setFont(new Font("宋体", Font.PLAIN, 35));
		
		BookTypeNameTxt = new JTextField();
		BookTypeNameTxt.setColumns(10);
		
		
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.setFont(new Font("宋体", Font.PLAIN, 35));
		button.setIcon(new ImageIcon(BookTypeAddInterFrm.class.getResource("/manager/image/add.png")));
		
		JButton reset = new JButton("\u91CD\u7F6E");
		reset.setFont(new Font("宋体", Font.PLAIN, 35));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}

		
		});
		reset.setIcon(new ImageIcon(BookTypeAddInterFrm.class.getResource("/manager/image/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(100)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(BookName)
								.addComponent(BookDesc))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(BookTypeDescTxt, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
								.addComponent(BookTypeNameTxt, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(236)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 397, Short.MAX_VALUE)
							.addComponent(reset, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(32)))
					.addGap(43))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(104)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(BookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(BookName))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(141)
							.addComponent(BookDesc))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(110)
							.addComponent(BookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(reset, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addGap(58))
		);
		getContentPane().setLayout(groupLayout);
		// 设置文本域边框
		BookTypeDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
	}
	/**
	 * 重置事件处理
	 * @param e
	 */
	private void resetValueActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.ResetValue();
	}
	/**
	 * 重置表单
	 */
	private void ResetValue(){
		this.BookTypeNameTxt.setText("");
		this.BookTypeDescTxt.setText("");
	}
}
