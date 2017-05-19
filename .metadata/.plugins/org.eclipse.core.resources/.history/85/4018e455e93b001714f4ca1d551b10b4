package manager.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import manager.dao.BookDao;
import manager.dao.BookTypeDao;
import manager.entity.Book;
import manager.entity.BookType;
import manager.entity.Manager;
import manager.util.DbUtil;
import manager.util.StringUtil;
import manager.util.showMessageFrame;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookShopFrm extends JFrame {

	private JPanel contentPane;
	//设置跟随分辨率变化窗口
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = kit.getScreenSize();
		private int screenHeight = (int) screenSize.getHeight();
	    private int screenWidth = (int) screenSize.getWidth();
	    private double enlargement_x=screenWidth/1920;
	    private double enlargement_y=screenHeight/1080;

	    private int windowWidth ; //获得当前窗口宽
	    private int windowHeight; //获得当前窗口高
	   
	    
	    private DbUtil  dbUtil=new DbUtil();
	    private BookTypeDao bookTypeDao=new BookTypeDao();
		private BookDao bookDao=new BookDao();
		private Book shopBook;
		
	    private JTextField bookNameTxt;
	    private JComboBox bookTypeJCB;
	    private JTextField shopNum;
	    private JTable bookTable;
	    private Manager curManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookShopFrm frame = new BookShopFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BookShopFrm() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create the frame.
	 */
	public BookShopFrm(Manager currentManager) {
		this.curManager=currentManager;
		setTitle("\u56FE\u4E66\u91C7\u8D2D");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100,100,(int)( 1800*enlargement_x), (int)(1600*enlargement_y));//设置初始位置（无所谓，后面重置），大小		
		windowWidth = this.getWidth(); //获得窗口宽
		windowHeight = this.getHeight(); //获得窗口高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u56FE\u4E66\u540D\u79F0");
		label.setIcon(new ImageIcon(BookShopFrm.class.getResource("/manager/image/green_book.png")));
		label.setFont(new Font("宋体", Font.PLAIN, 35));
		
		bookNameTxt = new JTextField();
		bookNameTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		bookNameTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u56FE\u4E66\u7C7B\u522B");
		label_1.setIcon(new ImageIcon(BookShopFrm.class.getResource("/manager/image/Evernote_Book.png")));
		label_1.setFont(new Font("宋体", Font.PLAIN, 35));
		
		bookTypeJCB = new JComboBox();
		bookTypeJCB.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel label_2 = new JLabel("\u8D2D\u8FDB\u6570\u91CF");
		label_2.setIcon(new ImageIcon(BookShopFrm.class.getResource("/manager/image/BookNum.png")));
		label_2.setFont(new Font("宋体", Font.PLAIN, 35));
		
		shopNum = new JTextField();
		shopNum.setFont(new Font("宋体", Font.PLAIN, 35));
		shopNum.setColumns(10);
		
		JButton button = new JButton("\u91C7\u8D2D\u5B8C\u6BD5");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				purchaseAction(arg0);
			}


		});
		button.setIcon(new ImageIcon(BookShopFrm.class.getResource("/manager/image/HisBook.png")));
		button.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetValueactionPerformed(e);
			}
		
		});
		button_1.setIcon(new ImageIcon(BookShopFrm.class.getResource("/manager/image/exit.png")));
		button_1.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JScrollPane BookTableJsp = new JScrollPane();
			
		JButton bookSearch = new JButton("\u67E5\u8BE2");
		bookSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				bookSearchActionPerformed(evt);
			}
		});
		bookSearch.setIcon(new ImageIcon(BookShopFrm.class.getResource("/manager/image/search.png")));
		bookSearch.setFont(new Font("宋体", Font.PLAIN, 35));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(388)
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.RELATED, 733, Short.MAX_VALUE)
					.addComponent(button_1)
					.addGap(263))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(187)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_1)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(label_2)
							.addComponent(label)))
					.addGap(121)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(bookNameTxt, GroupLayout.DEFAULT_SIZE, 1117, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(bookTypeJCB, GroupLayout.PREFERRED_SIZE, 590, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 356, Short.MAX_VALUE)
							.addComponent(bookSearch))
						.addComponent(shopNum, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE)
						.addComponent(BookTableJsp, GroupLayout.DEFAULT_SIZE, 1117, Short.MAX_VALUE))
					.addGap(140))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(138)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(bookTypeJCB, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addComponent(bookSearch))
						.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(87)
					.addComponent(BookTableJsp, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(75)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(shopNum, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
							.addGap(222)
							.addComponent(button)
							.addContainerGap(178, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 297, Short.MAX_VALUE)
							.addComponent(button_1)
							.addGap(165))))
		);
		
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				bookSearchJTMousePressed(arg0);
			}
		});
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u4E66\u540D", "\u51FA\u7248\u793E", "\u4F5C\u8005"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.getColumnModel().getColumn(0).setPreferredWidth(102);
		bookTable.getColumnModel().getColumn(1).setPreferredWidth(562);
		bookTable.getColumnModel().getColumn(2).setPreferredWidth(286);
		bookTable.getColumnModel().getColumn(3).setPreferredWidth(280);
		bookTable.setFont(new Font("宋体", Font.PLAIN, 35));
		//修改表头大小和字体大小
		bookTable.getTableHeader().setPreferredSize(new Dimension((int)(1*enlargement_x),(int)(40*enlargement_y)));
		bookTable.getTableHeader().setFont(new Font("宋体", Font.PLAIN, (int)(35*enlargement_y)));
		bookTable.setRowHeight((int)(40*enlargement_y));
	    BookTableJsp.setViewportView(bookTable);
		contentPane.setLayout(gl_contentPane);
		
		fillBookType();
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
			this.bookTypeJCB.addItem(bookType);
			while(rs.next()){
				bookType=new BookType();
				bookType.setId(rs.getInt("id"));
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				this.bookTypeJCB.addItem(bookType);//添加项
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
	/**
	 * 采购事件
	 * @param arg0
	 */
	private void purchaseAction(ActionEvent arg0) 
	{
		String bookName=this.bookNameTxt.getText();
		Integer shopNumber=Integer.parseInt(shopNum.getText());
		if(StringUtil.isEmpty(bookName))
		{
			showMessageFrame note=new showMessageFrame(null,"图书名称不能为空！",showMessageFrame.NOTE);
			return;
		}
		if(shopBook==null)
		{
			showMessageFrame note=new showMessageFrame(null,"请在表格中选择所要添加数量的图书！",showMessageFrame.NOTE);
			return;
		}
		Connection con=null;
			try {
				con=dbUtil.getCon();
				//shopBook=BookDao.BookSearch(con, shopBook);
				String sql="update t_book set number=? where id=?";
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, shopNumber+shopBook.getBookNum());
				pstmt.setInt(2, shopBook.getId());
				int n=pstmt.executeUpdate();
				//插入购买记录
				java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
				String sqll="insert into t_perchase values(null,?,?,?,?)";
				PreparedStatement pstmtt=con.prepareStatement(sqll);
				pstmtt.setString(1, curManager.getId());
				pstmtt.setInt(2, shopBook.getId());
				pstmtt.setDate(3,currentDate);
				pstmtt.setInt(4, shopNumber);	
				int m=pstmtt.executeUpdate();
				if(n==1&&m==1)
				{
					ResetValue();
					showMessageFrame note=new showMessageFrame(null,"采购成功！",showMessageFrame.NORMAL);
				}
			} catch (Exception e) {
				showMessageFrame note=new showMessageFrame(null,"采购失败！",showMessageFrame.NOTE);
				e.printStackTrace();
			}
	}
	
	/**
	 * 重置响应
	 * @param e
	 */
	private void ResetValueactionPerformed(ActionEvent e) 
	{
		this.ResetValue();
	}
	
	private void ResetValue()
	{
		if(this.bookTypeJCB.getItemCount()>0)//图书类别项大于0,才操作
		{
			this.bookTypeJCB.setSelectedIndex(0);//选中第一项
		}
		bookNameTxt.setText("");
		shopNum .setText("");
		//清空表格
		DefaultTableModel model =(DefaultTableModel) bookTable.getModel();
		 while(model.getRowCount()>0)
		 {
		      model.removeRow(model.getRowCount()-1);
		 }
	}
	/**
	 * 图书查询事件处理
	 * @param e
	 */
	private void bookSearchActionPerformed(ActionEvent evt) 
	{
		String bookName=this.bookNameTxt.getText();
		BookType bookType=(BookType) this.bookTypeJCB.getSelectedItem();//返回object类,强转
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
		DefaultTableModel dtm=(DefaultTableModel) bookTable.getModel();//获取模型
		dtm.setRowCount(0); // 设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=bookDao.list(con, book);//获取所有记录
			while(rs.next()){
				Vector v=new Vector();//按顺序写
				v.add(rs.getString("id"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("publisher"));
				v.add(rs.getString("author"));
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
	 * 借书表格行点击事件处理
	 * @param e
	 */
	private void bookSearchJTMousePressed(MouseEvent evt) 
	{
		int row=bookTable.getSelectedRow();//选中行
		int BookID=Integer.parseInt((String)bookTable.getValueAt(row, 0));	
		Book book=new Book(BookID);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			shopBook=BookDao.BookSearch(con, book);
			bookNameTxt .setText(shopBook.getBookName());
			//bookTypeJCB.setSelectedIndex(arg0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String BookName=(String)bookTable.getValueAt(row,1);
		return;
		//BookTypeDescTxt.setText((String)BookSearchJT.getValueAt(row, 2));
	}
}
