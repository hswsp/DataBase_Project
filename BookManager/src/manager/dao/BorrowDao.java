package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import manager.entity.Book;
import manager.entity.Borrow;
import manager.entity.HisBorrow;
import manager.entity.Manager;
import manager.entity.User;
import manager.util.DateUtil;
import manager.util.StringUtil;

public class BorrowDao {
	/**
	 * �����¼����
	 * @param con
	 * @param borrow
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,Borrow borrow)throws Exception//����Ӱ�켸����¼
	{
		String sql="insert into t_borrow values(?,?,?,?,null,0,null)";
		PreparedStatement pstmt=con.prepareStatement(sql);//�������󣬵õ�SQL���
		pstmt.setString(1, borrow.getUserId());//���ݲ���
		pstmt.setInt(2, borrow.getBookID());
		pstmt.setDate(3, borrow.getBorTime());
		pstmt.setDate(4,DateUtil.DateAdd(borrow.getBorTime(), 60));
		//pstmt.setDate(5,borrow.getBookTypeId());
		return pstmt.executeUpdate();//����ִ�н��
	}

	/**
	 * ��ͼ�в�ѯ�ѽ���δ��ͼ��
	 * @param con
	 * @param borrow
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con,Borrow borrow)throws Exception
	{
		StringBuffer sb=new StringBuffer("select * from unreturned ");
		if (borrow.getID()!=-1)
		{
			sb.append(" and ID="+Integer.toString(borrow.getID()));
		}
		if(StringUtil.isNotEmpty(borrow.getUserId()))
		{
			sb.append(" and UserID=?");//+borrow.getUserId()			
		}
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));	
		if(StringUtil.isNotEmpty(borrow.getUserId()))
		{
			pstmt.setString(1, borrow.getUserId());//���ݲ���		
		}
		return pstmt.executeQuery();//ִ��
		
	}
	
	/**
	 * ����borrowID��ѯ
	 * @param con
	 * @param borrow
	 * @return
	 * @throws Exception
	 */
	public Borrow Search_ID(Connection con,Borrow borrow)throws Exception
	{
		Borrow currentborrow=null;
		StringBuffer sb=new StringBuffer("select * from t_borrow where ID=?");
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setInt(1, borrow.getID());//���ݲ���
		ResultSet prebo=pstmt.executeQuery();//ִ��
		if(prebo.next()){//����鵽�ˣ���ʵ����
			currentborrow=new Borrow();
			currentborrow.setUserId(prebo.getString("userID"));
			currentborrow.setBookID(prebo.getInt("bookId"));
			currentborrow.setBorTime(prebo.getDate("borTime"));
			currentborrow.setDueTime(prebo.getDate("dueTime"));
			currentborrow.setID(prebo.getInt("ID"));
			currentborrow.setReturnTime(prebo.getDate("returnTime"));
			currentborrow.setIsReturn(prebo.getByte("Isreturn"));
		}
		return currentborrow;
	}
	
	/**
	 * �����¼��޸�borrow��
	 * @param con
	 * @param borrow
	 * @return
	 * @throws Exception
	 */
	public int updateborrow(Connection con,Borrow borrow)throws Exception
	{
		String sql="update t_borrow set returnTime=?,IsReturn=? where ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setDate(1, borrow.getReturnTime());
		pstmt.setByte(2, borrow.getIsReturn());//ע��Tinyintȡ�������ǣ�getObject()�õ�boolean��
		pstmt.setInt(3, borrow.getID());
		return pstmt.executeUpdate();
	} 
	/**
	 * �����û�Ǯ��
	 * @param con
	 * @param user
	 * ��Ҫ��ǰ���ʱ��
	 */
	public void balanceaccount(Connection con,User user,Borrow borrow)
	{
		/**********************
		 * private void change(StringBuilder str) {
             str.append("sds");// ��Ӱ�쵽����ı���str_outer
             str = new StringBuilder("der");// ����Ӱ�쵽����ı���str_outer
                 }
		 * ********************************/		
		float reduce=DateUtil.balanceaccount(borrow.getDueTime(), borrow.getReturnTime());
		user.balanceRedu(reduce);
	}
	/**
	 * �����¼��޸�user��
	 * @param con
	 * @param user
	 * @param borrow
	 * @return
	 * @throws Exception
	 */
	public int updateuser(Connection con,User user,Borrow borrow)throws Exception
	{
		balanceaccount(con,user,borrow);//ʣ��Ǯ��
		String sql="update t_user set borrowNUmRem=?,balance=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, user.getBorrowNumRem()+1);		
		pstmt.setFloat(2,user.getBalance());//ע��Tinyintȡ�������ǣ�getObject()�õ�boolean��
		pstmt.setString(3, user.getId());
		return pstmt.executeUpdate();
	} 
	/**
	 * �����¼��޸�book��
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int updatebook(Connection con,Book book)throws Exception
	{
		String sql="update t_book set number=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, book.getBookNum()+1);		
		pstmt.setInt(2, book.getId());
		return pstmt.executeUpdate();
	} 
	
	/**
	 * ��ʷ���Ĳ�ѯ
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ResultSet hisBorrowSearch(Connection con,User user)throws Exception{
		HisBorrow hisborrow=null;
		StringBuffer sb=new StringBuffer("select * from t_book b,t_borrow bt where b.id=bt.bookId");
		//���ű�������ѯ����bookTypeID���ܲ�ѯ
		if(StringUtil.isNotEmpty(user.getId()))
		{
			sb.append(" and bt.userID=?");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setString(1, user.getId());
//		ResultSet rs=pstmt.executeQuery();//ִ��
//		if(rs.next()){//����鵽�ˣ���ʵ����
//			hisborrow=new HisBorrow();
//			hisborrow.setAuthor(rs.getString("b.author"));
//			hisborrow.setBookName(rs.getString("b.bookName"));
//			hisborrow.setPublisher(rs.getString("b.publisher"));
//			hisborrow.setBorTime(rs.getDate("bt.borTime"));
//			hisborrow.setReturnTime(rs.getDate("bt.returnTime"));
//		}
		return pstmt.executeQuery();//ִ��
	}
	/**
	 * ���ڻ���״ͼ
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ResultSet HisBorrowDistri(Connection con,User user)throws Exception
	{
		HisBorrow hisborrow=null;
		StringBuffer sb=new StringBuffer("select * from t_book b,t_borrow bb,t_booktype bt"
				+ " where b.id=bb.bookId and b.bookTypeId=bt.id");
		//���ű�������ѯ����bookTypeID���ܲ�ѯ
		if(StringUtil.isNotEmpty(user.getId()))
		{
			sb.append(" and bb.userID=?");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setString(1, user.getId());
		return pstmt.executeQuery();//ִ��
	}
	
	/**
	 * ���ڻ�����ͼ
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ResultSet HisBorrowTrend(Connection con,User user)throws Exception
	{
		StringBuffer sb=new StringBuffer("select * from t_borrow");
		
		if(StringUtil.isNotEmpty(user.getId()))
		{
			sb.append(" and userID=?");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		pstmt.setString(1, user.getId());
		return pstmt.executeQuery();//ִ��
	}
}