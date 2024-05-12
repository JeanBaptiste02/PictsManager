import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

const baseUrl = "http://10.0.2.2:8080";
//const baseUrl = "http://192.168.1.8:8080";

const getEmailFromUser = async (token) => {
  try {
    const token = await AsyncStorage.getItem("jwtToken");
    const response = await axios.get(`${baseUrl}/api/users/getuser`, {
      headers: { Authorization: `Bearer ${token}` },
    });
    if (response.status === 200) {
      console.log(response.data);
      console.log(response.data.email);
      return response.data.email;
    } else {
      throw new Error("An error has occurred");
    }
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
};

const getIdFromUser = async (token) => {
  try {
    const token = await AsyncStorage.getItem("jwtToken");
    const response = await axios.get(`${baseUrl}/api/users/getuser`, {
      headers: { Authorization: `Bearer ${token}` },
    });
    if (response.status === 200) {
      console.log(response.data);
      console.log(response.data.id);
      return response.data.id;
    } else {
      throw new Error("An error has occurred");
    }
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
};

export default getEmailFromUser;
export { getIdFromUser };
