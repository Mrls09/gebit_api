package mx.edu.utez.gebit.security.repository;

import mx.edu.utez.gebit.security.entity.Rol;
import mx.edu.utez.gebit.security.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolName(RolName rolName);
}
