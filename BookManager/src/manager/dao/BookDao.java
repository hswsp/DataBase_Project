package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import manager.entity.Book;
import manager.entity.HisBorrow;
import manager.entity.Manager;
import manager.entity.User;
import manager.util.StringUtil;

/**
 * ͼ��Dao��
 * @author asus-pc
 *
 */
public class BookDao {
	
	/**
	 * ͼ�����
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,Book book)throws Exception//����Ӱ�켸����¼
	{
		String sql="insert into t_book values(null,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);//�������󣬵õ�SQL���
		pstmt.setString(1, book.getBookName());//���ݲ���
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setInt(5, book.getBookTypeId());
		pstmt.setString(6, book.getBookDesc());
		pstmt.setInt(7, book.getBookNum());
		pstmt.setString(8, book.getPublisher());		
		return pstmt.executeUpdate();//����ִ�н��
	}
	
	
	/**
	 * ͼ����Ϣ��ѯ
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con,Book book)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_book b,t_bookType bt where b.bookTypeId=bt.id");
		//���ű������ѯ����bookTypeID���ܲ�ѯ
		if(StringUtil.isNotEmpty(book.getBookName())){
			sb.append(" and b.bookName like '%"+book.getBookName()+"%'");
		}
		if(StringUtil.isNotEmpty(book.getAuthor())){
			sb.append(" and b.author like '%"+book.getAuthor()+"%'");
		}
		if(book.getBookTypeId()!=null && book.getBookTypeId()!=-1)//����ѡ�񡱵�IDΪ-1
		{
			sb.append(" and b.bookTypeId="+book.getBookTypeId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();//ִ��
	}

	/**
	 * ������ID����ѯ,����ͼ������г�ʼ��Borrow
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public static Book BookSearch(Connection con,Book book)throws Exception{
		Book resultBook=null;
		StringBuffer sb=new StringBuffer("select * from t_book where id=?");		
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setInt(1, book.getId());
		ResultSet rs=pstmt.executeQuery();//ִ��
		if(rs.next()){//����鵽�ˣ���ʵ����
			resultBook=new Book();
			resultBook.setId(rs.getInt("id"));
			resultBook.setBookName(rs.getString("bookName"));
			resultBook.setAuthor(rs.getString("author"));
			resultBook.setSex(rs.getString("sex"));
			resultBook.setPrice(rs.getFloat("price"));
			resultBook.setBookTypeId(rs.getInt("bookTypeId"));
			resultBook.setBookDesc(rs.getString("BookDesc"));
			resultBook.setBookNum(rs.getInt("number"));
			resultBook.setPublisher(rs.getString("publisher"));
		}
		return resultBook;
	}
	/**
	 * ͼ����Ϣɾ��
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con,int id)throws Exception
	{
		String sql="delete from t_book where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, id);
		return pstmt.executeUpdate();//�޸�
	}
	
	/**
	 * ͼ����Ϣ�޸�
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con,Book book)throws Exception
	{
		String sql="update t_book set bookName=?,author=?,sex=?,price=?,bookDesc=?,bookTypeId=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setString(5, book.getBookDesc());
		pstmt.setInt(6, book.getBookTypeId());
		pstmt.setInt(7, book.getId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * ͼ�����
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int borrow(Connection con,Book book)throws Exception
	{
		String sql="update t_book set number=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		if(book.getBookNum()<=0)return -1;
		else
		{
			pstmt.setInt(1, book.getBookNum()-1);
		    pstmt.setInt(2, book.getId());
		    return pstmt.executeUpdate();
		}
	}
	
	/**
	 * ָ��ͼ��������Ƿ����ͼ��,Ϊ�˷�ֹ��ͼ��ʱ��ɾ�����
	 * @param con
	 * @param bookTypeId
	 * @return
	 * @throws Exception
	 */
	public boolean existBookByBookTypeId(Connection con,String bookTypeId)throws Exception
	{
		String sql="select * from t_book where bookTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, bookTypeId);//��д��һ���ʺ�
		ResultSet rs=pstmt.executeQuery();
		return rs.next();//û�м�¼����false
	}
	
	/**
	 * ��ʷ�����¼��ѯ
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ResultSet hisShopSearch(Connection con,Manager user)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_perchase p,t_manager m,t_book b and m.id=p.managerID and b.id=p.bookID");
		//���ű������ѯ����bookTypeID���ܲ�ѯ
		if(StringUtil.isNotEmpty(user.getId()))
		{
			sb.append(" and managerID=?");
		}
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		pstmt.setString(1, user.getId());
		return pstmt.executeQuery();//ִ��
	}
}
