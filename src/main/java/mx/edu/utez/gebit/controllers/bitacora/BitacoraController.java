package mx.edu.utez.gebit.controllers.bitacora;

import mx.edu.utez.gebit.controllers.bitacora.bitacoraDto.BitacoraDto;
import mx.edu.utez.gebit.models.bitacora.Bitacora;
import mx.edu.utez.gebit.services.bitacora.BitacoraService;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-gebit/bitacora")
@CrossOrigin(origins = {"*"})
public class BitacoraController {
    @Autowired
    private BitacoraService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Bitacora>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<Bitacora>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }
    @GetMapping("user/{id}")
    public ResponseEntity<Response<List<Bitacora>>> getAllByUser(@PathVariable Long id){
        return new ResponseEntity<>(
                this.service.getAllByUser(id),
                HttpStatus.OK
        );
    }
    @GetMapping("computer/{id}")
    public ResponseEntity<Response<List<Bitacora>>> getAllByComputer(@PathVariable Long id){
        return new ResponseEntity<>(
                this.service.getAllByComputer(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<Response<Bitacora>> insert(@RequestBody BitacoraDto bitacora){
        return new ResponseEntity<>(
                this.service.insert(bitacora.getBitacora()),
                HttpStatus.CREATED
        );
    }
    @PutMapping("end/{id}")
    public ResponseEntity<Response<Bitacora>> finish(@PathVariable Long id){
        return new ResponseEntity<>(
                this.service.finish(id),
                HttpStatus.OK
        );
    }
}
