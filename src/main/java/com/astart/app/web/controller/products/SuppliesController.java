package com.astart.app.web.controller.products;

import com.astart.app.domain.service.products.SuppliesServices;
import com.astart.app.persistence.entity.products.ProductsGroupsEntity;
import com.astart.app.persistence.entity.products.SuppliesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/supplies")
public class SuppliesController {

    private final SuppliesServices suppliesServices;

    @Autowired
    public SuppliesController(SuppliesServices suppliesServices){
        this.suppliesServices=suppliesServices;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody SuppliesEntity supply){
        if (this.suppliesServices.save(supply)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuppliesEntity> getById(@PathVariable Integer id){
        Optional<SuppliesEntity> result = this.suppliesServices.getById(id);
        if (result.isPresent()){
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<SuppliesEntity>> getAll(){
        return ResponseEntity.ok().body(this.suppliesServices.getAll());
    }

    @PutMapping("/edit")
    public ResponseEntity update(@RequestBody SuppliesEntity supplies){
        if (this.suppliesServices.update(supplies)) {
            return ResponseEntity.status(HttpStatus.OK).body("edited successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Not updated it");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Integer id){
        if (this.suppliesServices.delete((Integer) id)){
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .body("Not deleted, register not found or have error while try deleted it");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<SuppliesEntity>> search(@RequestParam String q){
        Optional<List<SuppliesEntity>> result= suppliesServices.search(q);
        if (result.isPresent()){
            return ResponseEntity.ok(result.get());
        } else {
            return (ResponseEntity<List<SuppliesEntity>>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
    }


}
