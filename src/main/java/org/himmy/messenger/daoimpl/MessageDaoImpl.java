package org.himmy.messenger.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.himmy.messenger.dao.MessageDao;
import org.himmy.messenger.pojo.MessagePojo;

public class MessageDaoImpl implements MessageDao {

	List<MessagePojo> messages = new ArrayList<MessagePojo>();

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
	public List<MessagePojo> getAllMessages() {
		query = "select * from messages order by messageId;";
		connect(query);
		try {
			set = pst.executeQuery();
			while (set.next()) {
				int msgId = set.getInt("messageId");
				messages.add(new MessagePojo(set.getInt("messageId"), set.getString("message"), set.getString("author"),
						set.getDate("dateOfCreation"), set.getInt("likes"), set.getInt("replies"),
						new CommentDaoImpl().getAllComments(msgId)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}

	@Override
	public MessagePojo getMessage(int id) {
		getAllMessages();
		for (MessagePojo msg : messages) {
			if (msg.getMessageId() == id)
				return msg;
		}
		return null;
	}

	@Override
	public MessagePojo updatemessage(MessagePojo message) {
		query = "update message set message=?,author=? where messageId=?;";
		connect(query);
		int msgId = message.getMessageId();
		try {
			pst.setString(1, message.getMessage());
			pst.setString(2, message.getAuthor());
			pst.setInt(3, msgId);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;

	}

	@Override
	public List<MessagePojo> deleteMessage(int id) {
		int result = 0;
		query = "delete from message where messageId=?;";
		connect(query);
		try {
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result != 0) {
			query = "delete from comments where messageId=?;";
			connect(query);
			try {
				pst.setInt(1, id);
				pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return getAllMessages();
	}

	@Override
	public MessagePojo addMessage(MessagePojo message) {
		getAllMessages();
		query = "insert into message values(?,?,?,'2018-10-01',?,?);";
		int id = messages.get(messages.size() - 1).getMessageId() + 1;
		connect(query);
		try {
			pst.setInt(1, id);
			pst.setString(2, message.getMessage());
			pst.setString(3, message.getAuthor());
			pst.setInt(4, 0);
			pst.setInt(5, 0);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}

	@Override
	public List<MessagePojo> getMessageBySize(int start, int size) {
		getAllMessages();
		return messages.subList(start, start + size);
	}

	@Override
	public List<MessagePojo> getMessagesByName(String name) {
		getAllMessages();
		List<MessagePojo> list = new ArrayList<MessagePojo>();
		for (MessagePojo msg : messages) {
			if (msg.getAuthor().equals(name))
				list.add(msg);
		}
		return list;
	}

	@Override
	public MessagePojo addLikeMessage(int id) {
		query = "update message set likes=likes+1 where messageId=?;";
		connect(query);
		try {
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getMessage(id);
	}

}
