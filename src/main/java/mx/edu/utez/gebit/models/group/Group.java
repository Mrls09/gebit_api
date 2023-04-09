package mx.edu.utez.gebit.models.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.career.Career;

import javax.persistence.*;

@Entity
@Table(name = "grupo")
@NoArgsConstructor
@Setter
@Getter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer degree;
    @Column(nullable = false)
    private String letter;
    @Column(nullable = false,columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "id_career", nullable = false)
    private Career career;

    public Group(Long id, Integer degree, String letter, Boolean status, Career career) {
        this.id = id;
        this.degree = degree;
        this.letter = letter;
        this.status = status;
        this.career = career;
    }
}
