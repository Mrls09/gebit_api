package mx.edu.utez.gebit.models.laboratory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {
    Optional<Laboratory> findById(Long id);
    Optional<Laboratory> findByName(String name);

    @Query(value = "SELECT * FROM laboratory l WHERE l.building_id = ?1" , nativeQuery = true)
    List<Laboratory> findAllByBuilding(Long building_id);
}
