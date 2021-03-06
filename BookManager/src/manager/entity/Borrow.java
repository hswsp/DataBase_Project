package manager.entity;

import java.sql.Date;

/*
 * 租借信息实体
 */
public class Borrow {
private String userId;
private int bookID;
private String bookName;
private java.sql.Date borTime;
private java.sql.Date dueTime;
private java.sql.Date returnTime;
private int ID;//借书事件的编号
private byte IsReturn;


public Borrow() {
	super();
	// TODO Auto-generated constructor stub
	this.ID=-1;
}


public Borrow(String userId) {
	super();
	this.userId = userId;
	this.ID=-1;
}


public Borrow(int iD) {
	super();
	ID = iD;
}


public Borrow(String userId, int iD) {
	super();
	this.userId = userId;
	ID = iD;
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
public String getBookName() {
	return bookName;
}

public void setBookName(String bookName) {
	this.bookName = bookName;
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
public int getID() {
	return ID;
}


public void setID(int iD) {
	ID = iD;
}


public byte getIsReturn() {
	return IsReturn;
}


public void setIsReturn(byte isReturn) {
	IsReturn = isReturn;
}


}
