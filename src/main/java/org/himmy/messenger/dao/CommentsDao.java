package org.himmy.messenger.dao;

import java.util.List;

import org.himmy.messenger.pojo.CommentPojo;

public interface CommentsDao {

	public List<CommentPojo> getAllComments(int msgId);

	public CommentPojo getComment(int msgId, int cmtId);

	public CommentPojo addComment(int msgId, CommentPojo comment);

	public List<CommentPojo> deleteComment(int msgId, int cmtId);

	public List<CommentPojo> getCommentsByName(String name, int msgId);

	public List<CommentPojo> getCommentsBySize(int start, int size, int msgId);

	public CommentPojo addLikeComment(int msgId, int cmtId);

}
