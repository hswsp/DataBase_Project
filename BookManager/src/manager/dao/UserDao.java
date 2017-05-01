package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import manager.entity.Book;
import manager.entity.User;


/**
 * 用户Dao类
 * @author Administrator
 *
 */
public class UserDao {

	/**
	 * 登录验证
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con,User user)throws Exception{
		User resultUser=null;
		String sql="select * from t_user where id=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		//对问号设置
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){//如果查到了，则实例化
			resultUser=new User();
			resultUser.setId(rs.getString("id"));//getInt("id")
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setBorrowNumRem(rs.getInt("borrowNumRem"));
			resultUser.setBalance(rs.getFloat("balance"));
		}
		return resultUser;
	}
	/**
	 * 用户注册
	 * @param con
	 * @param Id
	 * @param Username
	 * @param Passworld
	 * @return
	 * @throws Exception
	 */
	public int Register(Connection con,String Id, String Username,String Passworld)throws Exception
	{
		String sql="insert into t_user values(?,?,?,8,0)";//要匹配数量！
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,Id);
		pstmt.setString(2, Username);
		pstmt.setString(3, Passworld);
		return pstmt.executeUpdate();	
	}
	
	public User SearchUser(Connection con,User user)throws Exception
	{
		User resultUser=null;
		String sql="select * from t_user where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		//对问号设置
		pstmt.setString(1, user.getId());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){//如果查到了，则实例化
			resultUser=new User();
			resultUser.setId(rs.getString("id"));//getInt("id")
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setBorrowNumRem(rs.getInt("borrowNumRem"));
			resultUser.setBalance(rs.getFloat("balance"));
		}
		return resultUser;
	}
	
	/**
	 * 借书后读者信息修改
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static int borrow(Connection con,User user)throws Exception
	{
		String sql="update t_user set borrowNumRem =? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		if(user.getBorrowNumRem()<=0)return -1;
		else
		{
			pstmt.setInt(1, user.getBorrowNumRem()-1);
		    pstmt.setString(2, user.getId());
		    return pstmt.executeUpdate();
		}
	}
}
