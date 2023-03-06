package mx.edu.utez.gebit.controllers.brand;

import mx.edu.utez.gebit.controllers.brand.dtos.BrandDto;
import mx.edu.utez.gebit.models.brand.Brand;
import mx.edu.utez.gebit.services.brand.BrandService;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-gebit/brand")
@CrossOrigin(origins = {"*"})
public class BrandController {
    @Autowired
    private BrandService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Brand>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<Brand>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }
    @PostMapping("/")
    public ResponseEntity<Response<Brand>> insert(@RequestBody BrandDto brand){
        return new ResponseEntity<>(
                this.service.insert(brand.getBrand()),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/")
    public ResponseEntity<Response<Brand>> update(@RequestBody BrandDto brand){
        return new ResponseEntity<>(
                this.service.update(brand.getBrand()),
                HttpStatus.OK
        );
    }
}
