import { useState } from "react";
import { createPost } from "../api/postApi";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";

function CreatePost() {
  const [imageUrl, setImageUrl] = useState("");
  const [caption, setCaption] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!imageUrl) {
      alert("Image URL is required");
      return;
    }

    await createPost({ imageUrl, caption });
    navigate("/feed");
  };

  return (
    <>
      <Navbar />
      <div className="auth-container">
        <h2>Create Post</h2>

        <form onSubmit={handleSubmit}>
          <input
            placeholder="Image URL"
            value={imageUrl}
            onChange={(e) => setImageUrl(e.target.value)}
          />

          <input
            placeholder="Caption"
            value={caption}
            onChange={(e) => setCaption(e.target.value)}
          />

          <button type="submit">Post</button>
        </form>
      </div>
    </>
  );
}

export default CreatePost;
