package mx.edu.utez.gebit.controllers.building;

import mx.edu.utez.gebit.controllers.building.dtos.BuildingDto;
import mx.edu.utez.gebit.models.building.Building;
import mx.edu.utez.gebit.services.building.BuildingService;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-gebit/building")
@CrossOrigin(origins = {"*"})
public class BuildingController {

    @Autowired
    private BuildingService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Building>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<Building>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }
    @PostMapping("/")
    public ResponseEntity<Response<Building>> insert(@RequestBody BuildingDto building){
        return new ResponseEntity<>(
                this.service.insert(building.getBuilding()),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/")
    public ResponseEntity<Response<Building>> update(@RequestBody BuildingDto building){
        return new ResponseEntity<>(
                this.service.update(building.getBuilding()),
                HttpStatus.OK
        );
    }
}
