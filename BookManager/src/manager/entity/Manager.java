package manager.entity;

public class Manager {
	private String id; // ���
	private String userName; // �û���
	private String password; // ����
	
	public Manager() {
		super();
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
	public Manager(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

}
