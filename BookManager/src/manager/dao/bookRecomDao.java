package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import manager.entity.BookRecommend;
import manager.util.StringUtil;

public class bookRecomDao {
	
	/**
	 * �Ƽ���Ϣ���
	 * @param con
	 * @param Recom
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,BookRecommend Recom)throws Exception//����Ӱ�켸����¼
	{
		String sql="insert into t_recommand values(null,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);//�������󣬵õ�SQL���
		pstmt.setString(1, Recom.getUserID());//���ݲ���
		pstmt.setString(2, Recom.getBookName());
		pstmt.setString(3, Recom.getBookDesc());
		pstmt.setInt(4,Recom.getBookTypeID());
		pstmt.setString(5,Recom.getRecomReason());
		return pstmt.executeUpdate();//����ִ�н��
	}
/**
 * �Ƽ���Ϣ��ѯ
 * @param con
 * @param Recom
 * @return
 * @throws Exception
 */
	public ResultSet list(Connection con,BookRecommend Recom)throws Exception
	{
		StringBuffer sb=new StringBuffer("select * from t_recommand b,t_bookType bt where b.bookTypeId=bt.id");
		//���ű������ѯ����bookTypeID���ܲ�ѯ
		if(StringUtil.isNotEmpty(Recom.getBookName())){
			sb.append(" and b.bookName like '%"+Recom.getBookName()+"%'");
		}
		if(StringUtil.isNotEmpty(Recom.getUserID())){
			sb.append(" and b.author like '%"+Recom.getUserID()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();//ִ��
	}
	
	/**
	 * �Ƽ���Ϣɾ��
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
		return pstmt.executeUpdate();//�޸�
	}
}
