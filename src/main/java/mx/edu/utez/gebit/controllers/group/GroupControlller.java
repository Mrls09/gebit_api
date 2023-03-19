package mx.edu.utez.gebit.controllers.group;

import mx.edu.utez.gebit.controllers.group.dtos.GroupDto;
import mx.edu.utez.gebit.models.group.Group;
import mx.edu.utez.gebit.services.group.GroupService;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-gebit/group")
@CrossOrigin(origins = {"*"})
public class GroupControlller {

    @Autowired
    private GroupService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Group>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<Group>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Response<Group>> insert(@RequestBody GroupDto groupDto){
        return new ResponseEntity<>(
                this.service.insert(groupDto.getGroup()),
                HttpStatus.CREATED
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<Response<Group>> update(@RequestBody GroupDto groupDto){
        return new ResponseEntity<>(
                this.service.update(groupDto.getGroup()),
                HttpStatus.OK
        );
    }

}
