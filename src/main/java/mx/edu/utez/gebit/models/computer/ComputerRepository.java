package mx.edu.utez.gebit.models.computer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

    Optional<Computer> findById(Long id);

    Optional<Computer> findBySerial(String serial);

    Optional<Computer> findAllByBrand(Long id);

    @Query(value = "SELECT * FROM computer c WHERE c.id_laboratory = ?1", nativeQuery = true)
    List<Computer> findAllByLaboratory(Long id_laboratory);

}
