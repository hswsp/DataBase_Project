package manager.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import manager.dao.UserDao;
import manager.util.DbUtil;
import manager.util.Dialogutil;
import manager.util.MD5Util;
import manager.util.StringUtil;
import manager.util.showMessageFrame;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class RegisterFrm extends JFrame {

	private Integer kind;//0表示读者，1表示管理员
//设置跟随分辨率变化窗口
	Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
	private int screenHeight = (int) screenSize.getHeight();
    private int screenWidth = (int) screenSize.getWidth();
    private double enlargement_x=screenWidth/1920;
    private double enlargement_y=screenHeight/1080;
    
    private int windowWidth ; //获得窗口宽
    private int windowHeight; //获得窗口高
	private JPanel contentPane;
	private JTextField IDTxt;
	private JTextField UserNameTxt;
	private JPasswordField PasswordTxt;
	private JPasswordField PassAgainTxt;
	private DbUtil dbutil=new DbUtil();
	private UserDao userdao=new UserDao();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrm frame = new RegisterFrm();
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
	public RegisterFrm() 
	{
		kind=0;
		setTitle("\u65B0\u7528\u6237\u6CE8\u518C");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(screenWidth * 2/7, screenHeight / 3, (int)(1200*enlargement_x),(int)(1100*enlargement_y));
		windowWidth = this.getWidth(); //获得窗口宽
		windowHeight = this.getHeight(); //获得窗口高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("");
		
		JLabel lblid = new JLabel("\u65B0\u7528\u6237ID");
		lblid.setFont(new Font("宋体", Font.PLAIN, 35));
		
		IDTxt = new JTextField();
		IDTxt.setFont(new Font("隶书", Font.PLAIN, 35));
		IDTxt.setColumns(10);
		//IDTxt.setText("ID只能为数字");
		
		JLabel label_1 = new JLabel("\u65B0\u7528\u6237\u540D");
		label_1.setFont(new Font("宋体", Font.PLAIN, 35));
		
		UserNameTxt = new JTextField();
		UserNameTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		UserNameTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("\u767B\u5F55\u5BC6\u7801");
		label_2.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				IDTxt.setFont(new Font("宋体", Font.PLAIN, 35));
				RegisterAction(e);
			}

			
		});
		button.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton revertJb = new JButton("退出");
		revertJb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetValueAction(e);
			}
		});
		revertJb.setFont(new Font("宋体", Font.PLAIN, 35));
		
		PasswordTxt = new JPasswordField();
		PasswordTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel label_3 = new JLabel("\u5BC6\u7801\u786E\u8BA4");
		label_3.setFont(new Font("宋体", Font.PLAIN, 35));
		
		PassAgainTxt = new JPasswordField();
		PassAgainTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel kindJb = new JLabel("用户类型");
		kindJb.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JRadioButton readerJrb = new JRadioButton("读者");
		readerJrb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(readerJrb.isSelected())
					kind=0;			
			}
		});
		buttonGroup.add(readerJrb);
		readerJrb.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel label_4 = new JLabel("欢迎使用右更二图书管理系统");
		label_4.setFont(new Font("华文隶书", Font.PLAIN, 50));
		
		JRadioButton managerJrb = new JRadioButton("管理员");
		managerJrb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(managerJrb.isSelected())
					kind=1;			
			}
		});
		buttonGroup.add(managerJrb);
		managerJrb.setFont(new Font("宋体", Font.PLAIN, 35));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(label, GroupLayout.PREFERRED_SIZE, 1184, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(269)
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.RELATED, 502, Short.MAX_VALUE)
					.addComponent(revertJb)
					.addGap(207))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(153)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(kindJb)
								.addComponent(label_3))
							.addGap(45)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(readerJrb)
									.addPreferredGap(ComponentPlacement.RELATED, 319, Short.MAX_VALUE)
									.addComponent(managerJrb))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(PasswordTxt, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE))
								.addComponent(PassAgainTxt, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE))
							.addGap(263))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1)
								.addComponent(lblid))
							.addGap(44)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(UserNameTxt, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
								.addComponent(IDTxt, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE))
							.addGap(263))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(261)
					.addComponent(label_4)
					.addContainerGap(273, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label)
					.addGap(108)
					.addComponent(label_4)
					.addGap(105)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblid)
						.addComponent(IDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(90)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(UserNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(78)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(PasswordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(93)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(PassAgainTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(89)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(kindJb)
						.addComponent(readerJrb)
						.addComponent(managerJrb))
					.addGap(60)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(revertJb))
					.addGap(66))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void RegisterAction(ActionEvent e) {
		String s=new String(this.PassAgainTxt.getPassword()); //判断两次输入的密码是否一致
		String password=new String(this.PasswordTxt.getPassword());
		String userName=UserNameTxt.getText().trim();
		String Id="";
		if(!IDTxt.getText().trim().equals(""))//&&!IDTxt.getText().trim().equals("ID只能为数字"))//在输入后才能转
		Id=IDTxt.getText().trim();//		
		if(Id.equals(""))
		{			
			showMessageFrame note=new showMessageFrame(null,"请输入用户ID!",showMessageFrame.NOTE);
			this.Reset();
			return;
		}
		else if(StringUtil.isEmpty(userName))
		{
			showMessageFrame note=new showMessageFrame(null,"请输入用户名！",showMessageFrame.NOTE);
			this.Reset();
			return;
		}
		else if(StringUtil.isEmpty(password))
		{
			showMessageFrame note=new showMessageFrame(null,"请输入密码！",showMessageFrame.NOTE);
			this.Reset();
			return;
		}
		else if(StringUtil.isEmpty(s))
		{
			showMessageFrame note=new showMessageFrame(null,"请再次输入密码！",showMessageFrame.NOTE);
			this.Reset();
			return;
		}
		Connection con=null;
		try {
			con=dbutil.getCon();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(this.kind==0)
		{
			if(s.trim().equals(password.trim()))
		{
			String sql="SELECT * FROM t_user where id=?";//+"'"+Integer.parseInt(IDTxt.getText())+"'";					
			try {				
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setString(1, Id);
				ResultSet rs=pstmt.executeQuery();
				if(rs.next())
				{
					showMessageFrame note=new showMessageFrame(null,"用户ID已存在，添加失败！",showMessageFrame.NOTE);
					return;
				}
				else
				{
					
					try {
						String Md5Str=MD5Util.string2MD5(password);
						password=MD5Util.convertMD5(Md5Str);
						int currentUser=userdao.Register(con,Id,userName, password);
						if(currentUser>0)
						{
							showMessageFrame note=new showMessageFrame(null,"创建账户成功！",showMessageFrame.NORMAL);
							this.Reset();
						}
					} catch (Exception evt) {
						// TODO Auto-generated catch block
						evt.printStackTrace();
					}
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	  
		else
		{
			showMessageFrame note=new showMessageFrame(null,"两次密码不一致！",showMessageFrame.NOTE);
			this.Reset();
			return;
		}
	 } 
		else
		{
			 Dialogutil dialog=new Dialogutil(null,"Attention","正在施工，尚未开放管理员注册！");
		}
	}
	
	/**
	 * 清空
	 */
	private void Reset()
	{
		this.UserNameTxt.setText("");
		this.PasswordTxt.setText("");
		this.PassAgainTxt.setText("");
		this.IDTxt.setText("");			
	}
	/**
	 * 重置事件处理
	 * @param evt
	 */
		private void ResetValueAction(ActionEvent evt) {
			// TODO Auto-generated method stub
			this.Reset();
			dispose();
			LogOnfrm LogOnWindow=new LogOnfrm();
			LogOnWindow.setVisible(true);
		}
}
