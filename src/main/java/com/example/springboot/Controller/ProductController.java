package com.example.springboot.Controller;

import com.example.springboot.DTO.ProductRecordDto;
import com.example.springboot.Model.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/product/")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){

        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto,productModel);
        return  ResponseEntity.status(HttpStatus.created).body(productRepository.save(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return  ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id")UUID id){

        Optional<ProductModel> productO = productRepository.findById(id);
        if (productO.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not Found");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }
}
