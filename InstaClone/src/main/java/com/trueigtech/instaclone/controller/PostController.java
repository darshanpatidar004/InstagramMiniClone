// package com.trueigtech.instaclone.controller;

// import com.trueigtech.instaclone.model.Post;
// import com.trueigtech.instaclone.model.User;
// import com.trueigtech.instaclone.repository.PostRepository;
// import com.trueigtech.instaclone.repository.UserRepository;

// import jakarta.servlet.http.HttpServletRequest;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/posts")
// public class PostController {

//     private final PostRepository postRepository;
//     private final UserRepository userRepository;

//     public PostController(PostRepository postRepository,UserRepository userRepository) {
//         this.postRepository = postRepository;
//         this.userRepository = userRepository;
//     }

//     @PostMapping
//     public Post createPost(@RequestBody Post post, HttpServletRequest request) {

//         String email = (String) request.getAttribute("email");
//         User user = userRepository.findByEmail(email).orElseThrow();

//         post.setUser(user);
//         return postRepository.save(post);
//     }

//     @PostMapping("/{id}/like")
//     public Post likePost(@PathVariable Long id,HttpServletRequest request) {

//         String email = (String) request.getAttribute("email");
//         User user = userRepository.findByEmail(email).orElseThrow();
//         Post post = postRepository.findById(id).orElseThrow();

//         if (!post.getLikes().contains(user)) {
//             post.getLikes().add(user);
//         }

//         return postRepository.save(post);
//     }

//     @PostMapping("/{id}/unlike")
//     public Post unlikePost(@PathVariable Long id,HttpServletRequest request) {

//         String email = (String) request.getAttribute("email");
//         User user = userRepository.findByEmail(email).orElseThrow();
//         Post post = postRepository.findById(id).orElseThrow();

//         post.getLikes().remove(user);
//         return postRepository.save(post);
//     }
// }


package com.trueigtech.instaclone.controller;

import com.trueigtech.instaclone.model.Post;
import com.trueigtech.instaclone.model.User;
import com.trueigtech.instaclone.repository.UserRepository;
import com.trueigtech.instaclone.service.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;

    public PostController(PostService postService, UserRepository userRepository) {
        this.postService = postService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public Post createPost(@RequestBody Post post, Authentication auth) {
        String email = auth.getName();
        return postService.createPost(
                post.getImageUrl(),
                post.getCaption(),
                email
        );
    }

    @GetMapping("/feed")
    public List<Post> getFeed(Authentication auth) {
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        return postService.getFeed(user);
    }
}
