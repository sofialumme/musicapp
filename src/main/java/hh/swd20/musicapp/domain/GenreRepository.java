package hh.swd20.musicapp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
	
	@Query(value="select * from Genre order by name", nativeQuery = true)
	public List<Genre> findAllSortByName();

}
