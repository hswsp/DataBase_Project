package manager.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * �����ֶ��������Ա�˺š�
 * @author asus-pc
 *
 */
public class ManagerAccountUtil {
	static String userName="manager";
	static String password="manager";
	static String userID="USTC";
	public static void main(String[] args) 
	{
		
	String Md5Str=MD5Util.string2MD5(password);
	password=MD5Util.convertMD5(Md5Str);
	DbUtil dbUtil=new DbUtil();
	Connection con=null;
	try {
		con=dbUtil.getCon();
		String sql="SELECT * FROM t_manager where id=?";//+"'"+Integer.parseInt(IDTxt.getText())+"'";								
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, userID);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next())
		{
			showMessageFrame note=new showMessageFrame(null,"�û�ID�Ѵ��ڣ����ʧ�ܣ�",showMessageFrame.NOTE);
			return;
		}
			else
		  {
				String sqll="insert into t_manager values(?,?,?)";//Ҫƥ��������,�����Ϊ8
				PreparedStatement pstmtt=con.prepareStatement(sqll);
				pstmtt.setString(1, userID);
				pstmtt.setString(2, userName);
				pstmtt.setString(3, password);
				int num= pstmtt.executeUpdate();	
				if(num==1)
				{
					showMessageFrame note=new showMessageFrame(null,"�����˻��ɹ���",showMessageFrame.NORMAL);
					return;
				}
				else
				{
					showMessageFrame note=new showMessageFrame(null,"���ʧ�ܣ�",showMessageFrame.NOTE);
					return;
				}
		  }
			
	} catch (Exception e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	}

}
