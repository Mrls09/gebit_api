package mx.edu.utez.gebit.services.brand;

import mx.edu.utez.gebit.models.brand.Brand;
import mx.edu.utez.gebit.models.brand.BrandRepository;
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
public class BrandService {
    @Autowired
    private BrandRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Brand>> getAll(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Brand> insert(Brand brand){
        Optional<Brand> exist = this.repository.findByName(brand.getName());
        if(exist.isPresent()){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Se encuentra registrada -> Marca - Brand"
            );
        }
        return new Response<>(
                this.repository.saveAndFlush(brand),
                false,
                200,
                "Brand registrado correctamente"
        );
    }
    @Transactional(readOnly = true)
    public Response<Brand> getOne(Long id){
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
                "Brand no encontrado"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Brand> update(Brand brand){
        if(!this.repository.existsById(brand.getId())){
            return new Response<>(
                    null,
                    true,
                    400,
                    "Brand no encontrado"
            );
        }
        return new Response<>(
                this.repository.saveAndFlush(brand),
                false,
                200,
                "Success update brand"
        );
    }
}
