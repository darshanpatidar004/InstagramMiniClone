
package com.trueigtech.instaclone.model;

import jakarta.persistence.*;
// import java.util.HashSet;
import java.util.Set;

// we have to create this fields
// id 
// username 
// email 
// password 
// followers 
// following

@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    //Users who follow this user
    @ManyToMany
    @JoinTable(
        name = "user_followers",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<User>  followers;

    //Users this user is follows
    @ManyToMany
    @JoinTable(
        name = "user_following",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private Set<User>  following;
    public User(){}

    // Constructor with all fields
    public User(Long id, String username, String email, String password, Set<User> followers, Set<User> following) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.followers = followers;
        this.following = following;
    }
    
    // Getters and Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<User> getFollowers() {
        return this.followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<User> getFollowing() {
        return this.following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }


    //toString method
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", followers='" + getFollowers() + "'" +
            ", following='" + getFollowing() + "'" +
            "}";
    }

    

}