package manager.entity;

import java.sql.Date;

/*
 * �����Ϣʵ��
 */
public class Borrow {
private String userId;
private int bookID;
private java.sql.Date borTime;
private java.sql.Date dueTime;
private java.sql.Date returnTime;
public Borrow() {
	super();
	// TODO Auto-generated constructor stub
}


public Borrow(String userId, int bookID, Date borTime) {
	super();
	this.userId = userId;
	this.bookID = bookID;
	this.borTime = borTime;
}


public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public int getBookID() {
	return bookID;
}
public void setBookID(int bookID) {
	this.bookID = bookID;
}
public java.sql.Date getBorTime() {
	return borTime;
}
public void setBorTime(java.sql.Date borTime) {
	this.borTime = borTime;
}
public java.sql.Date getDueTime() {
	return dueTime;
}
public void setDueTime(java.sql.Date dueTime) {
	this.dueTime = dueTime;
}
public java.sql.Date getReturnTime() {
	return returnTime;
}
public void setReturnTime(java.sql.Date returnTime) {
	this.returnTime = returnTime;
}


}
