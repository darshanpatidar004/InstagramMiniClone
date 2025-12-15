package com.social.instagram.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// creating PostRequest DTO class which handle post creation requests
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    private String imageUrl;
    private String caption;
}
