package com.example.pdvapp.controller;

import com.example.pdvapp.dto.ProductDTO;
import com.example.pdvapp.dto.ResponseDTO;
import com.example.pdvapp.entity.Product;
import com.example.pdvapp.repository.ProductRepository;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductRepository productRepository;
    private ModelMapper mapper;

    public ProductController(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapper= new ModelMapper();
    }

    @GetMapping()
    public ResponseEntity getAll(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity create(@Valid @RequestBody ProductDTO productDTO){
        try {

            return new ResponseEntity<>(productRepository.save(mapper.map(productDTO, Product.class)), HttpStatus.CREATED);
        }catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity update(@Valid @RequestBody ProductDTO productDTO){
        try{
           return new ResponseEntity<>(productRepository.save(mapper.map(productDTO, Product.class)), HttpStatus.OK);
        }catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        try {
            productRepository.deleteById(id);
            return new ResponseEntity(new ResponseDTO("Produto Deletado Com Sucesso!!!"), HttpStatus.OK);
        } catch (Exception error){
            return new ResponseEntity(new ResponseDTO(error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
