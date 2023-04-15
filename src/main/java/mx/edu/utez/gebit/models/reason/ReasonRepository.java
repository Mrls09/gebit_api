package mx.edu.utez.gebit.models.reason;

import mx.edu.utez.gebit.models.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReasonRepository extends JpaRepository<Reason, Long> {
    @Override
    Optional<Reason> findById(Long aLong);
    @Query(value = "SELECT * FROM report_reason r WHERE r.user_id = ?1", nativeQuery = true)
    List<Report> findAllByUser(Long user_id);
    @Query(nativeQuery = true, value = "SELECT rr.reason_fk, r.name, COUNT(*) * 100.0 / (SELECT COUNT(*) FROM report_reason) as percentage FROM report_reason rr JOIN reason r ON rr.reason_fk = r.id GROUP BY rr.reason_fk, r.name")
    List<Object[]> getReasonAverages();
}
