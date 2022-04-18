package hh.swd20.musicapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.musicapp.domain.Artist;
import hh.swd20.musicapp.domain.ArtistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ArtistRepositoryTest {
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@Test
	public void createNewArtist() {
		Artist artist = new Artist("Artist", "Finland");
		artistRepository.save(artist);
		assertThat(artist.getId()).isNotNull();
	}
	
	@Test
	public void deleteArtist() {
		assertThat(artistRepository.findById((long) 3)).isNotEmpty();
		artistRepository.deleteById((long) 3);
		assertThat(artistRepository.findById((long) 3)).isEmpty();
	}
	
	@Test
	public void findByNameReturnsCountry() {
		List<Artist> artists = artistRepository.findByName("Ghost");
		assertThat(artists).hasSize(1);
		assertThat(artists.get(0).getCountry()).isEqualTo("Sweden");
	}

}
