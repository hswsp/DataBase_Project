package manager.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import manager.dao.BookDao;
import manager.dao.BookTypeDao;
import manager.dao.BorrowDao;
import manager.dao.UserDao;
import manager.entity.Book;
import manager.entity.BookType;
import manager.entity.Borrow;
import manager.entity.User;
import manager.util.DbUtil;
import manager.util.Dialogutil;
import manager.util.showMessageFrame;

public class userMainFrm extends JFrame {

	private String PresentUser;
	
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
    //容器组件
    private JPanel contentPane;
    private JSplitPane splitPane = new JSplitPane();
    JPanel TutorJp = new JPanel();
    JPanel OperationJp = new JPanel();
    JPanel ReturnJP = new JPanel();
    //控件
    private JComboBox booktypeJcb;  	
//   	private final JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u540D\u79F0");
//   	private final JTextField textField = new JTextField();
//   	private final JButton button = new JButton("\u67E5\u8BE2");
//   	private final JLabel label_1 = new JLabel("\u56FE\u4E66\u67E5\u8BE2\u4E0E\u501F\u9605");
   	private JTextField BookNameTxt;
   	private JTable BookSearchJT;
   	//根据左边按钮选择显示哪一个界面
   	private int ShowSelect=1;
   	//*******************借书事件变量**********************/
   	private boolean IsClickJT=false;
   	private int BookID;
   	private int BookSurplus;
   	
