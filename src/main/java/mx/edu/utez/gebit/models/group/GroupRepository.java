package mx.edu.utez.gebit.models.group;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import mx.edu.utez.gebit.models.career.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findById(Long id);

    Optional<Group> findByDegreeAndLetter(Integer degree, String letter);

    Optional<List<Group>> findAllByCareer(String career);

    //Falta validar el id de la carrera enlazada con grupo
}
