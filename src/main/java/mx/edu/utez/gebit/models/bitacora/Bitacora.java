package mx.edu.utez.gebit.models.bitacora;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bitacora")
@NoArgsConstructor
@Setter
@Getter
public class Bitacora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@Temporal (TemporalType.TIMESTAMP)
    private Timestamp created;*/
}
