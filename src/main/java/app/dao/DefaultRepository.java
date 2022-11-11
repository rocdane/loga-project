package app.dao;

import app.model.Default;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultRepository extends JpaRepository<Default,Long> {
}
