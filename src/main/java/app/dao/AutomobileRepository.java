package app.dao;

import app.model.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutomobileRepository extends JpaRepository<Automobile,Long> {
    @Query(value = "from Automobile where immtriculation=:txt")
    List<Automobile> findAllByImmatriculationContaining(String immatriculation);

    Optional<Automobile> findByImmatriculation(String immatriculation);
}
