import { useState } from "react";
import { toast } from "react-hot-toast";
import ForumApi from "../../DataFetching/DataFetching"; // Adjust the import if needed
import config from "../../Config/config";

const useSignup = () => {
  const [loading, setLoading] = useState(false);

  const signup = async ({
    fullName,
    username,
    email,
    password,
    confirmPassword,
  }) => {
    setLoading(true);
    try {
      //   const headers = { "x-client-type": "web" };
      const res = await ForumApi.post(
        `${config.apiUrl}/api/v1/signup`,
        {
          fullName,
          username,
          email,
          password,
          confirmPassword,
        },
        {
          headers: {
            "Content-Type": "application/json",
            "x-client-type": "web", // Include the client type header
          },
        }
      );
      // console.log("headers:", headers);

      const data = res.data;

      if (data.error) {
        if (data.unmetCriteria) {
          toast.error("Password does not meet the following criteria:");
          data.unmetCriteria.forEach((criterion) => toast.error(criterion));
        } else {
          throw new Error(data.error);
        }
      } else {
        toast.success(
          data.message ||
            "Sign up successful! Please check your email for verification."
        );
        console.log(data);
        return data; // Return user data for potential use in UI
      }
    } catch (error) {
      toast.error(error.message);
    } finally {
      setLoading(false);
    }
  };

  return { signup, loading };
};

export default useSignup;
