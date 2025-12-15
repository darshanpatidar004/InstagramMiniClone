import { Link, useNavigate } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";


function Navbar() {
  const navigate = useNavigate();
// const { logout } = useContext(AuthContext);

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (
    <nav style={{ padding: "10px", borderBottom: "1px solid #ccc" }}>
      <Link to="/feed" style={{ marginRight: "10px" }}>
        InstaClone
      </Link>

      <Link to="/create" style={{ marginRight: "10px" }}>
        Create Post
      </Link>

      <button onClick={handleLogout}>Logout</button>
    </nav>
  );
}

export default Navbar;
