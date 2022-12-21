package biblio.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import biblio.entity.Adherent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdherentRepository extends JpaRepository<Adherent, Integer> {

    public default boolean isPresent(Adherent adherent) {


        return exists(Example.of(adherent));
    }

    @Query("select case when count(a)> 0 then true else false end from Adherent as a where a.nom=:nom and a.prenom=:prenom and a.email=:email")
    public boolean isPresent2(@Param("nom") String nom, @Param("prenom") String prenom, @Param("email") String email);

    @Query("select case when count(a)> 0 then true else false end from Adherent as a where a.nom=:#{#ad.getNom()}nom and a.prenom=:#{#ad.prenom} and a.email=:#{#ad.email}")
    public boolean isPresent3(@Param("ad") Adherent ad);

    public default boolean isPresent4(Adherent adherent) {
        Example <Adherent> ea = Example.of(new Adherent(adherent.getNom(), adherent.getPrenom(), null, adherent.getEmail()));

        return exists(ea);
    }

    List<Adherent> findByPrenom(String prenom);


}
