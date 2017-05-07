package manager.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 数据库工具类
 * @author Administrator
 *
 */
public class DbUtil {

	private String dbUrl="jdbc:mysql://localhost:3306/db_book"; // 数据库连接地址://202.38.88.99:1434/student
	private String dbUserName="root"; // 用户名student
	private String dbPassword="student"; // 密码
	private String jdbcName="com.mysql.jdbc.Driver"; // 驱动名称
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon()throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	
	/**
	 * 关闭数据库连接
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con)throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
    /**
     * 关闭连接表对象  
     * @param pstmt
     */
    public static void close(java.sql.PreparedStatement pstmt){  
        if (pstmt!=null) {  
            try {  
                pstmt.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }     
        }  
    }  
    
    /**
     * 关闭结果集，释放资源
     * @param rs
     */
    public static void close(ResultSet rs) {  
        if(rs!=null){  
            try {  
                rs.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    
   /**
    * 关闭自动提交事务
    * @param conn
    */
    public static void beginTransaction(Connection conn){  
        if (conn!=null) {  
            try {  
                if(conn.getAutoCommit()){  
                    conn.setAutoCommit(false);  
                }  
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
    }  
    /**
     * 提交事务
     * @param conn
     */
    public static void commitTransaction(Connection conn){  
        try {  
            if(conn!=null){  
                if (!conn.getAutoCommit()) {  
                    conn.commit();  
                }  
            }  
        } catch (Exception e) {  
            // TODO: handle exception  
        }  
    }  
     
    /**
     * 回滚事务
     * @param conn
     */
    public static void rollbackTransaction (Connection conn) {  
        try {  
            if(conn!=null){  
                if (!conn.getAutoCommit()) {  
                    conn.rollback();  
                }  
            }  
        } catch (Exception e) {  
            // TODO: handle exception  
        }  
    }  
    
    /**
     * 将是否自动提交事务权限取反
     * @param conn
     */
    public static void resetTransaction(Connection conn){  
        try {  
            if(conn!=null)
            {  
                if (conn.getAutoCommit()) {  
                    conn.setAutoCommit(false);  
                }else{  
                    conn.setAutoCommit(true);  
                }  
            }  
        } catch (Exception e) {  
            // TODO: handle exception  
        }  
    }  
    
    /**
     * 主函数，测试使用
     * @param args
     */
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
	
	
}
