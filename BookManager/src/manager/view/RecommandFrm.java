package manager.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import manager.dao.bookRecomDao;
import manager.entity.BookRecommend;
import manager.entity.BookType;
import manager.util.DbUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RecommandFrm extends JFrame {

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
    private JTable recomJtable;
    private BookRecommend recommand=new BookRecommend();
    //private int RecomID=0;
    /*********************数据库借口********************************/
    private DbUtil dbUtil=new DbUtil();
    private bookRecomDao recommandDao=new bookRecomDao();
    
    private static boolean IsShowBookAddFrm=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RecommandFrm frame = new RecommandFrm();
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
	public RecommandFrm() 
	{
		setResizable(false);
		setTitle("\u8BFB\u8005\u63A8\u8350\u4FE1\u606F");		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(screenWidth * 2/7, screenHeight / 3, (int)(1400*enlargement_x),(int)(1600*enlargement_y));
		windowWidth = this.getWidth(); //获得窗口宽
		windowHeight = this.getHeight(); //获得窗口高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u8BFB\u8005\u63A8\u8350\u4FE1\u606F");
		label.setIcon(new ImageIcon(RecommandFrm.class.getResource("/manager/image/Recom.png")));
		label.setFont(new Font("华文行楷", Font.PLAIN, 40));
		
		JScrollPane RecomJsp = new JScrollPane();
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(554)
							.addComponent(label))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(121)
							.addComponent(RecomJsp, GroupLayout.PREFERRED_SIZE, 1169, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(121)
					.addComponent(label)
					.addGap(62)
					.addComponent(RecomJsp, GroupLayout.PREFERRED_SIZE, 900, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(397, Short.MAX_VALUE))
		);
		
		recomJtable = new JTable();
		recomJtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				RecomTableMousePressed(evt);
			}
		});
		recomJtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u63A8\u8350\u8005ID", "\u63A8\u8350\u4E66\u540D", "\u56FE\u4E66\u7C7B\u578B", "\u63A8\u8350\u7406\u7531"
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
		recomJtable.getColumnModel().getColumn(0).setPreferredWidth(130);
		recomJtable.getColumnModel().getColumn(1).setPreferredWidth(234);
		recomJtable.getColumnModel().getColumn(2).setPreferredWidth(274);
		recomJtable.getColumnModel().getColumn(3).setPreferredWidth(301);
		recomJtable.getColumnModel().getColumn(4).setPreferredWidth(688);
		recomJtable.setFont(new Font("宋体", Font.PLAIN, 35));
		//修改表头大小和字体大小
		recomJtable.getTableHeader().setPreferredSize(new Dimension((int)(1*enlargement_x),(int)(40*enlargement_y)));
		recomJtable.getTableHeader().setFont(new Font("宋体", Font.PLAIN, (int)(35*enlargement_y)));
		recomJtable.setRowHeight((int)(80*enlargement_y));
		
		RecomJsp.setViewportView(recomJtable);
		contentPane.setLayout(gl_contentPane);
		fillTable(recommand);
	}
	
	//************************初始化表格数据**************************************/
	/**
	 * 初始化表格数据
	 * @param recommand
	 */
	private void fillTable(BookRecommend recommand)
	{
		DefaultTableModel dtm=(DefaultTableModel) recomJtable.getModel();//获取模型
		dtm.setRowCount(0); // 设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			//OperationJp
			ResultSet rs=recommandDao.list(con,recommand);//获取所有记录
			while(rs.next()){
				Vector v=new Vector();//按顺序写
				v.add(rs.getInt("b.ID"));
				v.add(rs.getString("b.userID"));
				v.add(rs.getString("b.bookName"));
				v.add(rs.getString("bt.bookTypeName"));
				v.add(rs.getString("b.recomReason"));	
				dtm.addRow(v);//加一行
			}
			
		}catch(Exception e)
		{
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
	 * 表格行点击事件
	 * @param evt
	 */
	private void RecomTableMousePressed(MouseEvent evt) 
	{
		int row=this.recomJtable.getSelectedRow();//返回行号
		if(IsShowBookAddFrm)
		{
			return;
		}
		else
		{
			//弹出图书添加框
			BookAddFrm bookAddWinow=new BookAddFrm();
			//填写一些已知信息
			int RecomID=(int)this.recomJtable.getValueAt(row, 0);
			bookAddWinow.bookNameTxt.setText((String)recomJtable.getValueAt(row, 2));			
			String bookTypeName=(String)this.recomJtable.getValueAt(row, 3);
			int n=bookAddWinow.bookTypeJCB.getItemCount();//下拉框有多少项
			for(int i=0;i<n;i++)//遍历下拉框
			{
				BookType item=(BookType)bookAddWinow.bookTypeJCB.getItemAt(i);//getItemAt()表示获取第几行数据。只能获取类型名称
				if(item.getBookTypeName().equals(bookTypeName))
				{
					bookAddWinow.bookTypeJCB.setSelectedIndex(i);
				}
			}
			bookAddWinow.RecomID=RecomID;
			bookAddWinow.IsOpenFromRecom=true;
			bookAddWinow.setVisible(true);
			this.dispose();
			IsShowBookAddFrm=false;
		}
	}
}
