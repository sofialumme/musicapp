package hh.swd20.musicapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.musicapp.domain.Album;
import hh.swd20.musicapp.domain.AlbumRepository;
import hh.swd20.musicapp.domain.Artist;
import hh.swd20.musicapp.domain.ArtistRepository;
import hh.swd20.musicapp.domain.Genre;
import hh.swd20.musicapp.domain.GenreRepository;
import hh.swd20.musicapp.domain.Song;
import hh.swd20.musicapp.domain.SongRepository;

@SpringBootApplication
public class MusicappApplication {
	
	private static final Logger log = LoggerFactory.getLogger(MusicappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MusicappApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(GenreRepository genreRepository, ArtistRepository artistRepository, AlbumRepository albumRepository, SongRepository songRepository) {
		return (args) -> {
			log.info("save some demo genres");
			Genre genre1 = new Genre("Pop");
			Genre genre2 = new Genre("Metal");
			genreRepository.save(genre1);
			genreRepository.save(genre2);

			log.info("save some demo artists");
			Artist artist1 = new Artist("Magdalena Bay", "USA");
			Artist artist2 = new Artist("Ghost", "Sweden");
			artistRepository.save(artist1);
			artistRepository.save(artist2);
			
			log.info("save some demo albums");
			Album album1 = new Album("Mercurial World", 2021, artist1, genre1);
			Album album2 = new Album("Prequelle", 2018, artist2, genre2);
			Album album3 = new Album("Impera", 2022, artist2, genre2);
			albumRepository.save(album1);
			albumRepository.save(album2);
			albumRepository.save(album3);
			
			log.info("save some demo songs");
			Song song1 = new Song(1, "The End", album1);
			Song song2 = new Song(2, "Mercurial World", album1);
			Song song3 = new Song(1, "Ashes", album2);
			Song song4 = new Song(2, "Rats", album2);
			Song song5 = new Song(1, "Imperium", album3);
			songRepository.save(song1);
			songRepository.save(song2);
			songRepository.save(song3);
			songRepository.save(song4);
			songRepository.save(song5);

			log.info("fetch all genres");
			for (Genre genre : genreRepository.findAll()) {
				log.info(genre.toString());
			}
			
			log.info("fetch all artists");
			for (Artist artist : artistRepository.findAll()) {
				log.info(artist.toString());
			}

			log.info("fetch all albums");
			for (Album album : albumRepository.findAll()) {
				log.info(album.toString());
			}
			
			log.info("fetch all songs");
			for (Song song : songRepository.findAll()) {
				log.info(song.toString());
			}
		};
	}

}
