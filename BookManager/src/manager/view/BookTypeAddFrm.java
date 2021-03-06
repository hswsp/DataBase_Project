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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import manager.dao.BookTypeDao;
import manager.entity.BookType;
import manager.util.DbUtil;
import manager.util.StringUtil;
import manager.util.showMessageFrame;
import java.awt.Color;
import javax.swing.JScrollPane;

public class BookTypeAddFrm extends JFrame {

	private JPanel contentPane;
	private JTextField BookTypeNameTxt;
	JTextArea BookTypeDescTxt = new JTextArea();
//	DocColorTest BookTypeDescTxt=new DocColorTest();

	//设置跟随分辨率变化窗口
	Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
	private int screenHeight = (int) screenSize.getHeight();
    private int screenWidth = (int) screenSize.getWidth();
    private double enlargement_x=screenWidth/1920;
    private double enlargement_y=screenHeight/1080;

    private int windowWidth ; //获得窗口宽
    private int windowHeight; //获得窗口高
    
    private DbUtil dbUtil=new DbUtil();
    private BookTypeDao bookTypeDao=new BookTypeDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BookTypeAddFrm frame = new BookTypeAddFrm();
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
	public BookTypeAddFrm() {
		setResizable(false);
		setTitle("\u56FE\u4E66\u7C7B\u522B\u6DFB\u52A0");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//setBounds(100, 100, 892, 619);
		setBounds(100,100,(int)( 1400*enlargement_x), (int)(900*enlargement_y));//设置初始位置（无所谓，后面重置），大小		
		windowWidth = this.getWidth(); //获得窗口宽
		windowHeight = this.getHeight(); //获得窗口高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel BookName = new JLabel("\u56FE\u4E66\u7C7B\u522B\u6DFB\u52A0");
		BookName.setIcon(new ImageIcon(BookTypeAddFrm.class.getResource("/manager/image/green_book.png")));
		BookName.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JLabel BookDesc = new JLabel("\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0");
		BookDesc.setIcon(new ImageIcon(BookTypeAddFrm.class.getResource("/manager/image/bookTypeDesc.png")));
		BookDesc.setFont(new Font("宋体", Font.PLAIN, 35));
		
		BookTypeNameTxt = new JTextField();
		BookTypeNameTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		BookTypeNameTxt.setColumns(10);
		
		
		
		JButton Add = new JButton("\u6DFB\u52A0");
		Add.setIcon(new ImageIcon(BookTypeAddFrm.class.getResource("/manager/image/add.png")));
		Add.setFont(new Font("宋体", Font.PLAIN, 35));
		Add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				bookTypeAddActionPerformed(evt);
			}			
		});
		
		JButton reset = new JButton("\u91CD\u7F6E");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		reset.setIcon(new ImageIcon(BookTypeAddFrm.class.getResource("/manager/image/reset.png")));
		reset.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JScrollPane DescSP = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(103)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(BookDesc)
						.addComponent(BookName))
					.addGap(120)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(DescSP, 0, 0, Short.MAX_VALUE)
						.addComponent(BookTypeNameTxt, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
					.addGap(72))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(273)
					.addComponent(Add)
					.addPreferredGap(ComponentPlacement.RELATED, 671, Short.MAX_VALUE)
					.addComponent(reset)
					.addGap(177))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(73, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(BookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addComponent(BookName))
					.addGap(180)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(BookDesc)
						.addComponent(DescSP, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
					.addGap(82)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Add)
						.addComponent(reset))
					.addGap(43))
		);
		DescSP.add(BookTypeDescTxt);
		DescSP.setViewportView(BookTypeDescTxt);
		BookTypeDescTxt.setLineWrap(true);
		BookTypeDescTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		
		// 设置文本域边框
		BookTypeDescTxt.setBorder(new LineBorder(new Color(127, 157, 185)));
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * 图书类别添加事件处理
	 * @param evt
	 */
	private void bookTypeAddActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String bookTypeName =this.BookTypeNameTxt.getText();
		String bookTypeDesc=this.BookTypeDescTxt.getText();
		if(StringUtil.isEmpty(bookTypeName)){
			//JOptionPane.showMessageDialog(null, "图书类别名称不能为空！");
			showMessageFrame EmptyNote=new showMessageFrame(null,"图书类别名称不能为空！",showMessageFrame.NOTE);
			return;
		}
		BookType bookType=new BookType(bookTypeName,bookTypeDesc);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int n=bookTypeDao.add(con, bookType);
			if(n==1){
				//JOptionPane.showMessageDialog(null, "图书类别添加成功！");
				showMessageFrame SucNote=new showMessageFrame(null,"图书类别添加成功！",showMessageFrame.NOTE);
				ResetValue();
			}
			else
			{
				//JOptionPane.showMessageDialog(null, "图书类别添加失败！");
				showMessageFrame FailNote=new showMessageFrame(null,"图书类别添加失败！",showMessageFrame.NOTE);
			}
		}catch(Exception e){
			e.printStackTrace();
			//JOptionPane.showMessageDialog(null, "图书类别添加失败！");
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
	 * 重置事件处理
	 * @param e
	 */
	private void resetValueActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.ResetValue();
	}
	/**
	 * 重置表单
	 */
	private void ResetValue(){
		this.BookTypeNameTxt.setText("");
		this.BookTypeDescTxt.setText("");
	}
}
