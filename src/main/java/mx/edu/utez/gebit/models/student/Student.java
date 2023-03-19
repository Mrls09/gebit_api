package mx.edu.utez.gebit.models.student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.gebit.models.group.Group;
import mx.edu.utez.gebit.security.entity.User;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "student")
@NoArgsConstructor
@Setter
@Getter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String lastname;
    @Column(nullable = false)
    private Boolean status;
    @ManyToOne()
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private User user;


    public Student(Long id, String name, String lastname, Boolean status, Group group, User user) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.status = status;
        this.group = group;
        this.user = user;
        this.user.setStudent(this);
    }
}
