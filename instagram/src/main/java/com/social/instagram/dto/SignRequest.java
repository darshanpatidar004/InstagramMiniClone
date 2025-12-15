package com.social.instagram.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// creating SignRequest DTO class which handle sign up requests
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignRequest {

    private String username;
    private String password;
}

