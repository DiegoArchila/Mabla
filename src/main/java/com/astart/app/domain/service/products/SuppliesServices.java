package com.astart.app.domain.service.products;

import com.astart.app.persistence.entity.products.ProductsGroupsEntity;
import com.astart.app.persistence.entity.products.SuppliesEntity;
import com.astart.app.persistence.repository.products.SuppliesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SuppliesServices {

    private final SuppliesRepository suppliesRepository;

    @Autowired
    public SuppliesServices(SuppliesRepository suppliesRepository){
        this.suppliesRepository=suppliesRepository;
    }

    public Boolean save(SuppliesEntity supply){
        SuppliesEntity temp = this.suppliesRepository.save(supply);
        return this.suppliesRepository.existsById(temp.getId());
    }

    public Optional<SuppliesEntity> getById(Integer id){
        return this.suppliesRepository.findById(id);
    }

    public List<SuppliesEntity> getAllNotDeleted(){
        return this.suppliesRepository.findAllNotDeleted();
    }

    public Boolean update(SuppliesEntity supplies){
        Optional<SuppliesEntity> old = this.suppliesRepository.findById(supplies.getId());
        this.suppliesRepository.save(supplies);
        return old.equals(this.suppliesRepository.findById(supplies.getId()));
    }


    public Boolean delete(Integer id){

        if (this.suppliesRepository.existsById(id)) {
            this.suppliesRepository.deleteById(id);
            if (this.suppliesRepository.findById(id).get().getDeleted_at()!=null) {
                return true;
            }
        } else {
            return false;
        }

        return false;
    }

    public Optional<List<SuppliesEntity>> search(String q){
        return this.suppliesRepository.search(q);
    }

}
