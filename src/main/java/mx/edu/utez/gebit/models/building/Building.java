package mx.edu.utez.gebit.models.building;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.laboratory.Laboratory;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "building")
@NoArgsConstructor
@Setter
@Getter
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;

    public Building(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @OneToMany(mappedBy = "building", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private List<Laboratory> laboratoryList;


    public Building(Long id, String name, List<Laboratory> laboratoryList) {
        this.id = id;
        this.name = name;
        this.laboratoryList = laboratoryList;
    }
}
