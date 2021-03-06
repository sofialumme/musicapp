package hh.swd20.musicapp.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Album {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Name cannot be null or blank")
	private String name;

	@NotNull(message = "Year cannot be null")
	@PositiveOrZero(message = "Year must be a positive integer")
	private int year;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
	private List<Song> songs;
	
	@ManyToOne
    @JoinColumn(name = "artist_id")
	@NotNull
	private Artist artist;
	
	@ManyToOne
    @JoinColumn(name = "genre_id")
	@NotNull
	private Genre genre;
	
	public Album() {
		super();
	}

	public Album(String name, int year, Artist artist, Genre genre) {
		super();
		this.name = name;
		this.year = year;
		this.artist = artist;
		this.genre = genre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", name=" + name + ", year=" + year + ", artist=" + artist + ", genre=" + genre
				+ "]";
	}
	
}
