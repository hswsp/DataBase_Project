package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import manager.entity.BookType;
import manager.util.StringUtil;


/**
 * 图书类别Dao类
 * @author Administrator
 *
 */
public class BookTypeDao {

	/**
	 * 图书类别添加
	 * @param con
	 * @param bookType
	 * @return 操作的记录数
	 * @throws Exception
	 */
	public int add(Connection con,BookType bookType)throws Exception{
		String sql="insert into t_bookType values(null,?,?)";//预编译的方式，安全一点
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookTypeName());
		pstmt.setString(2, bookType.getBookTypeDesc());
		return pstmt.executeUpdate();//执行
	}
	/**
	 * 查询图书类别集合
	 * @param con
	 * @param bookType
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con,BookType bookType)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_bookType");
		if(StringUtil.isNotEmpty(bookType.getBookTypeName())){
			sb.append(" and bookTypeName like '%"+bookType.getBookTypeName()+"%'");//如果有多个条件（这里就一个），where没地方写，故而采用此算法
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));//转换成字符串，载把第一个and换成where
		return pstmt.executeQuery();
	}
}
