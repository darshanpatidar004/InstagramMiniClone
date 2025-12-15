package com.trueigtech.instaclone.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;



@Entity
@Table(name = "comments")
public class Comment {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String text;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructor with all fields

    public Comment(Long id, String text, LocalDateTime createdAt, Post post, User user) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.post = post;
        this.user = user;
    }

    public Comment(Post post) {
        this.post = post;
    }

    // Getters and Setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //toString method

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", text='" + getText() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", post='" + getPost() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }

    

}
