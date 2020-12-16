package com.example.Spring.Boot.service;

import com.example.Spring.Boot.model.Product;
import com.example.Spring.Boot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product saveOrUpdate(Product p) {
        return productRepository.saveOrUpdate(p);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAll(Double minCost, Double maxCost) {
        List<Product> out = findAll();
        if(minCost != null){
            out = out.stream().filter(p -> p.getCost() >= minCost).collect(Collectors.toList());
        }
        if(maxCost != null){
            out = out.stream().filter(p -> p.getCost() <= maxCost).collect(Collectors.toList());
        }
        return out;
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void add(Product p){
        productRepository.add(p);
    }
}
