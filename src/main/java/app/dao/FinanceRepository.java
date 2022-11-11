package app.dao;

import app.model.Finance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface FinanceRepository extends JpaRepository<Finance,Long> {
    Finance findByDate(Date date);
    List<Finance> findAllByDateBetween(Date debut, Date fin);
}
