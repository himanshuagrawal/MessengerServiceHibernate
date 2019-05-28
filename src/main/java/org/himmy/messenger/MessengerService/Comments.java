package org.himmy.messenger.MessengerService;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.himmy.messenger.dao.CommentsDao;
import org.himmy.messenger.dao.hibernateimpl.CommentHibDaoImpl;
import org.himmy.messenger.daoimpl.CommentDaoImpl;
import org.himmy.messenger.parambeans.ParamBeans;
import org.himmy.messenger.pojo.CommentPojo;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Comments {

	CommentsDao commentDao = new CommentHibDaoImpl();

	@GET
	@Path("getallcomments")
	public List<CommentPojo> getAllComments(@PathParam("messageid") int msgId, @BeanParam ParamBeans bean) {
		if (bean.getName() != null)
			return commentDao.getCommentsByName(bean.getName(), msgId);
		else if (bean.getStart() >= 0 && bean.getSize() > 0)
			return commentDao.getCommentsBySize(bean.getStart(), bean.getSize(), msgId);
		else return commentDao.getAllComments(msgId);
	}

	@GET
	@Path("getallcomments/{commentid}")
	public CommentPojo getComment(@PathParam("messageid") int msgId, @PathParam("commentid") int cmtId) {
		return commentDao.getComment(msgId, cmtId);
	}

	@DELETE
	@Path("deletecomment/{commentid}")
	public List<CommentPojo> deleteComment(@PathParam("messageid") int msgId, @PathParam("commentid") int cmtId) {
		return commentDao.deleteComment(msgId, cmtId);
	}

	@GET
	@Path("{commentid}/addlike")
	public CommentPojo addLike(@PathParam("messageid") int msgId, @PathParam("commentid") int cmtId) {
		return commentDao.addLikeComment(msgId, cmtId);
	}

	@POST
	@Path("addcomment")
	public CommentPojo addComment(@PathParam("messageid") int msgId, CommentPojo comment) {
		return commentDao.addComment(msgId, comment);
	}
}
