package com.epitech.pictsmanager.service;


import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.repositories.PhotoRepository;
import com.epitech.pictsmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Service class for managing photo-related operations
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Retrieves all photos
     * @return A list of all photos
     */
    public List<Photo> getPhotos(){
        return photoRepository.findAll();
    }

    /**
     * Retrieves photos by owner ID and album ID
     * @param ownerId The ID of the owner
     * @param albumId The ID of the album
     * @return A list of photos belonging to the specified owner and album
     */
    @Transactional(readOnly = true)
    public List<Photo> getPhotosByOwnerIdAndAlbumId(Long ownerId, Album albumId) {
        return photoRepository.findByOwnerIdAndAlbumId(ownerId, albumId);
    }

    /**
     * Retrieves photos by owner ID
     * @param ownerId The ID of the owner
     * @return A list of photos belonging to the specified owner
     */
    @Transactional(readOnly = true)
    public List<Photo> getPhotosByOwnerId(Long ownerId){
        return photoRepository.findByOwnerId_Id(ownerId);
    }

    /**
     * Finds a user by ID
     * @param userId The ID of the user to find
     * @return The user entity if found, otherwise null
     */
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    /**
     * Retrieves a photo by its ID
     * @param id The ID of the photo to retrieve
     * @return The photo entity if found, otherwise null
     */
    public Photo getPhotoById(Long id){
        return photoRepository.findPhotoById(id);
    }

    /**
     * Retrieves photos by album ID
     * @param albumId The ID of the album
     * @return A list of photos belonging to the specified album
     */
    public List<Photo> getPhotosByAlbumId(Album albumId) {
        return photoRepository.findByAlbumId(albumId);
    }

    /**
     * Saves a photo
     * @param photo The photo entity to save
     */
    public void savePhoto(Photo photo) {
        photoRepository.save(photo);
    }

    /**
     * Retrieves the paths of photos owned by a specific user
     * @param userId The ID of the user
     * @return A list of paths to photos owned by the specified user
     */
    public List<String> getPhotoPathsByUserId(long userId) {
        return photoRepository.findPhotoPathsByOwner_id(userId);
    }

    /**
     * Combines multiple images into a single byte array
     * @param photoPaths A list of paths to the images
     * @return A byte array representing the combined images
     * @throws IOException If an I/O error occurs
     */
    public byte[] combineImages(List<String> photoPaths) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (String path : photoPaths) {
            byte[] imageBytes = Files.readAllBytes(Paths.get(path));
            baos.write(imageBytes);
        }
        return baos.toByteArray();
    }

    /**
     * Compresses and saves an image
     * @param file The image file to compress and save
     * @param albumIdDirPath The directory path where the compressed image will be saved
     * @param fileName The name of the compressed image file
     * @return The name of the compressed image file
     * @throws IOException If an I/O error occurs
     */
    public String compressAndSaveImage(MultipartFile file, String albumIdDirPath, String fileName) throws IOException {
        File compressedFile = new File(albumIdDirPath, "compressed_" + fileName); // new compressed file name
        BufferedImage image = ImageIO.read(file.getInputStream()); // reading the image

        ImageWriteParam param = new JPEGImageWriteParam(null); // img quality
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.2f); // quality from 0.0 to 1.0

        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next(); // ImageWriter in jpg format

        // flux de sorti img compressé
        try (OutputStream os = new FileOutputStream(compressedFile)) {
            ImageOutputStream ios = ImageIO.createImageOutputStream(os);
            writer.setOutput(ios);
            // making the image
            writer.write(null, new IIOImage(image, null, null), param);
            //flux end
            ios.close();
            writer.dispose();
        }
        return compressedFile.getName();
    }

    /**
     * Retrieves photos owned by a specific user with a given visibility status
     * @param userId The ID of the owner user
     * @param visibility The visibility status
     * @return A list of photos owned by the specified user with the specified visibility status
     */
    public List<Photo> getPhotosByUserIdAndVisibility(Long userId, boolean visibility) {
        return photoRepository.findByOwnerIdAndVisibility(userId, visibility);
    }

    /**
     * Retrieves all public photos
     * @return A list of all public photos
     */
    public List<Photo> getAllPublicPhotos() {
        return photoRepository.findAllPublicPhotos();
    }

    /**
     * Deletes a photo by its ID
     * @param photoId The ID of the photo to delete
     * @throws RuntimeException If the photo with the specified ID is not found
     */
    public void deletePhotosById(Long photoId) {
        Photo photo = photoRepository.findPhotoById(photoId);
        // insert photo in local
       if(photo != null){
           File file = new File(photo.getPath());
           if (file.exists()) {
               file.delete();
           }
           photoRepository.delete(photo); // insert photo in database
       }else{
           throw new RuntimeException("Photo not found" + photoId);
       }
    }
}