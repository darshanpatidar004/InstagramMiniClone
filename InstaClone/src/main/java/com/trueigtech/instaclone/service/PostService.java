// package com.trueigtech.instaclone.service;

// import com.trueigtech.instaclone.model.Post;
// import com.trueigtech.instaclone.model.User;
// import com.trueigtech.instaclone.repository.PostRepository;
// import org.springframework.stereotype.Service;
// import java.util.List;
// import com.trueigtech.instaclone.repository.UserRepository;

// @Service
// public class PostService {

//     private final PostRepository postRepository;
//     private final UserRepository userRepository;

//     public PostService(PostRepository postRepository) {
//         this.postRepository = postRepository;
//     }

//     public Post createPost(Post post, User user) {

//         post.setUser(user);
//         return postRepository.save(post);
//     }

//     public Post likePost(Post post, User user) {

//         if (!post.getLikes().contains(user)) {
//             post.getLikes().add(user);
//         }

//         return postRepository.save(post);
//     }

//     public Post unlikePost(Post post, User user) {

//         post.getLikes().remove(user);
//         return postRepository.save(post);
//     }

//     public List<Post> getFeed(User user) {

//         return postRepository.findByUserInOrderByCreatedAtDesc((List<User>) user.getFollowing());
//     }
// }


package com.trueigtech.instaclone.service;

import com.trueigtech.instaclone.model.Post;
import com.trueigtech.instaclone.model.User;
import com.trueigtech.instaclone.repository.PostRepository;
import com.trueigtech.instaclone.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPost(String imageUrl, String caption, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setImageUrl(imageUrl);
        post.setCaption(caption);
        post.setUser(user);

        return postRepository.save(post);
    }

    public List<Post> getFeed(User user) {
        return postRepository.findByUserIn(user.getFollowing());
    }
}
