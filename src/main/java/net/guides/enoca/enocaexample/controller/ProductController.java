package net.guides.enoca.enocaexample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.enoca.enocaexample.elasticsearch.ElasticSearchBinded;
import net.guides.enoca.enocaexample.model.Product;
import net.guides.enoca.enocaexample.service.ProductService;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
    	ResponseEntity<Product> temp = ResponseEntity.ok(productService.createOrUpdate(product));
    	ElasticSearchBinded.elasticSearch(product);
    	return temp;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {

        Optional<Product> product = productService.findById(id);

        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product.get());
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Product> update(@PathVariable long id, @Valid @RequestBody Product product2) {

        Optional<Product> product = productService.findById(id);

        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        product.get().setName(product2.getName());
        product.get().setCode(product2.getCode());
        product.get().setSupplier(product2.getSupplier());
		final Product updatedProduct = productService.createOrUpdate(product.get());
		return ResponseEntity.ok(updatedProduct);

       // return ResponseEntity.ok(productService.createOrUpdate(product.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        Optional<Product> p = productService.findById(id);

        if (!p.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
