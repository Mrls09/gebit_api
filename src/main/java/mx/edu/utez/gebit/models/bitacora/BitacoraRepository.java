package mx.edu.utez.gebit.models.bitacora;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora , Long> {
    @Query(value = "SELECT * FROM bitacora b WHERE b.id_user LIKE %?1%", nativeQuery = true)
    List<Bitacora> findAllByUser(Long id_user);

    @Query(value = "SELECT * FROM bitacora b WHERE b.id_computer LIKE %?1%" , nativeQuery = true)
    List<Bitacora> findAllByComputer(Long id_computer);
}
