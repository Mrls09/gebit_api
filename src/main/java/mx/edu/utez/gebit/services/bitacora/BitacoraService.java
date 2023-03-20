package mx.edu.utez.gebit.services.bitacora;

import mx.edu.utez.gebit.models.bitacora.Bitacora;
import mx.edu.utez.gebit.models.bitacora.BitacoraRepository;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class BitacoraService {
    @Autowired
    private BitacoraRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Bitacora>> getAll(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }
    @Transactional(readOnly = true)
    public Response<Bitacora> getOne(Long id){
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
                "Registro en Bitacora no encontrado"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Bitacora> insert(Bitacora bitacora){
        return new Response<>(
                this.repository.saveAndFlush(bitacora),
                false,
                200,
                "Bitacora Registrada"
        );
    }
}
