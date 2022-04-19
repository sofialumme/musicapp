package hh.swd20.musicapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Song {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull(message = "Track number cannot be null")
	@Positive(message = "Track number must be a positive number")
	private int trackno;
	
	@NotBlank(message = "Name cannot be null or blank")
	private String name;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "album_id")
	@NotNull
    private Album album;
	
	public Song() {
		super();
	}

	public Song(int trackno, String name, Album album) {
		super();
		this.trackno = trackno;
		this.name = name;
		this.album = album;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTrackno() {
		return trackno;
	}

	public void setTrackno(int trackno) {
		this.trackno = trackno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", trackno=" + trackno + ", name=" + name + ", album=" + album
				+ "]";
	}	

}
