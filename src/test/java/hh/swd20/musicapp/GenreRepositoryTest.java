package hh.swd20.musicapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.musicapp.domain.Genre;
import hh.swd20.musicapp.domain.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GenreRepositoryTest {
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Test
	public void createNewGenre() {
		Genre genre = new Genre("Genre");
		genreRepository.save(genre);
		assertThat(genre.getId()).isNotNull();
	}
	
	@Test
	public void deleteGenre() {
		assertThat(genreRepository.findById((long) 2)).isNotEmpty();
		genreRepository.deleteById((long) 2);
		assertThat(genreRepository.findById((long) 2)).isEmpty();
	}
	
}
