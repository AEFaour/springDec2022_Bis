package biblio.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import biblio.entity.Livre;

public interface LivreRepository extends JpaRepository<Livre, Integer> {
	public default long getCountLivreIdentique(Livre l) {
		return count(Example.of(l));
	}	
	
	@Query("select l from Livre l where parution >= :min and parution <= :max")
	public List<Livre> getLivreFromRangeParution(
			@Param("min")int min, 
			@Param("max")int max);
}


