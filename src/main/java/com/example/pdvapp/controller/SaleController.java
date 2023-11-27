package com.example.pdvapp.controller;

import com.example.pdvapp.dto.ResponseDTO;
import com.example.pdvapp.dto.SaleDTO;
import com.example.pdvapp.service.SaleService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sale")
public class SaleController {
    private SaleService saleService;

    public SaleController(@Autowired SaleService saleService){
        this.saleService = saleService;
    }

    @GetMapping()
    public ResponseEntity getAll(){
        return new ResponseEntity<>(saleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable long id){
        try {
            return new ResponseEntity<>(saleService.findById(id), HttpStatus.OK);
        } catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity create(@Valid @RequestBody SaleDTO saleDTO){
        try {
            long id = saleService.save(saleDTO);
            return new ResponseEntity<>(new ResponseDTO("Venda Realizada com Sucesso!! "), HttpStatus.CREATED);
        } catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
