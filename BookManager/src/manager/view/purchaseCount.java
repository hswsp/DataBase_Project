package manager.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import manager.dao.BookDao;
import manager.entity.DateInt;
import manager.entity.Manager;
import manager.util.DateUtil;
import manager.util.DbUtil;
import manager.util.TimeSeriesChart;

public class purchaseCount extends JFrame {

	private JPanel contentPane;
    private Manager curManager;
	 //设置跟随分辨率变化窗口
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = kit.getScreenSize();
		private int screenHeight = (int) screenSize.getHeight();
	    private int screenWidth = (int) screenSize.getWidth();
	    private double enlargement_x=screenWidth/1920;
	    private double enlargement_y=screenHeight/1080;
	    private int windowWidth ; //获得窗口宽
	    private int windowHeight; //获得窗口高
	    int []BookMonthly=new int[13];//统计各月借阅数量,下标与月份对齐
		int CurrentYear;//当前年份，用于折线图数据
		DbUtil dbUtil=new DbUtil();
		BookDao bookDao=new BookDao(); 
		private JTable PurchaseTable;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					purchaseCount frame = new purchaseCount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}




	public purchaseCount() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * Create the frame.
	 */
	public purchaseCount(Manager curManager2) 
	{
		setTitle("\u56FE\u4E66\u91C7\u8D2D\u60C5\u51B5\u7EDF\u8BA1");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(screenWidth * 2/7, screenHeight / 3, (int)(1400*enlargement_x),(int)(1600*enlargement_y));
		windowWidth = this.getWidth(); //获得窗口宽
		windowHeight = this.getHeight(); //获得窗口高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2,1,10,10));
		HisBorrowTimeSeries( BookMonthly);
		contentPane.add(new TimeSeriesChart(BookMonthly,CurrentYear).getChartPanel());
		this.curManager=curManager2;
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel label = new JLabel("\u4ECA\u5E74\u8D2D\u4E70\u56FE\u4E66\u60C5\u51B5\u5217\u8868");
		label.setIcon(new ImageIcon(purchaseCount.class.getResource("/manager/image/BookNum.png")));
		label.setFont(new Font("华文行楷", Font.PLAIN, 40));
		
		JButton searchJB = new JButton("\u67E5\u8BE2");
		searchJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filltableAction(arg0);
			}

		
		});
		searchJB.setIcon(new ImageIcon(purchaseCount.class.getResource("/manager/image/bor_book.png")));
		searchJB.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(288)
							.addComponent(label)
							.addGap(216)
							.addComponent(searchJB))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(88)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1176, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(102)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(searchJB))
					.addGap(57)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		PurchaseTable = new JTable();
		PurchaseTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7BA1\u7406\u5458ID", "\u7BA1\u7406\u5458\u540D\u79F0", "\u8D2D\u4E70\u56FE\u4E66\u540D\u79F0", "\u8D2D\u4E70\u65F6\u95F4", "\u8D2D\u4E70\u6570\u91CF"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		PurchaseTable.getColumnModel().getColumn(0).setPreferredWidth(205);
		PurchaseTable.getColumnModel().getColumn(1).setPreferredWidth(221);
		PurchaseTable.getColumnModel().getColumn(2).setPreferredWidth(295);
		PurchaseTable.getColumnModel().getColumn(3).setPreferredWidth(336);
		PurchaseTable.getColumnModel().getColumn(4).setPreferredWidth(237);
		PurchaseTable.setFont(new Font("宋体", Font.PLAIN, 35));
		//修改表头大小和字体大小
		PurchaseTable.getTableHeader().setPreferredSize(new Dimension((int)(1*enlargement_x),(int)(40*enlargement_y)));
		PurchaseTable.getTableHeader().setFont(new Font("宋体", Font.PLAIN, (int)(35*enlargement_y)));
		PurchaseTable.setRowHeight((int)(80*enlargement_y));
		scrollPane.setViewportView(PurchaseTable);
		panel.setLayout(gl_panel);
	}

	/**
	 * 今年各月购书数量统计
	 * @param BookMonthly
	 */
	void HisBorrowTimeSeries( int []BookMonthly)
	{
		Connection con=null;
		Arrays.fill(BookMonthly,0);
		try {
			con=dbUtil.getCon();			
				//获取当前时间
				java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
				DateInt curdate=new DateInt();
				DateUtil.getdate(currentDate, curdate);
				this.CurrentYear=curdate.getYear();
				//查询所有记录
				StringBuffer sb=new StringBuffer("select * from t_perchase");
				PreparedStatement pstmt=con.prepareStatement(sb.toString());
				ResultSet Hispur=pstmt.executeQuery();//获取所有记录
				while( Hispur.next())
				{
					DateInt date=new DateInt();				
					DateUtil.getdate(Hispur.getDate("purTime"), date);				
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
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 填表格
	 * @param arg0
	 */
	private void filltableAction(ActionEvent arg0)
	{
		flltable(curManager);
		
	}

	/**
	 * 填表
	 * @param curManager2
	 */
	private void flltable(Manager curManager2) 
	{
		DefaultTableModel dtm=(DefaultTableModel) PurchaseTable.getModel();//获取模型
		dtm.setRowCount(0); // 设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			//OperationJp
			ResultSet rs=bookDao.hisShopSearch(con, curManager2);//获取所有记录
			while(rs.next()){
				Vector v=new Vector();//按顺序写
				v.add(rs.getString("managerID"));
				v.add(rs.getString("m.userName"));
				v.add(rs.getString("b.bookName"));
				v.add(rs.getDate("purTime"));
				v.add(rs.getInt("purNum"));		
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
}
