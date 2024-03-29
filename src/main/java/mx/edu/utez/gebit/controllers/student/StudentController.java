package mx.edu.utez.gebit.controllers.student;

import mx.edu.utez.gebit.controllers.student.studentDto.StudentDto;
import mx.edu.utez.gebit.models.group.Group;
import mx.edu.utez.gebit.models.student.Student;
import mx.edu.utez.gebit.services.student.StudentService;
import mx.edu.utez.gebit.utils.Response;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-gebit/student")
@CrossOrigin(origins = {"*"})
public class StudentController {
    @Autowired
    private StudentService service;

    @PreAuthorize("hasRole('ADMIN') " + "|| hasRole('TEACHER')")
    @GetMapping("/")
    public ResponseEntity<Response<List<Student>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<Student>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }
    @GetMapping("grupo/{id}")
    public ResponseEntity<Response<List<Student>>> getAllByGroup(@PathVariable Long id){
        return new ResponseEntity<>(
                this.service.getAllByGroup(id),
                HttpStatus.OK
        );
    }
    @GetMapping("status/{status}")
    public ResponseEntity<Response<List<Student>>> getAllByStatus(@PathVariable Boolean status){
        return new ResponseEntity<>(
                this.service.getAllByStatus(status),
                HttpStatus.OK
        );
    }
    @PostMapping("/")
    public ResponseEntity<Response<Student>> insert(@RequestBody StudentDto student){
        return new ResponseEntity<>(
                this.service.insert(student.getStudent()),
                HttpStatus.CREATED
        );
    }
    @PreAuthorize("hasRole('ADMIN') " + "|| hasRole('USER')")
    @PutMapping("/")
    public ResponseEntity<Response<Student>> update(@RequestBody StudentDto student){
        return new ResponseEntity<>(
                this.service.update(student.getStudent()),
                HttpStatus.OK
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> changeStatus(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.changeStatus(id),
                HttpStatus.OK
        );
    }
}
