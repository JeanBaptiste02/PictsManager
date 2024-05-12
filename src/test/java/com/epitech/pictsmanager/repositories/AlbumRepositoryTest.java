package com.epitech.pictsmanager.repositories;

import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.repositories.UserRepository;
import com.epitech.pictsmanager.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AlbumRepositoryTest {
	
	 	@Autowired
	 	private AlbumRepository albumRepository;
	 	@Autowired
	 	private UserRepository userRepository;
	 	private User user;
	    private Album album1;
	    private Album album2;

	    @BeforeEach
	    void setUp() {
	    	user = new User();
	        user.setNom("John Doe");
	        user.setEmail("test@example.com");
	        user.setPassword("defrzferz");
	        user = userRepository.save(user);

	        album1 = new Album();
	        album1.setTitle("Album 1");
	        album1.setOwner(user);

	        album2 = new Album();
	        album2.setTitle("Album 2");
	        album2.setOwner(user);
	    }


	    @AfterEach
	    void tearDown() {
	        albumRepository.deleteAll();
	    }

	    @Test
	    void testFindAll() {
	        albumRepository.save(album1);
	        albumRepository.save(album2);

	        List<Album> albums = albumRepository.findAll();
	        assertThat(albums).contains(album1, album2);
	    }

	    @Test
	    void testFindAlbumById() {
	        Album savedAlbum = albumRepository.save(album1);

	        Optional<Album> foundAlbum = albumRepository.findById(savedAlbum.getId());
	        assertThat(foundAlbum).isPresent();
	        assertThat(foundAlbum.get()).isEqualTo(savedAlbum);
	    }

	    @Test
	    void testGetAlbumById() {
	        Album savedAlbum = albumRepository.save(album1);

	        Album foundAlbum = albumRepository.getAlbumById(savedAlbum.getId());
	        assertThat(foundAlbum).isEqualTo(savedAlbum);
	    }

	    @Test
	    void testDeleteAlbumById() {
	        Album savedAlbum = albumRepository.save(album1);

	        albumRepository.deleteAlbumById(savedAlbum.getId());
	        Optional<Album> foundAlbum = albumRepository.findById(savedAlbum.getId());

	        assertThat(foundAlbum).isEmpty();
	    }

	    @Test
	    void testFindAlbumsByOwnerId() {
	        user = albumRepository.save(album1).getOwner();
	        album2.setOwner(user);

	        albumRepository.save(album1);
	        albumRepository.save(album2);

	        List<Album> albums = albumRepository.findAlbumsByOwnerId(user.getId());
	        assertThat(albums).hasSize(2);
	        assertThat(albums).contains(album1, album2);
	    }

	    @Test
	    void testGetMinAlbumIdByOwnerId() {
	        user = albumRepository.save(album1).getOwner();
	        album2.setOwner(user);

	        albumRepository.save(album1);
	        albumRepository.save(album2);

	        Long minAlbumId = albumRepository.getMinAlbumIdByOwnerId(user.getId());
	        assertThat(minAlbumId).isEqualTo(Math.min(album1.getId(), album2.getId()));
	    }

}
