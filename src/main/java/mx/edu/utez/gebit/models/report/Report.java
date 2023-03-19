package mx.edu.utez.gebit.models.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.computer.Computer;
import mx.edu.utez.gebit.security.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "report")
@NoArgsConstructor
@Setter
@Getter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String problem;
    @Column
    private String description;
    @Column(nullable = false)
    private Integer status;
    @ManyToOne()
    @JoinColumn(name = "computer_id")
    private Computer computer;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Report(Long id, String problem, String description, Integer status, Computer computer, User user) {
        this.id = id;
        this.problem = problem;
        this.description = description;
        this.status = status;
        this.computer = computer;
        this.user = user;
    }
}
