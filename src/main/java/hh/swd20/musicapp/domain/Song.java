package hh.swd20.musicapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Song {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int trackno;
	private String name;
	private int length;
	
	@ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;
	
	public Song() {
		super();
	}

	public Song(long id, int trackno, String name, int length, Album album) {
		super();
		this.id = id;
		this.trackno = trackno;
		this.name = name;
		this.length = length;
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", trackno=" + trackno + ", name=" + name + ", length=" + length + ", album=" + album
				+ "]";
	}	

}
