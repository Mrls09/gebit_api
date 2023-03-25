package mx.edu.utez.gebit.controllers.career;

import mx.edu.utez.gebit.controllers.career.dtos.CareerDto;
import mx.edu.utez.gebit.models.career.Career;
import mx.edu.utez.gebit.services.career.CareerService;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-gebit/career")
@CrossOrigin(origins = {"*"})
public class CareerController {

    @Autowired
    private CareerService service;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<Response<List<Career>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Response<Career>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Response<Career>> insert(@RequestBody CareerDto career){
        return new ResponseEntity<>(
                this.service.insert(career.getCareer()),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<Response<Career>> update(@RequestBody CareerDto career){
        return new ResponseEntity<>(
                this.service.update(career.getCareer()),
                HttpStatus.OK
        );
    }
}
