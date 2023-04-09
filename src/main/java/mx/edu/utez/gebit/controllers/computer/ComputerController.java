package mx.edu.utez.gebit.controllers.computer;


import mx.edu.utez.gebit.controllers.computer.dtos.ComputerDto;
import mx.edu.utez.gebit.models.computer.Computer;
import mx.edu.utez.gebit.services.computer.ComputerService;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-gebit/computer")
@CrossOrigin(origins = {"*"})
public class ComputerController {
    @Autowired
    private ComputerService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Computer>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<Computer>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }
    @GetMapping("laboratory/{id}")
    public ResponseEntity<Response<List<Computer>>> getAllByLaboratory(@PathVariable Long id){
        return new ResponseEntity<>(
                this.service.getAllByLaboratory(id),
                HttpStatus.OK
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Response<Computer>> insert(@RequestBody ComputerDto computer){
        return new ResponseEntity<>(
                this.service.insert(computer.getComputer()),
                HttpStatus.CREATED
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<Response<Computer>> update(@RequestBody ComputerDto computer){
        return new ResponseEntity<>(
                this.service.update(computer.getComputer()),
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.changeStatus(id),
                HttpStatus.OK
        );
    }


}
