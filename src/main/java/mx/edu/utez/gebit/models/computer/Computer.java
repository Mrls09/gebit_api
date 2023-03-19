package mx.edu.utez.gebit.models.computer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.brand.Brand;
import mx.edu.utez.gebit.models.laboratory.Laboratory;

import javax.persistence.*;

@Entity
@Table(name = "computer")
@NoArgsConstructor
@Setter
@Getter
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String serial;
    @Column(nullable = false)
    private String model;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_brand", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "id_laboratory", nullable = false)
    private Laboratory laboratory;


    public Computer(Long id, String serial, String model, String description, Brand brand, Laboratory laboratory) {
        this.id = id;
        this.serial = serial;
        this.model = model;
        this.description = description;
        this.brand = brand;
        this.laboratory = laboratory;
    }
}
