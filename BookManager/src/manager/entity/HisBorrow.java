package manager.entity;

public class HisBorrow {
private String userID;
private String bookName;
private String author;
private String publisher;
private java.sql.Date borTime;
private java.sql.Date returnTime;


public HisBorrow() {
	super();
	// TODO Auto-generated constructor stub
}

public HisBorrow(String userID) {
	super();
	this.userID = userID;
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
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getPublisher() {
	return publisher;
}
public void setPublisher(String publisher) {
	this.publisher = publisher;
}
public java.sql.Date getBorTime() {
	return borTime;
}
public void setBorTime(java.sql.Date borTime) {
	this.borTime = borTime;
}
public java.sql.Date getReturnTime() {
	return returnTime;
}
public void setReturnTime(java.sql.Date returnTime) {
	this.returnTime = returnTime;
}



}
