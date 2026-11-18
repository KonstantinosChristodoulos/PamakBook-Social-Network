package PamakBook;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
	
	private Date timestamp;
    private String text;
    private User author;

    public Post(User author, String text) {
        this.author = author;
        this.text = text;
        this.timestamp = new Date(); 
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

}
