package app.dao;

import app.model.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierRepository extends JpaRepository<Dossier,Long> {
    @Query(value = "from Dossier d, Automobile a where d.automobile=a.id and a.immatriculation = :immatriculation")
    List<Dossier> findDossierByImmatriculationNamedParams(@Param("immatriculation") String immatriculation);

    Dossier findByReference(String reference);
}
