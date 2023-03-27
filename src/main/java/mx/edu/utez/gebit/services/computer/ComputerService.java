package mx.edu.utez.gebit.services.computer;

import mx.edu.utez.gebit.models.computer.Computer;
import mx.edu.utez.gebit.models.computer.ComputerRepository;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ComputerService {
    @Autowired
    private ComputerRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Computer>> getAll(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }
    @Transactional(readOnly = true)
    public Response<List<Computer>> getAllByLaboratory(Long id_laboratory){
        return new Response<>(
                this.repository.findAllByLaboratory(id_laboratory),
                false,
                200,
                "OK"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Computer> insert(Computer computer){
        Optional<Computer> exist = this.repository.findBySerial(computer.getSerial());
        if(exist.isPresent()){
            return new Response<>(
                    null,
                    true,
                    400,
                    "La computadora se encuentra registrada"
            );
        }
        return new Response<>(
                this.repository.saveAndFlush(computer),
                false,
                200,
                "La computadora ha sido registrada"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Computer> update(Computer computer){
        if(!this.repository.existsById(computer.getId())){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Computadora no encontrada"
            );
        }
        return new Response<>(
                this.repository.saveAndFlush(computer),
                false,
                200,
                "Se ha actualizado correctamente la computadora "
        );
    }
    @Transactional(readOnly = true)
    public Response<Computer> getOne(Long id){
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
                "Computadora no encontrada"
        );
    }
}
