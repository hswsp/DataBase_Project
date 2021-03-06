package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import manager.entity.Book;
import manager.entity.Borrow;
import manager.entity.HisBorrow;
import manager.entity.User;
import manager.util.DateUtil;
import manager.util.StringUtil;

public class BorrowDao {
	/**
	 * 借书记录添加
	 * @param con
	 * @param borrow
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,Borrow borrow)throws Exception//返回影响几条记录
	{
		String sql="insert into t_borrow values(?,?,?,?,null,0,null)";
		PreparedStatement pstmt=con.prepareStatement(sql);//创建对象，得到SQL语句
		pstmt.setString(1, borrow.getUserId());//传递参数
		pstmt.setInt(2, borrow.getBookID());
		pstmt.setDate(3, borrow.getBorTime());
		pstmt.setDate(4,DateUtil.DateAdd(borrow.getBorTime(), 60));
		//pstmt.setDate(5,borrow.getBookTypeId());
		return pstmt.executeUpdate();//返回执行结果
	}

	/**
	 * 视图中查询已借阅未还图书
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
			pstmt.setString(1, borrow.getUserId());//传递参数		
		}
		return pstmt.executeQuery();//执行
		
	}
	
	/**
	 * 根据borrowID查询
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
		pstmt.setInt(1, borrow.getID());//传递参数
		ResultSet prebo=pstmt.executeQuery();//执行
		if(prebo.next()){//如果查到了，则实例化
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
	 * 还书事件修改borrow表
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
		pstmt.setByte(2, borrow.getIsReturn());//注意Tinyint取出数据是，getObject()得到boolean型
		pstmt.setInt(3, borrow.getID());
		return pstmt.executeUpdate();
	} 
	/**
	 * 计算用户钱数
	 * @param con
	 * @param user
	 * 需要提前算好时间
	 */
	public void balanceaccount(Connection con,User user,Borrow borrow)
	{
		/**********************
		 * private void change(StringBuilder str) {
             str.append("sds");// 会影响到外面的变量str_outer
             str = new StringBuilder("der");// 不会影响到外面的变量str_outer
                 }
		 * ********************************/		
		float reduce=DateUtil.balanceaccount(borrow.getDueTime(), borrow.getReturnTime());
		user.balanceRedu(reduce);
	}
	/**
	 * 还书事件修改user表
	 * @param con
	 * @param user
	 * @param borrow
	 * @return
	 * @throws Exception
	 */
	public int updateuser(Connection con,User user,Borrow borrow)throws Exception
	{
		balanceaccount(con,user,borrow);//剩余钱数
		String sql="update t_user set borrowNUmRem=?,balance=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, user.getBorrowNumRem()+1);		
		pstmt.setFloat(2,user.getBalance());//注意Tinyint取出数据是，getObject()得到boolean型
		pstmt.setString(3, user.getId());
		return pstmt.executeUpdate();
	} 
	/**
	 * 还书事件修改book表
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
	 * 历史借阅查询
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ResultSet hisBorrowSearch(Connection con,User user)throws Exception{
		HisBorrow hisborrow=null;
		StringBuffer sb=new StringBuffer("select * from t_book b,t_borrow bt where b.id=bt.bookId");
		//两张表关联查询，有bookTypeID才能查询
		if(StringUtil.isNotEmpty(user.getId()))
		{
			sb.append(" and bt.userID=?");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setString(1, user.getId());
//		ResultSet rs=pstmt.executeQuery();//执行
//		if(rs.next()){//如果查到了，则实例化
//			hisborrow=new HisBorrow();
//			hisborrow.setAuthor(rs.getString("b.author"));
//			hisborrow.setBookName(rs.getString("b.bookName"));
//			hisborrow.setPublisher(rs.getString("b.publisher"));
//			hisborrow.setBorTime(rs.getDate("bt.borTime"));
//			hisborrow.setReturnTime(rs.getDate("bt.returnTime"));
//		}
		return pstmt.executeQuery();//执行
	}
	/**
	 * 用于画饼状图
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
		//两张表关联查询，有bookTypeID才能查询
		if(StringUtil.isNotEmpty(user.getId()))
		{
			sb.append(" and bb.userID=?");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setString(1, user.getId());
		return pstmt.executeQuery();//执行
	}
	
	/**
	 * 统计用于画折线图
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
		return pstmt.executeQuery();//执行
	}
	
	/**
	 * 指定图书ID下是否有借阅
	 * @param con
	 * @param bookTypeId
	 * @return
	 * @throws Exception
	 */
	public boolean existborrowByBookId(Connection con,int bookId)throws Exception
	{
		String sql="select * from unreturned ur,t_book b where b.bookName=ur.BookName and b.id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, bookId);//填写第一个问号
		ResultSet rs=pstmt.executeQuery();
		return rs.next();//没有记录就是false
	}
	
	/**
	 * 总体借阅统计
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ResultSet TotalHisBorrowDistri(Connection con)throws Exception
	{
		HisBorrow hisborrow=null;
		StringBuffer sb=new StringBuffer("select * from t_book b,t_borrow bb,t_booktype bt"
				+ " where b.id=bb.bookId and b.bookTypeId=bt.id");
		//两张表关联查询，有bookTypeID才能查询
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();//执行
	}
}
