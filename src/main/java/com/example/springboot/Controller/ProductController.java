package com.example.springboot.Controller;

import com.example.springboot.DTO.ProductRecordDto;
import com.example.springboot.Model.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
