package manager.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import manager.dao.BookDao;
import manager.dao.BookTypeDao;
import manager.dao.BorrowDao;
import manager.entity.Book;
import manager.entity.BookType;
import manager.util.DbUtil;
import manager.util.Dialogutil;
import manager.util.ShowConfirmDialog;
import manager.util.StringUtil;
import manager.util.showMessageFrame;

public class BookManagerFrm extends JFrame {

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
	private JTable BookTable;
	private JTextField S_BookNameTxt;
	private JTextField S_AuthorTxt;
	private JComboBox S_BookTypeJcb; 
	private JComboBox bookTypeJcb;
	private JTextField idTxt;
	private JTextField bookNameTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField priceTxt;
	private JTextField authorTxt;
	private JTextArea BookDescTxt;
	private JRadioButton manJrb;
	private JRadioButton femaleJrb;
	
	private DbUtil dbUtil=new DbUtil();
	private BookTypeDao bookTypeDao=new BookTypeDao();
	private BookDao bookDao=new BookDao();
	private BorrowDao borrowDao=new BorrowDao();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BookManagerFrm frame = new BookManagerFrm();
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
	public BookManagerFrm() {
		setResizable(false);
		setTitle("\u56FE\u4E66\u7BA1\u7406");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100,100,(int)( 1900*enlargement_x), (int)(1600*enlargement_y));//设置初始位置（无所谓，后面重置），大小		
		windowWidth = this.getWidth(); //获得窗口宽
		windowHeight = this.getHeight(); //获得窗口高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane BookTableJsp = new JScrollPane();
		
