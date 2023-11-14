package com.example.pdvapp.controller;

import com.example.pdvapp.entity.Product;
import com.example.pdvapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping()
    public ResponseEntity getAll(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody Product product){
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Product product){
        Product productAchado = productRepository.findById(product.getId()).orElse(null);

        if(productAchado != null){
            productRepository.save(product);
            return new ResponseEntity(product, HttpStatus.OK);
        } else {
            return new ResponseEntity("Produto não encontrado!!!", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        productRepository.deleteById(id);
        return new ResponseEntity("Produto Deletado Com Sucesso!!!", HttpStatus.OK);
    }
}
