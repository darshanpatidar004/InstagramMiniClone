import { likePost, unlikePost } from "../api/postApi";
import CommentBox from "./CommentBox";
import { useState } from "react";

function PostCard({ post }) {
  const [liked, setLiked] = useState(false);

  const handleLike = async () => {
    if (liked) {
      await unlikePost(post.id);
      setLiked(false);
    } else {
      await likePost(post.id);
      setLiked(true);
    }
  };

  return (
    <div style={{ border: "1px solid #ddd", marginBottom: "15px", padding: "10px" }}>
      <p><b>{post.user?.username}</b></p>

      <img
        src={post.imageUrl}
        alt="post"
        style={{ width: "100%", maxHeight: "300px" }}
      />

      <p>{post.caption}</p>

      <button onClick={handleLike}>
        {liked ? "Unlike" : "Like"} ({post.likes.length})
      </button>

      <CommentBox postId={post.id} />
    </div>
  );
}

export default PostCard;
