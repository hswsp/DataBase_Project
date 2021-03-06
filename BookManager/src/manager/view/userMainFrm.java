package manager.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import manager.dao.BookDao;
import manager.dao.BookTypeDao;
import manager.dao.BorrowDao;
import manager.dao.UserDao;
import manager.dao.bookRecomDao;
import manager.entity.Book;
import manager.entity.BookRecommend;
import manager.entity.BookType;
import manager.entity.Borrow;
import manager.entity.DateInt;
import manager.entity.HisBorrow;
import manager.entity.MyDate;
import manager.entity.User;
import manager.util.DateUtil;
import manager.util.DbUtil;
import manager.util.Dialogutil;
import manager.util.PieChart;
import manager.util.ShowConfirmDialog;
import manager.util.StringUtil;
import manager.util.TimeSeriesChart;
import manager.util.showMessageFrame;

public class userMainFrm extends JFrame {
    //当前用户信息
	private String PresentUser;
	private User presentUser;//=new User()
	private final int TotalBookCanBorrow=8;
	
  //设置跟随分辨率变化窗口
	Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
	private int screenHeight = (int) screenSize.getHeight();
    private int screenWidth = (int) screenSize.getWidth();
    private double enlargement_x=screenWidth/1920;
    private double enlargement_y=screenHeight/1080;
    private final static double split_scale=0.25;
    private int windowWidth ; //获得窗口宽
    private int windowHeight; //获得窗口高
    private MyDate date=new MyDate();//当前时间
    //容器组件
   // private JPanel contentPane;
    private JSplitPane splitPane = new JSplitPane();
    private JPanel TutorJp = new JPanel();
    private JPanel OperationJp = new JPanel();
    private JPanel PreBorrowJP = new JPanel();
    private JPanel RecomJP = new JPanel();
    private JPanel HisBorrowJP=new JPanel();
    private JPanel PieChartJP = new JPanel();	
   	private JPanel BrokenLineJP = new JPanel();
   	private JPanel CredentialInfoJP = new JPanel();
    //控件
    private JComboBox booktypeJcb;  //查询界面书类选择	
//   	private final JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u540D\u79F0");
//   	private final JTextField textField = new JTextField();
//   	private final JButton button = new JButton("\u67E5\u8BE2");
//   	private final JLabel label_1 = new JLabel("\u56FE\u4E66\u67E5\u8BE2\u4E0E\u501F\u9605");
   	private JTextField BookNameTxt;
   	private JTable BookSearchJT;
    private JTable PreBorrowJtable;
    private JTable HisBorrowJtable;   
	private JScrollPane PreBorrowJsp; 
	private JTextArea RecomReasonTxt;
   	private JTextArea BookDescTxt;
   	private JComboBox BookTypeJcb;//推荐界面
    private JTextField ReaderIDTxt;
    private JTextField ReaderName;
    private JTextField bookTotalTxt;
    private JTextField bookBorTxt;
    public JTextField balanceTxt;
    private JTextField NewNameTxt;
    private JLabel NewNameJL;
   	private JTextField RecomNameTxt;
   	private JButton NewNameButton;
   	//根据左边按钮选择显示哪一个界面
 	//1是查询，2是当前借阅，3是历史借阅信息，4是证件信息，5是推荐
   	private int ShowSelect=1;
    	
