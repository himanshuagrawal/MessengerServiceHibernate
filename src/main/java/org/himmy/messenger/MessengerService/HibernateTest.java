package org.himmy.messenger.MessengerService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.himmy.messenger.dao.CommentsDao;
import org.himmy.messenger.dao.hibernateimpl.CommentHibDaoImpl;
import org.himmy.messenger.daoimpl.CommentDaoImpl;
import org.himmy.messenger.hibernate.util.HibernateUtil;
import org.himmy.messenger.pojo.CommentPojo;
import org.himmy.messenger.pojo.MessagePojo;

public class HibernateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MessagePojo msg = new MessagePojo("Helllo", "himmy", new Date(), 1, 1);
		CommentPojo comment = new CommentPojo("comment", "himmynew", new Date(), 2);
		CommentPojo comment1 = new CommentPojo("comment2", "himmynew2", new Date(), 2);
		List<CommentPojo> list1 = new ArrayList<CommentPojo>();
		List<CommentPojo> list2 = new ArrayList<CommentPojo>();
		try(SessionFactory sf = HibernateUtil.getConfig().buildSessionFactory())
		{
			Session session = sf.openSession();
			session.beginTransaction();
			session.save(comment);
			session.save(comment1);
			session.save(msg);
			session.getTransaction().commit();
		}
//		
//		System.out.println(list.size());
//		for(MessagePojo m:list)
//		{
//			System.out.println(m);
//		}
//		CommentsDao commentDao2 = new CommentHibDaoImpl();
//		CommentsDao commentDao1 = new CommentDaoImpl();
//		list1 = (List<CommentPojo>)commentDao1.getAllComments(0);
//		list2 = (List<CommentPojo>)commentDao2.getAllComments(0);
//		System.out.println(list1.size());
//		
//		for(CommentPojo m:list1)
//		{
//			System.out.println(m);
//		}
//		
//		System.out.println(list2.size());
//		for(CommentPojo m:list2)
//		{
//			System.out.println(m);
//		}
		
	}

}
