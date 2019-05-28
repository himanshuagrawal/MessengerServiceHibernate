package org.himmy.messenger.dao.hibernateimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.himmy.messenger.dao.MessageDao;
import org.himmy.messenger.pojo.CommentPojo;
import org.himmy.messenger.pojo.MessagePojo;

public class MessageHibDaoImpl implements MessageDao{

	
	
	private static Configuration config;
	private static SessionFactory sf;
	private static Query query;
	
	public void connect() {
		config = new Configuration().configure().addAnnotatedClass(CommentPojo.class)
				.addAnnotatedClass(MessagePojo.class);
	}
	
	public MessageHibDaoImpl() {
		super();
		connect();
	}
	
	public void disconnect() {
		sf.close();
	}
	
	@Override
	public List<MessagePojo> getAllMessages() {
		try{
			sf = config.buildSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			query = session.getNamedQuery("getAllMessages");
			List<MessagePojo> messages = (List<MessagePojo>) query.getResultList();
			session.getTransaction().commit();
			disconnect();
			return messages;
		}catch(Exception e){disconnect();}
		return null;
	}

	@Override
	public MessagePojo getMessage(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessagePojo addMessage(MessagePojo message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessagePojo updatemessage(MessagePojo message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessagePojo> deleteMessage(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessagePojo> getMessagesByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessagePojo> getMessageBySize(int start, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessagePojo addLikeMessage(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
