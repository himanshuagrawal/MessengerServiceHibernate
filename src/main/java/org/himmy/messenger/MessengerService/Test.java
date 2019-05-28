package org.himmy.messenger.MessengerService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.himmy.messenger.daoimpl.MessageDaoImpl;
import org.himmy.messenger.pojo.MessagePojo;

@Path("test")
public class Test {

//	public static void main(String[] args) throws SQLException {
//		int n = 101;
//		String query="select * from message where messageId=?;";
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webservices?useSSL=false", "root", "mysql");
//			
//			PreparedStatement st = con.prepareStatement(query);
//			st.setInt(1, n);
//			ResultSet rs = st.executeQuery();
//			rs.last();
//			System.out.println(rs.getString("message"));
//			System.out.println(rs.getDate("dateOfCreation"));
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    @GET
	    @Produces(MediaType.TEXT_PLAIN)
		public void x()
		{
		MessageDaoImpl m = new MessageDaoImpl();
		List<MessagePojo> x = m.getAllMessages();
		for(MessagePojo msg:x)
			System.out.println(msg.getMessage());
	}


}
