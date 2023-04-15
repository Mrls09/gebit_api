package mx.edu.utez.gebit.services.reason;

import mx.edu.utez.gebit.models.reason.Reason;
import mx.edu.utez.gebit.models.reason.ReasonRepository;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ReasonService {
    @Autowired
    private ReasonRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Reason>> getAll(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }
    @Transactional(readOnly = true)
    public Response<List<Object[]>> getCount(){
        return new Response<>(
                this.repository.getReasonAverages(),
                false,
                200,
                "OK"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Reason> insert(Reason reason){
        return new Response<>(
                this.repository.saveAndFlush(reason),
                false,
                200,
                "Razon registradad correctamente"
        );
    }
    @Transactional(readOnly = true)
    public Response<Reason> getOne(Long id){
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
                "Razon no encontrada"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Reason> update(Reason reason){
        if(!this.repository.existsById(reason.getId())){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Razon no encontrada"
            );
        }
        return new Response<>(
                this.repository.saveAndFlush(reason),
                false,
                200,
                "Se ha actualizado correctamente la razon"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Boolean> changeStatus(Long id){
        if(!this.repository.existsById(id)){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Reason no encontrada"
            );
        }
        Reason reason = this.repository.findById(id).get();
        reason.setStatus(!reason.getStatus());
        this.repository.saveAndFlush(reason);
        return new Response<>(
                reason.getStatus(),
                false,
                200,
                "Reason actuañlizado status correctamente"
        );
    }

}
