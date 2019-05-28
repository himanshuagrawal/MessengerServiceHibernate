package org.himmy.messenger.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.himmy.messenger.dao.CommentsDao;
import org.himmy.messenger.pojo.CommentPojo;

public class CommentDaoImpl implements CommentsDao {

	List<CommentPojo> comments = new ArrayList<CommentPojo>();
	static Connection connection = null;
	static PreparedStatement pst = null;
	static String query = null;
	ResultSet set = null;

	public void connect(String queryToExecute) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernatewebservice?useSSL=false", "root",
					"mysql");
			pst = connection.prepareStatement(queryToExecute);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CommentPojo> getAllComments(int msgId) {
		query = "select * from comments where messageId=? order by commentId; ";
		connect(query);
		try {
			pst.setInt(1, msgId);
			set = pst.executeQuery();
			while (set.next()) {
				comments.add(new CommentPojo(set.getInt("messageId"), set.getInt("commentId"), set.getString("message"),
						set.getString("author"), set.getDate("dateOfCreation"), set.getInt("likes")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}

	@Override
	public CommentPojo getComment(int msgId, int cmtId) {
		getAllComments(msgId);
		for (CommentPojo cmt : comments) {
			if (cmt.getCommentId() == cmtId)
				return cmt;
		}
		return null;
	}

	@Override
	public CommentPojo addComment(int msgId, CommentPojo comment) {
		getAllComments(msgId);
		int result = 0;
		int cmtId=1001;
		if(comments.size()!=0)
		cmtId = comments.get(comments.size() - 1).getCommentId() + 1;
		getAllComments(msgId);
		query = "insert into comments values(?,?,?,?,'2018-10-01',0);";
		connect(query);
		try {
			pst.setInt(1, msgId);
			pst.setInt(2, cmtId);
			pst.setString(3, comment.getMessage());
			pst.setString(4, comment.getAuthor());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result != 0) {
			query = "update message set replies=replies+1 where messageId=?;";
			connect(query);
			try {
				pst.setInt(1, msgId);
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return getComment(msgId, cmtId);
	}

	@Override
	public List<CommentPojo> deleteComment(int msgId, int cmtId) {
		int result = 0;
		query = "delete from comments where messageId=? and commentId=?;";
		connect(query);
		try {
			pst.setInt(1, msgId);
			pst.setInt(2, cmtId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result != 0) {
			query = "update message set replies=replies-1 where messageId=?;";
			connect(query);
			try {
				pst.setInt(1, msgId);
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return getAllComments(msgId);
	}

	@Override
	public List<CommentPojo> getCommentsByName(String name, int msgId) {
		getAllComments(msgId);
		List<CommentPojo> list = new ArrayList<CommentPojo>();
		for (CommentPojo cmt : comments) {
			if (cmt.getAuthor().equals(name))
				list.add(cmt);
		}
		return list;
	}

	@Override
	public List<CommentPojo> getCommentsBySize(int start, int size, int msgId) {
		getAllComments(msgId);
		return comments.subList(start, start + size);
	}

	@Override
	public CommentPojo addLikeComment(int msgId, int cmtId) {
		query = "update comments set likes=likes+1 where messageId=? and commentId=?;";
		connect(query);
		try {
			pst.setInt(1, msgId);
			pst.setInt(2, cmtId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getComment(msgId, cmtId);

	}

}
