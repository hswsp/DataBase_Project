package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import manager.entity.BookRecommend;
import manager.util.StringUtil;

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
		String sql="insert into t_recommand values(null,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);//创建对象，得到SQL语句
		pstmt.setString(1, Recom.getUserID());//传递参数
		pstmt.setString(2, Recom.getBookName());
		pstmt.setString(3, Recom.getBookDesc());
		pstmt.setInt(4,Recom.getBookTypeID());
		pstmt.setString(5,Recom.getRecomReason());
		return pstmt.executeUpdate();//返回执行结果
	}
/**
 * 推荐信息查询
 * @param con
 * @param Recom
 * @return
 * @throws Exception
 */
	public ResultSet list(Connection con,BookRecommend Recom)throws Exception
	{
		StringBuffer sb=new StringBuffer("select * from t_recommand b,t_bookType bt where b.bookTypeId=bt.id");
		//两张表关联查询，有bookTypeID才能查询
		if(StringUtil.isNotEmpty(Recom.getBookName())){
			sb.append(" and b.bookName like '%"+Recom.getBookName()+"%'");
		}
		if(StringUtil.isNotEmpty(Recom.getUserID())){
			sb.append(" and b.author like '%"+Recom.getUserID()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();//执行
	}
	
	/**
	 * 推荐信息删除
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con,int id)throws Exception
	{
		String sql="delete from t_recommand where ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, id);
		return pstmt.executeUpdate();//修改
	}
}
