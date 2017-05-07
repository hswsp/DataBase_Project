package manager.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * ���ݿ⹤����
 * @author Administrator
 *
 */
public class DbUtil {

	private String dbUrl="jdbc:mysql://localhost:3306/db_book"; // ���ݿ����ӵ�ַ://202.38.88.99:1434/student
	private String dbUserName="root"; // �û���student
	private String dbPassword="student"; // ����
	private String jdbcName="com.mysql.jdbc.Driver"; // ��������
	
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 * @throws Exception
	 */
	public Connection getCon()throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	
	/**
	 * �ر����ݿ�����
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con)throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
    /**
     * �ر����ӱ����  
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
     * �رս�������ͷ���Դ
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
    * �ر��Զ��ύ����
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
     * �ύ����
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
     * �ع�����
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
     * ���Ƿ��Զ��ύ����Ȩ��ȡ��
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
     * ������������ʹ��
     * @param args
     */
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("���ݿ����ӳɹ���");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���ݿ�����ʧ��");
		}
	}
	
	
}
