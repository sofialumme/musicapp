package hh.swd20.musicapp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long> {

	@Query(value="select * from Artist order by name", nativeQuery = true)
	public List<Artist> findAllSortByName();
	
}
