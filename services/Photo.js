import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

const baseUrl = "http://10.0.2.2:8080";

const getPhotosFromAlbum = async (albumId) => {
  try {
    const token = await AsyncStorage.getItem("jwtToken");

    const response = await axios.get(
      `${baseUrl}/api/photo/album/${albumId}`,
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

const getLastPhotoFromAlbum = async (albumId) => {
  try {
    const token = await AsyncStorage.getItem("jwtToken");

    const response = await axios.get(
      `${baseUrl}/api/photo/album/${albumId}/last`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );

    if (response.status === 200 || response.status === 201) {
      return response.data;
    } else {
      throw new Error("An error has occurred");
    }
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
};

export default getLastPhotoFromAlbum;
export { getPhotosFromAlbum };
