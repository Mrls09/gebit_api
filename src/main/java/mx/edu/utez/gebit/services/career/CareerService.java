package mx.edu.utez.gebit.services.career;


import mx.edu.utez.gebit.models.career.Career;
import mx.edu.utez.gebit.models.career.CareerRepository;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CareerService {
    @Autowired
    private CareerRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Career>> getAll(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Career> insert(Career career){
        Optional<Career> exist = this.repository.findByName(career.getName());
        if(exist.isPresent()){
            return new Response<>(
                    null,
                    true,
                    400,
                    "La carrera ya se encuentra registrada."
            );
        }
        return new Response<>(
                this.repository.saveAndFlush(career),
                false,
                200,
                "Carrera registrada correctamente"
        );
    }

    @Transactional(readOnly = true)
    public Response<Career> getOne(Long id){
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
                "La carrera no se encontró"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Career> update(Career career){
        if(!this.repository.existsById(career.getId())){
            return new Response<>(
                    null,
                    true,
                    400,
                    "La carrera no se encontró"
            );
        }
        return new Response<>(
                this.repository.saveAndFlush(career),
                false,
                200,
                "Se ha actualizado correctamente la carrera"
        );
    }
}
