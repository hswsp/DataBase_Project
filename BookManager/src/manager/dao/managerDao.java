package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import manager.entity.Manager;

public class managerDao {
	/**
	 * 登录验证
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Manager login(Connection con,Manager user)throws Exception{
		Manager resultUser=null;
		String sql="select * from t_manager where id=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		//对问号设置
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){//如果查到了，则实例化
			resultUser=new Manager();
			resultUser.setId(rs.getString("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
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
		String sql="insert into t_manager values(?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,Id);
		pstmt.setString(2, Username);
		pstmt.setString(3, Passworld);
		return pstmt.executeUpdate();	
	}
}