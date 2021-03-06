package manager.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import manager.dao.BorrowDao;
import manager.entity.DateInt;
import manager.util.BarChart;
import manager.util.DateUtil;
import manager.util.DbUtil;
import manager.util.Dialogutil;
import manager.util.PieChart;
import manager.util.StringUtil;

public class Statistic extends JFrame {

	private JPanel contentPane;
     //设置跟随分辨率变化窗口
	Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
	private int screenHeight = (int) screenSize.getHeight();
    private int screenWidth = (int) screenSize.getWidth();
    private double enlargement_x=screenWidth/1920;
    private double enlargement_y=screenHeight/1080;
    private int windowWidth ; //获得窗口宽
    private int windowHeight; //获得窗口高
    /*******************统计变量***************************************/
	final private int typenum=50;//用于存放有多少图书种类
   	int []TypeNum=new int[typenum];
    StringBuffer[] TypeBuffer=new StringBuffer[typenum];//记录各类书数量
	int []BookMonthly=new int[13];//统计各月借阅数量,下标与月份对齐
	int CurrentYear;//当前年份，用于折线图数据
	/**************************数据库操作****************************************/
	DbUtil dbUtil=new DbUtil();
	BorrowDao borrowDao=new BorrowDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Statistic frame = new Statistic();
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
	public Statistic() 
	{
		setResizable(false);
		setTitle("\u6570\u636E\u7EDF\u8BA1\u56FE");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(screenWidth * 2/7, screenHeight / 3, (int)(1400*enlargement_x),(int)(1600*enlargement_y));
		windowWidth = this.getWidth(); //获得窗口宽
		windowHeight = this.getHeight(); //获得窗口高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2,1,10,10));
		 //Dimension PieDi=new Dimension(50,80);
		HisBorrowPieChart(TypeBuffer,TypeNum);//获得数据
		contentPane.add(new PieChart(TypeBuffer,TypeNum).getChartPanel());           //添加饼状图  
	    contentPane.add(new BarChart(TypeBuffer,TypeNum).getChartPanel());//添加柱形图  
		//设置JFrame最大化
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}

	/**
	 * 数据获得
	 * @param TypeBuffer
	 * @param number
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
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			//获得当前年份
			DateInt curdate=new DateInt();
			DateUtil.getdate(currentDate, curdate);
			this.CurrentYear=curdate.getYear();
			
			ResultSet Hisbo=borrowDao.TotalHisBorrowDistri(con);//获取所有记录,由用户ID查找
			while( Hisbo.next())
			{
				int index=Hisbo.getInt("bt.id");
				if(StringUtil.isEmpty(TypeBuffer[index].toString()))
				{
					TypeBuffer[index].append(Hisbo.getString("bt.bookTypeName"));
				}
				number[index]++;	//该类型+1
//				DateInt date=new DateInt();				
//				DateUtil.getdate(Hisbo.getDate("borTime"), date);				
//				if(curdate.getYear()==date.getYear())
//				{
//					BookMonthly[date.getMonth()]++;//该月数量+1
//				}
//				else
//				{
//					continue;
//				}
			}
				
		} catch (Exception e) {
			Dialogutil attention=new Dialogutil(null,"Attention!","用户信息获取失败！");
			e.printStackTrace();
		}	
	}
}
