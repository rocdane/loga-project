package app.dao;

import app.model.AuthSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AuthSessionRepository extends JpaRepository<AuthSession,Long> {
    AuthSession findByToken(String token);
}
