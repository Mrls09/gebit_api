package mx.edu.utez.gebit.services.group;

import mx.edu.utez.gebit.models.group.Group;
import mx.edu.utez.gebit.models.group.GroupRepository;
import mx.edu.utez.gebit.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupService {
    @Autowired
    private GroupRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Group>> getAll(){
         return new Response<>(
                 this.repository.findAll(),
                 false,
                 200,
                 "Ok"
         );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Group> insert(Group group){
        Optional<Group> exist = this.repository.findByDegreeAndLetter(group.getDegree(), group.getLetter());
        if(exist.isPresent()){
            return new Response<>(
                    null,
                    true,
                    400,
                    "El grupo ya se encuentra registrado"
            );
        }
        return new Response<>(
                this.repository.saveAndFlush(group),
                false,
                200,
                "Se ha registrado correctamente el grupo"
        );
    }

    @Transactional(readOnly = true)
    public Response<Group> getOne(Long id){
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
                "El grupo no se encontró"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Group> update(Group group){
        if(!this.repository.existsById(group.getId())){
            return new Response<>(
                    null,
                    true,
                    400,
                    "El grupo no se encontró"
            );
        }
        return new Response<>(
                this.repository.saveAndFlush(group),
                false,
                200,
                "Se ha actualizado correctamente el grupo"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Boolean> changeStatus(Long id){
        if(!this.repository.existsById(id)){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Grupo no encontrado"
            );
        }
        Group group = this.repository.findById(id).get();
        group.setStatus(!group.getStatus());
        this.repository.saveAndFlush(group);
        return new Response<>(
                group.getStatus(),
                false,
                200,
                "Grupo actualizado status correctamente"
        );
    }
}
