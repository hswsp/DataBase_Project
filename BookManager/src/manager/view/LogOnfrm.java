package manager.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import manager.dao.UserDao;
import manager.dao.managerDao;
import manager.entity.Manager;
import manager.entity.User;
import manager.util.DbUtil;
import manager.util.Dialogutil;
import manager.util.MD5Util;
import manager.util.StringUtil;
import manager.util.showMessageFrame;

public class LogOnfrm extends JFrame {

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
	    private JTextField UserIDTxt;
	    private JPasswordField passwordTxt;
	    private DbUtil dbutil=new DbUtil();
	    private UserDao userdao=new UserDao();
	    private managerDao managerdao =new managerDao();
	    private final ButtonGroup buttonGroup = new ButtonGroup();
	    
	    JRadioButton userJrb = new JRadioButton();
	    JRadioButton managerJrb = new JRadioButton();
	    Integer kind;//0为用户，1位管理员；
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		 try
//		    {
//		        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//		    }
//		    catch(Exception e)
//		    {
//		        //TODO exception
//		    }
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LogOnfrm frame = new LogOnfrm();
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
	public LogOnfrm() {
		/************改变系统默认字体***********/
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) 
		{
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) 
			{
				UIManager.put(key, font);
			}
		}
		
		kind=0;//初值
		setResizable(false);
		setTitle("\u6B22\u8FCE\u4F7F\u7528\u53F3\u66F4\u4E8C\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(screenWidth * 2/7, screenHeight / 3, (int)(1300*enlargement_x),(int)(1100*enlargement_y));
		//参数分别为前两个是左上角位置，后两个是窗体大小
		//setSize(screenWidth / 2, screenHeight / 2);
		
		windowWidth = this.getWidth(); //获得窗口宽
		windowHeight = this.getHeight(); //获得窗口高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel Title_Label = new JLabel(" \u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		Title_Label.setIcon(new ImageIcon(LogOnfrm.class.getResource("/manager/image/logo.png")));
		Title_Label.setFont(new Font("楷体", Font.PLAIN, 60));
		//Title_Label.setIcon(ResizePicture.changeImage(new ImageIcon(LogOnfrm.class.getResource("/manager/image/logo.png")),1));
		
		
		
		JLabel User_Label = new JLabel("\u7528\u6237ID");
		User_Label.setFont(new Font("宋体", Font.PLAIN, 35));
		User_Label.setIcon(new ImageIcon(LogOnfrm.class.getResource("/manager/image/userName.png")));
		User_Label.setPreferredSize(new Dimension((int)(120*enlargement_x),(int)(80*enlargement_y)));
		
		JLabel Password_Label = new JLabel(" \u5BC6\u7801 ");
		Password_Label.setFont(new Font("宋体", Font.PLAIN, 35));
		Password_Label.setIcon(new ImageIcon(LogOnfrm.class.getResource("/manager/image/password.png")));
		Password_Label.setPreferredSize(new Dimension((int)(120*enlargement_x),(int)(80*enlargement_y)));
		UserIDTxt = new JTextField();
		UserIDTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		UserIDTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton Login_Button = new JButton("\u767B\u5F55");
		Login_Button.setFont(new Font("宋体", Font.PLAIN, 30));
		Login_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginAction(e);
			}
		});
		Login_Button.setIcon(new ImageIcon(LogOnfrm.class.getResource("/manager/image/login.png")));
		
		JButton Reset_Button = new JButton("\u91CD\u7F6E");
		Reset_Button.setFont(new Font("宋体", Font.PLAIN, 30));
		Reset_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResetValueAction(e);
			}
		});
		Reset_Button.setIcon(new ImageIcon(LogOnfrm.class.getResource("/manager/image/reset.png")));
		
		JLabel LogonJL = new JLabel("\u5C1A\u672A\u6CE8\u518C\uFF1F\u70B9\u51FB\u8FD9\u91CC\u6CE8\u518C");
		LogonJL.setFont(new Font("华文行楷", Font.PLAIN, 35));
		
		JButton LogonButton = new JButton("\u6CE8\u518C");
		LogonButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterFrm RegisterWindow=new RegisterFrm();
				RegisterWindow.setVisible(true);
			}
		});
		LogonButton.setIcon(new ImageIcon(LogOnfrm.class.getResource("/manager/image/userName.png")));
		LogonButton.setFont(new Font("宋体", Font.PLAIN, 35));
		
		userJrb = new JRadioButton("\u8BFB\u8005");
		userJrb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(userJrb.isSelected())
					kind=0;			
			}
		});
		buttonGroup.add(userJrb);
		userJrb.setSelected(true);
		userJrb.setFont(new Font("宋体", Font.PLAIN, 35));
		
		managerJrb = new JRadioButton("\u7BA1\u7406\u5458");
		managerJrb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			 if(managerJrb.isSelected())
			 {
				kind=1;
			 }
			
			}
		});
		buttonGroup.add(managerJrb);
		managerJrb.setFont(new Font("宋体", Font.PLAIN, 35));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(137)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(Password_Label, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
								.addComponent(User_Label, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(46)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(userJrb)
										.addComponent(Login_Button, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(managerJrb)
											.addGap(214))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(LogonButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(Reset_Button, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
											.addGap(41))))
								.addComponent(passwordTxt)
								.addComponent(UserIDTxt, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(205)
							.addComponent(LogonJL)))
					.addGap(252))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(374)
					.addComponent(Title_Label, GroupLayout.PREFERRED_SIZE, 514, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(396, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(64)
					.addComponent(Title_Label, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(74)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(User_Label, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
						.addComponent(UserIDTxt, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(235)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(userJrb)
								.addComponent(managerJrb)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(102)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(Password_Label, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))))
					.addGap(82)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Login_Button, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addComponent(Reset_Button, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
					.addGap(72)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(LogonJL)
						.addComponent(LogonButton, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
					.addGap(148))
		);
		
//		int iconwidth = Title_Label.getWidth(),iconheight = Title_Label.getHeight();
//		ImageIcon image = new ImageIcon("/manager/image/logo.png");//实例化ImageIcon 对象  
//		/*下面这句意思是：得到此图标的 Image（image.getImage()）； 
//		在此基础上创建它的缩放版本，缩放版本的宽度，高度与JLble一致（getScaledInstance(width, height,Image.SCALE_DEFAULT )） 
//		最后该图像就设置为得到的缩放版本（image.setImage） 
//		*/  
//		image.setImage(image.getImage().getScaledInstance(iconwidth, iconheight,Image.SCALE_DEFAULT ));//可以用下面三句代码来代替  
//		//Image img = image.getImage();  
//		//img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);  
//		//image.setImage(img);  
//		Title_Label.setIcon(image);  
//		//Title_Label.setSize(width, height);  
		contentPane.setLayout(gl_contentPane);
		//设置JFrame居中显示
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * 登录事件处理函数
	 * @param evt
	 */
private void LoginAction(ActionEvent evt) 
{
		// TODO Auto-generated method stub
		String userID=this.UserIDTxt.getText();
		String password=new String(this.passwordTxt.getPassword());//返回char型
		if(StringUtil.isEmpty(userID))
		{
			showMessageFrame note=new showMessageFrame(null,"\n用户名不能为空\n",showMessageFrame.NOTE);//第一个为parentcomponent,写null表示最顶层，第二个为提示信息
			//JOptionPane.showMessageDialog(null, "用户名不能为空","Attention", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(StringUtil.isEmpty(password))
		{
			showMessageFrame note=new showMessageFrame(null,"\n密码不能为空\n",showMessageFrame.NOTE);
			//JOptionPane.showMessageDialog(null, "密码不能为空","Attention", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		//将输入密码解密，与数据库中比较
		String Md5Str=MD5Util.string2MD5(password);
		password=MD5Util.convertMD5(Md5Str);
		//Md5Str=MD5Util.convertMD5(password);//两次解密t
		User user=new User(userID,password);
		Manager manager =new Manager(userID,password);
		
		Connection con=null;		
		try {
			con=dbutil.getCon();
			if(kind==0)//用户
		    {
				
				User currentUser=userdao.login( con, user);
				if(currentUser!=null)
				{
//					JLabel LogSuc = new JLabel("登录成功！");
//					LogSuc.setFont(new Font("宋体", Font.PLAIN, 35));
//					JOptionPane.showMessageDialog(null, LogSuc,"Success", JOptionPane.INFORMATION_MESSAGE);
					//showMessageFrame LogSuc=new showMessageFrame(contentPane,"登录成功！",showMessageFrame.NORMAL);					
					dispose();									
					userMainFrm UserMainWindow=new userMainFrm(currentUser);//userID
					UserMainWindow.setVisible(true);				    																					
					//MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				else
				{
					//JOptionPane.showMessageDialog(null, "用户名或密码错误","Error", JOptionPane.ERROR_MESSAGE);
					Dialogutil Logfail=new Dialogutil(this,"Error","  用户名或密码错误  ");
				}
		    }
			else
			{
				Manager currentUser=managerdao.login(con,manager);
				if(currentUser!=null)
				{
//					JLabel LogSuc = new JLabel("登录成功！");
//					LogSuc.setFont(new Font("宋体", Font.PLAIN, 35));
//					JOptionPane.showMessageDialog(null, LogSuc,"Success", JOptionPane.INFORMATION_MESSAGE);
//					//应该为登录窗口销毁，打开主界面，这里还没写，测试
					//showMessageFrame LogSuc=new showMessageFrame(contentPane,"登录成功！",showMessageFrame.NORMAL);
					dispose();										
					MainFrm MainWindow=new MainFrm(currentUser);
				    MainWindow.setVisible(true);	
					//MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				else
				{
					//JOptionPane.showMessageDialog(null, "用户名或密码错误","Error", JOptionPane.ERROR_MESSAGE);
					Dialogutil Logfail=new Dialogutil(this,"Error","  用户名或密码错误  ");
				}
			}
		}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					try {
						dbutil.closeCon(con);
					    } catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}				
	}

/**
 * 重置时间处理
 * @param evt
 */
	private void ResetValueAction(ActionEvent evt) {
		// TODO Auto-generated method stub
		this.UserIDTxt.setText("");
		this.passwordTxt.setText("");
		
	}
}
