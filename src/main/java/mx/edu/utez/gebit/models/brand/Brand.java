package mx.edu.utez.gebit.models.brand;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.computer.Computer;
import mx.edu.utez.gebit.models.laboratory.Laboratory;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brand")
@NoArgsConstructor
@Setter
@Getter
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 100)
    private String name;
    @Column(nullable = false,columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Computer> computerList;

    public Brand(Long id, String name, Boolean status, List<Computer> computerList) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.computerList = computerList;
    }
}
