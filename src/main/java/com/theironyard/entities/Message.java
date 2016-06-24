package com.theironyard.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by hoseasandstrom on 6/23/16.
 */
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String text;

    @Column(nullable = false)
    LocalDateTime time;

    @ManyToOne
    User user;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    public Message(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Message(String text, LocalDateTime time, User user) {
        this.text = text;
        this.time = time;
        this.user = user;
    }

    public Message(int id, String text, LocalDateTime time, User user) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
