package org.himmy.messenger.dao.hibernateimpl;


import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.himmy.messenger.dao.CommentsDao;
import org.himmy.messenger.pojo.CommentPojo;
import org.himmy.messenger.pojo.MessagePojo;

public class CommentHibDaoImpl implements CommentsDao {

	private static Configuration config;
	private static SessionFactory sf;
	private static Query query;

	public void connect() {
		config = new Configuration().configure().addAnnotatedClass(CommentPojo.class)
				.addAnnotatedClass(MessagePojo.class);
		
	}

	public CommentHibDaoImpl() {
		super();
		connect();
	}

	public void disconnect() {
		sf.close();
	}

	@Override
	public List<CommentPojo> getAllComments(int msgId) {
		try{
			sf = config.buildSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			query = session.getNamedQuery("getAllComments");
			query.setParameter("msgId", msgId);
			List<CommentPojo> comments = (List<CommentPojo>) query.getResultList();
			session.getTransaction().commit();
			disconnect();
			return comments;
		}catch(Exception e){disconnect();}
		return null;
	}

	@Override
	public CommentPojo getComment(int msgId, int cmtId) {
		try{
		sf = config.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		query = session.getNamedQuery("getCommentById");
		query.setParameter("msgId", msgId);
		query.setParameter("cmtId", cmtId);
		CommentPojo comment = (CommentPojo)query.getSingleResult();
		session.getTransaction().commit();
		disconnect();
		return comment;
		}catch(Exception e){disconnect();}
		return null;
		
	}

	@Override
	public CommentPojo addComment(int msgId, CommentPojo comment) {
		
		return null;
	}

	@Override
	public List<CommentPojo> deleteComment(int msgId, int cmtId) {
		try{
			sf = config.buildSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			CommentPojo comment = getComment(msgId, cmtId);
			session.delete(comment);
			session.getTransaction().commit();
			disconnect();
			return getAllComments(msgId);
		}catch(Exception e){disconnect();}
		return null;
		
	}

	@Override
	public List<CommentPojo> getCommentsByName(String name, int msgId) {
		try{
			sf = config.buildSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			query = session.getNamedQuery("getCommentsByName");
			query.setParameter("msgId", msgId);
			query.setParameter("author", name);
			List<CommentPojo> comments = (List<CommentPojo>) query.getResultList();
			session.getTransaction().commit();
			disconnect();
			return comments;
		}catch(Exception e){disconnect();}
		return null;
	}

	@Override
	public List<CommentPojo> getCommentsBySize(int start, int size, int msgId) {
		try{
			sf = config.buildSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			query = session.getNamedQuery("getAllComments");
			query.setParameter("msgId", msgId);
			query.setFirstResult(start);
			query.setMaxResults(size);
			List<CommentPojo> comments = (List<CommentPojo>) query.getResultList();
			session.getTransaction().commit();
			disconnect();
			return comments;
		}catch(Exception e){disconnect();}
		return null;
		
	}

	@Override
	public CommentPojo addLikeComment(int msgId, int cmtId) {
			try{
				sf = config.buildSessionFactory();
				Session session = sf.openSession();
				session.beginTransaction();
				CommentPojo comment = getComment(msgId, cmtId);
				comment.setLikes(comment.getLikes()+1);
				session.update(comment);
				session.getTransaction().commit();
				disconnect();
				return comment;
			}catch(Exception e){disconnect();}
			return null;
	}

}
