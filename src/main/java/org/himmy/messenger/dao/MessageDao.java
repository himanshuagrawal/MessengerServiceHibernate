package org.himmy.messenger.dao;

import java.util.List;

import org.himmy.messenger.pojo.MessagePojo;

public interface MessageDao {

	public List<MessagePojo> getAllMessages();

	public MessagePojo getMessage(int id);

	public MessagePojo addMessage(MessagePojo message);

	public MessagePojo updatemessage(MessagePojo message);

	public List<MessagePojo> deleteMessage(int id);

	public List<MessagePojo> getMessagesByName(String name);

	public List<MessagePojo> getMessageBySize(int start, int size);

	public MessagePojo addLikeMessage(int id);
}
