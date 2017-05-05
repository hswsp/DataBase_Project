package manager.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import manager.entity.User;
import manager.util.DateUtil;
import manager.util.DbUtil;
import manager.util.Dialogutil;
import manager.util.StringUtil;
import manager.util.showMessageFrame;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class PayFrm extends JFrame {
	
	//设置跟随分辨率变化窗口
	Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
	private int screenHeight = (int) screenSize.getHeight();
    private int screenWidth = (int) screenSize.getWidth();
    private double enlargement_x=screenWidth/1920;
    private double enlargement_y=screenHeight/1080;
    
    private int windowWidth ; //获得窗口宽
    private int windowHeight; //获得窗口高
	private JPanel Jrb5;
	private JTextField IDTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	//具体操作
   // private boolean IsOpened;
	private User user;
	private float payNum;
	private String payWay;
	private DbUtil  dbUtil=new DbUtil();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayFrm frame = new PayFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	public PayFrm() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create the frame.
	 */
	public PayFrm(String PresentUser) //
	{
		setResizable(false);
		setTitle("\u7F34\u7EB3\u8D39\u7528");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(screenWidth * 2/7, screenHeight / 3, (int)(1300*enlargement_x),(int)(1100*enlargement_y));
		windowWidth = this.getWidth(); //获得窗口宽
		windowHeight = this.getHeight(); //获得窗口高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		Jrb5 = new JPanel();
		Jrb5.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Jrb5);
		
		JLabel IDJL = new JLabel("\u5F53\u524D\u7528\u6237ID");
		IDJL.setIcon(new ImageIcon(PayFrm.class.getResource("/manager/image/ReaderID.png")));
		IDJL.setFont(new Font("华文隶书", Font.PLAIN, 40));
		
		IDTxt = new JTextField();
		IDTxt.setText(PresentUser);
		IDTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		IDTxt.setEditable(false);
		IDTxt.setColumns(10);
		
		JLabel MoneyJL = new JLabel("\u7F34\u7EB3\u91D1\u989D");
		MoneyJL.setIcon(new ImageIcon(PayFrm.class.getResource("/manager/image/money_bag.png")));
		MoneyJL.setFont(new Font("华文隶书", Font.PLAIN, 40));
		
		JRadioButton radioButton = new JRadioButton("5");
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioButton.isSelected())
				   payNum=5;			
			}
		});
		buttonGroup.add(radioButton);
		radioButton.setFont(new Font("宋体", Font.PLAIN, 40));
		
		JRadioButton Jrb10 = new JRadioButton("10");
		Jrb10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Jrb10.isSelected())
					   payNum=10;			
			}
		});
		buttonGroup.add(Jrb10);
		Jrb10.setFont(new Font("宋体", Font.PLAIN, 40));
		
		JRadioButton Jrb20 = new JRadioButton("20");
		Jrb20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Jrb20.isSelected())
					   payNum=20;		
			}
		});
		buttonGroup.add(Jrb20);
		Jrb20.setFont(new Font("宋体", Font.PLAIN, 40));
		
		JRadioButton Jrb50 = new JRadioButton("50");
		Jrb50.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Jrb50.isSelected())
					   payNum=50;		
			}
		});
		buttonGroup.add(Jrb50);
		Jrb50.setFont(new Font("宋体", Font.PLAIN, 40));
		
		JLabel PayWay = new JLabel("\u7F34\u7EB3\u65B9\u5F0F:");
		PayWay.setFont(new Font("华文隶书", Font.PLAIN, 40));
		
		JRadioButton cashJrb = new JRadioButton("\u73B0\u91D1");
		cashJrb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cashJrb.isSelected())
					   payWay="现金";		
			}
		});
		buttonGroup_1.add(cashJrb);
		cashJrb.setFont(new Font("宋体", Font.PLAIN, 40));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PayFrm.class.getResource("/manager/image/cash.png")));
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 40));
		
		JRadioButton yinglianJrb = new JRadioButton("\u94F6\u8054");
		yinglianJrb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(yinglianJrb.isSelected())
					   payWay="银联";		
			}
		});
		buttonGroup_1.add(yinglianJrb);
		yinglianJrb.setFont(new Font("宋体", Font.PLAIN, 40));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PayFrm.class.getResource("/manager/image/china_union_pay.png")));
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 40));
		
		JRadioButton rdbtnVisa = new JRadioButton("VISA");
		rdbtnVisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnVisa.isSelected())
					   payWay="VISA";		
			}
		});
		buttonGroup_1.add(rdbtnVisa);
		rdbtnVisa.setFont(new Font("宋体", Font.PLAIN, 40));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PayFrm.class.getResource("/manager/image/card_pay_payment_visa.png")));
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 40));
		
		JRadioButton AlipayJrb = new JRadioButton("\u652F\u4ED8\u5B9D");
		AlipayJrb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AlipayJrb.isSelected())
					   payWay="支付宝";		
			}
		});
		buttonGroup_1.add(AlipayJrb);
		AlipayJrb.setFont(new Font("宋体", Font.PLAIN, 40));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(PayFrm.class.getResource("/manager/image/alipay_pay.png")));
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 40));
		
		JButton ConfirmJb = new JButton("\u786E\u5B9A");
		ConfirmJb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payOpration(e);
			}
		});
		ConfirmJb.setFont(new Font("宋体", Font.PLAIN, 35));
		GroupLayout gl_Jrb5 = new GroupLayout(Jrb5);
		gl_Jrb5.setHorizontalGroup(
			gl_Jrb5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Jrb5.createSequentialGroup()
					.addGap(190)
					.addGroup(gl_Jrb5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Jrb5.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cashJrb)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel))
						.addGroup(gl_Jrb5.createSequentialGroup()
							.addGroup(gl_Jrb5.createParallelGroup(Alignment.LEADING)
								.addComponent(IDJL)
								.addComponent(MoneyJL)
								.addComponent(PayWay))
							.addGap(147)
							.addGroup(gl_Jrb5.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_Jrb5.createSequentialGroup()
									.addComponent(radioButton)
									.addGap(72)
									.addComponent(Jrb10)
									.addGap(79)
									.addComponent(Jrb20)
									.addGap(66)
									.addComponent(Jrb50))
								.addGroup(gl_Jrb5.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(Alignment.LEADING, gl_Jrb5.createSequentialGroup()
										.addGroup(gl_Jrb5.createParallelGroup(Alignment.TRAILING)
											.addGroup(gl_Jrb5.createSequentialGroup()
												.addComponent(yinglianJrb)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblNewLabel_1))
											.addGroup(gl_Jrb5.createSequentialGroup()
												.addComponent(AlipayJrb)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblNewLabel_3)))
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(rdbtnVisa)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblNewLabel_2))
									.addComponent(IDTxt, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 571, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(105, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_Jrb5.createSequentialGroup()
					.addContainerGap(618, Short.MAX_VALUE)
					.addComponent(ConfirmJb)
					.addGap(563))
		);
		gl_Jrb5.setVerticalGroup(
			gl_Jrb5.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_Jrb5.createSequentialGroup()
					.addGap(154)
					.addGroup(gl_Jrb5.createParallelGroup(Alignment.BASELINE)
						.addComponent(IDJL)
						.addComponent(IDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(159)
					.addGroup(gl_Jrb5.createParallelGroup(Alignment.BASELINE)
						.addComponent(radioButton)
						.addComponent(Jrb10)
						.addComponent(Jrb20)
						.addComponent(Jrb50)
						.addComponent(MoneyJL))
					.addGroup(gl_Jrb5.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_Jrb5.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnVisa))
						.addGroup(gl_Jrb5.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_Jrb5.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_2))
							.addGroup(gl_Jrb5.createSequentialGroup()
								.addGap(160)
								.addGroup(gl_Jrb5.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNewLabel_3)
									.addGroup(gl_Jrb5.createParallelGroup(Alignment.BASELINE)
										.addComponent(PayWay)
										.addComponent(AlipayJrb)))
								.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
								.addGroup(gl_Jrb5.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNewLabel)
									.addComponent(cashJrb)
									.addComponent(lblNewLabel_1)
									.addComponent(yinglianJrb)))))
					.addGap(108)
					.addComponent(ConfirmJb)
					.addGap(86))
		);
		Jrb5.setLayout(gl_Jrb5);
		//this.IsOpened=IsOpened;
		this.user=new User(PresentUser);
		this.payNum=-1;//初值
	}
	/**
	 * 支付操作
	 * @param e
	 */
	private void payOpration(ActionEvent e) 
	{
		Connection con=null;
		try {
			con=dbUtil.getCon();
			if(StringUtil.isEmpty(user.getId()))
			{
				Dialogutil attention=new Dialogutil(null,"Attention!","用户信息获取失败！");
				return;
			}
			else if(StringUtil.isEmpty(payWay))
			{
				Dialogutil attention=new Dialogutil(null,"Attention!","请选择支付方式！");
				return;
			}
			else if(payNum==-1)
			{
				Dialogutil attention=new Dialogutil(null,"Attention!","请选择支付数目！");
				return;
			}
			else
			{
				String sql="insert into t_pay values(?,?,?,?,null)";//主键不填，自动赋值
				//获取当前时间
				java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
				PreparedStatement pstmt=con.prepareStatement(sql);//创建对象，得到SQL语句
				pstmt.setString(1, user.getId());//传递参数
				pstmt.setDate(2, currentDate);
				pstmt.setFloat(3, payNum);
				pstmt.setString(4, payWay);//传递参数
				int pay=pstmt.executeUpdate();
				if(pay==1)
				{
					showMessageFrame SucNote=new showMessageFrame(null,"缴纳费用成功！",showMessageFrame.NORMAL);	
					dispose();	
					return;										
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
}
