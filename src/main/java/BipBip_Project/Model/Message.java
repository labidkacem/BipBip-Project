package BipBip_Project.Model;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue
    private long id;
    private Date date;
    private String content;
    private String status;
    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;

    public long getId(){
        return id;
    }
    public Date getDate(){
        return date;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getStatus(){
        return status;
    }   
    public void setStatus(String status){
        this.status = status;
    } 
    public User getSender(){
        return sender;
    }
    public void setSender(User sender){
        this.sender = sender;
    }
    public User getReceiver(){
        return receiver;
    }
    public void setReceiver(User receiver){
        this.receiver = receiver;
    }

    public Message(String content){
        this.content = content;
    }
}