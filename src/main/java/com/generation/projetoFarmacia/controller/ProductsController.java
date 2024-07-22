package com.generation.projetoFarmacia.controller;

import com.generation.projetoFarmacia.model.Products;
import com.generation.projetoFarmacia.repository.CategoryRepository;
import com.generation.projetoFarmacia.repository.ProductsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Products>> getAll() {
        return ResponseEntity.ok(productsRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getById(@PathVariable Long id) {
        return productsRepository.findById(id)
                .map(request -> ResponseEntity.ok(request))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<Products>> getByDescription(@PathVariable String description) {
        return ResponseEntity.ok(productsRepository.findAllByProductDescriptionContainingIgnoreCase(description));
    }

//    @GetMapping("/price/{price}")
//    public ResponseEntity<List<Products>> getByProductPrice(@PathVariable double productPrice) {
//        return ResponseEntity.ok(productsRepository.findAllByProductPrice(productPrice));
//    }

    @PostMapping
    public ResponseEntity<Products> post(@Valid @RequestBody Products products) {
        if (categoryRepository.existsById(products.getCategories().getId())) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(productsRepository.save(products));
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This category doesn't exists!", null);
    }

    @PutMapping
    public ResponseEntity<Products> put(@Valid @RequestBody Products products) {
        if (productsRepository.existsById(products.getId())) {
            if (categoryRepository.existsById(products.getCategories().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(productsRepository.save(products));

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This category doesn't exists!", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Products> products = productsRepository.findById(id);

        if (products.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        productsRepository.deleteById(id);
    }
}
