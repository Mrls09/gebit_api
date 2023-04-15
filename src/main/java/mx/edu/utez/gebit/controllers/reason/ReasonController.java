package mx.edu.utez.gebit.controllers.reason;

import mx.edu.utez.gebit.controllers.reason.dtos.ReasonDto;
import mx.edu.utez.gebit.models.reason.Reason;
import mx.edu.utez.gebit.services.reason.ReasonService;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-gebit/reason")
@CrossOrigin(origins = {"*"})
public class ReasonController {
    @Autowired
    private ReasonService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Reason>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("count/")
    public ResponseEntity<Response<List<Object[]>>> getCount(){
        return new ResponseEntity<>(
                this.service.getCount(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Reason>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Response<Reason>> insert(@RequestBody ReasonDto reason){
        return new ResponseEntity<>(
                this.service.insert(reason.getReason()),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<Response<Reason>> update(@RequestBody ReasonDto reason){
        return new ResponseEntity<>(
                this.service.update(reason.getReason()),
                HttpStatus.OK
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.changeStatus(id),
                HttpStatus.OK
        );
    }

}
