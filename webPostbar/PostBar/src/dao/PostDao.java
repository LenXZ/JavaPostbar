package dao;

import java.util.List;

import entity.Post;
import entity.User;

public interface PostDao {
	public List<Post> findPost(String titleString);
	public List<Post> findTitle(String titleString);
	public List<Post> findAll();
	public void addPost(Post post);
	public Post findById(String idString);
	public void deletePost(String idString);
	public void deleteTitle(String idString);
	public void modifyPost(Post post);
}
