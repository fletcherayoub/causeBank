// import { useState } from "react";
// import axios from "axios";
// import { useAuthContext } from "../../Context/AuthContext";
// import toast from "react-hot-toast";
// import config from "../../config/config";

// const useCreatePost = () => {
//   const [error, setError] = useState(null);
//   const [loading, setLoading] = useState(false);
//   const { authUser } = useAuthContext();
//   // tested : working
//   const createPost = async (formData, cancelPost) => {
//     if (!authUser) {
//       toast.error("You need to be logged in to create a post");
//       return;
//     }
//     console.log(formData);
//     try {
//       setLoading(true);
//       const response = await axios.post(
//         `${config.apiUrl}/api/createPost`,
//         formData,
//         {
//           withCredentials: true, // Send cookies with request
//           headers: { "x-client-type": "web" },
//         }
//       );
//       setLoading(false);
//       cancelPost();
//       return response.data;
//     } catch (error) {
//       setLoading(false);
//       setError(error.response.data.error);
//       throw new Error(error.response.data.error); // Throw error to handle it in the component
//     }
//   };

//   return { createPost, error, loading };
// };

// export default useCreatePost;
