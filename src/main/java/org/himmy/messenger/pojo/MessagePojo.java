package org.himmy.messenger.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="Messages")
@SequenceGenerator(name = "seq",initialValue=101)
@NamedQuery(name = "getAllMessages", query = "from MessagePojo")
@XmlRootElement
public class MessagePojo {
	
    @Id @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq")
    @Column(name="MessageId")
	private int messageId;
    @Column(name="Message")
	private String message;
    @Column(name="Author")
	private String author;
    @Column(name="DateOfCreation")
    @Temporal(TemporalType.DATE)
	private Date dateOfCreation;
    @Column(name="Likes")
	private int likes;
    @Column(name="Replies")
	private int replies;
    
    @OneToMany()
	private List<CommentPojo> comments;

	public MessagePojo() {
	}

	public MessagePojo(String message, String author, Date dateOfCreation, int likes, int replies) {
		super();
		this.message = message;
		this.author = author;
		this.dateOfCreation = dateOfCreation;
		this.likes = likes;
		this.replies = replies;
	}

	public MessagePojo(int messageId, String message, String author, Date dateOfCreation, int likes, int replies,
			List<CommentPojo> comments) {
		super();
		this.messageId = messageId;
		this.message = message;
		this.author = author;
		this.dateOfCreation = dateOfCreation;
		this.likes = likes;
		this.replies = replies;
		this.comments = comments;
	}

	
	public List<CommentPojo> getComments() {
		return comments;
	}

	public void setComments(List<CommentPojo> comments) {
		this.comments = comments;
	}

	public int getMessageId() {
		return messageId;
	}

	public int getLikes() {
		return likes;
	}

	public int getReplies() {
		return replies;
	}

	public void setReplies(int replies) {
		this.replies = replies;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
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

	@Override
	public String toString() {
		return "MessagePojo [messageId=" + messageId + ", message=" + message + ", author=" + author
				+ ", dateOfCreation=" + dateOfCreation + ", likes=" + likes + ", replies=" + replies + "]";
	}
	
	

}
