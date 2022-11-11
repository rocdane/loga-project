package app.dao;

import app.model.Furnisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnisherRepository extends JpaRepository<Furnisher,Long> {
}
