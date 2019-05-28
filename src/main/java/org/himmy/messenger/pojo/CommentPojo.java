package org.himmy.messenger.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
//
@Entity
@Table(name = "Comments")
@SequenceGenerator(name = "seq", initialValue = 1001)
@NamedQueries(value = {@NamedQuery(name = "getAllComments", query = "from CommentPojo where messageId=:msgId"),
		@NamedQuery(name = "getCommentById", query = "from CommentPojo where messageId=:msgId and commentId=:cmtId"),
		@NamedQuery(name = "getCommentsByName", query = "from CommentPojo where messageId=:msgId and author=:author")
}
)
@XmlRootElement
public class CommentPojo {

	private int messageId;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "CommentId")
	private int commentId;

	@Column(name = "Message")
	private String message;
	@Column(name = "Author")
	private String author;

//	public MessagePojo getMessagepojo() {
//		return messagepojo;
//	}
//
//	public void setMessagepojo(MessagePojo messagepojo) {
//		this.messagepojo = messagepojo;
//	}

	@Column(name = "DateOfCreation")
	@Temporal(TemporalType.DATE)
	private Date dateOfCreation;
	@Column(name = "Likes")
	private int likes;

//	@ManyToOne
//	@JoinColumn(name = "MessageIdaa")
//	@XmlTransient
//	private MessagePojo messagepojo;

	public CommentPojo() {
	}


	public CommentPojo(String message, String author, Date dateOfCreation, int likes) {
		super();
		this.message = message;
		this.author = author;
		this.dateOfCreation = dateOfCreation;
		this.likes = likes;
	}
	
	public CommentPojo(int messageId, int commentId, String message, String author, Date dateOfCreation, int likes) {
		super();
		this.messageId = messageId;
		this.commentId = commentId;
		this.message = message;
		this.author = author;
		this.dateOfCreation = dateOfCreation;
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "CommentPojo [messageId=" + messageId + ", commentId=" + commentId + ", message=" + message + ", author="
				+ author + ", dateOfCreation=" + dateOfCreation + ", likes=" + likes + "]";
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

}
