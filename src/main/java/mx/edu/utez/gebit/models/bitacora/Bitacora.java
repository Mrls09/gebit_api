package mx.edu.utez.gebit.models.bitacora;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.computer.Computer;
import mx.edu.utez.gebit.security.entity.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
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
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp created_at;

    @Column()
    private Timestamp finish_at;
    @ManyToOne
    @JoinColumn(name = "id_computer", nullable = false)
    private Computer computer;

    @ManyToOne
    @JoinColumn(name = "id_user" , nullable = false)
    private User user;

    public Bitacora(Long id, Timestamp created_at, Timestamp finish_at, Computer computer, User user) {
        this.id = id;
        this.created_at = created_at;
        this.finish_at = finish_at;
        this.computer = computer;
        this.user = user;
    }
}