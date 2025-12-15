import { useState } from "react";
import api from "../api/axiosConfig";

function CommentBox({ postId }) {
  const [text, setText] = useState("");

  const submitComment = async () => {
    if (!text.trim()) return;

    try {
      await api.post(`/comments/${postId}`, { text });
      setText("");
    } catch (err) {
      alert("Failed to add comment");
    }
  };

  return (
    <div style={{ marginTop: "10px" }}>
      <input
        type="text"
        placeholder="Write a comment..."
        value={text}
        onChange={(e) => setText(e.target.value)}
      />

      <button onClick={submitComment}>Post</button>
    </div>
  );
}

export default CommentBox;
