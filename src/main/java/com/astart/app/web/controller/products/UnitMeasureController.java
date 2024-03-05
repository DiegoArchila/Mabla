package com.astart.app.web.controller.products;

import com.astart.app.domain.service.products.UnitMeasureServices;
import com.astart.app.persistence.entity.products.UnitMeasureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/unitmeasure")
public class UnitMeasureController {
    private UnitMeasureServices unitMeasureServices;

    @Autowired
    public UnitMeasureController(UnitMeasureServices unitMeasureServices){
        this.unitMeasureServices=unitMeasureServices;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitMeasureEntity> getById(@PathVariable Integer id){
        Optional<UnitMeasureEntity> result= unitMeasureServices.getById(id);

        if (result.isPresent()){
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("")
    public ResponseEntity getAll(){
        List<UnitMeasureEntity> result= unitMeasureServices.getAll();
        return ResponseEntity.ok(result);
    }


    @GetMapping("/search")
    public ResponseEntity<List<UnitMeasureEntity>> search(@RequestParam String q){
            Optional<List<UnitMeasureEntity>> result= unitMeasureServices.search(q);
            if (result.isPresent()){
                return ResponseEntity.ok(result.get());
            } else {
                return (ResponseEntity<List<UnitMeasureEntity>>) ResponseEntity.status(HttpStatus.NOT_FOUND);
            }
    }


    @PostMapping("/save")
    public ResponseEntity save(@RequestBody UnitMeasureEntity unitMeasureEntity){
        if ((this.unitMeasureServices.Save(unitMeasureEntity))) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/edit")
    public ResponseEntity edit(@RequestBody UnitMeasureEntity unitMeasureEntity){

        if (this.unitMeasureServices.update(unitMeasureEntity)) {
            return ResponseEntity.status(HttpStatus.OK).body("edited successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Not updated it");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Integer id){
        if (this.unitMeasureServices.delete((Integer) id)){
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .body("Not deleted, register not found or have error while try deleted it");
        }
    }
}
