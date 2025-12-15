
package com.trueigtech.instaclone.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "posts")
public class Post{
    

//  id
// imageUrl
// caption
// createdAt
// user (ManyToOne)
// likes (ManyToMany)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    @Column(length = 500)
    private String caption;

    private LocalDateTime createdAt;

    @ManyToOne
     @JoinColumn(name = "user_id")
    private User user;

   @ManyToMany
    @JoinTable(
        name = "post_likes",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    
     private Set<User> likes = new HashSet<>();

    public Post() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructor with all fields

    public Post(Long id, String imageUrl, String caption, LocalDateTime createdAt, User user, Set<User> likes) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.createdAt = createdAt;
        this.user = user;
        this.likes = likes;
    }

    // Getters and Setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getLikes() {
        return this.likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }


    //toString method
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", caption='" + getCaption() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", user='" + getUser() + "'" +
            ", likes='" + getLikes() + "'" +
            "}";
    }


    

    
}