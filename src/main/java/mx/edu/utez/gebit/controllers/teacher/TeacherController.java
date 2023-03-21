package mx.edu.utez.gebit.controllers.teacher;

import mx.edu.utez.gebit.controllers.teacher.teacherDto.TeacherDto;
import mx.edu.utez.gebit.models.teacher.Teacher;
import mx.edu.utez.gebit.services.teacher.TeacherService;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-gebit/teacher")
@CrossOrigin(origins = {"*"})
public class TeacherController {
    @Autowired
    private TeacherService service;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<Response<List<Teacher>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<Teacher>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }
    @PostMapping("/")
    public ResponseEntity<Response<Teacher>> insert(@RequestBody TeacherDto teacher){
        return new ResponseEntity<>(
                this.service.insert(teacher.getTeacher()),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/")
    public ResponseEntity<Response<Teacher>> update(@RequestBody TeacherDto teacher){
        return new ResponseEntity<>(
                this.service.update(teacher.getTeacher()),
                HttpStatus.OK
        );
    }
}
