package mx.edu.utez.gebit.services.building;

import mx.edu.utez.gebit.models.building.Building;
import mx.edu.utez.gebit.models.building.BuildingRepository;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BuildingService {
    @Autowired
    private BuildingRepository repository;
    @Transactional(readOnly = true)
    public Response<List<Building>> getAll(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Building> insert(Building building){
        Optional<Building> exist = this.repository.findByName(building.getName());
        if(exist.isPresent()){
            return new Response<>(
                    null,
                    true,
                    400,
                    "El edicifio se encuentra registrado"
            );
        }
        return new Response<>(
                this.repository.saveAndFlush(building),
                false,
                200,
                "Edificio registrado correctamente"
        );
    }
    @Transactional(readOnly = true)
    public Response<Building> getOne(Long id){
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
                "El edificio se encontro"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Building> update(Building building){
        if(!this.repository.existsById(building.getId())){
            return new Response<>(
                    null,
                    true,
                    400,
                    "El edificio no se encontro"
            );
        }
        return new Response<>(
                this.repository.saveAndFlush(building),
                false,
                200,
                "Se ha actualizado correctamente el edificio "
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Boolean> changeStatus(Long id){
        if(!this.repository.existsById(id)){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Edificio no encontrado"
            );
        }
        Building building = this.repository.findById(id).get();
        building.setStatus(!building.getStatus());
        this.repository.saveAndFlush(building);
        return new Response<>(
                building.getStatus(),
                false,
                200,
                "Edificio actualizado status correctamente"
        );
    }


}
