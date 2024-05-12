package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.dtos.PhotoDTO;
import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.PhotoService;
import com.epitech.pictsmanager.service.UserService;
import com.epitech.pictsmanager.service.AlbumService;
import com.epitech.pictsmanager.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Controller class for handling photo-related operations
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@RestController
@RequestMapping("/api/photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @Autowired
    UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private  AlbumService albumService;

    /**
     * Retrieves photos by album ID
     * @param ownerId The ID of the owner of the photos
     * @param albumId The ID of the album containing the photos
     * @return ResponseEntity containing the list of photos and HTTP status 200 (OK)
     */
    @Cacheable(cacheNames = "photos")
    @GetMapping("/user/{ownerId}/album/{albumId}")
    public ResponseEntity<List<PhotoDTO>> getPhotosByAlbumId(@PathVariable Long ownerId, @PathVariable Album albumId){
        List<Photo> photosList = photoService.getPhotosByOwnerIdAndAlbumId(ownerId, albumId);
        List<PhotoDTO> photoDTOs = photosList.stream().map(PhotoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(photoDTOs);
    }

    /**
     * Retrieves photos by user ID
     * @param userId The ID of the user
     * @return ResponseEntity containing the list of photos and HTTP status 200 (OK)
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Photo>> getPhotosByUserId(@PathVariable Long userId){
        List<Photo> photoListByUser = photoService.getPhotosByOwnerId(userId);
        return ResponseEntity.ok(photoListByUser);
    }
    
    /**
     * Retrieve image by Filename
     * @param filename The name of the file 
     * @return The file
     * @throws IOException
     */
    @GetMapping("/image/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String filePath = currentDirectory + File.separator + "photosData" + File.separator + filename;
        File file = new File(filePath);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        byte[] bytes = Files.readAllBytes(file.toPath());

        return ResponseEntity
                .ok()
                .body(bytes);
    }

    /**
     * Retrieves photos by user ID and visibility
     * @param userId The ID of the user
     * @param visibility The visibility status of the photos
     * @return ResponseEntity containing the list of photos and HTTP status 200 (OK)
     */
    @GetMapping("/user/{userId}/visibility/{visibility}")
    public ResponseEntity<List<Photo>> getPhotosByUserIdAndVisibility(@PathVariable Long userId, @PathVariable boolean visibility) {
        List<Photo> photos = photoService.getPhotosByUserIdAndVisibility(userId, visibility);
        return ResponseEntity.ok(photos);
    }

    /**
     * Retrieves all public photos
     * @return ResponseEntity containing the list of public photos and HTTP status 200 (OK)
     */
    @GetMapping("/public/photos")
    public ResponseEntity<List<Photo>> getAllPublicPhotos() {
        List<Photo> publicPhotos = photoService.getAllPublicPhotos();
        return ResponseEntity.ok(publicPhotos);
    }

    /**
     * Retrieves photos by album ID
     * @param albumId The ID of the album containing the photos
     * @return ResponseEntity containing the list of photos and HTTP status 200 (OK)
     */
    @GetMapping("/album/{albumId}")
    public ResponseEntity<List<Photo>> getPhotosByAlbumId(@PathVariable Album albumId) {
        List<Photo> photos = photoService.getPhotosByAlbumId(albumId);
        return ResponseEntity.ok(photos);
    }
    
    /**
     * Retrieves the last photo of a specified album
     * @param albumId The ID of the album from which to retrieve the last photo
     * @return ResponseEntity containing the last photo of the album if it exists, otherwise returns a 404 response
     */
    @GetMapping("/album/{albumId}/last")
    public ResponseEntity<Photo> getLastPhotoByAlbumId(@PathVariable Long albumId) {
        Album album = albumService.getAlbumById(albumId);
        if (album != null) {
            Photo lastPhoto = photoService.getLastPhotoByAlbumId(album);
            if (lastPhoto != null) {
                return ResponseEntity.ok(lastPhoto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves public photos by user ID
     * @param userId The ID of the user
     * @return ResponseEntity containing the list of public photos and HTTP status 200 (OK)
     */
    @GetMapping("/user/{userId}/public")
    public ResponseEntity<List<Photo>> getPublicPhotosByUserId(@PathVariable Long userId) {
        List<Photo> publicPhotosByUser = photoService.getPublicPhotosByOwnerId(userId);
        return ResponseEntity.ok(publicPhotosByUser);
    }

    /**
     * Deletes a photo by its ID
     * @param photoId The ID of the photo to delete
     * @param request The HTTP servlet request
     * @return ResponseEntity containing a success message or an error message and HTTP status
     */
    @DeleteMapping("delete/{photoId}")
    public ResponseEntity<String> delete(@PathVariable Long photoId, HttpServletRequest request) {

        String token = extractTokenFromRequest(request);
        if (token != null) {
            User existingUser = userService.getUserById(jwtUtil.extractUser(token).getId());
            Photo photo = photoService.getPhotoById(photoId);
            if(photo.getOwner().getId() == existingUser.getId()){
                photoService.deletePhotosById(photoId);
                return  ResponseEntity.ok().body("Photo deleted successfully");
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are note Owner of this photo");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    /**
     * Uploads a photo to the server
     * @param file The MultipartFile containing the photo data
     * @param name The name of the photo
     * @param description The description of the photo
     * @param albumId The ID of the album to which the photo belongs
     * @param request The HTTP servlet request
     * @return ResponseEntity containing a success message or an error message and HTTP status
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file,
                                              @RequestParam("name") String name,
                                              @RequestParam("description") String description,
                                              @RequestParam("albumId") Album albumId,

                                              HttpServletRequest request) {


            String token = extractTokenFromRequest(request);

            if (token != null) {
                User existingUser = userService.getUserById(jwtUtil.extractUser(token).getId());
                try {
                    LocalDateTime date = LocalDateTime.now();
                    String currentDirectory = System.getProperty("user.dir");

                    String photoDirPath = currentDirectory + File.separator + "photosData";
                    File photoDir = new File(photoDirPath);
                    if (!photoDir.exists()) {
                        photoDir.mkdirs();
                    }

                    String ownerIdDirPath = photoDirPath + File.separator + existingUser.getNom();
                    File ownerIdDir = new File(ownerIdDirPath);
                    if (!ownerIdDir.exists()) {
                        ownerIdDir.mkdirs();
                    }

                    String fileName = file.getOriginalFilename();
                    Path filePath = Paths.get(ownerIdDirPath, fileName);
                    file.transferTo(filePath.toFile());


                    Photo photo = new Photo(fileName, filePath.toString(), description, date, albumId, existingUser);
                    photoService.savePhoto(photo);

                    return ResponseEntity.ok().body("Photo uploaded successfully");
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload photo");
                }
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
    }

    /**
     * Constructs the directory path for a specific album and owner
     * @param albumId The ID of the album
     * @param ownerId The ID of the owner
     * @param currentDirectory The current directory path
     * @return The constructed directory path
     */
    private static String getString(Long albumId, User ownerId, String currentDirectory) {
        String photoDirPath = currentDirectory + File.separator + "photosData";
        File photoDir = new File(photoDirPath);
        if (!photoDir.exists()) {
            photoDir.mkdirs();
        }

        String ownerIdDirPath = photoDirPath + File.separator + ownerId.getNom();
        File ownerIdDir = new File(ownerIdDirPath);
        if (!ownerIdDir.exists()) {
            ownerIdDir.mkdirs();
        }

        String albumIdDirPath = ownerIdDirPath + File.separator + albumId;
        return albumIdDirPath;
    }

    /**
     * Extracts the JWT token from the HTTP servlet request
     * @param request The HTTP servlet request
     * @return The extracted JWT token, or null if not found
     */
    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

}