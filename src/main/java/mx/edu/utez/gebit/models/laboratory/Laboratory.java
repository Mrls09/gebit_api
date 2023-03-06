package mx.edu.utez.gebit.models.laboratory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.building.Building;

import javax.persistence.*;

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

    @ManyToOne()
    @JoinColumn(name = "building_id")
    private Building building;


    public Laboratory(Long id, String name, Integer capacity, Building building) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.building = building;
    }
}
