import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";
import getEmailFromUser from "./Users";
import { getIdFromUser } from "./Users";
import getLastPhotoFromAlbum, { getPhotosFromAlbum } from "./Photo.js";

const baseUrl = "http://10.0.2.2:8080";
//const baseUrl = "http://192.168.1.8:8080";

const createAlbum = async (albumTitle) => {
  try {
    const token = await AsyncStorage.getItem("jwtToken");
    const email = await getEmailFromUser(token);  // Assurez-vous que cette fonction est bien définie ailleurs dans votre code

    const body = {
      title: albumTitle,
      owner: {
        email: email,
      },
    };

    const response = await axios.post(`${baseUrl}/api/album/create`, body, {
      headers: { Authorization: `Bearer ${token}` },
    });

    if (response.status === 200 || response.status === 201) {
      console.log("Album created successfully", response.data);
      return response.data;
    } else {
      console.error("Server response status:", response.status);
      console.error("Server response body:", response.data);
      throw new Error("An error has occurred");
    }
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
};

/**
 * create the first album for the user when the user is created
 *
 * @param {*} user
 * @param {*} token
 * @returns a response to know if the album has been created
 */
const createFirstAlbum = async (user, token) => {
  try {
    const body = {
      title: "Default",
      owner: {
        email: user,
      },
    };

    console.log("checking if the token is the good one", token);

    const response = await axios.post(`${baseUrl}/api/album/create`, body, {
      headers: { Authorization: `Bearer ${token}` },
    });

    if (response.status === 200 || response.status === 201) {
      return response.data;
    } else {
      console.error("Server response status:", response.status);
      console.error("Server response body:", response.data);
      throw new Error("An error has occurred");
    }
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
};

/**
 *
 * @param {*} token
 * @returns an album ID of type integer, it is the minimum (default) ID of the album
 */
const getAlbumById = async (token) => {
  try {
    const minId = await getIdFromUser(token);
    console.log("Min ID:", minId); // Log the min ID to ensure it's correct

    const response = await axios.get(
      `${baseUrl}/api/album/min-id/user/${minId}`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    console.log("Response data:", response.data); 

    if (response.status === 200) {
      console.log("Album ID:", response.data);
      return response.data;
    } else {
      throw new Error("An error has occurred");
    }
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
};

const FetchAllAlbums = async () => {
  try {
    const jwtToken = await AsyncStorage.getItem("jwtToken");
    if (!jwtToken) {
      throw new Error("JWT token not found.");
    }

    const url = `${baseUrl}/api/album/user`;
    const response = await axios.get(url, {
      headers: { Authorization: `Bearer ${jwtToken}` },
    });

    if (response.status === 200 || response.status === 201) {
      const albums = response.data;
      for (let album of albums) {
        const photoData = await getLastPhotoFromAlbum(album.id);
        if (photoData) {
          album.lastPhotoUrl = `${baseUrl}/photosData/${photoData.owner.nom}/${photoData.path}`;
        } else {
          album.lastPhotoUrl = `${baseUrl}/photosData/empty/empty.jpg`;
        }
      }
      return { albums, error: null };
    } else {
      throw new Error("Failed to fetch albums.");
    }
  } catch (error) {
    return { albums: [], error: error.message };
  }
};

export default FetchAllAlbums;
export { createAlbum, getAlbumById, createFirstAlbum };
