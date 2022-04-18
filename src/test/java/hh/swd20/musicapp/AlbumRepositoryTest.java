package hh.swd20.musicapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.musicapp.domain.Album;
import hh.swd20.musicapp.domain.AlbumRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AlbumRepositoryTest {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Test
	public void createNewAlbum() {
		Album album = new Album("Album", 2022, null, null);
		albumRepository.save(album);
		assertThat(album.getId()).isNotNull();
	}
	
	@Test
	public void deleteAlbum() {
		assertThat(albumRepository.findById((long) 7)).isNotEmpty();
		albumRepository.deleteById((long) 7);
		assertThat(albumRepository.findById((long) 7)).isEmpty();
	}
	
	@Test
	public void findByNameReturnsYear() {
		List<Album> albums = albumRepository.findByName("Prequelle");
		assertThat(albums).hasSize(1);
		assertThat(albums.get(0).getYear()).isEqualTo(2018);
	}

}
