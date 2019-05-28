package org.himmy.messenger.MessengerService;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.himmy.messenger.dao.MessageDao;
import org.himmy.messenger.dao.hibernateimpl.MessageHibDaoImpl;
import org.himmy.messenger.daoimpl.MessageDaoImpl;
import org.himmy.messenger.parambeans.ParamBeans;
import org.himmy.messenger.pojo.MessagePojo;

@Path("messages1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Messages {

	MessageDao messageDao = new MessageHibDaoImpl();

	@GET
	@Path("/getallmessages")
	public List<MessagePojo> getAllMessage(@BeanParam ParamBeans bean) {
		if (bean.getName() != null)
			return messageDao.getMessagesByName(bean.getName());
		if (bean.getStart() >= 0 && bean.getSize() > 0)
			return messageDao.getMessageBySize(bean.getStart(), bean.getSize());
		return messageDao.getAllMessages();
	}

	@GET
	@Path("/getmessage/{msgid}")
	public MessagePojo getMessage(@PathParam("msgid") int id) {
		return messageDao.getMessage(id);
	}

	@PUT
	@Path("/updatemessage")
	public MessagePojo updateMessage(MessagePojo message) {
		return messageDao.updatemessage(message);
	}

	@DELETE
	@Path("/deletemessage/{msgid}")
	public List<MessagePojo> deleteMessage(@PathParam("msgid") int id) {
		return messageDao.deleteMessage(id);
	}

	@POST
	@Path("/addmessage")
	public MessagePojo addMessage(MessagePojo message) {
		return messageDao.addMessage(message);
	}
	@GET
	@Path("{messageid}/addlike")
	public MessagePojo addLike(@PathParam("messageid") int msgId) {
		return messageDao.addLikeMessage(msgId);
	}

	@Path("{messageid}/comments")
	public Comments comments() {
		return new Comments();

	}

}
