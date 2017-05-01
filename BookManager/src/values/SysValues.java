/**
 * 
 */
package values;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * 系统级常量信息类
 */
public final class SysValues {

	//设置跟随分辨率变化窗口
	Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
	private int screenHeight = (int) screenSize.getHeight();
    private int screenWidth = (int) screenSize.getWidth();
    private double enlargement_x=screenWidth/1980;
    private double enlargement_y=screenHeight/1020;
	// system
	public String RESOURCES_PATH = "/res/";// 系统资源文件存放的路径

	public String ABOUT_DIALOG_TITLE = "系统提示";

	public String SYSTEM_ICON_IMG = "sys_ico.kw";

	// system main
	public String MAIN_VIEW_TITLE = "酷喔音乐盒";// 主界面标题

	public int MAIN_VIEW_WIDTH = 885;// 主页面宽度

	public int MAIN_VIEW_HEIGHT = 640;// 主页面高度

	// public String MAIN_VIEW_HEADER_IMG = "sys_main_header.png";

	public int MAIN_VIEW_HEADER_WIDTH = (int)(885*enlargement_x);

	public int MAIN_VIEW_HEADER_HEIGHT = (int)(56*enlargement_y);

	public String MAIN_VIEW_HEADER_IMG_01 = "01_sys_main_header.kw";

	public int MAIN_VIEW_HEADER_WIDTH_01 = (int)(885*enlargement_x);

	public int MAIN_VIEW_HEADER_HEIGHT_01 = (int)(18*enlargement_y);

	public String MAIN_VIEW_HEADER_IMG_02 = "02_sys_main_header.kw";

	public int MAIN_VIEW_HEADER_WIDTH_02 = (int)(885*enlargement_x);

	public int MAIN_VIEW_HEADER_HEIGHT_02 = (int)(38*enlargement_y);

	public String MAIN_VIEW_FOOTER_IMG = "sys_main_footer.png";

	public int MAIN_VIEW_FOOTER_WIDTH = (int)(885*enlargement_x);

	public int MAIN_VIEW_FOOTER_HEIGHT = (int)(40*enlargement_y);

	public String MAIN_VIEW_LEFT_IMG = "sys_main_left.png";

	public String MAIN_VIEW_SYS_MENU_IMG = "sys_menu_btn.png";

	public String MAIN_VIEW_SYS_MENU_TIP = "打开系统菜单";

	public String MAIN_VIEW_MAX_BTN_IMG = "sys_max_btn.png";

	public String MAIN_VIEW_MAX_BTN_TIP = "最大化窗口";

	public String MAIN_VIEW_MIN_BTN_IMG = "sys_min_btn.png";

	public String MAIN_VIEW_MIN_BTN_TIP = "最小化窗口";

	public String MAIN_VIEW_CLOSE_BTN_IMG = "sys_close_btn.png";

	public String MAIN_VIEW_CLOSE_BTN_TIP = "关闭窗口";

	public String MAIN_VIEW_CSKIN_BTN_IMG = "sys_cskin_btn.png";

	public String MAIN_VIEW_TOP_MENU_IMG_SY = "sys_top_menu_sy.png";

	public String MAIN_VIEW_TOP_MENU_IMG_XTGL = "sys_top_menu_xtgl.png";

	public String MAIN_VIEW_TOP_MENU_IMG_YWCL = "sys_top_menu_ywcl.png";

	public String MAIN_VIEW_TOP_MENU_IMG_XTBZ = "sys_top_menu_xtbz.png";

	public String MAIN_VIEW_LEFT_MENU_IMG_YHGL = "sys_left_menu_yhgl.png";

	public String MAIN_VIEW_LEFT_MENU_IMG_JSGL = "sys_left_menu_jsgl.png";

	public String MAIN_VIEW_LEFT_MENU_IMG_CSGL = "sys_left_menu_csgl.png";

	// system login

	// system user

	// system role

	// system param
}
