package mx.edu.utez.gebit.models.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {
    Optional<Student> findById(Long id);

    List<Student> findAllByStatus(Boolean status);

    List<Student> findAllByGroup(Long id);

}
