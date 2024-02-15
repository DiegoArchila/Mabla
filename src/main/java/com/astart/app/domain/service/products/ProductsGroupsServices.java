package com.astart.app.domain.service.products;

import com.astart.app.persistence.entity.products.ProductsGroupsEntity;
import com.astart.app.persistence.entity.products.UnitMeasureEntity;
import com.astart.app.persistence.repository.products.ProductsGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsGroupsServices {

    private final ProductsGroupsRepository productsGroupsRepository;

    @Autowired
    public ProductsGroupsServices(ProductsGroupsRepository productsGroupsRepository){
        this.productsGroupsRepository=productsGroupsRepository;
    }

    public Boolean Save(ProductsGroupsEntity productsGroupsEntity) {
        this.productsGroupsRepository.save(productsGroupsEntity);
        Optional<ProductsGroupsEntity> temp = this.productsGroupsRepository.findById(productsGroupsEntity.getId());
        return temp.isPresent();
    }

    public Optional<ProductsGroupsEntity> getById(Integer id){
        return this.productsGroupsRepository.findById(id);
    }

    public List<ProductsGroupsEntity> getAll(){
        return this.productsGroupsRepository.findAll();
    }

    public Boolean update(ProductsGroupsEntity productsGroups){
        Optional<ProductsGroupsEntity> old = this.productsGroupsRepository.findById(productsGroups.getId());
        this.productsGroupsRepository.save(productsGroups);
        return old.equals(this.productsGroupsRepository.findById(productsGroups.getId()));
    }

    public Boolean delete(Integer id){

        if (this.productsGroupsRepository.existsById(id)) {
            this.productsGroupsRepository.deleteById(id);
            if (this.productsGroupsRepository.findById(id).get().getDeleted_at()!=null) {
                return true;
            }
        } else {
            return false;
        }

        return false;
    }

    public Optional<List<ProductsGroupsEntity>> search(String q){
        return this.productsGroupsRepository.search(q);
    }
}
