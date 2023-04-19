package mx.edu.utez.gebit.services.report;

import mx.edu.utez.gebit.models.reason.Reason;
import mx.edu.utez.gebit.models.reason.ReasonRepository;
import mx.edu.utez.gebit.models.report.Report;
import mx.edu.utez.gebit.models.report.ReportRepository;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service
@Transactional
public class ReportService {
    @Autowired
    private ReportRepository repository;
    @Autowired
    private ReasonRepository reasonRepository;

    @Transactional(readOnly = true)
    public Response<List<Report>> getAll(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }
    @Transactional(readOnly = true)
    public Response<List<Report>> getAllTrue(){
        return new Response<>(
                this.repository.findAllByStatus(true),
                false,
                200,
                "OK"
        );
    }
    @Transactional(readOnly = true)
    public Response<List<Report>> getAllFalse(){
        return new Response<>(
                this.repository.findAllByStatus(false),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public Response<List<Report>> getAllByReason(Long id_reason){
        return new Response<>(
                this.repository.findAllByReason(id_reason),
                false,
                200,
                "OK"
        );
    }
    @Transactional(readOnly = true)
    public Response<List<Report>> getAllByUser(Long user_id){
        return new Response<>(
                this.repository.findAllByUser(user_id),
                false,
                200,
                "OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Report> insert(Report report){
        Set<Reason> reasons = report.getReasons();
        StringBuilder sb = new StringBuilder();
        for (Reason reason : reasons) {
            sb.append(reasonRepository.findById(reason.getId()).get().getName()).append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); // Elimina la última coma y espacio
        }
        String reasonsString = sb.toString();
        System.out.println(reasonsString); // Imprime los nombres de cada Reason separados por coma
        report.setReasonString(reasonsString);
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
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Boolean> changeStatus(Long id){
        if(!this.repository.existsById(id)){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Reporte no encontrado"
            );
        }
        Report report = this.repository.findById(id).get();
        report.setStatus(!report.getStatus());
        this.repository.saveAndFlush(report);
        return new Response<>(
                report.getStatus(),
                false,
                200,
                "Reporte status actualizado correctamente"
        );
    }

}

