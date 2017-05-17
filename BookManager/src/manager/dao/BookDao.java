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
 * 图书Dao类
 * @author asus-pc
 *
 */
public class BookDao {
	
	/**
	 * 图书添加
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,Book book)throws Exception//返回影响几条记录
	{
		String sql="insert into t_book values(null,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);//创建对象，得到SQL语句
		pstmt.setString(1, book.getBookName());//传递参数
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setInt(5, book.getBookTypeId());
		pstmt.setString(6, book.getBookDesc());
		pstmt.setInt(7, book.getBookNum());
		pstmt.setString(8, book.getPublisher());		
		return pstmt.executeUpdate();//返回执行结果
	}
	
	
	/**
	 * 图书信息查询
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con,Book book)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_book b,t_bookType bt where b.bookTypeId=bt.id");
		//两张表关联查询，有bookTypeID才能查询
		if(StringUtil.isNotEmpty(book.getBookName())){
			sb.append(" and b.bookName like '%"+book.getBookName()+"%'");
		}
		if(StringUtil.isNotEmpty(book.getAuthor())){
			sb.append(" and b.author like '%"+book.getAuthor()+"%'");
		}
		if(book.getBookTypeId()!=null && book.getBookTypeId()!=-1)//“请选择”的ID为-1
		{
			sb.append(" and b.bookTypeId="+book.getBookTypeId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();//执行
	}

	/**
	 * 根据书ID主查询,用于图书借阅中初始化Borrow
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
		ResultSet rs=pstmt.executeQuery();//执行
		if(rs.next()){//如果查到了，则实例化
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
	 * 图书信息删除
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
		return pstmt.executeUpdate();//修改
	}
	
	/**
	 * 图书信息修改
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
	 * 图书借阅
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
	 * 指定图书类别下是否存在图书,为了防止有图书时候删除类别
	 * @param con
	 * @param bookTypeId
	 * @return
	 * @throws Exception
	 */
	public boolean existBookByBookTypeId(Connection con,String bookTypeId)throws Exception
	{
		String sql="select * from t_book where bookTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, bookTypeId);//填写第一个问号
		ResultSet rs=pstmt.executeQuery();
		return rs.next();//没有记录就是false
	}
	
	/**
	 * 历史购书记录查询
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ResultSet hisShopSearch(Connection con,Manager user)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_perchase p,t_manager m,t_book b and m.id=p.managerID and b.id=p.bookID");
		//两张表关联查询，有bookTypeID才能查询
		if(StringUtil.isNotEmpty(user.getId()))
		{
			sb.append(" and managerID=?");
		}
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		pstmt.setString(1, user.getId());
		return pstmt.executeQuery();//执行
	}
}
