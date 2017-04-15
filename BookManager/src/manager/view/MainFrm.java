package manager.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import manager.dao.BookTypeDao;
import manager.entity.BookType;
import manager.util.DbUtil;
import manager.util.ShowConfirmDialog;
import javax.swing.JDesktopPane;
import java.awt.Color;

public class MainFrm extends JFrame {

	 private JPanel contentPane;
	 private JDesktopPane Table; 
	//设置跟随分辨率变化窗口
			Toolkit kit = Toolkit.getDefaultToolkit();
		    Dimension screenSize = kit.getScreenSize();
			private int screenHeight = (int) screenSize.getHeight();
		    private int screenWidth = (int) screenSize.getWidth();
		    private double enlargement_x=screenWidth/1920;
		    private double enlargement_y=screenHeight/1080;
		   
	/**
	 * Launch the application. 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
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
	public MainFrm() {
		setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 835, 635);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Table = new JDesktopPane();
		Table.setBackground(SystemColor.inactiveCaptionBorder);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(Table, GroupLayout.DEFAULT_SIZE, 3174, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(Table, GroupLayout.DEFAULT_SIZE, 1667, Short.MAX_VALUE))
		);
		GroupLayout gl_Table = new GroupLayout(Table);
		gl_Table.setHorizontalGroup(
			gl_Table.createParallelGroup(Alignment.LEADING)
				.addGap(0, 3174, Short.MAX_VALUE)
		);
		gl_Table.setVerticalGroup(
			gl_Table.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1667, Short.MAX_VALUE)
		);
		Table.setLayout(gl_Table);
		contentPane.setLayout(gl_contentPane);
		
		JMenuBar MainMenuBar = new JMenuBar();
		MainMenuBar.setBackground(SystemColor.info);
		MainMenuBar.setForeground(SystemColor.info);
		setJMenuBar(MainMenuBar);
		
		JMenu DataServeMenu = new JMenu("\u57FA\u672C\u6570\u636E\u7EF4\u62A4");
		DataServeMenu.setFont(new Font("宋体", Font.PLAIN, 35));
		DataServeMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/manager/image/base.png")));
		MainMenuBar.add(DataServeMenu);
//		DataServeMenu.setPreferredSize(new Dimension((int)(220*enlargement_x),(int)(30*enlargement_y)));
		
		JMenu CategoryMgr = new JMenu("\u56FE\u4E66\u7C7B\u522B\u7BA1\u7406");
		CategoryMgr.setFont(new Font("宋体", Font.PLAIN, 35));
		CategoryMgr.setIcon(new ImageIcon(MainFrm.class.getResource("/manager/image/bookTypeManager.png")));
		DataServeMenu.add(CategoryMgr);
		
		JMenuItem CategoryServ = new JMenuItem("\u56FE\u4E66\u7C7B\u522B\u7EF4\u62A4");
		CategoryServ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent maintenance) {
				BookTypeManagerFrm bookTypeManagerFrm=new BookTypeManagerFrm();
				bookTypeManagerFrm.setVisible(true);
			}
		});
		CategoryServ.setFont(new Font("宋体", Font.PLAIN, 35));
		CategoryServ.setIcon(new ImageIcon(MainFrm.class.getResource("/manager/image/edit.png")));
		CategoryMgr.add(CategoryServ);
		
		JMenuItem CategoryAdd = new JMenuItem("\u56FE\u4E66\u7C7B\u522B\u6DFB\u52A0");
		CategoryAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Add) {
				BookTypeAddFrm bookTypeAddFrm=new BookTypeAddFrm();
				bookTypeAddFrm.setVisible(true);				
			}
		});
		CategoryAdd.setFont(new Font("宋体", Font.PLAIN, 35));
		CategoryAdd.setIcon(new ImageIcon(MainFrm.class.getResource("/manager/image/add.png")));
		CategoryMgr.add(CategoryAdd);
		
		JMenu BookMgr = new JMenu("\u56FE\u4E66\u7BA1\u7406");
		BookMgr.setFont(new Font("宋体", Font.PLAIN, 35));
		BookMgr.setIcon(new ImageIcon(MainFrm.class.getResource("/manager/image/bookManager.png")));
		DataServeMenu.add(BookMgr);
		
		JMenuItem BookAdd = new JMenuItem("\u56FE\u4E66\u6DFB\u52A0");
		BookAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookAddFrm bookAddFrm=new BookAddFrm();
				bookAddFrm.setVisible(true);			
			}
		});
		BookAdd.setFont(new Font("宋体", Font.PLAIN, 35));
		BookAdd.setIcon(new ImageIcon(MainFrm.class.getResource("/manager/image/add.png")));
		BookMgr.add(BookAdd);
		
		JMenuItem BookServ = new JMenuItem("\u56FE\u4E66\u7EF4\u62A4");
		BookServ.setFont(new Font("宋体", Font.PLAIN, 35));
		BookServ.setIcon(new ImageIcon(MainFrm.class.getResource("/manager/image/edit.png")));
		BookMgr.add(BookServ);
		
		JMenuItem Exit = new JMenuItem("\u5B89\u5168\u9000\u51FA");
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowConfirmDialog confrm=new ShowConfirmDialog(null,"提示","是否退出系统");
//				synchronized(confrm)
//				{
//					if(confrm.getResult()==-1)
//				{
//				     try {
//						wait();
//					} catch (InterruptedException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				     notifyAll();
//				}
//					}
//			int result=JOptionPane.showConfirmDialog(null,"是否退出系统");
					new Thread(
						new Runnable() {							 
						    @Override
						public void run(){
//						    	synchronized(confrm)
//						    	{
							do
							{
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}while(confrm.getResult()==-1);							
					if(confrm.getResult()==0)
				          {
						     dispose(); //清除主窗口
				          }		
//					System.out.println(confrm.getResult());	
//						    	}
				}						
			}).start();
			}
		});
		Exit.setFont(new Font("宋体", Font.PLAIN, 35));
		Exit.setIcon(new ImageIcon(MainFrm.class.getResource("/manager/image/exit.png")));
		DataServeMenu.add(Exit);
		
		JMenu AboutUsMenu = new JMenu("\u5173\u4E8E\u6211\u4EEC");
		AboutUsMenu.setFont(new Font("宋体", Font.PLAIN, 35));
		AboutUsMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/manager/image/about.png")));
		MainMenuBar.add(AboutUsMenu);
		
		JMenuItem Itemblog = new JMenuItem("\u6211\u7684\u535A\u5BA2");
		Itemblog.setFont(new Font("宋体", Font.PLAIN, 35));
		Itemblog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳转到我的博客
				new Thread() {
					//重写run方法
					public void run() {
						//构造命令
						String cmd = "cmd.exe /c start ";//cmd /c start dir 会打开一个新窗口后执行dir指令				
						//构造本地文件路径或者网页URL
						String file = "http://www.cnblogs.com/satire/";
						//String file = "C:/Users/Wentasy/Desktop/core_java_3_api/index.html";						
						try {
							//执行操作
							Runtime.getRuntime().exec(cmd + file);
						} catch (Exception ignore) {
							//打印异常
							ignore.printStackTrace();//打印toString()结果和栈层次到System.err，即错误输出流。
						}
					}
				}.start();//启动线程
			//}
			}
		});
		Itemblog.setIcon(new ImageIcon(MainFrm.class.getResource("/manager/image/about.png")));
		AboutUsMenu.add(Itemblog);
							
		//设置JFrame最大化
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}
}
