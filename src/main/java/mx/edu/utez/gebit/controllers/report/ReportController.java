package mx.edu.utez.gebit.controllers.report;

import mx.edu.utez.gebit.controllers.reason.dtos.ReasonDto;
import mx.edu.utez.gebit.controllers.report.reportDto.ReportDto;
import mx.edu.utez.gebit.models.reason.Reason;
import mx.edu.utez.gebit.models.reason.ReasonRepository;
import mx.edu.utez.gebit.models.report.Report;
import mx.edu.utez.gebit.models.report.ReportRepository;
import mx.edu.utez.gebit.security.repository.UserRepository;
import mx.edu.utez.gebit.services.report.ReportService;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api-gebit/report")
@CrossOrigin(origins = {"*"})
public class ReportController {
    @Autowired
    private ReportService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<Response<List<Report>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("pendientes/")
    public ResponseEntity<Response<List<Report>>> getAllTrue(){
        return new ResponseEntity<>(
                this.service.getAllTrue(),
                HttpStatus.OK
        );
    }
    @GetMapping("finalizados/")
    public ResponseEntity<Response<List<Report>>> getAllFalse(){
        return new ResponseEntity<>(
                this.service.getAllFalse(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<Report>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }
    @GetMapping("reason/{id}")
    public ResponseEntity<Response<List<Report>>> getAllByReason(@PathVariable Long id){
        return new ResponseEntity<>(
                this.service.getAllByReason(id),
                HttpStatus.OK
        );
    }
    @GetMapping("user/{id}")
    public ResponseEntity<Response<List<Report>>> getAllByUser(@PathVariable Long id){
        return new ResponseEntity<>(
                this.service.getAllByUser(id),
                HttpStatus.OK
        );
    }
    @PostMapping("/")
    public ResponseEntity<Response<Report>> insert(@RequestBody ReportDto report){
        return new ResponseEntity<>(
                this.service.insert(report.getReport()),
                HttpStatus.CREATED
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> changeStatus(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.changeStatus(id),
                HttpStatus.OK
        );
    }

}