		JPanel BookSearchJP = new JPanel();
		BookSearchJP.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("宋体", Font.PLAIN, 30), null));
		
		JPanel BookListJp = new JPanel();
		BookListJp.setBorder(new TitledBorder(null, "\u56FE\u4E66\u5217\u8868\u64CD\u4F5C", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("宋体", Font.PLAIN, 30), null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(126)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(BookListJp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(BookTableJsp)
							.addComponent(BookSearchJP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(87, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(54)
					.addComponent(BookSearchJP, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addGap(53)
					.addComponent(BookTableJsp, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addComponent(BookListJp, GroupLayout.PREFERRED_SIZE, 801, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		
		JLabel label_4 = new JLabel("\u7F16\u53F7");
		label_4.setFont(new Font("宋体", Font.PLAIN, 35));
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		idTxt.setColumns(10);
		
		JLabel label_5 = new JLabel("\u56FE\u4E66\u540D\u79F0");
		label_5.setFont(new Font("宋体", Font.PLAIN, 35));
		
		bookNameTxt = new JTextField();
		bookNameTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		bookNameTxt.setColumns(10);
		
		JLabel label_6 = new JLabel("\u4F5C\u8005\u6027\u522B");
		label_6.setFont(new Font("宋体", Font.PLAIN, 35));
		
		manJrb = new JRadioButton("\u7537");
		buttonGroup.add(manJrb);
		manJrb.setSelected(true);
		manJrb.setFont(new Font("宋体", Font.PLAIN, 35));
		
		femaleJrb = new JRadioButton("\u5973");
		buttonGroup.add(femaleJrb);
		femaleJrb.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel label_7 = new JLabel("\u4EF7\u683C");
		label_7.setFont(new Font("宋体", Font.PLAIN, 35));
		
		priceTxt = new JTextField();
		priceTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		priceTxt.setColumns(10);
		
		JLabel label_8 = new JLabel("\u56FE\u4E66\u4F5C\u8005");
		label_8.setFont(new Font("宋体", Font.PLAIN, 35));
		
		authorTxt = new JTextField();
		authorTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		authorTxt.setColumns(10);
		
		JLabel L_BookTypeLabel = new JLabel("\u56FE\u4E66\u7C7B\u522B");
		L_BookTypeLabel.setFont(new Font("宋体", Font.PLAIN, 35));
		
		bookTypeJcb = new JComboBox();
		bookTypeJcb.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel label_10 = new JLabel("\u56FE\u4E66\u63CF\u8FF0");
		label_10.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton ModifyButton = new JButton("\u4FEE\u6539");
		ModifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				bookUpdateActionPerformed(evt);
			}
		});
		ModifyButton.setIcon(new ImageIcon(BookManagerFrm.class.getResource("/manager/image/modify.png")));
		ModifyButton.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton DeleteButton = new JButton("\u6DD8\u6C70");
		DeleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bookDeleteActionPerformed(e);
			}
		});
		DeleteButton.setIcon(new ImageIcon(BookManagerFrm.class.getResource("/manager/image/delete.png")));
		DeleteButton.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JScrollPane BookDescJsp = new JScrollPane();
		GroupLayout gl_BookListJp = new GroupLayout(BookListJp);
		gl_BookListJp.setHorizontalGroup(
			gl_BookListJp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_BookListJp.createSequentialGroup()
					.addGap(84)
					.addGroup(gl_BookListJp.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_BookListJp.createSequentialGroup()
							.addComponent(label_10)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(BookDescJsp, GroupLayout.DEFAULT_SIZE, 1342, Short.MAX_VALUE))
						.addGroup(gl_BookListJp.createSequentialGroup()
							.addGroup(gl_BookListJp.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_BookListJp.createSequentialGroup()
									.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(priceTxt))
								.addGroup(gl_BookListJp.createSequentialGroup()
									.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
							.addGroup(gl_BookListJp.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_BookListJp.createSequentialGroup()
									.addComponent(label_5)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
									.addGap(80)
									.addComponent(label_6)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(manJrb)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(femaleJrb))
								.addGroup(gl_BookListJp.createSequentialGroup()
									.addComponent(label_8)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
									.addGap(68)
									.addComponent(L_BookTypeLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(bookTypeJcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
					.addGap(67))
				.addGroup(gl_BookListJp.createSequentialGroup()
					.addGap(231)
					.addComponent(ModifyButton)
					.addPreferredGap(ComponentPlacement.RELATED, 1036, Short.MAX_VALUE)
					.addComponent(DeleteButton)
					.addGap(146))
		);
		gl_BookListJp.setVerticalGroup(
			gl_BookListJp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_BookListJp.createSequentialGroup()
					.addGap(76)
					.addGroup(gl_BookListJp.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(femaleJrb)
						.addComponent(manJrb)
						.addComponent(label_6)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5))
					.addGap(97)
					.addGroup(gl_BookListJp.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_8)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(L_BookTypeLabel)
						.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_BookListJp.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_BookListJp.createSequentialGroup()
							.addGap(98)
							.addComponent(label_10)
							.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_BookListJp.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(BookDescJsp, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
							.addGap(46)))
					.addGroup(gl_BookListJp.createParallelGroup(Alignment.BASELINE)
						.addComponent(ModifyButton)
						.addComponent(DeleteButton))
					.addGap(35))
		);
		
		BookDescTxt = new JTextArea();
		BookDescTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		// 设置文本域边框
	    BookDescTxt.setBorder(new LineBorder(new Color(127, 157, 185)));
	    BookDescTxt.setLineWrap(true);
	    BookDescJsp.add(BookDescTxt);
		BookDescJsp.setViewportView(BookDescTxt);
		BookListJp.setLayout(gl_BookListJp);
		
		JLabel label = new JLabel("\u56FE\u4E66\u540D\u79F0");
		label.setFont(new Font("宋体", Font.PLAIN, 35));
		
		S_BookNameTxt = new JTextField();
		S_BookNameTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		S_BookNameTxt.setColumns(10);
		
		JLabel authorLabel = new JLabel("\u56FE\u4E66\u4F5C\u8005");
		authorLabel.setFont(new Font("宋体", Font.PLAIN, 35));
		
		S_AuthorTxt = new JTextField();
		S_AuthorTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		S_AuthorTxt.setColumns(10);
		
		JLabel S_BookTypeLabel = new JLabel("\u56FE\u4E66\u7C7B\u522B");
		S_BookTypeLabel.setFont(new Font("宋体", Font.PLAIN, 35));
		
		S_BookTypeJcb = new JComboBox();
		S_BookTypeJcb.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel label_3 = new JLabel("");
		
		JButton S_BookNameJb = new JButton("\u67E5\u8BE2");
		S_BookNameJb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);
			}
		});
		S_BookNameJb.setIcon(new ImageIcon(BookManagerFrm.class.getResource("/manager/image/search.png")));
		S_BookNameJb.setFont(new Font("宋体", Font.PLAIN, 35));
		GroupLayout gl_BookSearchJP = new GroupLayout(BookSearchJP);
		gl_BookSearchJP.setHorizontalGroup(
			gl_BookSearchJP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_BookSearchJP.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addGap(18)
					.addComponent(S_BookNameTxt, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(authorLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(S_AuthorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(75)
					.addComponent(S_BookTypeLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(S_BookTypeJcb, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_BookSearchJP.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_BookSearchJP.createSequentialGroup()
							.addGap(48)
							.addComponent(label_3)
							.addContainerGap(229, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_BookSearchJP.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(S_BookNameJb)
							.addGap(48))))
		);
		gl_BookSearchJP.setVerticalGroup(
			gl_BookSearchJP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_BookSearchJP.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_BookSearchJP.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(S_BookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(S_AuthorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(authorLabel)
						.addComponent(S_BookTypeLabel)
						.addComponent(S_BookTypeJcb, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(S_BookNameJb))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		BookSearchJP.setLayout(gl_BookSearchJP);
		
		BookTable = new JTable();
		BookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) 
			{
				bookTableMousePressed(me);
			}
		});
		BookTableJsp.add(BookTable);
		BookTable.setFont(new Font("宋体", Font.PLAIN, 35));
		BookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u56FE\u4E66\u4F5C\u8005", "\u4F5C\u8005\u6027\u522B", "\u56FE\u4E66\u4EF7\u683C", "\u56FE\u4E66\u63CF\u8FF0", "\u56FE\u4E66\u7C7B\u522B"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		BookTable.getColumnModel().getColumn(0).setPreferredWidth(96);
		BookTable.getColumnModel().getColumn(1).setPreferredWidth(203);
		BookTable.getColumnModel().getColumn(2).setPreferredWidth(177);
		BookTable.getColumnModel().getColumn(3).setPreferredWidth(143);
		BookTable.getColumnModel().getColumn(4).setPreferredWidth(152);
		BookTable.getColumnModel().getColumn(5).setPreferredWidth(465);
		BookTable.getColumnModel().getColumn(6).setPreferredWidth(162);
		//修改表头大小和字体大小
		BookTable.getTableHeader().setPreferredSize(new Dimension((int)(1*enlargement_x),(int)(40*enlargement_y)));
		BookTable.getTableHeader().setFont(new Font("宋体", Font.PLAIN, (int)(35*enlargement_y)));
		BookTable.setRowHeight((int)(40*enlargement_y));
		BookTableJsp.setViewportView(BookTable);
		contentPane.setLayout(gl_contentPane);
		
		this.fillBookType("search");//规范一点用final static
		this.fillBookType("modify");
		this.fillTable(new Book());
	}
	
	/**
	 * 重置表单
	 */
	private void ResetValue(){
		this.idTxt.setText("");
		this.bookNameTxt.setText("");
		this.authorTxt.setText("");
		this.priceTxt.setText("");
		this.manJrb.setSelected(true);
		this.BookDescTxt.setText("");
		if(this.bookTypeJcb.getItemCount()>0){
			this.bookTypeJcb.setSelectedIndex(0);
		}
	}
	
	/**
	 * 图书查询事件处理
	 * @param e
	 */
	private void bookSearchActionPerformed(ActionEvent evt) 
	{
		String bookName=this.S_BookNameTxt.getText();
		String author=this.S_AuthorTxt.getText();
		BookType bookType=(BookType) this.S_BookTypeJcb.getSelectedItem();//返回object类,强转
		int bookTypeId=bookType.getId();
		
		Book book=new Book(bookName,author,bookTypeId);
		this.fillTable(book);
	}
	
	/**
	 * 图书修改事件处理
	 * @param evt
	 */
	private void bookUpdateActionPerformed(ActionEvent evt) {
		String id=this.idTxt.getText();
		if(StringUtil.isEmpty(id))
		{
			showMessageFrame note=new showMessageFrame(null,"请选择要修改的记录",showMessageFrame.NOTE);
			return;
		}
		
		String bookName=this.bookNameTxt.getText();
		String author=this.authorTxt.getText();
		String price=this.priceTxt.getText();
		String bookDesc=this.BookDescTxt.getText();
		
		if(StringUtil.isEmpty(bookName)){
			showMessageFrame note=new showMessageFrame(null,"图书名称不能为空！",showMessageFrame.NOTE);
			return;
		}
		
		if(StringUtil.isEmpty(author)){
			showMessageFrame note=new showMessageFrame(null,"图书作者不能为空！",showMessageFrame.NOTE);
			return;
		}
		
		if(StringUtil.isEmpty(price)){
			showMessageFrame note=new showMessageFrame(null,"图书价格不能为空！",showMessageFrame.NOTE);
			return;
		}
		
		String sex="";
		if(manJrb.isSelected()){
			sex="男";
		}else if(femaleJrb.isSelected()){
			sex="女";
		}
		
		BookType bookType=(BookType) bookTypeJcb.getSelectedItem();
		int bookTypeId=bookType.getId();
		
		Book book=new Book(Integer.parseInt(id),  bookName, author, sex, Float.parseFloat(price),  bookTypeId,  bookDesc);
		//Integer.parseInt(id):string转int。主键要封装进去，确保删对
		
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int addNum=bookDao.update(con, book);
			if(addNum==1){
				showMessageFrame note=new showMessageFrame(null,"图书修改成功！",showMessageFrame.NORMAL);
				ResetValue();
				this.fillTable(new Book());//重置表单后刷新
			}else{				
				showMessageFrame note=new showMessageFrame(null,"图书修改失败！",showMessageFrame.NOTE);
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "图书修改失败！");
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
	 * 图书删除事件处理
	 * @param evt
	 */
	private void bookDeleteActionPerformed(ActionEvent evt) {
		int id=Integer.parseInt(idTxt.getText());
		if(StringUtil.isEmpty(idTxt.getText()))
		{
			showMessageFrame note=new showMessageFrame(null,"请选择要删除的记录！",showMessageFrame.NOTE);
			return;
		}
		ShowConfirmDialog confrm=new ShowConfirmDialog(null,"提示","确定要删除该记录吗？");
		//确认按钮
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
					
				int	ConfrmMassage=confrm.getResult();	
		if(ConfrmMassage==0){
			Connection con=null;
			try{
				con=dbUtil.getCon();
				boolean flag=borrowDao.existborrowByBookId(con, id);
				if(flag)
				{
					Dialogutil attention=new Dialogutil(null,"Attention!","该图书还有未还记录，不能删除！");
					return;
				}
				else
				{	
					int deleteNum=bookDao.delete(con, id);
					if(deleteNum==1)
					{
						showMessageFrame note=new showMessageFrame(null,"删除成功！",showMessageFrame.NORMAL);
						ResetValue();
						fillTable(new Book());
					}else
					{
						showMessageFrame note=new showMessageFrame(null,"删除失败！",showMessageFrame.NOTE);
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				showMessageFrame note=new showMessageFrame(null,"删除失败！",showMessageFrame.NOTE);
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
				}).start();	
	}
	
	/**
	 * 初始化下拉框
	 * @param type 下拉框类型 
	 */
	private void fillBookType(String type)
	{//区别哪一种下拉框
		Connection con=null;
		BookType bookType=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=bookTypeDao.list(con, new BookType());
			
			if("search".equals(type)){//如果是search的下拉框多一项请选择，其他就不用了
				bookType=new BookType();
				bookType.setBookTypeName("请选择...");
				bookType.setId(-1);//请选择的ID为-1
				this.S_BookTypeJcb.addItem(bookType);//additem 方法在组合框或列表框中添加一个新数据项，并且可以指定数据项索引。
			}
			while(rs.next()){
				//resultset的next（）方法，每调用一次，游标后移一个，
				//一开始是处于第一行前,beforeFirst,第一次使用next()就将指针指向返回结果集的第一行。
				//当resultset游动到最后一行，再调用next（）方法会返回false，并且游标也到了最后一行的后面。
				bookType=new BookType();
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				bookType.setId(rs.getInt("id"));//getInt(int columnIndex) 以 Java 编程语言中的整数形式获取此 ResultSet 对象当前行中指定列的值。
				if("search".equals(type)){
					this.S_BookTypeJcb.addItem(bookType);
				}else if("modify".equals(type))
				{
					this.bookTypeJcb.addItem(bookType);
				}
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
	 * 初始化表格数据
	 * @param book
	 */
	private void fillTable(Book book)
	{
		DefaultTableModel dtm=(DefaultTableModel) BookTable.getModel();//获取模型
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
				v.add(rs.getString("sex"));
				v.add(rs.getFloat("price"));
				v.add(rs.getString("bookDesc"));
				v.add(rs.getString("bookTypeName"));
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
	 * @param met
	 */
	private void bookTableMousePressed(MouseEvent met) 
	{
		int row=this.BookTable.getSelectedRow();//返回行号
		this.idTxt.setText((String)BookTable.getValueAt(row, 0));
		//获取row行，0列数据。注意fill时候是string类型。注意顺序
		this.bookNameTxt.setText((String)BookTable.getValueAt(row, 1));
		this.authorTxt.setText((String)BookTable.getValueAt(row, 2));
		String sex=(String)BookTable.getValueAt(row, 3);
		if("男".equals(sex)){
			this.manJrb.setSelected(true);
		}else if("女".equals(sex)){
			this.femaleJrb.setSelected(true);
		}
		this.priceTxt.setText(BookTable.getValueAt(row, 4)+"");
		this.BookDescTxt.setText((String)BookTable.getValueAt(row, 5));
		String bookTypeName=(String)this.BookTable.getValueAt(row, 6);
		int n=this.bookTypeJcb.getItemCount();//下拉框有多少项
		for(int i=0;i<n;i++)//遍历下拉框
		{
			BookType item=(BookType)this.bookTypeJcb.getItemAt(i);//getItemAt()表示获取第几行数据。只能获取类型名称
			if(item.getBookTypeName().equals(bookTypeName))
			{
				this.bookTypeJcb.setSelectedIndex(i);
			}
		}
	}
}
