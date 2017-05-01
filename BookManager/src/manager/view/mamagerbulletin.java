package manager.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import manager.util.PictureUtil;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class mamagerbulletin extends JFrame {

	private JPanel TutorJp;
	//设置跟随分辨率变化窗口
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = kit.getScreenSize();
		private int screenHeight = (int) screenSize.getHeight();
	    private int screenWidth = (int) screenSize.getWidth();
	    private double enlargement_x=screenWidth/1920;
	    private double enlargement_y=screenHeight/1080;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mamagerbulletin frame = new mamagerbulletin();
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
	public mamagerbulletin() {
		setTitle("\u6D4B\u8BD5");
		TutorJp = new JPanel();
		setContentPane(TutorJp);
//		GroupLayout gl_TutorJp = new GroupLayout(TutorJp);
//		JLayeredPane SearchJlp = new JLayeredPane();		
//		TutorJp.setLayout(gl_TutorJp);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 786, 1207);
//		//创立三个内窗口
//		JInternalFrame SearchJIF = new JInternalFrame("\u56FE\u4E66\u67E5\u8BE2\u4E0E\u501F\u9605");
//		SearchJIF.getContentPane().setFont(new Font("宋体", Font.PLAIN, 35));
//		BasicInternalFrameUI ui = (BasicInternalFrameUI)SearchJIF.getUI();
//		ui.setNorthPane(null);		
//		SearchJIF.setBorder(BorderFactory.createEmptyBorder());
//		SearchJIF.setVisible(true);		
//		JInternalFrame ID_JIF = new JInternalFrame("\u4E2A\u4EBA\u4FE1\u606F\u8BBE\u7F6E");
//		//去边框
//		BasicInternalFrameUI ui_1 = (BasicInternalFrameUI)ID_JIF.getUI();
//		ui_1.setNorthPane(null);
//		ID_JIF.setBorder(BorderFactory.createEmptyBorder());		
//		ID_JIF.getContentPane().setFont(new Font("宋体", Font.PLAIN, 35));
//		ID_JIF.setVisible(true);		
//		JInternalFrame RecomJIF = new JInternalFrame("\u63A8\u8350\u4FE1\u606F");
//		RecomJIF.getContentPane().setFont(new Font("宋体", Font.PLAIN, 35));
//		//去边框
//		BasicInternalFrameUI RecomUI = (BasicInternalFrameUI)RecomJIF.getUI();
//		RecomUI.setNorthPane(null);
//		RecomJIF.setBorder(BorderFactory.createEmptyBorder());
//		JPanel RecommendJP = new JPanel();
//		RecommendJP.setBorder(new TitledBorder(null, "\u63A8\u8350\u4FE1\u606F", TitledBorder.LEADING, 
//				TitledBorder.TOP, new Font("宋体", Font.PLAIN, 35), null));
//		//边框布局
//		GroupLayout groupLayout_2 = new GroupLayout(RecomJIF.getContentPane());
//		groupLayout_2.setHorizontalGroup(
//			groupLayout_2.createParallelGroup(Alignment.LEADING)
//				.addComponent(RecommendJP, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
//		);
//		groupLayout_2.setVerticalGroup(
//			groupLayout_2.createParallelGroup(Alignment.LEADING)
//				.addGroup(Alignment.TRAILING, groupLayout_2.createSequentialGroup()
//					.addComponent(RecommendJP, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//		);
//		RecommendJP.setLayout(null);
//		
//		
//		RecomJIF.getContentPane().setLayout(groupLayout_2);
//		RecomJIF.setVisible(true);
//		
//		//GroupLayout gl_TutorJp = new GroupLayout(TutorJp);
//		gl_TutorJp.setHorizontalGroup(
//				gl_TutorJp.createParallelGroup(Alignment.LEADING)
//					.addComponent(SearchJIF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
//					.addComponent(ID_JIF, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
//					.addComponent(RecomJIF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
//			);
//			gl_TutorJp.setVerticalGroup(
//				gl_TutorJp.createParallelGroup(Alignment.LEADING)
//					.addGroup(gl_TutorJp.createSequentialGroup()
//						.addComponent(SearchJIF, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE)
//						.addPreferredGap(ComponentPlacement.RELATED)
//						.addComponent(ID_JIF, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)
//						.addPreferredGap(ComponentPlacement.UNRELATED)
//						.addComponent(RecomJIF, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
//			);
//		
//			
//		JPanel panel = new JPanel();
//		panel.setBorder(new TitledBorder(null, "\u4E2A\u4EBA\u4FE1\u606F\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, 
//				new Font("宋体", Font.PLAIN, 35), null));
//		GroupLayout groupLayout_1 = new GroupLayout(ID_JIF.getContentPane());
//		groupLayout_1.setHorizontalGroup(
//			groupLayout_1.createParallelGroup(Alignment.LEADING)
//				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
//		);
//		groupLayout_1.setVerticalGroup(
//			groupLayout_1.createParallelGroup(Alignment.LEADING)
//				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
//		);
//		
//		//标签
//		JLabel IDJLB = new JLabel("",JLabel.CENTER);		
//		IDJLB.setFont(new Font("宋体", Font.PLAIN, 35));
//		JLabel RecomJLb = new JLabel("",JLabel.CENTER);
//		RecomJLb.setBounds(254, 41, 256, 73);
//		RecomJLb.setFont(new Font("宋体", Font.PLAIN, 35));
//		RecommendJP.add(RecomJLb);
//		
//		JLabel Daily_Op = new JLabel("",JLabel.CENTER);
//		Daily_Op.setBounds(247, 43, 264, 91);
//		Daily_Op.setFont(new Font("宋体", Font.PLAIN, 35));
//		
//		JButton CertificateJB = new JButton("\u8BC1\u4EF6\u4FE1\u606F");
//		CertificateJB.setFont(new Font("宋体", Font.PLAIN, 35));
//		
//		JButton PreBorrowInfo = new JButton("\u5F53\u524D\u501F\u9605");
//		PreBorrowInfo.setFont(new Font("宋体", Font.PLAIN, 35));
//		PreBorrowInfo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//			}
//		});
//		
//		JButton HistoryBorrow = new JButton("\u501F\u9605\u5386\u53F2");
//		HistoryBorrow.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		HistoryBorrow.setFont(new Font("宋体", Font.PLAIN, 35));
//		GroupLayout gl_panel = new GroupLayout(panel);
//		gl_panel.setHorizontalGroup(
//			gl_panel.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_panel.createSequentialGroup()
//					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
//						.addGroup(gl_panel.createSequentialGroup()
//							.addGap(188)
//							.addComponent(IDJLB, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
//						.addGroup(gl_panel.createSequentialGroup()
//							.addGap(255)
//							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
//								.addComponent(HistoryBorrow, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//								.addComponent(PreBorrowInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//								.addComponent(CertificateJB, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))))
//					.addContainerGap(224, Short.MAX_VALUE))
//		);
//		gl_panel.setVerticalGroup(
//			gl_panel.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_panel.createSequentialGroup()
//					.addComponent(IDJLB, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.UNRELATED)
//					.addComponent(CertificateJB, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addComponent(PreBorrowInfo, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.UNRELATED)
//					.addComponent(HistoryBorrow, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap(41, Short.MAX_VALUE))
//		);
//		panel.setLayout(gl_panel);
//		ID_JIF.getContentPane().setLayout(groupLayout_1);
//		
//		//图片准备
//		int width = 300,height = 100; //这是图片和JLable的宽度和高度   
//		ImageIcon icon = new ImageIcon("src/res/dayly_op.png");  
//		ImageIcon ID_icon = new ImageIcon("src/res/ID_Info.png");  
//		ImageIcon Recom_icon = new ImageIcon("src/res/Recommend.png");
//		//改变图片大小          
//		icon.setImage(icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));//80和100为大小 可以自由设置       
//		ID_icon.setImage(ID_icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
//		Recom_icon.setImage(Recom_icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
//		
//		//添加图片
//		Daily_Op.setIcon(icon);
//		IDJLB.setIcon(ID_icon);
//		RecomJLb.setIcon(Recom_icon);
//		
//		JPanel dayly_panel = new JPanel();
//		dayly_panel.setBorder(new TitledBorder(null, "\u56FE\u4E66\u67E5\u8BE2\u4E0E\u501F\u9605", 
//				TitledBorder.LEADING, TitledBorder.TOP, new Font("宋体", Font.PLAIN, 35), null));
//		
//		GroupLayout groupLayout = new GroupLayout(SearchJIF.getContentPane());
//		groupLayout.setHorizontalGroup(
//			groupLayout.createParallelGroup(Alignment.LEADING)
//				.addComponent(dayly_panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
//		);
//		groupLayout.setVerticalGroup(
//			groupLayout.createParallelGroup(Alignment.LEADING)
//				.addComponent(dayly_panel, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
//		);					
//		
//		JButton SearchButton = new JButton("\u56FE\u4E66\u67E5\u8BE2");
//		SearchButton.setBounds(273, 149, 222, 59);
//		SearchButton.setFont(new Font("宋体", Font.PLAIN, 35));
//		
//		
//		JButton BorrowButton = new JButton("\u56FE\u4E66\u501F\u9605");
//		BorrowButton.setBounds(273, 221, 222, 64);
//		BorrowButton.setFont(new Font("宋体", Font.PLAIN, 35));
//		dayly_panel.setLayout(null);		
//		
//		
//		JButton ReturnButurn = new JButton("\u56FE\u4E66\u5F52\u8FD8");
//		ReturnButurn.setBounds(273, 295, 222, 59);
//		ReturnButurn.setFont(new Font("宋体", Font.PLAIN, 35));
//		dayly_panel.add(ReturnButurn);
//		dayly_panel.add(Daily_Op);
//		dayly_panel.add(SearchButton);
//		dayly_panel.add(BorrowButton);
//		
//		JButton RecomJB = new JButton("\u56FE\u4E66\u63A8\u8350");
//		RecomJB.setFont(new Font("宋体", Font.PLAIN, 35));
//		RecomJB.setBounds(278, 130, 207, 59);
//		RecommendJP.add(RecomJB);
//		SearchJIF.getContentPane().setLayout(groupLayout);
		
//		
		JInternalFrame SearchJIF = new JInternalFrame("\u56FE\u4E66\u67E5\u8BE2\u4E0E\u501F\u9605");
		SearchJIF.getContentPane().setFont(new Font("宋体", Font.PLAIN, 35));
		BasicInternalFrameUI ui = (BasicInternalFrameUI)SearchJIF.getUI();
		ui.setNorthPane(null);
		
		SearchJIF.setBorder(BorderFactory.createEmptyBorder());
		SearchJIF.setVisible(true);
		
		JInternalFrame ID_JIF = new JInternalFrame("\u4E2A\u4EBA\u4FE1\u606F\u8BBE\u7F6E");
		//去边框
		BasicInternalFrameUI ui_1 = (BasicInternalFrameUI)ID_JIF.getUI();
		ui_1.setNorthPane(null);
		ID_JIF.setBorder(BorderFactory.createEmptyBorder());
		
		ID_JIF.getContentPane().setFont(new Font("宋体", Font.PLAIN, 35));
		ID_JIF.setVisible(true);
		
		JInternalFrame RecomJIF = new JInternalFrame("\u63A8\u8350\u4FE1\u606F");
		RecomJIF.getContentPane().setFont(new Font("宋体", Font.PLAIN, 35));
		//去边框
		BasicInternalFrameUI RecomUI = (BasicInternalFrameUI)RecomJIF.getUI();
		RecomUI.setNorthPane(null);
		RecomJIF.setBorder(BorderFactory.createEmptyBorder());
		
		JPanel RecommendJP = new JPanel();
		RecommendJP.setBorder(new TitledBorder(null, "\u63A8\u8350\u4FE1\u606F", TitledBorder.LEADING, 
				TitledBorder.TOP, new Font("宋体", Font.PLAIN, 35), null));
		GroupLayout groupLayout_2 = new GroupLayout(RecomJIF.getContentPane());
		groupLayout_2.setHorizontalGroup(
			groupLayout_2.createParallelGroup(Alignment.LEADING)
				.addComponent(RecommendJP, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
		);
		groupLayout_2.setVerticalGroup(
			groupLayout_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout_2.createSequentialGroup()
					.addComponent(RecommendJP, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		RecommendJP.setLayout(null);
		
		JLabel RecomJLb = new JLabel("",JLabel.CENTER);
		RecomJLb.setBounds(254, 41, 256, 73);
		RecomJLb.setFont(new Font("宋体", Font.PLAIN, 35));
		RecommendJP.add(RecomJLb);
		RecomJIF.getContentPane().setLayout(groupLayout_2);
		RecomJIF.setVisible(true);
		
		JPanel ExitJPL = new JPanel();
		ExitJPL.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5B89\u5168\u9000\u51FA\u7CFB\u7EDF", 
				TitledBorder.LEADING, TitledBorder.TOP, new Font("宋体", Font.PLAIN, 35), Color.DARK_GRAY));
		GroupLayout gl_TutorJp = new GroupLayout(TutorJp);
		gl_TutorJp.setHorizontalGroup(
			gl_TutorJp.createParallelGroup(Alignment.LEADING)
				.addComponent(SearchJIF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
				.addComponent(ID_JIF, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
				.addComponent(RecomJIF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
				.addComponent(ExitJPL, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
		);
		gl_TutorJp.setVerticalGroup(
			gl_TutorJp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TutorJp.createSequentialGroup()
					.addComponent(SearchJIF, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ID_JIF, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(RecomJIF, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ExitJPL, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JButton button = new JButton("\u5B89\u5168\u9000\u51FA");
		button.setFont(new Font("宋体", Font.PLAIN, 35));
		GroupLayout gl_ExitJPL = new GroupLayout(ExitJPL);
		gl_ExitJPL.setHorizontalGroup(
			gl_ExitJPL.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ExitJPL.createSequentialGroup()
					.addGap(248)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(278, Short.MAX_VALUE))
		);
		gl_ExitJPL.setVerticalGroup(
			gl_ExitJPL.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ExitJPL.createSequentialGroup()
					.addGap(54)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(95, Short.MAX_VALUE))
		);
		ExitJPL.setLayout(gl_ExitJPL);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u4E2A\u4EBA\u4FE1\u606F\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, 
				new Font("宋体", Font.PLAIN, 35), null));
		GroupLayout groupLayout_1 = new GroupLayout(ID_JIF.getContentPane());
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
		);
		
		JLabel IDJLB = new JLabel("",JLabel.CENTER);
		
		IDJLB.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton CertificateJB = new JButton("\u8BC1\u4EF6\u4FE1\u606F");
		CertificateJB.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton PreBorrowInfo = new JButton("\u5F53\u524D\u501F\u9605");
		PreBorrowInfo.setFont(new Font("宋体", Font.PLAIN, 35));
		PreBorrowInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton HistoryBorrow = new JButton("\u501F\u9605\u5386\u53F2");
		HistoryBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		HistoryBorrow.setFont(new Font("宋体", Font.PLAIN, 35));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(188)
					.addComponent(IDJLB, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addGap(224))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(255)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(HistoryBorrow, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(PreBorrowInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(CertificateJB, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
					.addContainerGap(254, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(IDJLB, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(CertificateJB, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PreBorrowInfo, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(HistoryBorrow, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		ID_JIF.getContentPane().setLayout(groupLayout_1);
		//图片准备
		int width = 300,height = 100; //这是图片和JLable的宽度和高度   
		ImageIcon icon = new ImageIcon("src/res/dayly_op.png");  
		ImageIcon ID_icon = new ImageIcon("src/res/ID_Info.png");  
		ImageIcon Recom_icon = new ImageIcon("src/res/Recommend.png");
		//改变图片大小          
		icon.setImage(icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));//80和100为大小 可以自由设置       
		ID_icon.setImage(ID_icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
		Recom_icon.setImage(Recom_icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
		
		JPanel dayly_panel = new JPanel();
		dayly_panel.setBorder(new TitledBorder(null, "\u56FE\u4E66\u67E5\u8BE2\u4E0E\u501F\u9605", 
				TitledBorder.LEADING, TitledBorder.TOP, new Font("宋体", Font.PLAIN, 35), null));
		
		GroupLayout groupLayout = new GroupLayout(SearchJIF.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(dayly_panel, GroupLayout.PREFERRED_SIZE, 730, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(121, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(dayly_panel, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
		);
		
	
		JLabel Daily_Op = new JLabel("",JLabel.CENTER);
		Daily_Op.setFont(new Font("宋体", Font.PLAIN, 35));
		//添加图片
		Daily_Op.setIcon(icon);
		IDJLB.setIcon(ID_icon);
		RecomJLb.setIcon(Recom_icon);
		
		JButton RecomJB = new JButton("\u56FE\u4E66\u63A8\u8350");
		RecomJB.setFont(new Font("宋体", Font.PLAIN, 35));
		RecomJB.setBounds(278, 130, 207, 59);
		RecommendJP.add(RecomJB);
		JButton SearchButton = new JButton("\u56FE\u4E66\u67E5\u8BE2");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		SearchButton.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton BorrowButton = new JButton("\u56FE\u4E66\u501F\u9605");
		BorrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BorrowButton.setFont(new Font("宋体", Font.PLAIN, 35));
		
		JButton ReturnButurn = new JButton("\u56FE\u4E66\u5F52\u8FD8");
		ReturnButurn.setFont(new Font("宋体", Font.PLAIN, 35));
		GroupLayout gl_dayly_panel = new GroupLayout(dayly_panel);
		gl_dayly_panel.setHorizontalGroup(
			gl_dayly_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dayly_panel.createSequentialGroup()
					.addGap(245)
					.addGroup(gl_dayly_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_dayly_panel.createSequentialGroup()
							.addComponent(ReturnButurn)
							.addContainerGap())
						.addGroup(gl_dayly_panel.createSequentialGroup()
							.addGroup(gl_dayly_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(BorrowButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
								.addComponent(SearchButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(640))))
				.addGroup(gl_dayly_panel.createSequentialGroup()
					.addGap(170)
					.addComponent(Daily_Op, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(588, Short.MAX_VALUE))
		);
		gl_dayly_panel.setVerticalGroup(
			gl_dayly_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dayly_panel.createSequentialGroup()
					.addComponent(Daily_Op, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SearchButton, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(BorrowButton, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ReturnButurn, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(63, Short.MAX_VALUE))
		);
		dayly_panel.setLayout(gl_dayly_panel);
		//mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		SearchJIF.getContentPane().setLayout(groupLayout);
//		
//		//Rectangle clip=MenuItem_search.getClipBounds();
////		JMenuBar menuBar = new JMenuBar();
////		menuBar.setFont(new Font("宋体", Font.PLAIN, 35));
////		internalFrame.setJMenuBar(menuBar);
		TutorJp.setLayout(gl_TutorJp);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 1675);		
//		String imgPath = "D://Database//DataBase_Project//BookManager//src//manager//image//frame.jpg"; 
//		Dimension dimension = new Dimension(668, 157);  //图片大小
//		TutorJp.setPreferredSize(dimension);
//        final Image imageBg = Toolkit.getDefaultToolkit()  
//                .getImage(imgPath);  
//        ImageIcon imageIcon = new ImageIcon(imageBg.getScaledInstance  
//                (dimension.width, dimension.height, Image.SCALE_FAST));
//        final Point origin = new Point(10, 30);  
//        final Rectangle rectangle = new Rectangle(origin, dimension);

	}
}
