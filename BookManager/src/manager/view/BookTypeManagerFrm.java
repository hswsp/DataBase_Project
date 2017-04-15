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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import manager.dao.BookTypeDao;
import manager.entity.BookType;
import manager.util.DbUtil;
import manager.util.ShowConfirmDialog;
import manager.util.StringUtil;
import manager.util.showMessageFrame;

public class BookTypeManagerFrm extends JFrame {

	private JPanel contentPane;
	private JTable BookTypeTable;
	private JTextArea BookTypeDescTxt ;
	private DbUtil dbUtil=new DbUtil();
	private BookTypeDao bookTypeDao=new BookTypeDao();
	private JTextField Search_BookTypeTxt;
	private JTextField IDTxt;
	private JTextField bookTypeNameTxt;
	//设置跟随分辨率变化窗口
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = kit.getScreenSize();
		private int screenHeight = (int) screenSize.getHeight();
	    private int screenWidth = (int) screenSize.getWidth();
	    private double enlargement_x=screenWidth/1920;
	    private double enlargement_y=screenHeight/1080;

	    private int windowWidth ; //获得当前窗口宽
	    private int windowHeight; //获得当前窗口高
	    private volatile static int ConfrmMassage=-1;//确认信息框返回值，volatile用于线程同步
	   

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeManagerFrm frame = new BookTypeManagerFrm();
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
	public BookTypeManagerFrm() {
		setTitle("\u56FE\u4E66\u7C7B\u522B\u7EF4\u62A4");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setBounds(100, 100, 680, 522);
		setBounds(100,100,(int)( 1600*enlargement_x), (int)(1400*enlargement_y));//设置初始位置（无所谓，后面重置），大小		
		windowWidth = this.getWidth(); //获得窗口宽
		windowHeight = this.getHeight(); //获得窗口高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane SearchScroll = new JScrollPane();
			
		JLabel BookTypeLabel = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0");
		BookTypeLabel.setFont(new Font("宋体", Font.PLAIN, 35));
		
		Search_BookTypeTxt = new JTextField();
		Search_BookTypeTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		Search_BookTypeTxt.setColumns(10);
		
		JButton SearchButton = new JButton("\u641C\u7D22");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeSearchActionPerformed(e);
			}
		});
		SearchButton.setIcon(new ImageIcon(BookTypeManagerFrm.class.getResource("/manager/image/search.png")));
		SearchButton.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, 
				TitledBorder.TOP,new Font("宋体", Font.PLAIN, 35), new Color(0, 0, 0)));//第二个参数为标题，倒数第二个参数为修改标题字体
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(166)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(SearchScroll, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1226, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1226, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(BookTypeLabel)
							.addGap(50)
							.addComponent(Search_BookTypeTxt, GroupLayout.PREFERRED_SIZE, 758, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
							.addComponent(SearchButton)))
					.addGap(192))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(166)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(BookTypeLabel)
						.addComponent(Search_BookTypeTxt, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(SearchButton))
					.addGap(139)
					.addComponent(SearchScroll, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
					.addGap(97)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		
		JLabel label = new JLabel("\u7F16\u53F7");
		label.setFont(new Font("宋体", Font.PLAIN, 35));
		
		IDTxt = new JTextField();
		IDTxt.setEditable(false);
		IDTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		IDTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0");
		label_1.setFont(new Font("宋体", Font.PLAIN, 35));
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setFont(new Font("宋体", Font.PLAIN, 35));
		bookTypeNameTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("\u63CF\u8FF0");
		label_2.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton ModifyButton = new JButton("\u4FEE\u6539");
		ModifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeUpdateActionEvent(e);
			}

			
		});
		ModifyButton.setIcon(new ImageIcon(BookTypeManagerFrm.class.getResource("/manager/image/modify.png")));
		ModifyButton.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton DeleteButton = new JButton("\u5220\u9664");
		DeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeDeleteActionEvent(e);
			}
		});
		DeleteButton.setIcon(new ImageIcon(BookTypeManagerFrm.class.getResource("/manager/image/delete.png")));
		DeleteButton.setFont(new Font("宋体", Font.PLAIN, 35));
		
		BookTypeDescTxt = new JTextArea();		    
		JScrollPane DescScroll = new JScrollPane( BookTypeDescTxt);//加入滚动条		
		//DescScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);//隐藏垂直滚动条
		DescScroll.setViewportView(BookTypeDescTxt);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(73)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_2)
							.addGap(28)
							.addComponent(DescScroll, GroupLayout.PREFERRED_SIZE, 924, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(IDTxt, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
							.addGap(104)
							.addComponent(label_1)
							.addGap(99)
							.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(143, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(108)
					.addComponent(ModifyButton)
					.addPreferredGap(ComponentPlacement.RELATED, 807, Short.MAX_VALUE)
					.addComponent(DeleteButton)
					.addGap(77))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(68)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(IDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(68)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(DescScroll, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addPreferredGap(ComponentPlacement.UNRELATED, 43, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(ModifyButton)
						.addComponent(DeleteButton))
					.addGap(18))
		);
		
	    BookTypeDescTxt.setLineWrap(true);
	    BookTypeDescTxt.setFont(new Font("宋体", Font.PLAIN, 35));	    
	    // 设置文本域边框
	    BookTypeDescTxt.setBorder(new LineBorder(new Color(127, 157, 185)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		BookTypeTable = new JTable();
		BookTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTypeTableMousePressed(e);				
			}
		});
		BookTypeTable.setFont(new Font("宋体", Font.PLAIN, 35));
		BookTypeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u7C7B\u522B\u540D\u79F0", "\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0"
			})
			{
				boolean[] columnEditables = new boolean[] {//不可修改
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			}
		);
		BookTypeTable.getColumnModel().getColumn(0).setPreferredWidth(95);
		BookTypeTable.getColumnModel().getColumn(1).setPreferredWidth(225);
		BookTypeTable.getColumnModel().getColumn(2).setPreferredWidth(228);
		SearchScroll.add(BookTypeTable);
		SearchScroll.setViewportView(BookTypeTable);
		contentPane.setLayout(gl_contentPane);
		//修改表头大小和字体大小
		BookTypeTable.getTableHeader().setPreferredSize(new Dimension((int)(1*enlargement_x),(int)(40*enlargement_y)));
		BookTypeTable.getTableHeader().setFont(new Font("宋体", Font.PLAIN, (int)(35*enlargement_y)));
		BookTypeTable.setRowHeight((int)(40*enlargement_y));
		this.fillTable(new BookType());
	}
	
	/**
	 * 表格行点击事件处理
	 * @param e
	 */
	private void bookTypeTableMousePressed(MouseEvent evt) {
		int row=BookTypeTable.getSelectedRow();//选中行
		IDTxt.setText((String)BookTypeTable.getValueAt(row, 0));
		bookTypeNameTxt.setText((String)BookTypeTable.getValueAt(row, 1));
		BookTypeDescTxt.setText((String)BookTypeTable.getValueAt(row, 2));
	}
     /**
      * 图书类别修改事件处理
      * @param e
      */
	private void bookTypeUpdateActionEvent(ActionEvent evt) {
		// TODO Auto-generated method stub
		String id=IDTxt.getText();
		String bookTypeName=bookTypeNameTxt.getText();
		String bookTypeDesc=BookTypeDescTxt.getText();
		if(StringUtil.isEmpty(id)){
			showMessageFrame note=new showMessageFrame(null,"请选择要修改的记录",showMessageFrame.NOTE);
			return;
		}
		if(StringUtil.isEmpty(bookTypeName)){
			showMessageFrame note=new showMessageFrame(null,"图书类别名称不能为空",showMessageFrame.NOTE);
			return;
		}
		BookType bookType=new BookType(Integer.parseInt(id),bookTypeName,bookTypeDesc);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int modifyNum=bookTypeDao.Update(con, bookType);//影响的记录条数
			if(modifyNum==1){
				showMessageFrame note=new showMessageFrame(null,"修改成功",showMessageFrame.NORMAL);
				this.ResetValue();//清空表单
				this.fillTable(new BookType());//修改后重新刷新
			}else
			{
				showMessageFrame note=new showMessageFrame(null,"修改失败",showMessageFrame.NOTE);
			}
		}catch(Exception e){
			e.printStackTrace();
			showMessageFrame note=new showMessageFrame(null,"修改失败",showMessageFrame.NOTE);
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
	 * 图书类别删除事件处理
	 * @param e
	 */
	private void bookTypeDeleteActionEvent(ActionEvent evt) {
		String id=IDTxt.getText();
		if(StringUtil.isEmpty(id)){
			showMessageFrame note=new showMessageFrame(null,"请选择要删除的记录",showMessageFrame.NOTE);
			return;
		}
		
		ShowConfirmDialog confrm=new ShowConfirmDialog(null,"提示","确定要删除该记录吗？");
		ConfrmMassage=confrm.getResult();	
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
					
					ConfrmMassage=confrm.getResult();	
					
					if(ConfrmMassage==0){//n=0表示已经选了
						Connection con=null;
						try{
							con=dbUtil.getCon();
							int deleteNum=bookTypeDao.Delete(con, id);
							if(deleteNum==1){
								showMessageFrame note=new showMessageFrame(null,"删除成功",showMessageFrame.NOTE);
								ResetValue();
								fillTable(new BookType());
							}else{
								showMessageFrame note=new showMessageFrame(null,"删除失败",showMessageFrame.NOTE);
							}
						}catch(Exception e)
						{
							e.printStackTrace();
							showMessageFrame note=new showMessageFrame(null,"删除失败",showMessageFrame.NOTE);
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
	 * 图书类别搜索事件处理
	 * @param evt
	 */
	private void bookTypeSearchActionPerformed(ActionEvent evt) {
		String s_bookTypeName=this.Search_BookTypeTxt.getText();
		BookType bookType=new BookType();
		bookType.setBookTypeName(s_bookTypeName);
		this.fillTable(bookType);
	}
	
	/**
	 * 初始化表格
	 * @param bookType
	 */
	private  void fillTable(BookType bookType)
	{
		DefaultTableModel dtm=(DefaultTableModel) BookTypeTable.getModel();
		dtm.setRowCount(0); // 设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=bookTypeDao.list(con, bookType);
			while(rs.next()){//遍历
				Vector v=new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookTypeName"));
				v.add(rs.getString("bookTypeDesc"));
				dtm.addRow(v);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{//不管什么情况都执行，关闭连接
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 重置表单
	 */
	private  void ResetValue()
	{
		this.IDTxt.setText("");
		this.bookTypeNameTxt.setText("");
		this.BookTypeDescTxt.setText("");
	}
}
