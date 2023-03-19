package mx.edu.utez.gebit.controllers.student;

import mx.edu.utez.gebit.controllers.student.studentDto.StudentDto;
import mx.edu.utez.gebit.models.student.Student;
import mx.edu.utez.gebit.services.student.StudentService;
import mx.edu.utez.gebit.utils.Response;
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

    @PreAuthorize("hasRole('ADMIN')")
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
    @PostMapping("/")
    public ResponseEntity<Response<Student>> insert(@RequestBody StudentDto student){
        return new ResponseEntity<>(
                this.service.insert(student.getStudent()),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/")
    public ResponseEntity<Response<Student>> update(@RequestBody StudentDto student){
        return new ResponseEntity<>(
                this.service.update(student.getStudent()),
                HttpStatus.OK
        );
    }



}
