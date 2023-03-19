package mx.edu.utez.gebit.controllers.report;

import mx.edu.utez.gebit.controllers.report.reportDto.ReportDto;
import mx.edu.utez.gebit.models.report.Report;
import mx.edu.utez.gebit.services.report.ReportService;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{id}")
    public ResponseEntity<Response<Report>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
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
}
