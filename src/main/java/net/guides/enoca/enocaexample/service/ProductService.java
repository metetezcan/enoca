package net.guides.enoca.enocaexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.guides.enoca.enocaexample.model.Product;
import net.guides.enoca.enocaexample.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository ProductRepository;
    public List<Product> findAll() {
        return ProductRepository.findAll();
    }
    public Optional<Product> findById(long id) {
        return ProductRepository.findById(id);
    }
    public Product createOrUpdate(Product Product) {
        return ProductRepository.save(Product);
    }
    public void deleteById(long id) {
        ProductRepository.deleteById(id);
    }
}
