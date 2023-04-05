package mx.edu.utez.gebit.models.reason;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReasonRepository extends JpaRepository<Reason, Long> {
    @Override
    Optional<Reason> findById(Long aLong);
}
