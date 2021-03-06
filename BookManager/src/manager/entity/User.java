package manager.entity;

/**
 * 用户实体
 * @author Administrator
 *
 */
public class User {

	private String id; // 编号
	private String userName; // 用户名
	private String password; // 密码
	private int borrowNumRem;//可借书数量
	private float balance;
	private byte IsLogin;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}	
	public User(String id) {
		super();
		this.id = id;
	}
	public User(String userID, String password) {
		super();
		this.id = userID;
		this.password = password;
	}
	public int getBorrowNumRem() {
		return borrowNumRem;
	}
	public void setBorrowNumRem(int borrowNumRem) {
		this.borrowNumRem = borrowNumRem;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void balanceRedu(float reduce)
	{
		this.balance-=reduce;
	}
	public byte getIsLogin() {
		return IsLogin;
	}
	public void setIsLogin(byte isLogin) {
		IsLogin = isLogin;
	}
	
}
