
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

import dao.PostDao;
import entity.Post;
/**
 * PostDao的实现类
 * **/
public class PostDaoImpl implements PostDao{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PostDaoImpl postDaoImpl=new PostDaoImpl();
//		postDaoImpl.addPost(new Post("A3000100001","001","女","100"));
		
		System.out.println(postDaoImpl.findPost("A10001"));
	}
	public List<Post> findAll() {
	
		String sqlString="select * from post";
		List<Post> list=new ArrayList<Post>();
		Connection connection=DBUtil.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				String idString=resultSet.getString("postid");
				String userString=resultSet.getString("userid");
				String dateString=resultSet.getString("date");
				String textString=resultSet.getString("text");
				Post post=new Post(idString, userString, dateString, textString);
				list.add(post);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	//输入完整id 查找同一题目下的所有帖子
	public List<Post> findPost(String titleString) {
		String sqlString="select * from post where postid like ?";
		List<Post> list=new ArrayList<Post>();
		Connection connection=DBUtil.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, "%"+titleString.substring(0,6)+"%");
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				String idString=resultSet.getString("postid");
				String userString=resultSet.getString("userid");
				String dateString=resultSet.getString("date");
				String textString=resultSet.getString("text");
				Post post=new Post(idString, userString, dateString, textString);
				
				list.add(post);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	//模糊查找
	public List<Post> findPosts(String titleString) {
		String sqlString="select * from post where postid like ?";
		List<Post> list=new ArrayList<Post>();
		Connection connection=DBUtil.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, "%"+titleString+"%");
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				String idString=resultSet.getString("postid");
				String userString=resultSet.getString("userid");
				String dateString=resultSet.getString("date");
				String textString=resultSet.getString("text");
				Post post=new Post(idString, userString, dateString, textString);
				
				list.add(post);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public Post findById(String idString) {
		String sqlString="select * from post where postid=?";
		Post post = null;
		try {
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, idString);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				String userString=resultSet.getString("userid");
				String dateString=resultSet.getString("date");
				String textString=resultSet.getString("text");
				post=new Post(idString, userString, dateString, textString);
				
			}
			return post;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	public void addPost(Post post) {
		String sqlString="insert into post values (?,?,?,?)";
		Connection connection=DBUtil.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, post.getIdString());
			preparedStatement.setString(2, post.getUserString());
			preparedStatement.setString(3, post.getDateString());
			preparedStatement.setString(4, post.getTextString());
			preparedStatement.executeUpdate();
			System.out.println("插入成功");
		} catch (SQLException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deletePost(String idString) {
		String sqlString="delete from post where postid=?";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sqlString);	
			preparedStatement.setString(1, idString);
			preparedStatement.executeUpdate();
			System.out.println("删除成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void modifyPost(Post post) {
		String sqlString="update post set userid=?,date=?,text=?where postid=?";
		try {
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);			
			preparedStatement.setString(1, post.getUserString());
			preparedStatement.setString(2, post.getDateString());
			preparedStatement.setString(3, post.getTextString());
			preparedStatement.setString(4, post.getIdString());
			preparedStatement.executeUpdate();
			System.out.println("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<Post> findTitle(String titleString) {
		System.out.println(titleString);
		String sqlString="select * from post where postid like ?";
		List<Post> list=new ArrayList<Post>();
		Connection connection=DBUtil.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, "%"+titleString+"%"+"00000");
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				String idString=resultSet.getString("postid");
				String userString=resultSet.getString("userid");
				String dateString=resultSet.getString("date");
				String textString=resultSet.getString("text");
				Post post=new Post(idString, userString, dateString, textString);
				list.add(post);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public void deleteTitle(String idString) {
		String sqlString="delete from post where postid like ?";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sqlString);	
			preparedStatement.setString(1, "%"+idString+"%");
			preparedStatement.executeUpdate();
			System.out.println("删除成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
