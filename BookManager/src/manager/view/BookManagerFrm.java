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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import manager.dao.BookDao;
import manager.dao.BookTypeDao;
import manager.entity.Book;
import manager.entity.BookType;
import manager.util.DbUtil;
import manager.util.ShowConfirmDialog;
import manager.util.StringUtil;
import manager.util.showMessageFrame;

public class BookManagerFrm extends JFrame {

	//���ø���ֱ��ʱ仯����
	Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
	private int screenHeight = (int) screenSize.getHeight();
    private int screenWidth = (int) screenSize.getWidth();
    private double enlargement_x=screenWidth/1920;
    private double enlargement_y=screenHeight/1080;

    private int windowWidth ; //��ô��ڿ�
    private int windowHeight; //��ô��ڸ�
    
	private JPanel contentPane;
	private JTable BookTable;
	private JTextField S_BookNameTxt;
	private JTextField S_AuthorTxt;
	private JComboBox S_BookTypeJcb; 
	private JComboBox bookTypeJcb;
	
	private DbUtil dbUtil=new DbUtil();
	private BookTypeDao bookTypeDao=new BookTypeDao();
	private BookDao bookDao=new BookDao();
	private JTextField idTxt;
	private JTextField bookNameTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField priceTxt;
	private JTextField authorTxt;
	private JTextArea BookDescTxt;
	private JRadioButton manJrb;
	private JRadioButton femaleJrb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100,100,(int)( 1900*enlargement_x), (int)(1600*enlargement_y));//���ó�ʼλ�ã�����ν���������ã�����С		
		windowWidth = this.getWidth(); //��ô��ڿ�
		windowHeight = this.getHeight(); //��ô��ڸ�
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//���ô��ھ�����ʾ
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane BookTableJsp = new JScrollPane();
		
		JPanel BookSearchJP = new JPanel();
		BookSearchJP.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("����", Font.PLAIN, 30), null));
		
		JPanel BookListJp = new JPanel();
		BookListJp.setBorder(new TitledBorder(null, "\u56FE\u4E66\u5217\u8868\u64CD\u4F5C", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("����", Font.PLAIN, 30), null));
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
		label_4.setFont(new Font("����", Font.PLAIN, 35));
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setFont(new Font("����", Font.PLAIN, 35));
		idTxt.setColumns(10);
		
		JLabel label_5 = new JLabel("\u56FE\u4E66\u540D\u79F0");
		label_5.setFont(new Font("����", Font.PLAIN, 35));
		
		bookNameTxt = new JTextField();
		bookNameTxt.setFont(new Font("����", Font.PLAIN, 35));
		bookNameTxt.setColumns(10);
		
		JLabel label_6 = new JLabel("\u4F5C\u8005\u6027\u522B");
		label_6.setFont(new Font("����", Font.PLAIN, 35));
		
		manJrb = new JRadioButton("\u7537");
		buttonGroup.add(manJrb);
		manJrb.setSelected(true);
		manJrb.setFont(new Font("����", Font.PLAIN, 35));
		
		femaleJrb = new JRadioButton("\u5973");
		buttonGroup.add(femaleJrb);
		femaleJrb.setFont(new Font("����", Font.PLAIN, 35));
		
		JLabel label_7 = new JLabel("\u4EF7\u683C");
		label_7.setFont(new Font("����", Font.PLAIN, 35));
		
		priceTxt = new JTextField();
		priceTxt.setFont(new Font("����", Font.PLAIN, 35));
		priceTxt.setColumns(10);
		
		JLabel label_8 = new JLabel("\u56FE\u4E66\u4F5C\u8005");
		label_8.setFont(new Font("����", Font.PLAIN, 35));
		
		authorTxt = new JTextField();
		authorTxt.setFont(new Font("����", Font.PLAIN, 35));
		authorTxt.setColumns(10);
		
		JLabel L_BookTypeLabel = new JLabel("\u56FE\u4E66\u7C7B\u522B");
		L_BookTypeLabel.setFont(new Font("����", Font.PLAIN, 35));
		
		bookTypeJcb = new JComboBox();
		bookTypeJcb.setFont(new Font("����", Font.PLAIN, 35));
		
		JLabel label_10 = new JLabel("\u56FE\u4E66\u63CF\u8FF0");
		label_10.setFont(new Font("����", Font.PLAIN, 35));
		
		JButton ModifyButton = new JButton("\u4FEE\u6539");
		ModifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				bookUpdateActionPerformed(evt);
			}
		});
		ModifyButton.setIcon(new ImageIcon(BookManagerFrm.class.getResource("/manager/image/modify.png")));
		ModifyButton.setFont(new Font("����", Font.PLAIN, 35));
		
		JButton DeleteButton = new JButton("\u5220\u9664");
		DeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookDeleteActionPerformed(e);
			}
		});
		DeleteButton.setIcon(new ImageIcon(BookManagerFrm.class.getResource("/manager/image/delete.png")));
		DeleteButton.setFont(new Font("����", Font.PLAIN, 35));
		
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
		BookDescTxt.setFont(new Font("����", Font.PLAIN, 35));
		// �����ı���߿�
	    BookDescTxt.setBorder(new LineBorder(new Color(127, 157, 185)));
	    BookDescJsp.add(BookDescTxt);
		BookDescJsp.setViewportView(BookDescTxt);
		BookListJp.setLayout(gl_BookListJp);
		
		JLabel label = new JLabel("\u56FE\u4E66\u540D\u79F0");
		label.setFont(new Font("����", Font.PLAIN, 35));
		
		S_BookNameTxt = new JTextField();
		S_BookNameTxt.setFont(new Font("����", Font.PLAIN, 35));
		S_BookNameTxt.setColumns(10);
		
		JLabel authorLabel = new JLabel("\u56FE\u4E66\u4F5C\u8005");
		authorLabel.setFont(new Font("����", Font.PLAIN, 35));
		
		S_AuthorTxt = new JTextField();
		S_AuthorTxt.setFont(new Font("����", Font.PLAIN, 35));
		S_AuthorTxt.setColumns(10);
		
		JLabel S_BookTypeLabel = new JLabel("\u56FE\u4E66\u7C7B\u522B");
		S_BookTypeLabel.setFont(new Font("����", Font.PLAIN, 35));
		
		S_BookTypeJcb = new JComboBox();
		S_BookTypeJcb.setFont(new Font("����", Font.PLAIN, 35));
		
		JLabel label_3 = new JLabel("");
		
		JButton S_BookNameJb = new JButton("\u67E5\u8BE2");
		S_BookNameJb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);
			}
		});
		S_BookNameJb.setIcon(new ImageIcon(BookManagerFrm.class.getResource("/manager/image/search.png")));
		S_BookNameJb.setFont(new Font("����", Font.PLAIN, 35));
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
		BookTable.setFont(new Font("����", Font.PLAIN, 35));
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
		//�޸ı�ͷ��С�������С
		BookTable.getTableHeader().setPreferredSize(new Dimension((int)(1*enlargement_x),(int)(40*enlargement_y)));
		BookTable.getTableHeader().setFont(new Font("����", Font.PLAIN, (int)(35*enlargement_y)));
		BookTable.setRowHeight((int)(40*enlargement_y));
		BookTableJsp.setViewportView(BookTable);
		contentPane.setLayout(gl_contentPane);
		
		this.fillBookType("search");//�淶һ����final static
		this.fillBookType("modify");
		this.fillTable(new Book());
	}
	
	/**
	 * ���ñ���
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
	 * ͼ���ѯ�¼�����
	 * @param e
	 */
	private void bookSearchActionPerformed(ActionEvent evt) 
	{
		String bookName=this.S_BookNameTxt.getText();
		String author=this.S_AuthorTxt.getText();
		BookType bookType=(BookType) this.S_BookTypeJcb.getSelectedItem();//����object��,ǿת
		int bookTypeId=bookType.getId();
		
		Book book=new Book(bookName,author,bookTypeId);
		this.fillTable(book);
	}
	
	/**
	 * ͼ���޸��¼�����
	 * @param evt
	 */
	private void bookUpdateActionPerformed(ActionEvent evt) {
		String id=this.idTxt.getText();
		if(StringUtil.isEmpty(id))
		{
			showMessageFrame note=new showMessageFrame(null,"��ѡ��Ҫ�޸ĵļ�¼",showMessageFrame.NOTE);
			return;
		}
		
		String bookName=this.bookNameTxt.getText();
		String author=this.authorTxt.getText();
		String price=this.priceTxt.getText();
		String bookDesc=this.BookDescTxt.getText();
		
		if(StringUtil.isEmpty(bookName)){
			showMessageFrame note=new showMessageFrame(null,"ͼ�����Ʋ���Ϊ�գ�",showMessageFrame.NOTE);
			return;
		}
		
		if(StringUtil.isEmpty(author)){
			showMessageFrame note=new showMessageFrame(null,"ͼ�����߲���Ϊ�գ�",showMessageFrame.NOTE);
			return;
		}
		
		if(StringUtil.isEmpty(price)){
			showMessageFrame note=new showMessageFrame(null,"ͼ��۸���Ϊ�գ�",showMessageFrame.NOTE);
			return;
		}
		
		String sex="";
		if(manJrb.isSelected()){
			sex="��";
		}else if(femaleJrb.isSelected()){
			sex="Ů";
		}
		
		BookType bookType=(BookType) bookTypeJcb.getSelectedItem();
		int bookTypeId=bookType.getId();
		
		Book book=new Book(Integer.parseInt(id),  bookName, author, sex, Float.parseFloat(price),  bookTypeId,  bookDesc);
		//Integer.parseInt(id):stringתint������Ҫ��װ��ȥ��ȷ��ɾ��
		
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int addNum=bookDao.update(con, book);
			if(addNum==1){
				showMessageFrame note=new showMessageFrame(null,"ͼ���޸ĳɹ���",showMessageFrame.NORMAL);
				ResetValue();
				this.fillTable(new Book());//���ñ�����ˢ��
			}else{				
				showMessageFrame note=new showMessageFrame(null,"ͼ���޸�ʧ�ܣ�",showMessageFrame.NOTE);
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ͼ���޸�ʧ�ܣ�");
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
	 * ͼ��ɾ���¼�����
	 * @param evt
	 */
	private void bookDeleteActionPerformed(ActionEvent evt) {
		String id=idTxt.getText();
		if(StringUtil.isEmpty(id))
		{
			showMessageFrame note=new showMessageFrame(null,"��ѡ��Ҫɾ���ļ�¼��",showMessageFrame.NOTE);
			return;
		}
		ShowConfirmDialog confrm=new ShowConfirmDialog(null,"��ʾ","ȷ��Ҫɾ���ü�¼��");
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
				int deleteNum=bookDao.delete(con, id);
				if(deleteNum==1)
				{
					showMessageFrame note=new showMessageFrame(null,"ɾ���ɹ���",showMessageFrame.NORMAL);
					ResetValue();
					fillTable(new Book());
				}else
				{
					showMessageFrame note=new showMessageFrame(null,"ɾ��ʧ�ܣ�",showMessageFrame.NOTE);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				showMessageFrame note=new showMessageFrame(null,"ɾ��ʧ�ܣ�",showMessageFrame.NOTE);
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
	 * ��ʼ��������
	 * @param type ���������� 
	 */
	private void fillBookType(String type)
	{//������һ��������
		Connection con=null;
		BookType bookType=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=bookTypeDao.list(con, new BookType());
			
			if("search".equals(type)){//�����search���������һ����ѡ�������Ͳ�����
				bookType=new BookType();
				bookType.setBookTypeName("��ѡ��...");
				bookType.setId(-1);//��ѡ���IDΪ-1
				this.S_BookTypeJcb.addItem(bookType);//additem ��������Ͽ���б���������һ������������ҿ���ָ��������������
			}
			while(rs.next()){
				//resultset��next����������ÿ����һ�Σ��α����һ����
				//һ��ʼ�Ǵ��ڵ�һ��ǰ,beforeFirst,��һ��ʹ��next()�ͽ�ָ��ָ�򷵻ؽ�����ĵ�һ�С�
				//��resultset�ζ������һ�У��ٵ���next���������᷵��false�������α�Ҳ�������һ�еĺ��档
				bookType=new BookType();
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				bookType.setId(rs.getInt("id"));//getInt(int columnIndex) �� Java ��������е�������ʽ��ȡ�� ResultSet ����ǰ����ָ���е�ֵ��
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
	 * ��ʼ����������
	 * @param book
	 */
	private void fillTable(Book book)
	{
		DefaultTableModel dtm=(DefaultTableModel) BookTable.getModel();//��ȡģ��
		dtm.setRowCount(0); // ���ó�0��
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=bookDao.list(con, book);//��ȡ���м�¼
			while(rs.next()){
				Vector v=new Vector();//��˳��д
				v.add(rs.getString("id"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("author"));
				v.add(rs.getString("sex"));
				v.add(rs.getFloat("price"));
				v.add(rs.getString("bookDesc"));
				v.add(rs.getString("bookTypeName"));
				dtm.addRow(v);//��һ��
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
	 * �����е���¼�����
	 * @param met
	 */
	private void bookTableMousePressed(MouseEvent met) 
	{
		int row=this.BookTable.getSelectedRow();//�����к�
		this.idTxt.setText((String)BookTable.getValueAt(row, 0));
		//��ȡrow�У�0�����ݡ�ע��fillʱ����string���͡�ע��˳��
		this.bookNameTxt.setText((String)BookTable.getValueAt(row, 1));
		this.authorTxt.setText((String)BookTable.getValueAt(row, 2));
		String sex=(String)BookTable.getValueAt(row, 3);
		if("��".equals(sex)){
			this.manJrb.setSelected(true);
		}else if("Ů".equals(sex)){
			this.femaleJrb.setSelected(true);
		}
		this.priceTxt.setText((Float)BookTable.getValueAt(row, 4)+"");
		this.BookDescTxt.setText((String)BookTable.getValueAt(row, 5));
		String bookTypeName=(String)this.BookTable.getValueAt(row, 6);
		int n=this.bookTypeJcb.getItemCount();//�������ж�����
		for(int i=0;i<n;i++)//����������
		{
			BookType item=(BookType)this.bookTypeJcb.getItemAt(i);//getItemAt()��ʾ��ȡ�ڼ������ݡ�ֻ�ܻ�ȡ��������
			if(item.getBookTypeName().equals(bookTypeName))
			{
				this.bookTypeJcb.setSelectedIndex(i);
			}
		}
	}
}