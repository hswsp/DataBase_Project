package manager.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import manager.dao.BookDao;
import manager.dao.BookTypeDao;
import manager.entity.Book;
import manager.entity.BookType;
import manager.util.DbUtil;
import manager.util.StringUtil;
import manager.util.showMessageFrame;

public class BookAddFrm extends JFrame {

	private JPanel contentPane;
	private JTextField bookNameTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField authorTxt;
	private JTextField priceTxt;
	private JComboBox bookTypeJCB;
	private JTextArea bookDescTxt;
	private JRadioButton manJRB;
	private JRadioButton femaleJRB; 

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
	private JTextField purNumTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookAddFrm frame = new BookAddFrm();
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
	public BookAddFrm() {
		setResizable(false);
		setTitle("\u56FE\u4E66\u6DFB\u52A0");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		setBounds(100,100,(int)( 1600*enlargement_x), (int)(1400*enlargement_y));//设置初始位置（无所谓，后面重置），大小		
		windowWidth = this.getWidth(); //获得窗口宽
		windowHeight = this.getHeight(); //获得窗口高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u56FE\u4E66\u540D\u79F0");
		label.setFont(new Font("宋体", Font.PLAIN, 35));
		
		bookNameTxt = new JTextField();
		bookNameTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		bookNameTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u56FE\u4E66\u4F5C\u8005");
		label_1.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel label_2 = new JLabel("\u4F5C\u8005\u6027\u522B");
		label_2.setFont(new Font("宋体", Font.PLAIN, 35));
		
		manJRB = new JRadioButton("\u7537");
		buttonGroup.add(manJRB);
		manJRB.setSelected(true);
		manJRB.setFont(new Font("宋体", Font.PLAIN, 35));
		
		femaleJRB = new JRadioButton("\u5973");
		buttonGroup.add(femaleJRB);
		femaleJRB.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel label_3 = new JLabel("\u56FE\u4E66\u4EF7\u683C");
		label_3.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel label_4 = new JLabel("\u56FE\u4E66\u63CF\u8FF0");
		label_4.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JScrollPane DescSP = new JScrollPane();
		
		authorTxt = new JTextField();
		authorTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		authorTxt.setColumns(10);
		
		priceTxt = new JTextField();
		priceTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		priceTxt.setColumns(10);
		
		JLabel label_5 = new JLabel("\u56FE\u4E66\u7C7B\u522B");
		label_5.setFont(new Font("宋体", Font.PLAIN, 35));
		
	    bookTypeJCB = new JComboBox();
		bookTypeJCB.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookAddActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(BookAddFrm.class.getResource("/manager/image/add.png")));
		button.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(BookAddFrm.class.getResource("/manager/image/reset.png")));
		button_1.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel NumJL = new JLabel("\u8D2D\u8FDB\u6570\u91CF");
		NumJL.setFont(new Font("宋体", Font.PLAIN, 35));
		
		purNumTxt = new JTextField();
		purNumTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		purNumTxt.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(136)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED, 1086, Short.MAX_VALUE)
							.addComponent(button_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(label_5)
									.addComponent(label_4)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(bookTypeJCB, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(manJRB)
													.addGap(105)
													.addComponent(femaleJRB)))
											.addGap(179)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(NumJL)
												.addComponent(label_3)))
										.addComponent(label_1))
									.addGap(46)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(purNumTxt, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(authorTxt, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
											.addComponent(priceTxt, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE))))
								.addComponent(DescSP, GroupLayout.PREFERRED_SIZE, 1132, GroupLayout.PREFERRED_SIZE))
							.addGap(4))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addGap(47)
							.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)))
					.addGap(116))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(119)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addGap(94)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(manJRB)
						.addComponent(femaleJRB)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3))
					.addGap(116)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(bookTypeJCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(NumJL)
						.addComponent(purNumTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(125)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label_4)
						.addComponent(DescSP, GroupLayout.PREFERRED_SIZE, 495, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addGap(94))
		);
		
	    bookDescTxt = new JTextArea();
		bookDescTxt.setLineWrap(true);
		bookDescTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		DescSP.add(bookDescTxt);
		DescSP.setViewportView(bookDescTxt);
		contentPane.setLayout(gl_contentPane);
		
		// 设置文本域边框
		bookDescTxt.setBorder(new LineBorder(new Color(127, 157, 185)));
		
		fillBookType();//初始化下拉框
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
	 * 重置表单
	 */
	private void ResetValue(){
		this.bookNameTxt.setText("");
		this.authorTxt.setText("");
		this.priceTxt.setText("");
		this.manJRB.setSelected(true);
		this.bookDescTxt.setText("");
		if(this.bookTypeJCB.getItemCount()>0)//图书类别项大于0,才操作
		{
			this.bookTypeJCB.setSelectedIndex(0);//选中第一项
		}
	}
	
	/**
	 * 重置事件处理
	 * @param e
	 */
	private void resetValueActionPerformed(ActionEvent e) {
		this.ResetValue();
	}
	/**
	 * 图书添加事件处理
	 * @param e
	 */
	private void bookAddActionPerformed(ActionEvent evt) 
	{
		String bookName=this.bookNameTxt.getText();
		String author=this.authorTxt.getText();
		String price=this.priceTxt.getText();
		String bookDesc=this.bookDescTxt.getText();
		
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
		
		//获取性别
		String sex="";
		if(manJRB.isSelected()){//判断是否选择
			sex="男";
		}else if(femaleJRB.isSelected()){
			sex="女";
		}
		//获取类别
		BookType bookType=(BookType) bookTypeJCB.getSelectedItem();
		int bookTypeId=bookType.getId();		
		Book book=new Book(bookName,author, sex, Float.parseFloat(price) , bookTypeId,  bookDesc);//Float.parseFloat（）表示转成float
		
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int addNum=bookDao.add(con, book);
			if(addNum==1){
				showMessageFrame note=new showMessageFrame(null,"图书添加成功！",showMessageFrame.NORMAL);
				ResetValue();
			}else{
				showMessageFrame note=new showMessageFrame(null,"图书添加失败！",showMessageFrame.NOTE);
			}
		}catch(Exception e){
			e.printStackTrace();
			showMessageFrame note=new showMessageFrame(null,"图书添加失败！",showMessageFrame.NOTE);
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