   //JDBC接口	
    private DbUtil  dbUtil=new DbUtil();
    private UserDao userdao=new UserDao();
    private BookTypeDao bookTypeDao=new BookTypeDao();
   	private BookDao bookDao=new BookDao();
   	private BorrowDao borrowDao=new BorrowDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userMainFrm frame = new userMainFrm();
//					frame.setVisible(true);
//					frame.splitPane.setDividerLocation(split_scale);//设定分割面板的左右比例(这时候就生效了，如果放在setVisible(true)这据之前就不会有效果。
//					frame.splitPane.setEnabled(false);            //设置分隔条禁止拖动  					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public userMainFrm() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create the frame.
	 */
	public userMainFrm(String User) 
	{
		PresentUser=User;
//		button.setIcon(new ImageIcon(userMainFrm.class.getResource("/manager/image/search.png")));
//		button.setFont(new Font("宋体", Font.PLAIN, 35));
//		textField.setFont(new Font("宋体", Font.PLAIN, 35));
//		textField.setColumns(10);
//		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 35));
		setTitle("\u7528\u6237\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置JFrame最大化,要放在setVisible之后才能刷新，否则只执行一次
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//setResizable(false);
	//	setBounds(0, 0, screenWidth,screenHeight);
		setSize(new Dimension(screenSize.width,screenSize.height));
		
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 1120);
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
		
		//GroupLayout gl_TutorJp = new GroupLayout(TutorJp);
		gl_TutorJp.setHorizontalGroup(
				gl_TutorJp.createParallelGroup(Alignment.LEADING)
					.addComponent(SearchJIF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
					.addComponent(ID_JIF, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
					.addComponent(RecomJIF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
					.addComponent(ExitJPL, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
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
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))//Short.MAX_VALUE
					   
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
		JLabel IDJLB = new JLabel("",JLabel.CENTER);		
		IDJLB.setFont(new Font("宋体", Font.PLAIN, 35));
		JLabel RecomJLb = new JLabel("",JLabel.CENTER);
		RecomJLb.setBounds(254, 41, 256, 73);
		RecomJLb.setFont(new Font("宋体", Font.PLAIN, 35));
		RecommendJP.add(RecomJLb);
		
		JLabel Daily_Op = new JLabel("",JLabel.CENTER);
		Daily_Op.setBounds(247, 43, 264, 91);
		Daily_Op.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton CertificateJB = new JButton("\u8BC1\u4EF6\u4FE1\u606F");
		CertificateJB.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton PreBorrowInfo = new JButton("\u5F53\u524D\u501F\u9605");
		PreBorrowInfo.setFont(new Font("宋体", Font.PLAIN, 35));
		PreBorrowInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton HistoryBorrow = new JButton("\u501F\u9605\u5386\u53F2");
		HistoryBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
							.addComponent(IDJLB, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(255)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(HistoryBorrow, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(PreBorrowInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(CertificateJB, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(224, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(IDJLB, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(CertificateJB, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PreBorrowInfo, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(HistoryBorrow, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		ID_JIF.getContentPane().setLayout(groupLayout_1);
		
		//图片准备
		int width = 300,height = 100; //这是图片和JLable的宽度和高度   
		ImageIcon icon = new ImageIcon("src/res/dayly_op.png");  
		ImageIcon ID_icon = new ImageIcon("src/res/ID_Info.png");  
		ImageIcon Recom_icon = new ImageIcon("src/res/Recommend.png");
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
		SearchButton.setBounds(273, 149, 222, 59);
		SearchButton.setFont(new Font("宋体", Font.PLAIN, 35));
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ShowSelect=1;				
				FreshShow();
				}	
		});
		
		JButton BorrowButton = new JButton("\u56FE\u4E66\u501F\u9605");
		BorrowButton.setBounds(273, 221, 222, 64);
		BorrowButton.setFont(new Font("宋体", Font.PLAIN, 35));
		dayly_panel.setLayout(null);		
		BorrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				borrowActionPerformed(evt);
			}

			
		});
		
		JButton ReturnButurn = new JButton("\u56FE\u4E66\u5F52\u8FD8");
		ReturnButurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowSelect=2;				
				FreshShow();					
			}
		});
		ReturnButurn.setBounds(273, 295, 222, 59);
		ReturnButurn.setFont(new Font("宋体", Font.PLAIN, 35));
		dayly_panel.add(ReturnButurn);
		dayly_panel.add(Daily_Op);
		dayly_panel.add(SearchButton);
		dayly_panel.add(BorrowButton);
		
		JButton RecomJB = new JButton("\u56FE\u4E66\u63A8\u8350");
		RecomJB.setFont(new Font("宋体", Font.PLAIN, 35));
		RecomJB.setBounds(278, 130, 207, 59);
		RecommendJP.add(RecomJB);
		SearchJIF.getContentPane().setLayout(groupLayout);
		
		//退出按钮
		JButton exitButton = new JButton("");
		int exit_width=203,exit_height=65;
		exitButton.setFont(new Font("宋体", Font.PLAIN, 35));
		ImageIcon exiticon = new ImageIcon("src/res/exit.PNG");
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
		// splitPane.setRightComponent(ReturnJP);	
		splitPane.add(OperationJp, JSplitPane.RIGHT);
		
		
		
			booktypeJcb = new JComboBox();
			booktypeJcb.setFont(new Font("宋体", Font.PLAIN, 35));
			
			JLabel BookNameJL = new JLabel("\u56FE\u4E66\u540D\u79F0");
			BookNameJL.setFont(new Font("宋体", Font.PLAIN, 35));
			
			BookNameTxt = new JTextField();
			BookNameTxt.setFont(new Font("宋体", Font.PLAIN, 35));
			BookNameTxt.setColumns(10);
			
			JButton SearchBookJB = new JButton("\u67E5\u8BE2");
			SearchBookJB.addActionListener(new ActionListener() {
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
							.addComponent(booktypeJcb, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addComponent(BookNameJL, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addComponent(BookNameTxt, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addComponent(SearchBookJB, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
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
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			
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
			BookSearchJsp.setViewportView(BookSearchJT);
			OperationJp.setLayout(gl_OperationJp);
		    FreshShow();
		    
		  /*****初始化事件***/
	    this.addComponentListener(new ComponentAdapter()//初始化分割比
	    {
	        public void componentResized(ComponentEvent e) {
	        	splitPane.setDividerLocation(split_scale);
	        }
	    });
//		setVisible(true);
//		splitPane.setDividerLocation(split_scale);//设定分割面板的左右比例(这时候就生效了，如果放在setVisible(true)这据之前就不会有效果。
//		splitPane.setEnabled(false);            //设置分隔条禁止拖动  	
		fillBookType();//初始化下拉框
	}
	
	public void FreshShow()
	{				
//		booktypeJcb = new JComboBox();
//		booktypeJcb.setFont(new Font("宋体", Font.PLAIN, 35));							    
//********************选择右边显示的界面*******************************/	    
	    switch(ShowSelect)
		{
	       case 1:
	    	   splitPane.add(OperationJp, JSplitPane.RIGHT);
	    	   ReturnJP.setVisible(false);
	    	   OperationJp.setVisible(true);	    	  
	           break;
	       case 2:	    	   
	    	   splitPane.setRightComponent(ReturnJP);
	    	   OperationJp.setVisible(false);
	    	   ReturnJP.setVisible(true);
	    	   break;
	       default:
	    	   break;
	    }	 
		setVisible(true);
		splitPane.setDividerLocation(0.25);//设定分割面板的左右比例(这时候就生效了，如果放在setVisible(true)这据之前就不会有效果。
		splitPane.setEnabled(false);            //设置分隔条禁止拖动  	    	  
//		fillBookType();//初始化下拉框
	}
	
	/**
	 * 重置表单
	 */
	private void ResetValue()
	{
		booktypeJcb=null;
		BookNameTxt=null;
		BookSearchJT=null;
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
			while(rs.next())
			{
				bookType=new BookType();
				bookType.setId(rs.getInt("id"));
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				this.booktypeJcb.addItem(bookType);//添加项
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
 * 表格行点击事件处理
 * @param e
 */
private void bookSearchJTMousePressed(MouseEvent evt) {
	int row=BookSearchJT.getSelectedRow();//选中行
	IsClickJT=true;
	BookID=Integer.parseInt((String)BookSearchJT.getValueAt(row, 0));
	BookSurplus=Integer.parseInt((String)BookSearchJT.getValueAt(row, 5));
	//BookTypeDescTxt.setText((String)BookSearchJT.getValueAt(row, 2));
}

/**
 * 图书借阅事件
 * @param evt
 */
private void borrowActionPerformed(ActionEvent evt) 
{
	if(!IsClickJT)
	{
		Dialogutil attention=new Dialogutil(null,"Attention!","请选择所要借的书");
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
				if(BorrowNumRem<=0||currentBook.getBookNum()<=0)
					{
					  Dialogutil BorrowError=new Dialogutil(null,"Error!","您所借的书已达上限！");
					}
				else
					{
					int borrowNum=UserDao.borrow(con, currentUser);//修改读者借书上限
					int bookNum=bookDao.borrow(con, currentBook);//修改库存数量
					borrowDao.add(con, currentborrow);
					if(borrowNum!=-1&&bookNum!=-1)
					{
						showMessageFrame note=new showMessageFrame(null,"借书成功！",showMessageFrame.NORMAL);
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

}
	
