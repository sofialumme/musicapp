package hh.swd20.musicapp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SongRepository extends CrudRepository<Song, Long> {
	
	@Query(value="select * from Song where album_id = ?1 order by trackno", nativeQuery = true)
	public List<Song> findByAlbumSortByTrackno(Long albumId);

}
