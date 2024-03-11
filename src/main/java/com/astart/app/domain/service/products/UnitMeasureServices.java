package com.astart.app.domain.service.products;

import com.astart.app.persistence.entity.products.UnitMeasureEntity;
import com.astart.app.persistence.repository.products.UnitMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UnitMeasureServices {

    private final UnitMeasureRepository unitMeasureRepository;

    @Autowired
    public UnitMeasureServices(UnitMeasureRepository unitMeasureRepository){
        this.unitMeasureRepository=unitMeasureRepository;
    }

    public List<UnitMeasureEntity> getAll(){
        return this.unitMeasureRepository.findAll();
    }

    public Optional<List<UnitMeasureEntity>> search(String q){
        return this.unitMeasureRepository.search(q);
    }

    public Optional<UnitMeasureEntity> getById(Integer id){
        return this.unitMeasureRepository.findById(id);
    }

    public Boolean Save(UnitMeasureEntity unitMeasureEntity) {
        UnitMeasureEntity temp =this.unitMeasureRepository.save(unitMeasureEntity);
        return this.unitMeasureRepository.existsById(temp.getId());
    }

    public Boolean update(UnitMeasureEntity unitMeasure){
        Optional<UnitMeasureEntity> old = unitMeasureRepository.findById(unitMeasure.getId());
        this.unitMeasureRepository.save(unitMeasure);
        return old.equals(this.unitMeasureRepository.findById(unitMeasure.getId()));
    }

    public Boolean delete(Integer id){

        if (this.unitMeasureRepository.existsById(id)) {
            this.unitMeasureRepository.deleteById(id);
            if (this.unitMeasureRepository.findById(id).get().getDeleted_at()!=null) {
                return true;
            }
        } else {
            return false;
        }

        return false;
    }

}
