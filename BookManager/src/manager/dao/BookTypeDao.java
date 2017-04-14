package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import manager.entity.BookType;
import manager.util.StringUtil;


/**
 * ͼ�����Dao��
 * @author Administrator
 *
 */
public class BookTypeDao {

	/**
	 * ͼ��������
	 * @param con
	 * @param bookType
	 * @return �����ļ�¼��
	 * @throws Exception
	 */
	public int add(Connection con,BookType bookType)throws Exception{
		String sql="insert into t_bookType values(null,?,?)";//Ԥ����ķ�ʽ����ȫһ��
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookTypeName());
		pstmt.setString(2, bookType.getBookTypeDesc());
		return pstmt.executeUpdate();//ִ��
	}
	/**
	 * ��ѯͼ����𼯺�
	 * @param con
	 * @param bookType
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con,BookType bookType)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_bookType");
		if(StringUtil.isNotEmpty(bookType.getBookTypeName())){
			sb.append(" and bookTypeName like '%"+bookType.getBookTypeName()+"%'");//����ж�������������һ������whereû�ط�д���ʶ����ô��㷨
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));//ת�����ַ������ذѵ�һ��and����where
		return pstmt.executeQuery();
	}
}
