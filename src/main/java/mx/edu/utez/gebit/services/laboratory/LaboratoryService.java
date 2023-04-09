package mx.edu.utez.gebit.services.laboratory;

import mx.edu.utez.gebit.models.building.Building;
import mx.edu.utez.gebit.models.laboratory.Laboratory;
import mx.edu.utez.gebit.models.laboratory.LaboratoryRepository;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LaboratoryService {
    @Autowired
    private LaboratoryRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Laboratory>> getAll(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }
    @Transactional(readOnly = true)
    public Response<List<Laboratory>> getAllByBuilding(Long id){
        return new Response<>(
                this.repository.findAllByBuilding(id),
                false,
                200,
                "OK"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Laboratory> insert(Laboratory laboratory){
        Optional<Laboratory> exist = this.repository.findByName(laboratory.getName());
        if (exist.isPresent()){
            return new Response<>(
                    null,
                    true,
                    400,
                    "El laboratorio ya se encuentra registrado"
            );
        }
        return new Response<>(
                this.repository.saveAndFlush(laboratory),
                false,
                200,
                "Laboratorio registrado correctamente"
        );
    }
    @Transactional(readOnly = true)
    public Response<Laboratory> getOne(Long id){
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
                "El laboratorio no se encontró"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Laboratory> update(Laboratory laboratory){
        if (!this.repository.existsById(laboratory.getId())){
            return new Response<>(
                    null,
                    true,
                    400,
                    "El laboratorio no se encontro"
            );
        }
        return new Response<>(
                this.repository.saveAndFlush(laboratory),
                false,
                200,
                "Se ha actualizado correctamente el laboratorio"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Boolean> changeStatus(Long id){
        if(!this.repository.existsById(id)){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Laboratorio no econtrado"
            );
        }
        Laboratory laboratory = this.repository.findById(id).get();
        laboratory.setStatus(!laboratory.getStatus());
        this.repository.saveAndFlush(laboratory);
        return new Response<>(
                laboratory.getStatus(),
                false,
                200,
                "Laboratorio actualizado status correctamente"
        );
    }
}
