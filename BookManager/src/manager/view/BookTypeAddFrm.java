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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import manager.dao.BookTypeDao;
import manager.entity.BookType;
import manager.util.DbUtil;
import manager.util.StringUtil;
import manager.util.showMessageFrame;
import javax.swing.JTextPane;
import java.awt.Color;

public class BookTypeAddFrm extends JFrame {

	private JPanel contentPane;
	private JTextField BookTypeNameTxt;
	JTextArea BookTypeDescTxt = new JTextArea();
//	DocColorTest BookTypeDescTxt=new DocColorTest();

	//���ø���ֱ��ʱ仯����
	Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
	private int screenHeight = (int) screenSize.getHeight();
    private int screenWidth = (int) screenSize.getWidth();
    private double enlargement_x=screenWidth/1920;
    private double enlargement_y=screenHeight/1080;

    private int windowWidth ; //��ô��ڿ�
    private int windowHeight; //��ô��ڸ�
    
    private DbUtil dbUtil=new DbUtil();
    private BookTypeDao bookTypeDao=new BookTypeDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setBounds(100, 100, 892, 619);
		setBounds(100,100,(int)( 1400*enlargement_x), (int)(900*enlargement_y));//���ó�ʼλ�ã�����ν���������ã�����С		
		windowWidth = this.getWidth(); //��ô��ڿ�
		windowHeight = this.getHeight(); //��ô��ڸ�
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//���ô��ھ�����ʾ
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel BookName = new JLabel("\u56FE\u4E66\u7C7B\u522B\u6DFB\u52A0");
		BookName.setFont(new Font("����", Font.PLAIN, 35));
		
		JLabel BookDesc = new JLabel("\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0");
		BookDesc.setFont(new Font("����", Font.PLAIN, 35));
		
		BookTypeNameTxt = new JTextField();
		BookTypeNameTxt.setFont(new Font("����", Font.PLAIN, 35));
		BookTypeNameTxt.setColumns(10);
		
		
		
		JButton Add = new JButton("\u6DFB\u52A0");
		Add.setIcon(new ImageIcon(BookTypeAddFrm.class.getResource("/manager/image/add.png")));
		Add.setFont(new Font("����", Font.PLAIN, 35));
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookTypeAddActionPerformed(evt);
			}			
		});
		
		JButton reset = new JButton("\u91CD\u7F6E");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		reset.setIcon(new ImageIcon(BookTypeAddFrm.class.getResource("/manager/image/reset.png")));
		reset.setFont(new Font("����", Font.PLAIN, 35));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(236)
							.addComponent(Add)
							.addPreferredGap(ComponentPlacement.RELATED, 754, Short.MAX_VALUE)
							.addComponent(reset)
							.addGap(76))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(103)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(BookName)
								.addComponent(BookDesc))
							.addGap(104)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(BookTypeDescTxt, GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
								.addComponent(BookTypeNameTxt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE))))
					.addGap(72))
		);
		BookTypeDescTxt.setLineWrap(true);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(106)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(BookName)
						.addComponent(BookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(132)
							.addComponent(BookDesc))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(76)
							.addComponent(BookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
					.addGap(202)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Add)
						.addComponent(reset))
					.addGap(169))
		);
		BookTypeDescTxt.setFont(new Font("����", Font.PLAIN, 35));
		contentPane.setLayout(gl_contentPane);
		
		// �����ı���߿�
				BookTypeDescTxt.setBorder(new LineBorder(new Color(127, 157, 185)));
	}
	
	/**
	 * ͼ����������¼�����
	 * @param evt
	 */
	private void bookTypeAddActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String bookTypeName =this.BookTypeNameTxt.getText();
		String bookTypeDesc=this.BookTypeDescTxt.getText();
		if(StringUtil.isEmpty(bookTypeName)){
			//JOptionPane.showMessageDialog(null, "ͼ��������Ʋ���Ϊ�գ�");
			showMessageFrame EmptyNote=new showMessageFrame(null,"ͼ��������Ʋ���Ϊ�գ�",showMessageFrame.NOTE);
			return;
		}
		BookType bookType=new BookType(bookTypeName,bookTypeDesc);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int n=bookTypeDao.add(con, bookType);
			if(n==1){
				//JOptionPane.showMessageDialog(null, "ͼ��������ӳɹ���");
				showMessageFrame SucNote=new showMessageFrame(null,"ͼ��������ӳɹ���",showMessageFrame.NOTE);
				ResetValue();
			}
			else
			{
				//JOptionPane.showMessageDialog(null, "ͼ���������ʧ�ܣ�");
				showMessageFrame FailNote=new showMessageFrame(null,"ͼ���������ʧ�ܣ�",showMessageFrame.NOTE);
			}
		}catch(Exception e){
			e.printStackTrace();
			//JOptionPane.showMessageDialog(null, "ͼ���������ʧ�ܣ�");
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
	 * �����¼�����
	 * @param e
	 */
	private void resetValueActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.ResetValue();
	}
	/**
	 * ���ñ���
	 */
	private void ResetValue(){
		this.BookTypeNameTxt.setText("");
		this.BookTypeDescTxt.setText("");
	}
}