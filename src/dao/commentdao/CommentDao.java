package dao.commentdao;

import java.util.ArrayList;

import po.Comment;

public interface CommentDao {

	public Integer addComment(Comment comment) throws Exception;
	public ArrayList<Comment> findComment(Comment comment) throws Exception;
}
