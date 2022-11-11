package app.dao;

import app.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProfileRepository extends JpaRepository<Profile,Long> {
    List<Profile> findByNameOrSurname(String name);

    Profile findByUsername(String username);
}
