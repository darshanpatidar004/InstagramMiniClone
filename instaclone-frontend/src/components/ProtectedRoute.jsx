// import { Navigate } from "react-router-dom";

// function ProtectedRoute({ children }) {
//   const token = localStorage.getItem("token");

//   if (!token) {
//     return <Navigate to="/login" />;
//   }

//   return children;
// }

// export default ProtectedRoute;


import { Navigate } from "react-router-dom";
import { isLoggedIn } from "../utils/authHelper";

const ProtectedRoute = ({ children }) => {
  if (!isLoggedIn()) {
    return <Navigate to="/login" />;
  }
  return children;
};

export default ProtectedRoute;
