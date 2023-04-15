package mx.edu.utez.gebit.models.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.bitacora.Bitacora;
import mx.edu.utez.gebit.models.reason.Reason;
import mx.edu.utez.gebit.security.entity.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "report")
@NoArgsConstructor
@Setter
@Getter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column
    private String reasonString;
    @Column(nullable = false)
    private Integer status;

    @ManyToOne()
    @JoinColumn(name = "bitacora_id")
    private Bitacora bitacora;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "report_reason",
            joinColumns = @JoinColumn(name = "report_fk", nullable = false),
            inverseJoinColumns = @JoinColumn(name="reason_fk", nullable = false)
    )
    private Set<Reason> reasons = new HashSet<>();

    public Report(Long id, String description, Integer status, Bitacora bitacora, User user, Set<Reason> reasons) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.bitacora = bitacora;
        this.user = user;
        this.reasons = reasons;
    }
}
