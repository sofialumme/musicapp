package hh.swd20.musicapp.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Artist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	private String country;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
	private List<Album> albums;
	
	public Artist() {
		super();
	}

	public Artist(long id, String name, String country) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + ", country=" + country + "]";
	}

}
