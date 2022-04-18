package hh.swd20.musicapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.musicapp.web.AlbumController;
import hh.swd20.musicapp.web.ArtistController;
import hh.swd20.musicapp.web.GenreController;
import hh.swd20.musicapp.web.SongController;
import hh.swd20.musicapp.web.WebController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MusicappApplicationTests {
	
	@Autowired
	private AlbumController albumController;
	
	@Autowired
	private ArtistController artistController;
	
	@Autowired
	private GenreController genreController;
	
	@Autowired
	private SongController songController;
	
	@Autowired
	private WebController webController;

	@Test
	public void albumControllerLoads() {
		assertThat(albumController).isNotNull();
	}
	
	@Test
	public void artistControllerLoads() {
		assertThat(artistController).isNotNull();
	}

	@Test
	public void genreControllerLoads() {
		assertThat(genreController).isNotNull();
	}
	
	@Test
	public void songControllerLoads() {
		assertThat(songController).isNotNull();
	}

	@Test
	public void webControllerLoads() {
		assertThat(webController).isNotNull();
	}

}
