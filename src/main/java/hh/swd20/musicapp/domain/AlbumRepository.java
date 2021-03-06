package hh.swd20.musicapp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AlbumRepository extends CrudRepository<Album, Long> {
	
	List<Album> findByName(@Param("name") String name);
	
	@Query(value="select * from Album order by name", nativeQuery = true)
	public List<Album> findAllSortByName();
	
	@Query(value="select * from Album order by year", nativeQuery = true)
	public List<Album> findAllSortByYear();
	
	@Query(value="select * from Album where artist_id = ?1 order by name", nativeQuery = true)
	public List<Album> findByArtistSortByName(Long artistId);
	
	@Query(value="select * from Album where artist_id = ?1 order by year", nativeQuery = true)
	public List<Album> findByArtistSortByYear(Long artistId);
	
	@Query(value="select * from Album where genre_id = ?1 order by name", nativeQuery = true)
	public List<Album> findByGenreSortByName(Long genreId);
	
	@Query(value="select * from Album where genre_id = ?1 order by year", nativeQuery = true)
	public List<Album> findByGenreSortByYear(Long genreId);

}
