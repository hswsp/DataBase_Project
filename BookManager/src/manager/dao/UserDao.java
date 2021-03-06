package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import manager.entity.User;
import manager.util.DbUtil;


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
	public User login(Connection con,User user)throws Exception
	{
		User resultUser=null;
		DbUtil dbUtil=new DbUtil();
	    PreparedStatement pstmt = null;  
	    ResultSet rs = null;  
		String sql="select * from t_user where id=? and password=? and IsLogin=? for update";//加锁，不允许同时登陆
		try
		{	
			DbUtil.beginTransaction(con); 
			pstmt=con.prepareStatement(sql);
			//对问号设置
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setByte(3, (byte)0);
			rs=pstmt.executeQuery();
			if(rs.next())
			{//如果查到了，则实例化
				resultUser=new User();
				resultUser.setId(rs.getString("id"));//getInt("id")
				resultUser.setUserName(rs.getString("userName"));
				resultUser.setPassword(rs.getString("password"));
				resultUser.setBorrowNumRem(rs.getInt("borrowNumRem"));
				resultUser.setBalance(rs.getFloat("balance"));
				resultUser.setIsLogin((byte)1);//置成已登陆
				int num=modifyIsLoginField(con,user.getId(),(byte)1);  
	            //提交事务  
	            DbUtil.commitTransaction(con);  
			}
	  }catch(Exception e){  
          e.printStackTrace();  
          //回滚事务  
          DbUtil.rollbackTransaction (con);  
          throw new RuntimeException();  
      }finally{  
          DbUtil.close(rs);  
          DbUtil.close(pstmt);  
          DbUtil.resetTransaction(con);  
          dbUtil.closeCon(con);  
      }  
		return resultUser;
	}
	
	/** 
     * 更新IsLogin
     * @param conn 
     * @param tableName 
     * @param value 
     */  
   public static int modifyIsLoginField(Connection conn, String UserID, float value)throws SQLException 
    {  
        String sql = "update t_user set IsLogin=? where id=?";  
        PreparedStatement pstmt = null;  
        try {  
            pstmt = conn.prepareStatement(sql);  
            pstmt.setFloat(1, value);  
            pstmt.setString(2, UserID);  
            return pstmt.executeUpdate();  
        }finally 
        {  
            DbUtil.close(pstmt);  
        }  
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
		String sql="insert into t_user values(?,?,?,8,0,0)";//要匹配数量！,最大本数为8
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,Id);
		pstmt.setString(2, Username);
		pstmt.setString(3, Passworld);
		return pstmt.executeUpdate();	
	}
	
	/**
	 * 根据id查找用户
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User SearchUser(Connection con,User user)throws Exception
	{
		User resultUser=null;
		String sql="select * from t_user where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);	
		pstmt.setString(1, user.getId());//对问号设置
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){//如果查到了，则实例化
			resultUser=new User();
			resultUser.setId(rs.getString("id"));//getInt("id")
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setBorrowNumRem(rs.getInt("borrowNumRem"));
			resultUser.setBalance(rs.getFloat("balance"));
			resultUser.setIsLogin(rs.getByte("IsLogin"));				
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
	
	/**
	 * 修改用户名和密码
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static int Edit(Connection con,User user)throws Exception
	{
		StringBuffer sql=new StringBuffer("update t_user set userName=? where id=?");
		PreparedStatement pstmt=con.prepareStatement(sql.toString());
		pstmt.setString(1, user.getUserName());
		 pstmt.setString(2, user.getId());
		return pstmt.executeUpdate();			
    }

	/**
	 * 用户缴纳罚款
	 * @param tableName
	 * @return
	 */
	public static int Recharge(Connection con,User user)throws Exception
	{  
        //使用数据库的悲观锁for update  
        String sql = "select balance from t_user where id=? for update"; //加上for update为数据库加上行级排他锁,防止修改金额出错
        int num;    //update()返回条数                                                            
        DbUtil dbUtil=new DbUtil();
        PreparedStatement pstmt = null;  
        ResultSet rs = null;  
        float value = 0;  
        try{  
            //设置自动提交为false  
            DbUtil.beginTransaction(con);  
            pstmt = con.prepareStatement(sql);  
            pstmt.setString(1, user.getId());  
            rs = pstmt.executeQuery();  
            
            rs.next();  
//          if(!rs.next()){  
//              throw new RuntimeException();  
//          }  
            value = rs.getFloat("balance");  
            value=value+user.getBalance();//要求传进来的user为待增加的balance ！
            user.setBalance(value);//改变balance
            num=modifyValueField(con,user.getId(),value);  
            //提交事务  
            DbUtil.commitTransaction(con);  
        }catch(Exception e){  
            e.printStackTrace();  
            //回滚事务  
            DbUtil.rollbackTransaction (con);  
            throw new RuntimeException();  
        }finally{  
            DbUtil.close(rs);  
            DbUtil.close(pstmt);  
            DbUtil.resetTransaction(con);  
            dbUtil.closeCon(con);  
        }  
        return num;  
    }  
	
	/** 
     * 更新balance
     * @param conn 
     * @param tableName 
     * @param value 
     */  
    private static int modifyValueField(Connection conn, String UserID, float value)throws SQLException 
    {  
        String sql = "update t_user set balance=? where id=?";  
        PreparedStatement pstmt = null;  
        try {  
            pstmt = conn.prepareStatement(sql);  
            pstmt.setFloat(1, value);  
            pstmt.setString(2, UserID);  
            return pstmt.executeUpdate();  
        }finally 
        {  
            DbUtil.close(pstmt);  
        }  
    }   
}
