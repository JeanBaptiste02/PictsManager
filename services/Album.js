import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";
import getEmailFromUser from "./Users";
import { getIdFromUser } from "./Users";

const baseUrl = "http://10.0.2.2:8080";
//const baseUrl = "http://192.168.1.8:8080";

const createAlbum = async () => {
  try {
    const token = await AsyncStorage.getItem("jwtToken");

    const email = await getEmailFromUser(token);

    console.log("Email from user:", email);

    const body = {
      title: "Default",
      owner: {
        email: email,
      },
    };

    console.log("checking if the token is the good one", token);
    // In the createAlbum function

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
    console.log("Response data:", response.data); // Log the response data to ensure it's correct

    if (response.status === 200) {
      console.log("Album ID:", response.data); // Log the album ID to ensure it's correct
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
  const [albums, setAlbums] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const token = await AsyncStorage.getItem("jwtToken");

  useEffect(() => {
    const abortController = new AbortController();
    const url = `${baseUrl}/api/album/all`;

    const fetchAlbums = async (token) => {
      try {
        const response = await axios.get(url, {
          signal: abortController.signal,
          headers: { Authorization: `Bearer ${token}` },
        });
        if (response.status === 200) {
          const albums = response.data;
          for (let album of albums) {
            const photoResponse = await axios.get(
              `${baseUrl}/api/photo/album/${album.id}`,
              {
                signal: abortController.signal,
                headers: { Authorization: `Bearer ${token}` },
              }
            );
            if (photoResponse.status === 200 && photoResponse.data.length > 0) {
              album.firstImage = photoResponse.data[0].path;
            }
          }
          setAlbums(albums);
        } else {
          throw new Error("Error fetching albums");
        }
      } catch (error) {
        if (!abortController.signal.aborted) {
          if (error.response) {
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
          } else if (error.request) {
            console.log(error.request);
          } else {
            // Something happened in setting up the request that triggered an Error
            console.log("Error", error.message);
          }
          setError(error.message);
        }
      } finally {
        setLoading(false);
      }
    };

    fetchAlbums(token);

    return () => abortController.abort();
  }, [token]);

  return { albums, error, loading };
};

export default FetchAllAlbums;
export { createAlbum, getAlbumById, createFirstAlbum };
