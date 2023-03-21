package mx.edu.utez.gebit.models.teacher;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.security.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
@NoArgsConstructor
@Setter
@Getter

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String lastname;
    @Column(nullable = false)
    private Boolean status;

    @OneToOne(mappedBy = "teacher", cascade = CascadeType.ALL)
    private User user;

    public Teacher(Long id, String name, String lastname, Boolean status, User user) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.status = status;
        this.user = user;
        this.user.setTeacher(this);
    }
}