   	//*******************借书事件变量**********************/
   	private boolean IsClickJT=false;
   	private int BookID;
   	private int BookSurplus;
	//*******************还书事件变量**********************/
   	private boolean IsClickPreBorrowJT=false;
   	private int BorrowID;
   	private int[]IDinPreBorJT=new int[30];//用于记录当前PreBorrowJtable中每行对应的借阅ID,其实每个人最多借8本
   	//*****************查阅历史信息************************************/
   	final private int typenum=50;//用于存放有多少图书种类
   	int []TypeNum=new int[typenum];
	StringBuffer[] TypeBuffer=new StringBuffer[typenum];
	int []BookMonthly=new int[13];//统计各月借阅数量,下标与月份对齐
	int CurrentYear;//当前年份，用于折线图数据
	/**************************修改用户信息变量*********************************************/
	private boolean IsEditName=false;
	private PayFrm payfrm;
	public boolean IsOpened=false;
   //JDBC接口	
    private DbUtil  dbUtil=new DbUtil();
    private UserDao userdao=new UserDao();
    private BookTypeDao bookTypeDao=new BookTypeDao();
   	private BookDao bookDao=new BookDao();
   	private BorrowDao borrowDao=new BorrowDao();
    private bookRecomDao BookRecomDao=new bookRecomDao();

   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					userMainFrm frame = new userMainFrm();
//					frame.setVisible(true);
//				    frame.splitPane.setDividerLocation(split_scale);//设定分割面板的左右比例(这时候就生效了，如果放在setVisible(true)这据之前就不会有效果。
//				    frame.splitPane.setEnabled(false);            //设置分隔条禁止拖动  					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public userMainFrm() throws HeadlessException
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create the frame.
	 */
	public userMainFrm (User curUser) //String User
	{
		presentUser=curUser;
		PresentUser=curUser.getId();//单窗口调试请注释		
//		button.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/search.png")));
//		button.setFont(new Font("宋体", Font.PLAIN, 35));
//		textField.setFont(new Font("宋体", Font.PLAIN, 35));
//		textField.setColumns(10);
//		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 35));
		setTitle("\u7528\u6237\u4E3B\u754C\u9762");
		setSize(new Dimension(screenSize.width,screenSize.height));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//EXIT_ON_CLOSE	
		//setResizable(false);
	    //	setBounds(0, 0, screenWidth,screenHeight);		
		windowWidth = this.getWidth(); //获得窗口宽
		windowHeight = this.getHeight();		
		//分屏
		this.getContentPane().add(splitPane, java.awt.BorderLayout.CENTER);
		splitPane.add(TutorJp, JSplitPane.LEFT);		
		GroupLayout gl_TutorJp = new GroupLayout(TutorJp);
		JLayeredPane SearchJlp = new JLayeredPane();

//		label_1.setFont(new Font("宋体", Font.PLAIN, 35));
//		label_1.setBounds(41, 0, 135, 35);		
//		SearchJlp.add(label_1);
	//*************************************左边************************************************/		
		TutorJp.setLayout(gl_TutorJp);
		//setBounds(100, 100, 762, 1120);
		//创立三个内窗口
		JInternalFrame SearchJIF = new JInternalFrame("\u56FE\u4E66\u67E5\u8BE2\u4E0E\u501F\u9605");
		SearchJIF.getContentPane().setFont(new Font("宋体", Font.PLAIN, 35));
		BasicInternalFrameUI ui = (BasicInternalFrameUI)SearchJIF.getUI();
		ui.setNorthPane(null);		
		SearchJIF.setBorder(BorderFactory.createEmptyBorder());
		SearchJIF.setVisible(true);		
		JInternalFrame ID_JIF = new JInternalFrame("\u4E2A\u4EBA\u4FE1\u606F\u8BBE\u7F6E");
		//去边框
		BasicInternalFrameUI ui_1 = (BasicInternalFrameUI)ID_JIF.getUI();
		ui_1.setNorthPane(null);
		ID_JIF.setBorder(BorderFactory.createEmptyBorder());		
		ID_JIF.getContentPane().setFont(new Font("宋体", Font.PLAIN, 35));
		ID_JIF.setVisible(true);		
		JInternalFrame RecomJIF = new JInternalFrame("\u63A8\u8350\u4FE1\u606F");
		RecomJIF.getContentPane().setFont(new Font("宋体", Font.PLAIN, 35));
		//去边框
		BasicInternalFrameUI RecomUI = (BasicInternalFrameUI)RecomJIF.getUI();
		RecomUI.setNorthPane(null);
		RecomJIF.setBorder(BorderFactory.createEmptyBorder());
		JPanel RecommendJP = new JPanel();
		RecommendJP.setBorder(new TitledBorder(null, "\u63A8\u8350\u4FE1\u606F", TitledBorder.LEADING, 
				TitledBorder.TOP, new Font("宋体", Font.PLAIN, 35), null));		
		JPanel ExitJPL = new JPanel();
		ExitJPL.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5B89\u5168\u9000\u51FA\u7CFB\u7EDF", 
				TitledBorder.LEADING, TitledBorder.TOP, new Font("宋体", Font.PLAIN, 35), Color.DARK_GRAY));
		
		//边框布局
		GroupLayout groupLayout_2 = new GroupLayout(RecomJIF.getContentPane());
		groupLayout_2.setHorizontalGroup(
			groupLayout_2.createParallelGroup(Alignment.LEADING)
				.addComponent(RecommendJP, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
		);
		groupLayout_2.setVerticalGroup(
			groupLayout_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout_2.createSequentialGroup()
					.addComponent(RecommendJP, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		RecommendJP.setLayout(null);
		
		
		RecomJIF.getContentPane().setLayout(groupLayout_2);
		RecomJIF.setVisible(true);
		
		//欢迎标语
		//欢迎标签
        DateUtil.getdateWithMinute(date);
        String welcomeString;
        if(date.getHour()<12)
        {
        	welcomeString="上午好,"+this.presentUser.getUserName();
        }
        else
        {
        	welcomeString="下午好,"+this.presentUser.getUserName();
        }
		JLabel WwlcomeJL = new JLabel(welcomeString);
		WwlcomeJL.setFont(new Font("华文行楷", Font.PLAIN, 40));
		//GroupLayout gl_TutorJp = new GroupLayout(TutorJp);
		gl_TutorJp.setHorizontalGroup(
				gl_TutorJp.createParallelGroup(Alignment.LEADING)
					.addComponent(SearchJIF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
					.addComponent(ID_JIF, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
					.addComponent(RecomJIF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
					.addComponent(ExitJPL, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
					.addGroup(gl_TutorJp.createSequentialGroup()
							.addGap(263)
							.addComponent(WwlcomeJL)
							.addContainerGap(342, Short.MAX_VALUE))
			);
			gl_TutorJp.setVerticalGroup(
				gl_TutorJp.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_TutorJp.createSequentialGroup()
						.addComponent(SearchJIF, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(ID_JIF, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(RecomJIF, GroupLayout.DEFAULT_SIZE, 298,GroupLayout.PREFERRED_SIZE )
						.addComponent(ExitJPL, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)						
					   .addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)//
					   .addComponent(WwlcomeJL)
					   //.addGap(47)
					   .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)//Short.MAX_VALUE
					   )
			);
		
		//为每一个InterJframe增添组件	
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u4E2A\u4EBA\u4FE1\u606F\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, 
				new Font("宋体", Font.PLAIN, 35), null));
		GroupLayout groupLayout_1 = new GroupLayout(ID_JIF.getContentPane());
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
		);
		
		//标签
		JLabel IDJLB = new JLabel("",SwingConstants.CENTER);		
		IDJLB.setFont(new Font("宋体", Font.PLAIN, 35));
		JLabel RecomJLb = new JLabel("",SwingConstants.CENTER);
		RecomJLb.setBounds(254, 41, 256, 73);
		RecomJLb.setFont(new Font("宋体", Font.PLAIN, 35));
		RecommendJP.add(RecomJLb);
		
		JLabel Daily_Op = new JLabel("",SwingConstants.CENTER);
		Daily_Op.setBounds(247, 43, 264, 91);
		Daily_Op.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton CertificateJB = new JButton("\u8BC1\u4EF6\u4FE1\u606F");
		CertificateJB.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/certificate.png")));
		CertificateJB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ResetValue();
				ShowSelect=4;				
				FreshShow();	
			}
		});
		CertificateJB.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton PreBorrowInfo = new JButton("\u5F53\u524D\u501F\u9605");
		PreBorrowInfo.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/PreBook.net.png")));
		PreBorrowInfo.setFont(new Font("宋体", Font.PLAIN, 35));
		PreBorrowInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ResetValue();
				ShowSelect=2;				
				FreshShow();	
			}
		});
		
		JButton HistoryBorrow = new JButton("\u501F\u9605\u5386\u53F2");
		HistoryBorrow.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/HisBook.png")));
		HistoryBorrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResetValue();
				ShowSelect=3;				
				FreshShow();
			
			}


		});
		HistoryBorrow.setFont(new Font("宋体", Font.PLAIN, 35));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(188)
							.addComponent(IDJLB, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(255)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(HistoryBorrow, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(PreBorrowInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(CertificateJB, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(224, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(IDJLB, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(CertificateJB, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PreBorrowInfo, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(HistoryBorrow, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		ID_JIF.getContentPane().setLayout(groupLayout_1);
		
		//图片准备
		int width = 300,height = 100; //这是图片和JLable的宽度和高度   
		ImageIcon icon = new ImageIcon(userMainFrm.class.getResource("/res/dayly_op.png")); 
		ImageIcon ID_icon = new ImageIcon(userMainFrm.class.getResource("/res/ID_Info.png")); // 
		ImageIcon Recom_icon = new ImageIcon(userMainFrm.class.getResource("/res/Recommend.png"));//
		//改变图片大小          
		icon.setImage(icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));//80和100为大小 可以自由设置       
		ID_icon.setImage(ID_icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
		Recom_icon.setImage(Recom_icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
		
		//添加图片
		Daily_Op.setIcon(icon);
		IDJLB.setIcon(ID_icon);
		RecomJLb.setIcon(Recom_icon);
		
		JPanel dayly_panel = new JPanel();
		dayly_panel.setBorder(new TitledBorder(null, "\u56FE\u4E66\u67E5\u8BE2\u4E0E\u501F\u9605", 
				TitledBorder.LEADING, TitledBorder.TOP, new Font("宋体", Font.PLAIN, 35), null));
		
		GroupLayout groupLayout = new GroupLayout(SearchJIF.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(dayly_panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(dayly_panel, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
		);					
		
		JButton SearchButton = new JButton("\u56FE\u4E66\u67E5\u8BE2");
		SearchButton.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/search.png")));
		SearchButton.setBounds(243, 139, 262, 70);
		SearchButton.setFont(new Font("宋体", Font.PLAIN, 35));
		SearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				ResetValue();
				ShowSelect=1;				
				FreshShow();
				}	
		});
		
		JButton BorrowButton = new JButton("\u56FE\u4E66\u501F\u9605");
		BorrowButton.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/return_book.png")));
		BorrowButton.setBounds(243, 221, 262, 70);
		BorrowButton.setFont(new Font("宋体", Font.PLAIN, 35));
		dayly_panel.setLayout(null);		
		BorrowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent brevt) {
				borrowActionPerformed(brevt);
			}			
		});
		
		JButton ReturnButurn = new JButton("\u56FE\u4E66\u5F52\u8FD8");
		ReturnButurn.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/bor_book.png")));
		ReturnButurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
							returnactionPerformed(e);	
			}
		});
		ReturnButurn.setBounds(243, 305, 262, 70);
		ReturnButurn.setFont(new Font("宋体", Font.PLAIN, 35));
		dayly_panel.add(ReturnButurn);
		dayly_panel.add(Daily_Op);
		dayly_panel.add(SearchButton);
		dayly_panel.add(BorrowButton);
		
		JButton RecomJB = new JButton("\u56FE\u4E66\u63A8\u8350");
		RecomJB.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/Recom.png")));
		RecomJB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResetValue();
				ShowSelect=5;				
				FreshShow();	
			}
		});
		RecomJB.setFont(new Font("宋体", Font.PLAIN, 35));
		RecomJB.setBounds(258, 130, 227, 70);
		RecommendJP.add(RecomJB);
		SearchJIF.getContentPane().setLayout(groupLayout);
		
		//退出按钮
		JButton exitButton = new JButton("");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent exit) {
				ShowConfirmDialog confrm=new ShowConfirmDialog(null,"提示","是否退出系统");
					new Thread(
						new Runnable() {							 
						    @Override
						public void run(){
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
					       	Connection con=null;
						     try {
								con=dbUtil.getCon();
							   int num=UserDao.modifyIsLoginField(con,PresentUser, (byte)0);
							   if(num==1)
							   {
								   dispose(); //清除主窗口
								   System.exit(0);
							   }
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    
						     
				          }		
				}						
			}).start();
			}			
		});
		//加图片
		int exit_width=203,exit_height=65;
		exitButton.setFont(new Font("宋体", Font.PLAIN, 35));
		ImageIcon exiticon = new ImageIcon(userMainFrm.class.getResource("/res/exit.PNG"));
		exiticon.setImage(exiticon.getImage().getScaledInstance(exit_width, exit_height,Image.SCALE_DEFAULT ));
		exitButton.setIcon(exiticon);
		GroupLayout gl_ExitJPL = new GroupLayout(ExitJPL);
		gl_ExitJPL.setHorizontalGroup(
				gl_ExitJPL.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_ExitJPL.createSequentialGroup()
						.addGap(248)
						.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, exit_width, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(278, Short.MAX_VALUE))
			);
		gl_ExitJPL.setVerticalGroup(
			gl_ExitJPL.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ExitJPL.createSequentialGroup()
					.addGap(54)
					.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, exit_height, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(95, Short.MAX_VALUE))
		);
		ExitJPL.setLayout(gl_ExitJPL);		
		/**********************************右边屏幕**************************************************/	
		//调试时候使用，发布时候请注释
		//splitPane.add(OperationJp, JSplitPane.RIGHT);
		//splitPane.setRightComponent(RecomJP);
		//splitPane.setRightComponent(PreBorrowJP);	//图书归还界面
		 //splitPane.setRightComponent(HisBorrowJP);
		//splitPane.setRightComponent(CredentialInfoJP);
		//******************************用户信息界面******************************************************/
		//GetPresentUserInfo();
		JLabel IDJLable = new JLabel("\u8BFB\u8005ID");
		IDJLable.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/ReaderID.png")));
		IDJLable.setFont(new Font("华文行楷", Font.PLAIN, 40));
		
		ReaderIDTxt = new JTextField();
		ReaderIDTxt.setEditable(false);
		ReaderIDTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		ReaderIDTxt.setColumns(10);
		ReaderIDTxt.setText(PresentUser);
		
		JLabel ReaderNameJL = new JLabel("\u7528\u6237\u540D\u79F0");
		ReaderNameJL.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/ReaderName.png")));
		ReaderNameJL.setFont(new Font("华文行楷", Font.PLAIN, 40));
		
		ReaderName = new JTextField();
		ReaderName.setEditable(false);
		ReaderName.setFont(new Font("宋体", Font.PLAIN, 40));
		ReaderName.setColumns(10);
		ReaderName.setText(presentUser.getUserName());
		JButton UserNameChangeJb = new JButton("\u4FEE\u6539\u7528\u6237\u540D");
		UserNameChangeJb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent edit) {
				UserNameEditactionPerformed(edit);
			}
		});
		UserNameChangeJb.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/changeUser.png")));
		UserNameChangeJb.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel bookTotalJL = new JLabel("\u53EF\u501F\u4E66\u672C\u6570");
		bookTotalJL.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/Evernote_Book.png")));
		bookTotalJL.setFont(new Font("华文行楷", Font.PLAIN, 40));
		
		bookTotalTxt = new JTextField();
		bookTotalTxt.setEditable(false);
		bookTotalTxt.setFont(new Font("宋体", Font.PLAIN, 40));
		bookTotalTxt.setColumns(10);
		
		bookTotalTxt.setText(Integer.toString(TotalBookCanBorrow));
		JLabel bookBorJL = new JLabel("\u5DF2\u501F\u4E66\u6570\u76EE");
		bookBorJL.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/green_book.png")));
		bookBorJL.setFont(new Font("华文行楷", Font.PLAIN, 40));
		
		bookBorTxt = new JTextField();
		bookBorTxt.setEditable(false);
		bookBorTxt.setFont(new Font("宋体", Font.PLAIN, 40));
		bookBorTxt.setColumns(10);
		bookBorTxt.setText(Integer.toString(TotalBookCanBorrow-presentUser.getBorrowNumRem()));
		JLabel balanceJL = new JLabel("\u4F59\u989D");
		balanceJL.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/money_bag.png")));
		balanceJL.setFont(new Font("华文隶书", Font.PLAIN, 40));
		
		balanceTxt = new JTextField();
		balanceTxt.setEditable(false);
		balanceTxt.setFont(new Font("宋体", Font.PLAIN, 40));
		balanceTxt.setColumns(10);
		String str = String.valueOf(presentUser.getBalance());
		balanceTxt.setText(str);
		JButton payButton = new JButton("\u7F34\u7EB3\u8D39\u7528");
		payButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent pay) {
				payactionPerformed(pay);
			}			
		});
		payButton.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/pay.png")));
		payButton.setFont(new Font("华文隶书", Font.PLAIN, 40));
		
		NewNameJL = new JLabel("\u65B0\u7528\u6237\u540D\u79F0");
		NewNameJL.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/user_edit_JL.png")));
		NewNameJL.setFont(new Font("华文行楷", Font.PLAIN, 40));
		
		
		NewNameTxt = new JTextField();
		NewNameTxt.setFont(new Font("宋体", Font.PLAIN, 40));
		NewNameTxt.setColumns(10);
		
		
		NewNameButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		NewNameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent newName) {
				NewNameactionPerformed(newName);
			}		
		});
		NewNameButton.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/edit_user.png")));
		NewNameButton.setFont(new Font("宋体", Font.PLAIN, 40));
		GroupLayout gl_CredentialInfoJP = new GroupLayout(CredentialInfoJP);
		gl_CredentialInfoJP.setHorizontalGroup(
			gl_CredentialInfoJP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CredentialInfoJP.createSequentialGroup()
					.addGap(333)
					.addGroup(gl_CredentialInfoJP.createParallelGroup(Alignment.LEADING)
						.addComponent(bookTotalJL)
						.addGroup(gl_CredentialInfoJP.createSequentialGroup()
							.addGroup(gl_CredentialInfoJP.createParallelGroup(Alignment.LEADING)
								.addComponent(IDJLable)
								.addComponent(ReaderNameJL)
								.addComponent(balanceJL)
								.addComponent(NewNameJL))
							.addGap(145)
							.addGroup(gl_CredentialInfoJP.createParallelGroup(Alignment.LEADING, false)
								.addComponent(NewNameTxt)
								.addComponent(ReaderIDTxt)
								.addComponent(ReaderName, GroupLayout.DEFAULT_SIZE, 1107, Short.MAX_VALUE)
								.addGroup(gl_CredentialInfoJP.createSequentialGroup()
									.addGroup(gl_CredentialInfoJP.createParallelGroup(Alignment.LEADING, false)
										.addComponent(balanceTxt)
										.addComponent(bookTotalTxt, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
									.addGroup(gl_CredentialInfoJP.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_CredentialInfoJP.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, 379, Short.MAX_VALUE)
											.addComponent(payButton, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
											.addGap(208))
										.addGroup(gl_CredentialInfoJP.createSequentialGroup()
											.addGap(189)
											.addComponent(bookBorJL)
											.addPreferredGap(ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
											.addComponent(bookBorTxt, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)))))
							.addGap(115)
							.addGroup(gl_CredentialInfoJP.createParallelGroup(Alignment.LEADING, false)
								.addComponent(NewNameButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(UserNameChangeJb, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))))
					.addContainerGap(354, Short.MAX_VALUE))
		);
		gl_CredentialInfoJP.setVerticalGroup(
			gl_CredentialInfoJP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CredentialInfoJP.createSequentialGroup()
					.addGap(221)
					.addGroup(gl_CredentialInfoJP.createParallelGroup(Alignment.BASELINE)
						.addComponent(IDJLable, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(ReaderIDTxt, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addGap(164)
					.addGroup(gl_CredentialInfoJP.createParallelGroup(Alignment.BASELINE)
						.addComponent(ReaderNameJL)
						.addComponent(ReaderName, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addComponent(UserNameChangeJb, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
					.addGap(234)
					.addGroup(gl_CredentialInfoJP.createParallelGroup(Alignment.BASELINE)
						.addComponent(NewNameJL)
						.addComponent(NewNameTxt, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(NewNameButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addGap(261)
					.addGroup(gl_CredentialInfoJP.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookTotalJL)
						.addComponent(bookTotalTxt, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookBorJL)
						.addComponent(bookBorTxt, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
					.addGap(255)
					.addGroup(gl_CredentialInfoJP.createParallelGroup(Alignment.BASELINE)
						.addComponent(balanceJL, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(balanceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(payButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addGap(238))
		);
		NewNameButton.setVisible(false);
		NewNameJL.setVisible(false);
		NewNameTxt.setVisible(false);
		CredentialInfoJP.setLayout(gl_CredentialInfoJP);
		 /***************************返回按钮*******************************/
		JLabel ReturnJL = new JLabel("\u5F53\u524D\u501F\u9605");
		ReturnJL.setEnabled(false);
		ReturnJL.setFont(new Font("宋体", Font.PLAIN, 35));
		/***************************当前借阅界面*********************************************/
		PreBorrowJsp = new JScrollPane();
		PreBorrowJsp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ret) {
				//bookReturnClick(ret);
			}	
		});
		
		JButton PreBorSearButton = new JButton("\u67E5\u8BE2");
		PreBorSearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PreBorrowSearchPerformed(e);
			}			
		});
		PreBorSearButton.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/search.png")));
		PreBorSearButton.setFont(new Font("宋体", Font.PLAIN, 35));
		GroupLayout gl_PreBorrowJP = new GroupLayout(PreBorrowJP);
		gl_PreBorrowJP.setHorizontalGroup(
			gl_PreBorrowJP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PreBorrowJP.createSequentialGroup()
					.addGap(285)
					.addGroup(gl_PreBorrowJP.createParallelGroup(Alignment.LEADING)
						.addComponent(PreBorrowJsp, GroupLayout.PREFERRED_SIZE, 1927, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_PreBorrowJP.createSequentialGroup()
							.addComponent(ReturnJL, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
							.addGap(133)
							.addComponent(PreBorSearButton, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(476, Short.MAX_VALUE))
		);
		gl_PreBorrowJP.setVerticalGroup(
			gl_PreBorrowJP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PreBorrowJP.createSequentialGroup()
					.addGap(191)
					.addGroup(gl_PreBorrowJP.createParallelGroup(Alignment.BASELINE)
						.addComponent(ReturnJL, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(PreBorSearButton, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
					.addGap(62)
					.addComponent(PreBorrowJsp, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(961, Short.MAX_VALUE))
		);
		
		PreBorrowJtable = new JTable();
		PreBorrowJtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7528\u6237ID", "\u5F53\u524D\u501F\u9605", "\u5E94\u8FD8\u65E5\u671F"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		PreBorrowJtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent ret) {
				bookReturnClick(ret);
			}	
		});
		PreBorrowJtable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		PreBorrowJtable.getTableHeader().setPreferredSize(new Dimension((int)(1*enlargement_x),(int)(40*enlargement_y)));
		PreBorrowJtable.getTableHeader().setFont(new Font("宋体", Font.PLAIN, (int)(35*enlargement_y)));
		PreBorrowJtable.setRowHeight((int)(80*enlargement_y));
		PreBorrowJtable.getColumnModel().getColumn(0).setPreferredWidth(209);
		PreBorrowJtable.getColumnModel().getColumn(1).setPreferredWidth(406);
		PreBorrowJtable.getColumnModel().getColumn(2).setPreferredWidth(221);
		PreBorrowJtable.setFont(new Font("宋体", Font.PLAIN, 35));
		PreBorrowJsp.add(PreBorrowJtable);
		PreBorrowJsp.setViewportView(PreBorrowJtable);
		
		PreBorrowJP.setLayout(gl_PreBorrowJP);
		
		/***************************推荐界面********************************************/
		JLabel RecomNameJL = new JLabel("\u63A8\u8350\u4E66\u540D");
		RecomNameJL.setFont(new Font("宋体", Font.PLAIN, 35));
		
		RecomNameTxt = new JTextField();
		RecomNameTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		RecomNameTxt.setColumns(10);
		
		JButton RecomConfirJB = new JButton("\u63A8\u8350");
		RecomConfirJB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent rec) {
				RecomactionPerformed(rec);
			}

		
		});
		RecomConfirJB.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/Recom.png")));
		
		RecomConfirJB.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel RecomTypeJL = new JLabel("\u56FE\u4E66\u7C7B\u578B");
		RecomTypeJL.setFont(new Font("宋体", Font.PLAIN, 35));
		
		BookTypeJcb = new JComboBox();
		BookTypeJcb.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel BookDescJL = new JLabel("\u56FE\u4E66\u63CF\u8FF0");
		BookDescJL.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JScrollPane DescJsp = new JScrollPane();
		
		JLabel ReasonJL = new JLabel("\u63A8\u8350\u7406\u7531");
		ReasonJL.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JScrollPane RecomJsp = new JScrollPane();
		GroupLayout gl_RecomJP = new GroupLayout(RecomJP);
		gl_RecomJP.setHorizontalGroup(
			gl_RecomJP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RecomJP.createSequentialGroup()
					.addGap(176)
					.addGroup(gl_RecomJP.createParallelGroup(Alignment.LEADING)
						.addComponent(RecomNameJL)
						.addComponent(RecomTypeJL)
						.addComponent(BookDescJL)
						.addComponent(ReasonJL))
					.addGap(44)
					.addGroup(gl_RecomJP.createParallelGroup(Alignment.LEADING)
						.addComponent(BookTypeJcb, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_RecomJP.createSequentialGroup()
							.addGroup(gl_RecomJP.createParallelGroup(Alignment.LEADING)
								.addComponent(RecomNameTxt, GroupLayout.DEFAULT_SIZE, 1643, Short.MAX_VALUE)
								.addComponent(DescJsp, GroupLayout.DEFAULT_SIZE, 1643, Short.MAX_VALUE)
								.addComponent(RecomJsp, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1643, Short.MAX_VALUE))
							.addGap(163)
							.addComponent(RecomConfirJB, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)))
					.addGap(72))
		);
		gl_RecomJP.setVerticalGroup(
			gl_RecomJP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RecomJP.createSequentialGroup()
					.addGap(167)
					.addGroup(gl_RecomJP.createParallelGroup(Alignment.BASELINE)
						.addComponent(RecomNameJL)
						.addComponent(RecomNameTxt, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addComponent(RecomConfirJB))
					.addGap(128)
					.addGroup(gl_RecomJP.createParallelGroup(Alignment.BASELINE)
						.addComponent(RecomTypeJL)
						.addComponent(BookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(156)
					.addGroup(gl_RecomJP.createParallelGroup(Alignment.BASELINE)
						.addComponent(BookDescJL)
						.addComponent(DescJsp, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_RecomJP.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_RecomJP.createSequentialGroup()
							.addGap(117)
							.addComponent(ReasonJL))
						.addGroup(gl_RecomJP.createSequentialGroup()
							.addGap(130)
							.addComponent(RecomJsp, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(331, Short.MAX_VALUE))
		);
		
		RecomReasonTxt = new JTextArea();
		RecomReasonTxt.setEditable(true);
		RecomReasonTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		RecomReasonTxt.setText("");
		RecomJsp.add(RecomReasonTxt);
		RecomJsp.setViewportView(RecomReasonTxt);
		RecomReasonTxt.setBorder(new LineBorder(new Color(127, 157, 185)));
		RecomReasonTxt.setLineWrap(true);
		BookDescTxt = new JTextArea();
		BookDescTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		DescJsp.add(BookDescTxt);
		DescJsp.setViewportView(BookDescTxt);
		BookDescTxt.setLineWrap(true);
		RecomJP.setLayout(gl_RecomJP);
		 // 设置文本域边框
		BookDescTxt.setBorder(new LineBorder(new Color(127, 157, 185)));
		
			booktypeJcb = new JComboBox();
			booktypeJcb.setFont(new Font("宋体", Font.PLAIN, 35));
			
			JLabel BookNameJL = new JLabel("\u56FE\u4E66\u540D\u79F0");
			BookNameJL.setFont(new Font("宋体", Font.PLAIN, 35));
			
			BookNameTxt = new JTextField();
			BookNameTxt.setFont(new Font("宋体", Font.PLAIN, 35));
			BookNameTxt.setColumns(10);
	/****************************查询界面***********************************************/		
			JButton SearchBookJB = new JButton("\u67E5\u8BE2");
			SearchBookJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					BookSearchActionPerformed(evt);
					}		
			});
			SearchBookJB.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/search.png")));
			SearchBookJB.setFont(new Font("宋体", Font.PLAIN, 35));
			
			JScrollPane BookSearchJsp = new JScrollPane();
			GroupLayout gl_OperationJp = new GroupLayout(OperationJp);
			gl_OperationJp.setHorizontalGroup(
				gl_OperationJp.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_OperationJp.createSequentialGroup()
						.addGap(159)
						.addGroup(gl_OperationJp.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(BookSearchJsp, Alignment.LEADING)
							.addGroup(Alignment.LEADING, gl_OperationJp.createSequentialGroup()
								.addComponent(booktypeJcb, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
								.addGap(74)
								.addComponent(BookNameJL, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(BookNameTxt, GroupLayout.PREFERRED_SIZE, 1112, GroupLayout.PREFERRED_SIZE)
								.addGap(89)
								.addComponent(SearchBookJB, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(945, Short.MAX_VALUE))
			);
			gl_OperationJp.setVerticalGroup(
				gl_OperationJp.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_OperationJp.createSequentialGroup()
						.addGap(131)
						.addGroup(gl_OperationJp.createParallelGroup(Alignment.BASELINE)
							.addComponent(booktypeJcb, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addComponent(BookNameJL, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addComponent(BookNameTxt, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addComponent(SearchBookJB, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))//修改第四个参数可以改大小
						.addGap(111)
						.addComponent(BookSearchJsp, GroupLayout.PREFERRED_SIZE, 1017, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(409, Short.MAX_VALUE))
			);
			
			BookSearchJT = new JTable();
			BookSearchJT.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					bookSearchJTMousePressed(e);				
				}
			});
			BookSearchJT.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
				"图书ID","\u56FE\u4E66\u540D\u79F0", "\u56FE\u4E66\u4F5C\u8005", "\u51FA\u7248\u793E", "\u56FE\u4E66\u63CF\u8FF0", "\u5269\u4F59\u672C\u6570"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			BookSearchJT.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			//修改表头大小和字体大小
			BookSearchJT.getTableHeader().setPreferredSize(new Dimension((int)(1*enlargement_x),(int)(40*enlargement_y)));
			BookSearchJT.getTableHeader().setFont(new Font("宋体", Font.PLAIN, (int)(35*enlargement_y)));
			BookSearchJT.setRowHeight((int)(80*enlargement_y));
			
			BookSearchJT.getColumnModel().getColumn(0).setPreferredWidth(200);
			BookSearchJT.getColumnModel().getColumn(1).setPreferredWidth(266);
			BookSearchJT.getColumnModel().getColumn(2).setPreferredWidth(217);
			BookSearchJT.getColumnModel().getColumn(3).setPreferredWidth(285);
			BookSearchJT.getColumnModel().getColumn(4).setPreferredWidth(523);
			BookSearchJT.getColumnModel().getColumn(5).setPreferredWidth(249);
			
			BookSearchJT.setFont(new Font("宋体", Font.PLAIN, 35));
			//BookSearchJsp.add(BookSearchJT);
			BookSearchJsp.setViewportView(BookSearchJT);
			OperationJp.setLayout(gl_OperationJp);
	/*****************************借阅历史界面**********************************************/		
			
			JLabel label = new JLabel("\u5386\u53F2\u501F\u9605",SwingConstants.CENTER);
			label.setEnabled(false);
			label.setFont(new Font("幼圆", Font.BOLD, 35));
			
			JButton HisBorSearchButton = new JButton("\u67E5\u8BE2");
			HisBorSearchButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					hisBorrowactionPerformed(e);
				}
			});
			HisBorSearchButton.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/search.png")));
			HisBorSearchButton.setFont(new Font("幼圆", Font.BOLD, 35));
			
			JScrollPane HisBorrowJsp = new JScrollPane();			
			GroupLayout gl_HisBorrowJP = new GroupLayout(HisBorrowJP);
			gl_HisBorrowJP.setHorizontalGroup(
				gl_HisBorrowJP.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_HisBorrowJP.createSequentialGroup()
						.addGroup(gl_HisBorrowJP.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_HisBorrowJP.createSequentialGroup()
								.addGap(254)
								.addGroup(gl_HisBorrowJP.createParallelGroup(Alignment.LEADING)
									.addComponent(HisBorrowJsp, GroupLayout.PREFERRED_SIZE, 2024, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_HisBorrowJP.createSequentialGroup()
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
										.addGap(261)
										.addComponent(HisBorSearchButton, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_HisBorrowJP.createSequentialGroup()
								.addGap(85)
								.addComponent(PieChartJP, GroupLayout.PREFERRED_SIZE, 847, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(BrokenLineJP, GroupLayout.PREFERRED_SIZE, 1415, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(315, Short.MAX_VALUE))
			);
			gl_HisBorrowJP.setVerticalGroup(
				gl_HisBorrowJP.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_HisBorrowJP.createSequentialGroup()
						.addGap(204)
						.addGroup(gl_HisBorrowJP.createParallelGroup(Alignment.BASELINE)
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
							.addComponent(HisBorSearchButton))
						.addGap(115)
						.addComponent(HisBorrowJsp, GroupLayout.PREFERRED_SIZE, 605, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
						.addGroup(gl_HisBorrowJP.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(BrokenLineJP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(PieChartJP, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE))
						.addContainerGap())
			);
			//布局
			FlowLayout  pieflow=new FlowLayout(FlowLayout.CENTER );
			PieChartJP.setLayout(pieflow);
			FlowLayout  Lineflow=new FlowLayout(FlowLayout.CENTER );
			BrokenLineJP.setLayout(Lineflow);
			//获得当前窗口大小
			Dimension PieDi=new Dimension(847,678);//这个值要手动调整
			Dimension Linetrend=new Dimension(1415,678);//这个值要手动调整
			//BrokenLineJP.getSize(Linetrend);
			//PieChartJP.getSize(PieDi);//setVisuale之前得到是0！
			//获得数据集			
			HisBorrowPieChart(TypeBuffer,TypeNum);
			HisBorrowBrokenLine(BookMonthly);
			//画图
			PieChartJP.add(new PieChart(PieDi,TypeBuffer,TypeNum).getChartPanel());           //添加饼状图  
			BrokenLineJP.add(new TimeSeriesChart(Linetrend,BookMonthly,CurrentYear).getChartPanel());    //添加折线图  
			HisBorrowJtable = new JTable();
			HisBorrowJtable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"\u56FE\u4E66\u540D\u79F0", "\u56FE\u4E66\u4F5C\u8005", "\u51FA\u7248\u793E", "\u501F\u9605\u65F6\u95F4", "\u5F52\u8FD8\u65F6\u95F4"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, true, false, false
				};
				@Override
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			//修改表头大小和字体大小
			HisBorrowJtable.getTableHeader().setPreferredSize(new Dimension((int)(1*enlargement_x),(int)(40*enlargement_y)));
			HisBorrowJtable.getTableHeader().setFont(new Font("宋体", Font.PLAIN, (int)(35*enlargement_y)));
			HisBorrowJtable.setRowHeight((int)(80*enlargement_y));
			HisBorrowJtable.getColumnModel().getColumn(0).setPreferredWidth(324);
			HisBorrowJtable.getColumnModel().getColumn(1).setPreferredWidth(182);
			HisBorrowJtable.getColumnModel().getColumn(2).setPreferredWidth(237);
			HisBorrowJtable.getColumnModel().getColumn(3).setPreferredWidth(272);
			HisBorrowJtable.getColumnModel().getColumn(4).setPreferredWidth(227);
			HisBorrowJtable.setFont(new Font("宋体", Font.PLAIN, 36));
			//HisBorrowJP.add(HisBorrowJtable);
			HisBorrowJsp.setViewportView(HisBorrowJtable);
			HisBorrowJP.setLayout(gl_HisBorrowJP);			
			//选择界面
		    FreshShow();
		    
		  /*****初始化事件***/
	    this.addComponentListener(new ComponentAdapter()//初始化分割比
	    {
	        @Override
			public void componentResized(ComponentEvent e) {
	        	splitPane.setDividerLocation(split_scale);
	        }
	    });	
	    //设置JFrame最大化,要放在setVisible之后才能刷新，否则只执行一次
		  this.setExtendedState(Frame.MAXIMIZED_BOTH); 
		  fillBookType();//初始化下拉框
		  setVisible(true);
		  splitPane.setDividerLocation(0.25);//设定分割面板的左右比例(这时候就生效了，如果放在setVisible(true)这据之前就不会有效果。
		 splitPane.setEnabled(false);            //设置分隔条禁止拖动  	    	  

	}
	
//*************************************绘制用户界面信息*******************************************/	
	public void FreshShow()
	{				
//		booktypeJcb = new JComboBox();
//		booktypeJcb.setFont(new Font("宋体", Font.PLAIN, 35));							    
//********************选择右边显示的界面*******************************/	    
	    switch(ShowSelect)
		{
	       case 1:
	    	   splitPane.add(OperationJp, JSplitPane.RIGHT);
	    	   CredentialInfoJP.setVisible(false);
	    	   PreBorrowJP.setVisible(false);
	    	   RecomJP.setVisible(false);
	    	   HisBorrowJP.setVisible(false);
	    	   OperationJp.setVisible(true);	    	  
	           break;
	       case 2:	    	   
	    	   splitPane.setRightComponent(PreBorrowJP);
	    	   CredentialInfoJP.setVisible(false);
	    	   OperationJp.setVisible(false);
	    	   RecomJP.setVisible(false);
	    	   HisBorrowJP.setVisible(false);
	    	   PreBorrowJP.setVisible(true);
	    	   break;
	       case 3:
	    	   splitPane.setRightComponent(HisBorrowJP);
	    	   CredentialInfoJP.setVisible(false);
	    	   OperationJp.setVisible(false);
	    	   RecomJP.setVisible(false);
	    	   PreBorrowJP.setVisible(false);
	    	   HisBorrowJP.setVisible(true);
	    	   break;
	       case 4:
	    	   splitPane.setRightComponent(CredentialInfoJP);	    	
	    	   OperationJp.setVisible(false);
	    	   RecomJP.setVisible(false);
	    	   PreBorrowJP.setVisible(false);
	    	   HisBorrowJP.setVisible(false);
	    	   CredentialInfoJP.setVisible(true);
	    	   break;
	       default:splitPane.setRightComponent(RecomJP);
	               CredentialInfoJP.setVisible(false);
	               PreBorrowJP.setVisible(false);
	               OperationJp.setVisible(false);
	               HisBorrowJP.setVisible(false);
	               RecomJP.setVisible(true);
	    	   break;
	    }
	 

	}
	
	/**
	 * 重置表单
	 */
	private void ResetValue()
	{

		BookSearchJT.clearSelection();
		BookID=-1;
		bookTotalTxt.setText(Integer.toString(TotalBookCanBorrow));
		switch(ShowSelect)
		{
		case 1:
			break;
		case 2://当前借阅信息
			if(this.PreBorrowJtable.getRowCount()>0)
				{
				  DefaultTableModel tableModel = (DefaultTableModel)PreBorrowJtable.getModel();
				  tableModel.setRowCount( 0 );
				}
//			HisBorrow hisBorrow=new HisBorrow(PresentUser);	
//			this.fillHisBorrowTable(hisBorrow);
//			PreBorrowJtable.updateUI();
//			PreBorrowJsp.repaint();
//			  int num = PreBorrowJtable.getModel().getRowCount(); //得到此数据表中的行数      
//			  for (int i = 0; i < num; i++)     //利用循环依次清空所有行      
//				  PreBorrowJtable.getModel().removeRow(0);
			  break;
		case 3://历史借阅信息
			if(this.HisBorrowJtable.getRowCount()>0)
			{
			  DefaultTableModel tableModel = (DefaultTableModel)HisBorrowJtable.getModel();
			  tableModel.setRowCount( 0 );
			}			
			break;
		case 4://读者信息界面
			NewNameButton.setVisible(false);
			NewNameJL.setVisible(false);
			NewNameTxt.setVisible(false);
			NewNameTxt.setText("");
			break;
		default://推荐窗口
		this.RecomNameTxt.setText("");
		this.BookDescTxt.setText("");
		this.RecomReasonTxt .setText("");
		if(this.BookTypeJcb.getItemCount()>0)
		{
			this.BookTypeJcb.setSelectedIndex(0);
		}
		break;
		}
	}
	
	/**
	 * 初始化图书类别下拉框
	 */
	private void fillBookType(){
		Connection con=null;
		BookType bookType=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=bookTypeDao.list(con, new BookType());
			//添加第一项ID=-1
			bookType=new BookType();
			bookType.setBookTypeName("请选择...");
			bookType.setId(-1);//请选择的ID为-1
			this.booktypeJcb.addItem(bookType);
			BookTypeJcb.addItem(bookType);
			while(rs.next())
			{
				bookType=new BookType();
				bookType.setId(rs.getInt("id"));
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				this.booktypeJcb.addItem(bookType);//添加项
				BookTypeJcb.addItem(bookType);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
	
	/**
	 * 图书检索
	 * @param evt
	 */
private void BookSearchActionPerformed(ActionEvent evt)
{
	String bookName=this.BookNameTxt.getText();
	//String author=this.S_AuthorTxt.getText();
	BookType bookType=(BookType) this.booktypeJcb.getSelectedItem();//返回object类,强转
	int bookTypeId=bookType.getId();	
	Book book=new Book(bookName,bookTypeId);
	this.fillTable(book);	
}
/**
 * 当前借阅信息
 * @param e
 */
private void PreBorrowSearchPerformed(ActionEvent e) 
{
	Borrow borrow=new Borrow(PresentUser);
	this.fillPreBorrowTable(borrow);	
}
//************************初始化表格数据**************************************/
/**
 * 初始化表格数据
 * @param book
 */
private void fillTable(Book book)
{
	DefaultTableModel dtm=(DefaultTableModel) BookSearchJT.getModel();//获取模型
	dtm.setRowCount(0); // 设置成0行
	Connection con=null;
	try{
		con=dbUtil.getCon();
		//OperationJp
		ResultSet rs=bookDao.list(con, book);//获取所有记录
		while(rs.next()){
			Vector v=new Vector();//按顺序写
			v.add(rs.getString("id"));
			v.add(rs.getString("bookName"));
			v.add(rs.getString("author"));
			v.add(rs.getString("publisher"));
			v.add(rs.getString("bookDesc"));
			v.add(rs.getString("number"));			
			dtm.addRow(v);//加一行
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try {
			dbUtil.closeCon(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
/**
 * 初始化当前借阅表格
 * @param borrow
 */
private void fillPreBorrowTable(Borrow borrow)
{
	DefaultTableModel dtm=(DefaultTableModel) PreBorrowJtable.getModel();//获取表格模型
	dtm.setRowCount(0); // 设置成0行
	Connection con=null;
	try{
		con=dbUtil.getCon();
		int i=0;//table中从0开始
		//PreBorrowJP
		ResultSet prebo=borrowDao.list(con, borrow);//获取所有记录,由用户ID查找
//		if(prebo.next()==false)
//		{
//			Dialogutil attention=new Dialogutil(null,"Attention!","您目前还没有借任何书哦！");
//			return;
//		}
		while(prebo.next()){
			IDinPreBorJT[i++]=prebo.getInt("ID");//存放ID
			Vector v=new Vector();//按顺序写
			v.add(prebo.getString("UserID"));
			v.add(prebo.getString("BookName"));
			v.add(prebo.getString("dueTime"));	
			dtm.addRow(v);//加一行
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try {
			dbUtil.closeCon(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}

/**
 * 初始化历史借阅表格
 * @param hisborrow
 */
private void fillHisBorrowTable(HisBorrow hisborrow)
{
	DefaultTableModel dtm=(DefaultTableModel) HisBorrowJtable.getModel();//获取表格模型
	dtm.setRowCount(0); // 设置成0行
	Connection con=null;
	try{
		con=dbUtil.getCon();
		int i=0;//table中从0开始
		if(StringUtil.isEmpty(PresentUser))
		{
			Dialogutil attention=new Dialogutil(null,"Attention!","用户信息获取失败！");
			return;
		}
		else			
		{
			User user=new User(PresentUser);
			ResultSet Hisbo=borrowDao.hisBorrowSearch(con,user);//获取所有记录,由用户ID查找
			while( Hisbo.next()){
				//IDinPreBorJT[i++]=prebo.getInt("ID");//存放ID
				Vector v=new Vector();//按顺序写
				v.add(Hisbo.getString("b.bookName"));
			    v.add(Hisbo.getString("b.author"));
				v.add(Hisbo.getString("b.publisher"));	
				v.add(Hisbo.getDate("bt.borTime"));	
				v.add(Hisbo.getDate("bt.returnTime"));	
				dtm.addRow(v);//加一行
			}
		}		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try {
			dbUtil.closeCon(con);
		} catch (Exception e)
		{
			Dialogutil attention=new Dialogutil(null,"Attention!","查询记录失败！");
			e.printStackTrace();
		}
	}	
}
//*********************************************************************/
/**
 * 借书表格行点击事件处理
 * @param e
 */
private void bookSearchJTMousePressed(MouseEvent evt) 
{
	int row=BookSearchJT.getSelectedRow();//选中行
	IsClickJT=true;
	BookID=Integer.parseInt((String)BookSearchJT.getValueAt(row, 0));
	BookSurplus=Integer.parseInt((String)BookSearchJT.getValueAt(row, 5));
	//BookTypeDescTxt.setText((String)BookSearchJT.getValueAt(row, 2));
}
/**
 * 还书行表格点击事件
 * @param ret
 */
private void bookReturnClick(MouseEvent ret) 
{
	int row=PreBorrowJtable.getSelectedRow();//选中行
	IsClickPreBorrowJT=true;
	BorrowID=IDinPreBorJT[row];	
}
/**
 * 图书借阅事件
 * @param evt
 */
private void borrowActionPerformed(ActionEvent evt) 
{
	if(!IsClickJT||BookID==-1)
	{
		Dialogutil attention=new Dialogutil(null,"Attention!","请选择所要借的书");
		return;
	}
	else
	{
		Connection con=null;
		try {
			con=dbUtil.getCon();
			//找到当前书和用户
			User user=new User(PresentUser);
			Book book=new Book(BookID);
			User currentUser=userdao.SearchUser(con, user); 
			Book currentBook=BookDao.BookSearch(con, book);
			//获取系统时间
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			Borrow currentborrow=new Borrow(currentUser.getId(),currentBook.getId(),currentDate);
			if(currentUser!=null)
			{
				int BorrowNumRem=currentUser.getBorrowNumRem();
				if(BorrowNumRem<=0)//||currentBook.getBookNum()<=0
					{
					  Dialogutil BorrowError=new Dialogutil(null,"Error!","您所借的书已达上限！");
					}
				else if(currentUser.getBalance()<-10)
				{
					Dialogutil BorrowError=new Dialogutil(null,"Error!","您欠款太多！");
				}
				else if(currentBook.getBookNum()<=0)
				{
					Dialogutil BorrowError=new Dialogutil(null,"Error!","该书已经被借完，请等待他人归还！");
				}
				else
					{
					int borrowNum=UserDao.borrow(con, currentUser);//修改读者借书上限
					int bookNum=bookDao.borrow(con, currentBook);//修改库存数量
					borrowDao.add(con, currentborrow);
					if(borrowNum!=-1&&bookNum!=-1)
					{
						showMessageFrame note=new showMessageFrame(null,"借书成功！",showMessageFrame.NORMAL);
						//重新统计,刷新
						HisBorrowPieChart(TypeBuffer,TypeNum);
						HisBorrowBrokenLine(BookMonthly);
						Dimension PieDi=new Dimension();//这个值要手动调整
						Dimension Linetrend=new Dimension();//这个值要手动调整
						PieChartJP.getSize(PieDi);
						BrokenLineJP.getSize(PieDi);
						PieChartJP.removeAll();
						BrokenLineJP.removeAll();
						PieChartJP.add(new PieChart(PieDi,TypeBuffer,TypeNum).getChartPanel());  //添加饼状图  
						BrokenLineJP.add(new TimeSeriesChart(Linetrend,BookMonthly,CurrentYear).getChartPanel());    //添加折线图  
						//PieChartJP.repaint();
						BookID=-1;
						BookSearchJT.clearSelection();  //取消选择状态
					}
					else
					{
						showMessageFrame note=new showMessageFrame(null,"借书失败！",showMessageFrame.NOTE);
					}
					}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			showMessageFrame note=new showMessageFrame(null,"借书失败！",showMessageFrame.NOTE);
			e.printStackTrace();
		}
	}
	IsClickJT=false;
}	

/**
 * 图书归还事件
 * @param e
 */
protected void returnactionPerformed(ActionEvent e) 
{
	if(!IsClickPreBorrowJT)
	{
		Dialogutil attention=new Dialogutil(null,"Attention!","请选择所要还的书");
	}
	else
	{
		Connection con=null;
		try {
			con=dbUtil.getCon();
			//获取当前还书信息
			Borrow borrow=new Borrow(PresentUser,BorrowID);			
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			
			Borrow preborrow=borrowDao.Search_ID(con, borrow);
			preborrow.setReturnTime(currentDate);
			preborrow.setIsReturn((byte)1);
//			ResultSet prebo=borrowDao.list(con, preborrow);	
//			if(prebo.next())//一定要有这一句，光标下移
//			{
//				preborrow.setBookName(prebo.getString("BookName"));
//				preborrow.setBookID(prebo.getInt("bookId"));
//				preborrow.setBorTime(prebo.getDate("borTime"));
//				preborrow.setDueTime(prebo.getDate("dueTime"));
//			}
			//找到当前书和用户
			User user=new User(PresentUser);
			Book book=new Book(preborrow.getBookID());
			User currentUser=userdao.SearchUser(con, user); 
			Book currentBook=BookDao.BookSearch(con, book);
			//获取系统时间			
			if(currentUser!=null)
			{
				int i1=borrowDao.updateborrow(con, preborrow);
				int i2=borrowDao.updateuser(con, currentUser, preborrow);
				int i3=borrowDao.updatebook(con, currentBook);
				if(i1!=-1&&i2!=-1&&i3!=-1)
				{
					showMessageFrame note=new showMessageFrame(null,"还书成功！",showMessageFrame.NORMAL);
				}
				else
				{
					showMessageFrame note=new showMessageFrame(null,"还书失败！",showMessageFrame.NOTE);
				}
			}
		} catch (Exception e1)
		{
			showMessageFrame note=new showMessageFrame(null,"还书失败！",showMessageFrame.NOTE);
			e1.printStackTrace();
		}
	}
	ResetValue();
	IsClickPreBorrowJT=false;
}
/**
 * 推荐事件
 * @param rec
 */
private void RecomactionPerformed(ActionEvent rec) {
	// TODO Auto-generated method stub
	String bookTypeDesc=BookDescTxt.getText();
	String bookRecomReason=RecomNameTxt.getText();
	String bookRecomName=RecomNameTxt.getText();
	BookType BookType=(BookType) this.BookTypeJcb.getSelectedItem();//选择书的种类
	int bookTypeId=BookType.getId();
	if(StringUtil.isEmpty(bookRecomName))
	{
		showMessageFrame note=new showMessageFrame(null,"请输入所要推荐的书名！",showMessageFrame.NOTE);
		return;
	}
	else if(StringUtil.isEmpty(bookRecomReason))
	{
		showMessageFrame note=new showMessageFrame(null,"请输入所推荐的理由！",showMessageFrame.NOTE);
		return;
	}
	else if(bookTypeId==-1)
	{
		showMessageFrame note=new showMessageFrame(null,"请选择图书种类",showMessageFrame.NOTE);
		return;
	}
	else
	{
		Connection con=null;
		try {
			    con=dbUtil.getCon();
			  //User user=new User(PresentUser);
			    if(!StringUtil.isEmpty(PresentUser))
				{
			    	 BookRecommend bookRecom=new BookRecommend(PresentUser,bookRecomName,bookTypeDesc,bookRecomReason,bookTypeId);
			         int n=BookRecomDao.add(con, bookRecom);	
					if(n==1){
						//JOptionPane.showMessageDialog(null, "图书类别添加成功！");
						showMessageFrame SucNote=new showMessageFrame
								(null,"图书推荐成功，等待管理员审核！",showMessageFrame.NORMAL);
						ResetValue();
					}
					else
					{
						showMessageFrame FailNote=new showMessageFrame(null,"图书推荐失败！",showMessageFrame.NOTE);
					}
				}
			 else
			    {
			    	showMessageFrame FailNote=new showMessageFrame(null,"图书推荐失败！",showMessageFrame.NOTE);
			    	return;
			    }
		 } 
		catch (Exception e) {
			// TODO Auto-generated catch block
			showMessageFrame FailNote=new showMessageFrame(null,"图书推荐失败！",showMessageFrame.NOTE);
			e.printStackTrace();
		}
		
	}
}

/**
 * 借书历史查询事件
 * @param e
 */
private void hisBorrowactionPerformed(ActionEvent e) 
{
	// TODO Auto-generated method stub
	HisBorrow hisBorrow=new HisBorrow(PresentUser);	
	this.fillHisBorrowTable(hisBorrow);
}

/**
 * 借阅分布统计
 */
private void HisBorrowPieChart(StringBuffer[] TypeBuffer,int[] number)
{
	Connection con=null;
	try {
		con=dbUtil.getCon();
		Arrays.fill(number,0);
		for(int i=0;i<TypeBuffer.length;++i)
		{
			 TypeBuffer[i]=new StringBuffer();//分配空间！
		}
		if(StringUtil.isEmpty(PresentUser))
		{
			Dialogutil attention=new Dialogutil(null,"Attention!","用户信息获取失败！");
			return;
		}
		else			
		{
			//找到当前书和用户
			User user=new User(PresentUser);
			ResultSet Hisbo=borrowDao.HisBorrowDistri(con,user);//获取所有记录,由用户ID查找
			while( Hisbo.next()){
				//IDinPreBorJT[i++]=prebo.getInt("ID");//存放ID
				int index=Hisbo.getInt("bt.id");
				if(StringUtil.isEmpty(TypeBuffer[index].toString()))
				{
					TypeBuffer[index].append(Hisbo.getString("bt.bookTypeName"));
				}
				number[index]++;				
			}
		}		
	} catch (Exception e) {
		Dialogutil attention=new Dialogutil(null,"Attention!","用户信息获取失败！");
		e.printStackTrace();
	}	
}

/**
 * 借书趋势统计
 * @param BookMonthly
 * @param CurrentYear
 */
private void HisBorrowBrokenLine(int []BookMonthly)
{
	Connection con=null;
	Arrays.fill(BookMonthly,0);
	try {
		con=dbUtil.getCon();
		if(StringUtil.isEmpty(PresentUser))
		{
			Dialogutil attention=new Dialogutil(null,"Attention!","用户信息获取失败！");
			return;
		}
		else			
		{
			//找到当前书和用户
			User user=new User(PresentUser);
			//获取当前时间
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			DateInt curdate=new DateInt();
			DateUtil.getdate(currentDate, curdate);
			this.CurrentYear=curdate.getYear();
			ResultSet Hisbo=borrowDao.HisBorrowTrend(con,user);//获取所有记录,由用户ID查找
			while( Hisbo.next())
			{
				DateInt date=new DateInt();				
				DateUtil.getdate(Hisbo.getDate("borTime"), date);				
				if(curdate.getYear()==date.getYear())
				{
					BookMonthly[date.getMonth()]++;
				}
				else
				{
					continue;
				}
			}
		}		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

/**
 * 修改用户名界面
 * @param edit
 */
private void UserNameEditactionPerformed(ActionEvent edit)
{
	IsEditName=!IsEditName;
	if(IsEditName)
	{
		NewNameButton.setVisible(true);
		NewNameJL.setVisible(true);
		NewNameTxt.setVisible(true);
	}
	else
	{
		NewNameButton.setVisible(false);
		NewNameJL.setVisible(false);
		NewNameTxt.setVisible(false);
	}
}

/**
 * 获取当前信息
 */
//private void GetPresentUserInfo()
//{
//	//presentUser
//	Connection con=null;
//	Arrays.fill(BookMonthly,0);
//	try {
//		con=dbUtil.getCon();
//		if(StringUtil.isEmpty(PresentUser))
//		{
//			Dialogutil attention=new Dialogutil(null,"Attention!","用户信息获取失败！");
//			return;
//		}
//		else			
//		{
//			//找到当前书和用户
//			presentUser.setId(PresentUser);
//			StringBuffer sb=new StringBuffer("select * from t_user");
//			//两张表关联查询，有bookTypeID才能查询
//			if(StringUtil.isNotEmpty(presentUser.getId()))
//			{
//				sb.append(" and id=?");
//				PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
//				pstmt.setString(1, presentUser.getId());
//				ResultSet rs=pstmt.executeQuery();//执行
//				if(rs.next())
//				{
//					presentUser.setUserName(rs.getString("userName"));
//					presentUser.setBalance(rs.getFloat("balance"));
//					presentUser.setBorrowNumRem(rs.getInt("borrowNUmRem"));
//				}
//			}
//			else
//			{
//				Dialogutil attention=new Dialogutil(null,"Attention!","用户信息获取失败！");
//				return;
//			}					
//		}		
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}
/**
 * 缴纳罚款
 * @param pay
 */
private void payactionPerformed(ActionEvent pay) {
	// TODO Auto-generated method stub
	if(IsOpened)
	{
		Dialogutil attention=new Dialogutil(null,"Attention!","已经打开该窗口！");
		return;
	}
	else
	{
		if(StringUtil.isEmpty(PresentUser))
		{
			Dialogutil attention=new Dialogutil(null,"Attention!","用户信息获取失败！");
			return;
		}
		else
		{
			 this.payfrm=new PayFrm(PresentUser,this);
			 payfrm.setVisible(true);			 
//			 payfrm.addWindowListener(new WindowAdapter(){
//				 public void windowClosing(WindowEvent e){
//					 IsOpened=false;//关闭后才能置为false
//					 }
//					 });
			IsOpened=true;
		}
	}
}
/**
 * 修改名称
 * @param newName
 */
private void NewNameactionPerformed(ActionEvent newName) 
{
	Connection con=null;
	try {
		con=dbUtil.getCon();
		String newReaderName=NewNameTxt.getText();
		if(StringUtil.isEmpty(newReaderName))
		{
			Dialogutil attention=new Dialogutil(null,"Attention!","请输入新用户名！");
			return;
		}
		else if(newReaderName.length()>15)
		{
			Dialogutil attention=new Dialogutil(null,"Attention!","用户名在15字以内！");
			return;
		}
		else
		{
			presentUser.setUserName(newReaderName);
			int num=UserDao.Edit(con, presentUser);
			if(num==1)
			{
				showMessageFrame SucNote=new showMessageFrame(null,"修改用户名成功！",showMessageFrame.NORMAL);
				ReaderName.setText(newReaderName);
				ResetValue();
			}
			else
			{
				showMessageFrame FailNote=new showMessageFrame(null,"修改用户名失败！",showMessageFrame.NOTE);
		    	return;
			}
		}
	} catch (Exception e) 
	{
		showMessageFrame FailNote=new showMessageFrame(null,"修改用户名失败！",showMessageFrame.NOTE);
		e.printStackTrace();
	}	
}			
}
	
