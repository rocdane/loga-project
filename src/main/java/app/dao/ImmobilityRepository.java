package app.dao;

import app.model.Immobility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmobilityRepository extends JpaRepository<Immobility,Long> {
}
