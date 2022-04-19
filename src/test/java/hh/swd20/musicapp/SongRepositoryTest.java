package hh.swd20.musicapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.musicapp.domain.AlbumRepository;
import hh.swd20.musicapp.domain.Song;
import hh.swd20.musicapp.domain.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SongRepositoryTest {
	
	@Autowired
	private SongRepository songRepository;
	
	@Autowired
	private AlbumRepository albumRepository;

	@Test
	public void createNewSong() {
		Song song = new Song(15, "Song", albumRepository.findByName("Prequelle").get(0));
		songRepository.save(song);
		assertThat(song.getId()).isNotNull();
	}
	
	@Test
	public void deleteSong() {
		assertThat(songRepository.findById((long) 12)).isNotEmpty();
		songRepository.deleteById((long) 12);
		assertThat(songRepository.findById((long) 12)).isEmpty();
	}
	
	@Test
	public void findByAlbumSortByTrackNoReturnsTracksInOrder() {
		List<Song> songs = songRepository.findByAlbumSortByTrackno((long) 7);
		assertThat(songs.get(0).getTrackno()).isEqualTo(1);
	}

}
