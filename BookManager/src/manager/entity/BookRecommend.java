package manager.entity;

public class BookRecommend {
	private String userID;
	private String bookName;
	private String bookDesc;
	private String bookType;
	private String recomReason;
	private int bookTypeID;
	public int getBookTypeID() {
		return bookTypeID;
	}
	public void setBookTypeID(int bookTypeID) {
		this.bookTypeID = bookTypeID;
	}
	public BookRecommend() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BookRecommend(String userID, String bookName, String bookDesc, String recomReason, int bookTypeID) {
		super();
		this.userID = userID;
		this.bookName = bookName;
		this.bookDesc = bookDesc;
		this.recomReason = recomReason;
		this.bookTypeID = bookTypeID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookDesc() {
		return bookDesc;
	}
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getRecomReason() {
		return recomReason;
	}
	public void setRecomReason(String recomReason) {
		this.recomReason = recomReason;
	}

}
