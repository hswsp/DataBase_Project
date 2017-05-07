package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import manager.entity.BookRecommend;
import manager.entity.Borrow;
import manager.util.DateUtil;

public class bookRecomDao {
	
	/**
	 * 推荐信息添加
	 * @param con
	 * @param Recom
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,BookRecommend Recom)throws Exception//返回影响几条记录
	{
		String sql="insert into t_recommand values(?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);//创建对象，得到SQL语句
		pstmt.setString(1, Recom.getUserID());//传递参数
		pstmt.setString(2, Recom.getBookName());
		pstmt.setString(3, Recom.getBookDesc());
		pstmt.setInt(4,Recom.getBookTypeID());
		pstmt.setString(5,Recom.getRecomReason());
		return pstmt.executeUpdate();//返回执行结果
	}

}
