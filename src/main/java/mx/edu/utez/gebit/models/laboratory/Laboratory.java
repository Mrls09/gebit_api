package mx.edu.utez.gebit.models.laboratory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.building.Building;
import mx.edu.utez.gebit.models.computer.Computer;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "laboratory")
@NoArgsConstructor
@Setter
@Getter
public class Laboratory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer capacity;
    @Column(nullable = false,columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;

    @ManyToOne()
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "laboratory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Computer> computerList;


    public Laboratory(Long id, String name, Integer capacity, Boolean status, Building building, List<Computer> computerList) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.status = status;
        this.building = building;
        this.computerList = computerList;
    }
}
