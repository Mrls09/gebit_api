package mx.edu.utez.gebit.services.report;

import mx.edu.utez.gebit.models.report.Report;
import mx.edu.utez.gebit.models.report.ReportRepository;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReportService {
    @Autowired
    private ReportRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Report>> getAll(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Report> insert(Report report){
        return new Response<>(
                this.repository.saveAndFlush(report),
                false,
                200,
                "Reporte registrado correctamente"
        );
    }
    @Transactional(readOnly = true)
    public Response<Report> getOne(Long id){
        if(this.repository.existsById(id)){
            return new Response<>(
                    this.repository.findById(id).get(),
                    false,
                    200,
                    "OK"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "Reporte no encontrado"
        );
    }

}
