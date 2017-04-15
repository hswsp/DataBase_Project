package manager.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import manager.dao.UserDao;
import manager.entity.User;
import manager.util.DbUtil;
import manager.util.Dialogutil;
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
	    private JTextField UsernameTxt;
	    private JPasswordField passwordTxt;
	    private DbUtil dbutil=new DbUtil();
	    private UserDao userdao=new UserDao();
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		//改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		
		setResizable(false);
		setTitle("\u7BA1\u7406\u5458\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(screenWidth * 2/7, screenHeight / 3, (int)(1200*enlargement_x),(int)(1000*enlargement_y));
		//参数分别为前两个是左上角位置，后两个是窗体大小
		//setSize(screenWidth / 2, screenHeight / 2);
		
		windowWidth = this.getWidth(); //获得窗口宽
		windowHeight = this.getHeight(); //获得窗口高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel Title_Label = new JLabel(" \u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		Title_Label.setFont(new Font("楷体", Font.PLAIN, 60));
		Title_Label.setIcon(new ImageIcon(LogOnfrm.class.getResource("/manager/image/logo.png")));
		
		JLabel User_Label = new JLabel("\u7528\u6237\u540D");
		User_Label.setFont(new Font("宋体", Font.PLAIN, 35));
		User_Label.setIcon(new ImageIcon(LogOnfrm.class.getResource("/manager/image/userName.png")));
		User_Label.setPreferredSize(new Dimension((int)(120*enlargement_x),(int)(80*enlargement_y)));
		
		JLabel Password_Label = new JLabel(" \u5BC6\u7801 ");
		Password_Label.setFont(new Font("宋体", Font.PLAIN, 35));
		Password_Label.setIcon(new ImageIcon(LogOnfrm.class.getResource("/manager/image/password.png")));
		Password_Label.setPreferredSize(new Dimension((int)(120*enlargement_x),(int)(80*enlargement_y)));
		UsernameTxt = new JTextField();
		UsernameTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		UsernameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton Login_Button = new JButton("\u767B\u5F55");
		Login_Button.setFont(new Font("宋体", Font.PLAIN, 30));
		Login_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginAction(e);
			}
		});
		Login_Button.setIcon(new ImageIcon(LogOnfrm.class.getResource("/manager/image/login.png")));
		
		JButton Reset_Button = new JButton("\u91CD\u7F6E");
		Reset_Button.setFont(new Font("宋体", Font.PLAIN, 30));
		Reset_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetValueAction(e);
			}
		});
		Reset_Button.setIcon(new ImageIcon(LogOnfrm.class.getResource("/manager/image/reset.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(137)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(Password_Label, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addGap(64)
							.addComponent(passwordTxt, GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(User_Label, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
							.addGap(46)
							.addComponent(UsernameTxt, GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)))
					.addGap(157))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(279)
					.addComponent(Login_Button, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGap(396)
					.addComponent(Reset_Button, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(166, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(358)
					.addComponent(Title_Label, GroupLayout.PREFERRED_SIZE, 514, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(312, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(78)
					.addComponent(Title_Label, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(60)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(UsernameTxt, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(User_Label, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
					.addGap(120)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Password_Label, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
					.addGap(141)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Login_Button, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addComponent(Reset_Button, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
					.addGap(209))
		);
		contentPane.setLayout(gl_contentPane);
		//设置JFrame居中显示
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * 登录事件处理函数
	 * @param evt
	 */
private void LoginAction(ActionEvent evt) {
		// TODO Auto-generated method stub
		String userName=this.UsernameTxt.getText();
		String password=new String(this.passwordTxt.getPassword());//返回char型
		if(StringUtil.isEmpty(userName))
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
		User user=new User(userName,password);
		Connection con=null;
		try {
			con=dbutil.getCon();
		    User currentUser=userdao.login( con, user);
		if(currentUser!=null)
		{
//			JLabel LogSuc = new JLabel("登录成功！");
//			LogSuc.setFont(new Font("宋体", Font.PLAIN, 35));
//			JOptionPane.showMessageDialog(null, LogSuc,"Success", JOptionPane.INFORMATION_MESSAGE);
//			//应该为登录窗口销毁，打开主界面，这里还没写，测试
			//showMessageFrame LogSuc=new showMessageFrame(contentPane,"登录成功！",showMessageFrame.NORMAL);
			dispose();
			MainFrm MainWindow=new MainFrm();
			MainWindow.setVisible(true);
			//MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		else
		{
			//JOptionPane.showMessageDialog(null, "用户名或密码错误","Error", JOptionPane.ERROR_MESSAGE);
			Dialogutil Logfail=new Dialogutil(this,"Error","  用户名或密码错误  ");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
		this.UsernameTxt.setText("");
		this.passwordTxt.setText("");
		
	}
}
