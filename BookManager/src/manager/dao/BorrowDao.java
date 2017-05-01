package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import manager.entity.Borrow;
import manager.util.DateUtil;

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
		String sql="insert into t_borrow values(?,?,?,?,null)";
		PreparedStatement pstmt=con.prepareStatement(sql);//创建对象，得到SQL语句
		pstmt.setString(1, borrow.getUserId());//传递参数
		pstmt.setInt(2, borrow.getBookID());
		pstmt.setDate(3, borrow.getBorTime());
		pstmt.setDate(4,DateUtil.DateAdd(borrow.getBorTime(), 60));
		//pstmt.setDate(5,borrow.getBookTypeId());
		return pstmt.executeUpdate();//返回执行结果
	}

}
