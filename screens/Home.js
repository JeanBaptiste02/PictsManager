import React, { useEffect, useState } from "react";
import {
  StyleSheet,
  View,
  ScrollView,
  Image,
  TouchableOpacity,
  Modal,
  TextInput,
  Text,
  Button,
} from "react-native";
import { useImages } from "../context/ImageContext";
import Icon from "react-native-vector-icons/EvilIcons"; // Assurez-vous d'avoir importé correctement vos icônes
import FetchAllAlbums, { createAlbum } from "../services/Album.js";
import PhotoList from "./Users";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { useNavigation } from "@react-navigation/native";

const Home = () => {
  const { images } = useImages();
  const [modalVisible, setModalVisible] = useState(false);
  const [selectedImage, setSelectedImage] = useState(null);
  const [searchText, setSearchText] = useState("");
  const [token, setToken] = useState(null);

  const [albums, setAlbums] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const navigation = useNavigation();
  const navigateToPhotoList = () => {
    navigation.navigate("PhotoList");
  };

  useEffect(() => {
    const fetchToken = async () => {
      const storedToken = await AsyncStorage.getItem("jwtToken");
      console.log("Stored token:", storedToken);
      setToken(storedToken);
    };

    fetchToken();
  }, []);

  useEffect(() => {
    fetchAlbumsData(); // Call the function to fetch albums data
  }, []);

  const fetchAlbumsData = async () => {
    setLoading(true);
    const { albums, error } = await FetchAllAlbums(); // Call FetchAllAlbums function
    setAlbums(albums);
    setError(error);
    setLoading(false);
  };

  useEffect(() => {
    if (error) {
      console.log("Fetch error:", error);
    }
  }, [error]);

  useEffect(() => {
    console.log("Albums:");
    albums.forEach((album, index) => {
      console.log(`Album ${index + 1}:`);
      console.log(`ID: ${album.id}`);
      console.log(`Last Photo URL: ${album.lastPhotoUrl}`);
      console.log(`Title: ${album.title}`);
      console.log(`Owner: ${album.owner.email}`);
      console.log("-------------------");
    });
  }, [albums]);

  const people = [
    {
      name: "Mehdi",
      imageUri:
        "https://t3.ftcdn.net/jpg/02/43/12/34/360_F_243123463_zTooub557xEWABDLk0jJklDyLSGl2jrr.jpg",
    },
    {
      name: "Kamel",
      imageUri:
        "https://t3.ftcdn.net/jpg/02/43/12/34/360_F_243123463_zTooub557xEWABDLk0jJklDyLSGl2jrr.jpg",
    },
    {
      name: "Jb",
      imageUri:
        "https://t3.ftcdn.net/jpg/02/43/12/34/360_F_243123463_zTooub557xEWABDLk0jJklDyLSGl2jrr.jpg",
    },
    {
      name: "Victor",
      imageUri:
        "https://t3.ftcdn.net/jpg/02/43/12/34/360_F_243123463_zTooub557xEWABDLk0jJklDyLSGl2jrr.jpg",
    },
    {
      name: "Anna",
      imageUri:
        "https://t3.ftcdn.net/jpg/02/43/12/34/360_F_243123463_zTooub557xEWABDLk0jJklDyLSGl2jrr.jpg",
    },
    {
      name: "Luis",
      imageUri:
        "https://t3.ftcdn.net/jpg/02/43/12/34/360_F_243123463_zTooub557xEWABDLk0jJklDyLSGl2jrr.jpg",
    },
  ];
  const handleOpenImage = (uri) => {
    setSelectedImage(uri);
    setModalVisible(true);
  };

  const imagePairs = [];
  for (let i = 0; i < images.length; i += 2) {
    imagePairs.push(images.slice(i, i + 2));
  }

  const renderColumn = (pair, index) => (
    <View key={index} style={styles.column}>
      {pair.map((uri, index) => (
        <TouchableOpacity key={index} onPress={() => handleOpenImage(uri)}>
          <Image source={{ uri }} style={styles.image} resizeMode="cover" />
        </TouchableOpacity>
      ))}
    </View>
  );

  return (
    <View style={styles.container}>
      <View style={styles.searchContainer}>
        <Icon name="search" size={20} color="grey" style={styles.searchIcon} />
        <TextInput
          style={styles.input}
          onChangeText={setSearchText}
          value={searchText}
          placeholder="Search"
          placeholderTextColor="grey" // Définir la couleur du placeholder ici
        />
      </View>
      <View style={styles.headerContainer}>
        <Text style={styles.headerTitle}>Albums</Text>
        <TouchableOpacity style={styles.addButton} onPress={createAlbum}>
          <Text style={styles.addButtonText}>+ Add New</Text>
        </TouchableOpacity>
      </View>

      <ScrollView
        horizontal
        showsHorizontalScrollIndicator={false}
        contentContainerStyle={styles.scrollViewContainer}
      >
        {loading ? (
          <Text>Loading...</Text>
        ) : (
          albums.map((album, index) => (
            <View key={index}>
              <Text>{album.name}</Text>
            </View>
          ))
        )}
      </ScrollView>

      <View style={styles.container}>
        <Button title="View Photos" onPress={navigateToPhotoList} />
      </View>
      <View style={styles.sectionContainer}>
        <Text style={styles.sectionHeader}>People</Text>
        <ScrollView
          horizontal
          showsHorizontalScrollIndicator={false}
          contentContainerStyle={styles.peopleScrollView}
        >
          {people.map((person, index) => (
            <View key={index} style={styles.personContainer}>
              <Image
                source={{ uri: person.imageUri }}
                style={styles.personImage}
              />
              <Text style={styles.personName}>{person.name}</Text>
            </View>
          ))}
        </ScrollView>
      </View>

      <Modal
        animationType="slide"
        transparent={true}
        visible={modalVisible}
        onRequestClose={() => setModalVisible(false)}
      >
        <View style={styles.modalView}>
          <Image source={{ uri: selectedImage }} style={styles.fullImage} />
        </View>
      </Modal>
    </View>
  );
};

