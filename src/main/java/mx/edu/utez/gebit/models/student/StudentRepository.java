package mx.edu.utez.gebit.models.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {
    Optional<Student> findById(Long id);

    List<Student> findAllByStatus(Boolean status);

    @Query(value = "SELECT * FROM student s WHERE s.group_id LIKE %?1%" , nativeQuery = true)
    List<Student> findAllByGroup(Long group_id);


    //List<Student> findAllBy

}
