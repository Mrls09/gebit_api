package mx.edu.utez.gebit.controllers.laboratory;

import mx.edu.utez.gebit.controllers.laboratory.laboratoryDto.LaboratoryDto;
import mx.edu.utez.gebit.models.laboratory.Laboratory;
import mx.edu.utez.gebit.services.laboratory.LaboratoryService;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-gebit/laboratory")
@CrossOrigin(origins = {"*"})
public class LaboratoryController {
    @Autowired
    private LaboratoryService service;
    @GetMapping("/")
    public  ResponseEntity<Response<List<Laboratory>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<Laboratory>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Response<Laboratory>> insert(@RequestBody LaboratoryDto laboratory){
        return new ResponseEntity<>(
                this.service.insert(laboratory.getLaboratory()),
                HttpStatus.CREATED
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<Response<Laboratory>> update(@RequestBody LaboratoryDto laboratory){
        return new ResponseEntity<>(
                this.service.update(laboratory.getLaboratory()),
                HttpStatus.OK
        );
    }
}
