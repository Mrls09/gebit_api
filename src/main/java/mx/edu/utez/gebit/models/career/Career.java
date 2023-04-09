package mx.edu.utez.gebit.models.career;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.group.Group;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "career")
@NoArgsConstructor
@Setter
@Getter
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = false, length = 20)
    private String abbreviation;
    @Column(nullable = false,columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Group> groupList;

    public Career(Long id, String name, String abbreviation, Boolean status, List<Group> groupList) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.status = status;
        this.groupList = groupList;
    }
}
