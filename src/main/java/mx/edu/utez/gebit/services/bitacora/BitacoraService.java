package mx.edu.utez.gebit.services.bitacora;

import mx.edu.utez.gebit.models.bitacora.Bitacora;
import mx.edu.utez.gebit.models.bitacora.BitacoraRepository;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    @Transactional(rollbackFor =  {SQLException.class})
    public Response<Bitacora> finish(Long id){
        if(!this.repository.existsById(id)){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Bitacora no encontrada"
            ) ;
        }
        Bitacora bitacora = repository.findById(id).get();
        LocalDateTime horaActual = LocalDateTime.now();
        bitacora.setFinish_at(Timestamp.valueOf(horaActual));
        repository.saveAndFlush(bitacora);
        return new Response<>(
                this.repository.saveAndFlush(bitacora),
                false,
                200,
                "Se registro la salida"
        );
    }
    @Transactional(readOnly = true)
    public Response<List<Bitacora>> getAllByUser(Long id){
        return new Response<>(
                this.repository.findAllByUser(id),
                false,
                200,
                "OK"
        );
    }
    @Transactional(readOnly = true)
    public Response<List<Bitacora>> getAllByComputer(Long id){
        return new Response<>(
                this.repository.findAllByComputer(id),
                false,
                200,
                "OK"
        );
    }

}
