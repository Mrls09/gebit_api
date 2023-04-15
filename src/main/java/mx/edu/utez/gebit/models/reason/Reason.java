package mx.edu.utez.gebit.models.reason;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.report.Report;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    @Column(nullable = false,columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;

    public Reason(Long id, String name, Boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
