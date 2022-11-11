package app.dao;

import app.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SpaceRepository extends JpaRepository<Space,Long> {
    Space findByName();
}
