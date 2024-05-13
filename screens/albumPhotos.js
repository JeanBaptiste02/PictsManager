import React, { useState, useEffect } from "react";
import {
  View,
  Text,
  FlatList,
  Image,
  StyleSheet,
  Dimensions,
  TouchableOpacity,
  Modal,
  TouchableWithoutFeedback,
} from "react-native";
import axios from "axios";


const AlbumPhotos = ({route})=>{
    const { id, title } = route.params;
    const [photos, setPhotos] = useState([]);
    [selectedPhoto, setSelectedPhoto] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
       
          `http://10.0.2.2:8080/api/photo/album/${id}`
        );
        setPhotos(response.data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [id]);

    const formatPath = (path) => {
        return path.replace(/\\/g, "/");
      };

      const toggleSelectedPhoto = (photo) => {
        setSelectedPhoto(photo === selectedPhoto ? null : photo);
      };

      return (
        <View style={styles.container}>
          <Text style={styles.title}> {title}'s photos:</Text>
          <FlatList
            data={photos}
            keyExtractor={(item) => item.id.toString()}
            renderItem={({ item }) => {
              const formattedPath = formatPath(item.path);
              return (
                <TouchableOpacity onPress={() => toggleSelectedPhoto(item)}>
                  <Image
                    source={{ uri: `http://10.0.2.2:8080${formattedPath}` }}
                    style={styles.image}
                    onError={(error) =>
                      console.log(
                        "Erreur de chargement de l'image:",
                        error.nativeEvent.error
                      )
                    }
                  />
                </TouchableOpacity>
              );
            }}
            numColumns={3}
          />
    
          {/* Modal pour l'image agrandie */}
          <Modal visible={!!selectedPhoto} transparent={true} animationType="fade">
            <TouchableWithoutFeedback onPress={() => toggleSelectedPhoto(null)}>
              <View style={styles.modalOverlay}>
                <View style={styles.modalContent}>
                  <Image
                    source={{
                      uri: selectedPhoto
                        ? `http://10.0.2.2:8080${formatPath(selectedPhoto.path)}`
                        : null,
                    }}
                    style={styles.selectedImage}
                    resizeMode="contain"
                  />
                </View>
              </View>
            </TouchableWithoutFeedback>
          </Modal>
        </View>
      );
    };
    
    const styles = StyleSheet.create({
      container: {
        flex: 1,
        padding: 10,
        backgroundColor: "#000",
      },
      title: {
        fontSize: 20,
        fontWeight: "bold",
        color: "white",
        marginBottom: 10,
      },
      image: {
        width: (Dimensions.get("window").width - 40) / 3,
        height: 150,
        marginBottom: 10,
        marginRight: 10,
        borderRadius: 10,
      },
      modalOverlay: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: "rgba(0, 0, 0, 0.5)",
      },
      modalContent: {
        backgroundColor: "white",
        padding: 20,
        borderRadius: 10,
      },
      selectedImage: {
        width: Dimensions.get("window").width - 40,
        height: Dimensions.get("window").height - 40,
        resizeMode: "contain",
      },
    });
    
    export default AlbumPhotos;