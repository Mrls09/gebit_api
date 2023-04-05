package mx.edu.utez.gebit.services.reason;

import mx.edu.utez.gebit.models.reason.Reason;
import mx.edu.utez.gebit.models.reason.ReasonRepository;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

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
}
