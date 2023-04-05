package mx.edu.utez.gebit.models.reason;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reason")
@NoArgsConstructor
@Setter
@Getter
public class Reason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Reason(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
