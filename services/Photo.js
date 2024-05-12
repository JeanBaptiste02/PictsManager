import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

const baseUrl = "http://10.0.2.2:8080";

const getPhotosFromAlbum = async (albumId) => {
  try {
    const token = await AsyncStorage.getItem("jwtToken");

    const response = await axios.get(
      `${baseUrl}/api/photo/getphotos/${albumId}`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );

    if (response.status === 200) {
      return response.data;
    } else {
      throw new Error("An error has occurred");
    }
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
};

const getFirstPhotoFromAlbum = async (albumId) => {
  try {
    const token = await AsyncStorage.getItem("jwtToken");

    const response = await axios.get(
      `${baseUrl}/api/photo/getfirstphoto/${albumId}`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );

    if (response.status === 200) {
      return response.data;
    } else {
      throw new Error("An error has occurred");
    }
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
};