export default Home;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#000",
  },

  column: {
    flexDirection: "column",
    marginLeft: 10,
    marginRight: 10,
  },
  image: {
    width: 180,
    height: 180,
    marginBottom: 10,
    borderRadius: 8,
  },
  modalView: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "rgba(0, 0, 0, 0.9)",
  },
  fullImage: {
    width: "100%",
    height: "80%",
    resizeMode: "contain",
  },
  searchContainer: {
    flexDirection: "row",
    alignItems: "center",
    borderRadius: 10,
    margin: 12,
    marginTop: 30,
    backgroundColor: "black",
    borderColor: "grey",
    borderWidth: 1,
  },
  searchIcon: {
    marginLeft: 10,
  },
  input: {
    flex: 1,
    height: 40,
    padding: 10,
    color: "white",
  },
  headerContainer: {
    flexDirection: "row",
    justifyContent: "space-between", // Ceci alignera le titre et le bouton aux côtés opposés
    alignItems: "center",
    padding: 12, // Ajoutez le padding selon votre design
  },
  headerTitle: {
    color: "white",
    fontSize: 22, // Ajustez la taille selon votre design
    fontWeight: "bold",
  },
  addButton: {
    backgroundColor: "black", // Utilisez la couleur de votre design
    padding: 8,
    borderRadius: 20, // Ajustez la forme du bouton selon votre design
  },
  addButtonText: {
    color: "orange", // Utilisez la couleur de votre design
    fontSize: 16, // Ajustez la taille selon votre design
  },
  sectionHeader: {
    color: "white",
    fontSize: 22,
    fontWeight: "bold",
    padding: 12,
  },

  peopleScrollView: {
    marginBottom: 80,
    marginLeft: 12,
  },
  personContainer: {
    alignItems: "center", // Center items horizontally
    marginRight: 15, // Add space between each person
  },
  personImage: {
    width: 70, // Adjust the size accordingly
    height: 70, // Adjust the size accordingly
    borderRadius: 35, // Make it round
  },
  personName: {
    color: "white",
    textAlign: "center",
    marginTop: 4,
  },
  errorContainer: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  errorMessage: {
    color: "red",
    fontSize: 18,
  },
});
