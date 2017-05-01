package manager.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.CustSkinPanel;
import ui.ViewDragHelper;
import ui.ViewToolkit;
import values.ValuesMgr;

import com.sun.awt.AWTUtilities;

/**
 * 系统的主窗体
 */
public class MainView extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 160140850862313179L;

	private Container conPanel;

	private JPanel headerPanel, headerTmpPanel1, headerTmpPanel2, footerPanel, leftPanel, rightPanel;

	private JLabel sysMenuLabel, sysMinLabel, sysMaxLabel, sysCloseLabel;

	private JLabel topMenuSyLabel, topMenuXtglLabel, topMenuYwclLabel, topMenuXtbzLabel;

	private JLabel leftMenuYhglLabel, leftMenuJsglLabel, leftMenuCsglLabel;

	private Icon sysMenuIcon1, sysMenuIcon2, sysMinIcon1, sysMinIcon2, sysMaxIcon1, sysMaxIcon2, sysCloseIcon1, sysCloseIcon2;

	private Icon topMenuSyIcon1, topMenuSyIcon2, topMenuXtglIcon1, topMenuXtglIcon2, topMenuYwclIcon1, topMenuYwclIcon2, topMenuXtbzIcon1,
			topMenuXtbzIcon2;

	private Icon leftMenuYhglIcon1, leftMenuYhglIcon2, leftMenuJsglIcon1, leftMenuJsglIcon2, leftMenuCsglIcon1, leftMenuCsglIcon2;

	private static final String ICON_FLAG_01 = "01_", ICON_FLAG_02 = "02_";

	/**
	 * 构造器
	 */
	public MainView() {
		init();
		buildElems();
		fitTogether();
	}
	/**
	 * 系统入口类
	 */
	
		/**
		 * 入口方法
		 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView Mainframe = new MainView();
					Mainframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * 初始化自己
	 */
	private void init() {
		this.setTitle(ValuesMgr.SYS_VALUES.MAIN_VIEW_TITLE);
		this.setSize(ValuesMgr.SYS_VALUES.MAIN_VIEW_WIDTH, ValuesMgr.SYS_VALUES.MAIN_VIEW_HEIGHT);
		this.setIconImage(ViewToolkit.createImageIcon(ValuesMgr.SYS_VALUES.SYSTEM_ICON_IMG).getImage());
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置圆角边框
		Shape shape = new RoundRectangle2D.Double(0, 0, ValuesMgr.SYS_VALUES.MAIN_VIEW_WIDTH, ValuesMgr.SYS_VALUES.MAIN_VIEW_HEIGHT, 9D, 9D);
		AWTUtilities.setWindowShape(this, shape);
		// 设置系统字体等
		// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		// UIManager.getLookAndFeelDefaults().put("defaultFont", new
		// Font("微软雅黑", Font.PLAIN, 12));
	}

	/**
	 * 构建窗口元素
	 */
	public void buildElems() {
		conPanel = this.getContentPane();
		conPanel.setLayout(new BorderLayout(0, 0));

		headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(ValuesMgr.SYS_VALUES.MAIN_VIEW_HEADER_WIDTH, ValuesMgr.SYS_VALUES.MAIN_VIEW_HEADER_HEIGHT));
		headerPanel.setLayout(new BorderLayout());
		new ViewDragHelper(this, headerPanel);

		headerTmpPanel1 = new CustSkinPanel(ValuesMgr.SYS_VALUES.MAIN_VIEW_HEADER_IMG_01, ValuesMgr.SYS_VALUES.MAIN_VIEW_HEADER_WIDTH_01,
				ValuesMgr.SYS_VALUES.MAIN_VIEW_HEADER_HEIGHT_01);
		headerTmpPanel1.setPreferredSize(new Dimension(ValuesMgr.SYS_VALUES.MAIN_VIEW_HEADER_WIDTH_01,
				ValuesMgr.SYS_VALUES.MAIN_VIEW_HEADER_HEIGHT_01));
		headerTmpPanel1.setLayout(new FlowLayout(2, 0, 0));

		headerTmpPanel2 = new CustSkinPanel(ValuesMgr.SYS_VALUES.MAIN_VIEW_HEADER_IMG_02, ValuesMgr.SYS_VALUES.MAIN_VIEW_HEADER_WIDTH_02,
				ValuesMgr.SYS_VALUES.MAIN_VIEW_HEADER_HEIGHT_02);
		headerTmpPanel2.setPreferredSize(new Dimension(ValuesMgr.SYS_VALUES.MAIN_VIEW_HEADER_WIDTH_02,
				ValuesMgr.SYS_VALUES.MAIN_VIEW_HEADER_HEIGHT_02));
		headerTmpPanel2.setLayout(new FlowLayout(0, 0, 0));

		footerPanel = new CustSkinPanel(ValuesMgr.SYS_VALUES.MAIN_VIEW_FOOTER_IMG, ValuesMgr.SYS_VALUES.MAIN_VIEW_FOOTER_WIDTH,
				ValuesMgr.SYS_VALUES.MAIN_VIEW_FOOTER_HEIGHT);
		footerPanel.setPreferredSize(new Dimension(ValuesMgr.SYS_VALUES.MAIN_VIEW_FOOTER_WIDTH, ValuesMgr.SYS_VALUES.MAIN_VIEW_FOOTER_HEIGHT));
		footerPanel.setLayout(new FlowLayout(2, 1, 0));
		new ViewDragHelper(this, footerPanel);

		leftPanel = new CustSkinPanel(ValuesMgr.SYS_VALUES.MAIN_VIEW_LEFT_IMG, 169, 550);
		leftPanel.setLayout(new FlowLayout(0, 0, 0));
		leftPanel.setPreferredSize(new Dimension(169, 550));

		rightPanel = new CustSkinPanel(null, 0, 0);

		sysMenuIcon1 = ViewToolkit.createImageIcon(ICON_FLAG_01 + ValuesMgr.SYS_VALUES.MAIN_VIEW_SYS_MENU_IMG);
		sysMenuIcon2 = ViewToolkit.createImageIcon(ICON_FLAG_02 + ValuesMgr.SYS_VALUES.MAIN_VIEW_SYS_MENU_IMG);

		sysMinIcon1 = ViewToolkit.createImageIcon(ICON_FLAG_01 + ValuesMgr.SYS_VALUES.MAIN_VIEW_MIN_BTN_IMG);
		sysMinIcon2 = ViewToolkit.createImageIcon(ICON_FLAG_02 + ValuesMgr.SYS_VALUES.MAIN_VIEW_MIN_BTN_IMG);

		sysMaxIcon1 = ViewToolkit.createImageIcon(ICON_FLAG_01 + ValuesMgr.SYS_VALUES.MAIN_VIEW_MAX_BTN_IMG);
		sysMaxIcon2 = ViewToolkit.createImageIcon(ICON_FLAG_02 + ValuesMgr.SYS_VALUES.MAIN_VIEW_MAX_BTN_IMG);

		sysCloseIcon1 = ViewToolkit.createImageIcon(ICON_FLAG_01 + ValuesMgr.SYS_VALUES.MAIN_VIEW_CLOSE_BTN_IMG);
		sysCloseIcon2 = ViewToolkit.createImageIcon(ICON_FLAG_02 + ValuesMgr.SYS_VALUES.MAIN_VIEW_CLOSE_BTN_IMG);

		topMenuSyIcon1 = ViewToolkit.createImageIcon(ICON_FLAG_01 + ValuesMgr.SYS_VALUES.MAIN_VIEW_TOP_MENU_IMG_SY);
		topMenuSyIcon2 = ViewToolkit.createImageIcon(ICON_FLAG_02 + ValuesMgr.SYS_VALUES.MAIN_VIEW_TOP_MENU_IMG_SY);

		topMenuXtglIcon1 = ViewToolkit.createImageIcon(ICON_FLAG_01 + ValuesMgr.SYS_VALUES.MAIN_VIEW_TOP_MENU_IMG_XTGL);
		topMenuXtglIcon2 = ViewToolkit.createImageIcon(ICON_FLAG_02 + ValuesMgr.SYS_VALUES.MAIN_VIEW_TOP_MENU_IMG_XTGL);

		topMenuYwclIcon1 = ViewToolkit.createImageIcon(ICON_FLAG_01 + ValuesMgr.SYS_VALUES.MAIN_VIEW_TOP_MENU_IMG_YWCL);
		topMenuYwclIcon2 = ViewToolkit.createImageIcon(ICON_FLAG_02 + ValuesMgr.SYS_VALUES.MAIN_VIEW_TOP_MENU_IMG_YWCL);

		topMenuXtbzIcon1 = ViewToolkit.createImageIcon(ICON_FLAG_01 + ValuesMgr.SYS_VALUES.MAIN_VIEW_TOP_MENU_IMG_XTBZ);
		topMenuXtbzIcon2 = ViewToolkit.createImageIcon(ICON_FLAG_02 + ValuesMgr.SYS_VALUES.MAIN_VIEW_TOP_MENU_IMG_XTBZ);

		leftMenuYhglIcon1 = ViewToolkit.createImageIcon(ICON_FLAG_01 + ValuesMgr.SYS_VALUES.MAIN_VIEW_LEFT_MENU_IMG_YHGL);
		leftMenuYhglIcon2 = ViewToolkit.createImageIcon(ICON_FLAG_02 + ValuesMgr.SYS_VALUES.MAIN_VIEW_LEFT_MENU_IMG_YHGL);

		leftMenuJsglIcon1 = ViewToolkit.createImageIcon(ICON_FLAG_01 + ValuesMgr.SYS_VALUES.MAIN_VIEW_LEFT_MENU_IMG_JSGL);
		leftMenuJsglIcon2 = ViewToolkit.createImageIcon(ICON_FLAG_02 + ValuesMgr.SYS_VALUES.MAIN_VIEW_LEFT_MENU_IMG_JSGL);

		leftMenuCsglIcon1 = ViewToolkit.createImageIcon(ICON_FLAG_01 + ValuesMgr.SYS_VALUES.MAIN_VIEW_LEFT_MENU_IMG_CSGL);
		leftMenuCsglIcon2 = ViewToolkit.createImageIcon(ICON_FLAG_02 + ValuesMgr.SYS_VALUES.MAIN_VIEW_LEFT_MENU_IMG_CSGL);

		sysMenuLabel = new JLabel(sysMenuIcon1);
		sysMenuLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sysMenuLabel.setToolTipText(ValuesMgr.SYS_VALUES.MAIN_VIEW_SYS_MENU_TIP);
		sysMenuLabel.addMouseListener(this);

		sysMinLabel = new JLabel(sysMinIcon1);
		sysMinLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sysMinLabel.setToolTipText(ValuesMgr.SYS_VALUES.MAIN_VIEW_MIN_BTN_TIP);
		sysMinLabel.addMouseListener(this);

		sysMaxLabel = new JLabel(sysMaxIcon1);
		sysMaxLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sysMaxLabel.setToolTipText(ValuesMgr.SYS_VALUES.MAIN_VIEW_MAX_BTN_TIP);
		sysMaxLabel.addMouseListener(this);

		sysCloseLabel = new JLabel(sysCloseIcon1);
		sysCloseLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sysCloseLabel.setToolTipText(ValuesMgr.SYS_VALUES.MAIN_VIEW_CLOSE_BTN_TIP);
		sysCloseLabel.addMouseListener(this);

		topMenuSyLabel = new JLabel(topMenuSyIcon1);
		topMenuSyLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		topMenuSyLabel.addMouseListener(this);

		topMenuXtglLabel = new JLabel(topMenuXtglIcon1);
		topMenuXtglLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		topMenuXtglLabel.addMouseListener(this);

		topMenuYwclLabel = new JLabel(topMenuYwclIcon1);
		topMenuYwclLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		topMenuYwclLabel.addMouseListener(this);

		topMenuXtbzLabel = new JLabel(topMenuXtbzIcon1);
		topMenuXtbzLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		topMenuXtbzLabel.addMouseListener(this);

		leftMenuYhglLabel = new JLabel(leftMenuYhglIcon1);
		leftMenuYhglLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		leftMenuYhglLabel.addMouseListener(this);

		leftMenuJsglLabel = new JLabel(leftMenuJsglIcon1);
		leftMenuJsglLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		leftMenuJsglLabel.addMouseListener(this);

		leftMenuCsglLabel = new JLabel(leftMenuCsglIcon1);
		leftMenuCsglLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		leftMenuCsglLabel.addMouseListener(this);
	}

	/**
	 * 组装拼接界面
	 */
	public void fitTogether() {
		headerTmpPanel1.add(sysMenuLabel);
		headerTmpPanel1.add(sysMinLabel);
		headerTmpPanel1.add(sysMaxLabel);
		headerTmpPanel1.add(sysCloseLabel);
		headerTmpPanel1.add(Box.createHorizontalStrut(7));

		headerTmpPanel2.add(Box.createHorizontalStrut(185));
		headerTmpPanel2.add(topMenuSyLabel);
		headerTmpPanel2.add(topMenuXtglLabel);
		headerTmpPanel2.add(topMenuYwclLabel);
		headerTmpPanel2.add(topMenuXtbzLabel);

		headerPanel.add(BorderLayout.NORTH, headerTmpPanel1);
		headerPanel.add(BorderLayout.CENTER, headerTmpPanel2);

		leftPanel.add(leftMenuYhglLabel);
		leftPanel.add(leftMenuJsglLabel);
		leftPanel.add(leftMenuCsglLabel);

		conPanel.add(BorderLayout.NORTH, headerPanel);
		conPanel.add(BorderLayout.WEST, leftPanel);
		conPanel.add(BorderLayout.CENTER, rightPanel);
		// conPanel.add(BorderLayout.CENTER, new
		// JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel));
		conPanel.add(BorderLayout.SOUTH, footerPanel);
	}

	// -------------------------------事件处理
	/**
	 * 处理顶部菜单选择
	 */
	private void dealMouseClickEffect(JLabel selLabel) {
		if (selLabel.equals(topMenuSyLabel)) {
			topMenuSyLabel.setIcon(topMenuSyIcon2);
			topMenuXtglLabel.setIcon(topMenuXtglIcon1);
			topMenuYwclLabel.setIcon(topMenuYwclIcon1);
			topMenuXtbzLabel.setIcon(topMenuXtbzIcon1);
		} else if (selLabel.equals(topMenuXtglLabel)) {
			topMenuXtglLabel.setIcon(topMenuXtglIcon2);
			topMenuSyLabel.setIcon(topMenuSyIcon1);
			topMenuYwclLabel.setIcon(topMenuYwclIcon1);
			topMenuXtbzLabel.setIcon(topMenuXtbzIcon1);
		} else if (selLabel.equals(topMenuYwclLabel)) {
			topMenuYwclLabel.setIcon(topMenuYwclIcon2);
			topMenuXtglLabel.setIcon(topMenuXtglIcon1);
			topMenuSyLabel.setIcon(topMenuSyIcon1);
			topMenuXtbzLabel.setIcon(topMenuXtbzIcon1);
		} else if (selLabel.equals(topMenuXtbzLabel)) {
			topMenuXtbzLabel.setIcon(topMenuXtbzIcon2);
			topMenuYwclLabel.setIcon(topMenuYwclIcon1);
			topMenuXtglLabel.setIcon(topMenuXtglIcon1);
			topMenuSyLabel.setIcon(topMenuSyIcon1);
		}
	}

	/**
	 * 设置系统菜单选择效果
	 */
	private void dealMouseOverAndOutEffect(JLabel selLabel, int type) {
		if (selLabel.equals(sysMenuLabel)) {
			if (0 == type) {
				sysMenuLabel.setIcon(sysMenuIcon2);
			} else if (1 == type) {
				sysMenuLabel.setIcon(sysMenuIcon1);
			}
		} else if (selLabel.equals(sysMaxLabel)) {
			if (0 == type) {
				sysMaxLabel.setIcon(sysMaxIcon2);
			} else if (1 == type) {
				sysMaxLabel.setIcon(sysMaxIcon1);
			}
		} else if (selLabel.equals(sysMinLabel)) {
			if (0 == type) {
				sysMinLabel.setIcon(sysMinIcon2);
			} else if (1 == type) {
				sysMinLabel.setIcon(sysMinIcon1);
			} else if (2 == type) {
				this.setState(JFrame.ICONIFIED);
			}
		} else if (selLabel.equals(sysCloseLabel)) {
			if (0 == type) {
				sysCloseLabel.setIcon(sysCloseIcon2);
			} else if (1 == type) {
				sysCloseLabel.setIcon(sysCloseIcon1);
			} else if (2 == type) {
				System.exit(0);
			}
		} else if (selLabel.equals(topMenuSyLabel)) {
			if (0 == type) {
				topMenuSyLabel.setIcon(topMenuSyIcon2);
			} else if (1 == type) {
				topMenuSyLabel.setIcon(topMenuSyIcon1);
			}
		} else if (selLabel.equals(topMenuXtglLabel)) {
			if (0 == type) {
				topMenuXtglLabel.setIcon(topMenuXtglIcon2);
			} else if (1 == type) {
				topMenuXtglLabel.setIcon(topMenuXtglIcon1);
			}
		} else if (selLabel.equals(topMenuYwclLabel)) {
			if (0 == type) {
				topMenuYwclLabel.setIcon(topMenuYwclIcon2);
			} else if (1 == type) {
				topMenuYwclLabel.setIcon(topMenuYwclIcon1);
			}
		} else if (selLabel.equals(topMenuXtbzLabel)) {
			if (0 == type) {
				topMenuXtbzLabel.setIcon(topMenuXtbzIcon2);
			} else if (1 == type) {
				topMenuXtbzLabel.setIcon(topMenuXtbzIcon1);
			}
		} else if (selLabel.equals(leftMenuYhglLabel)) {
			if (0 == type) {
				leftMenuYhglLabel.setIcon(leftMenuYhglIcon2);
			} else if (1 == type) {
				leftMenuYhglLabel.setIcon(leftMenuYhglIcon1);
			}
		} else if (selLabel.equals(leftMenuJsglLabel)) {
			if (0 == type) {
				leftMenuJsglLabel.setIcon(leftMenuJsglIcon2);
			} else if (1 == type) {
				leftMenuJsglLabel.setIcon(leftMenuJsglIcon1);
			}
		} else if (selLabel.equals(leftMenuCsglLabel)) {
			if (0 == type) {
				leftMenuCsglLabel.setIcon(leftMenuCsglIcon2);
			} else if (1 == type) {
				leftMenuCsglLabel.setIcon(leftMenuCsglIcon1);
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JLabel) {
			dealMouseOverAndOutEffect((JLabel) e.getSource(), 2);
		} else {
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() instanceof JLabel) {
			dealMouseOverAndOutEffect((JLabel) e.getSource(), 0);
		} else {
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() instanceof JLabel) {
			dealMouseOverAndOutEffect((JLabel) e.getSource(), 1);
		} else {
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}