package mx.edu.utez.gebit.models.report;

import mx.edu.utez.gebit.models.computer.Computer;
import mx.edu.utez.gebit.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findById(Long id);

    @Query(value = "SELECT * FROM report r WHERE r.reason_id = ?1", nativeQuery = true)
    List<Report> findAllByReason(Long id_reason);

    @Query(value = "SELECT * FROM report r WHERE r.user_id = ?1", nativeQuery = true)
    List<Report> findAllByUser(Long user_id);

    @Query(value = "SELECT * FROM report r WHERE r.status = ?1", nativeQuery = true)
    List<Report> findAllByStatus(Boolean status);

}
