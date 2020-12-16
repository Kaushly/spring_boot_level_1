package com.example.Spring.Boot.repository;

import com.example.Spring.Boot.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init(){
        this.products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Морковь", 70.05d),
                new Product(2L, "Картофель", 34.6d),
                new Product(3L, "Лук", 56.5d)
        ));
    }

    public Product saveOrUpdate(Product p){
        if(p.getId() != null){
            for (int i = 0; i < products.size(); i++) {
                if(products.get(i).getId().equals(p.getId())){
                    products.set(i, p);
                    return p;
                }
            }
        }
        Long newId = products.stream().mapToLong(Product::getId).max().orElseGet(() -> 0) + 1L;
        p.setId(newId);
        products.add(p);
        return p;
    }

    public List<Product> findAll(){
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> findById(Long id){
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id){
        products.removeIf(p -> p.getId().equals(id));
    }

    public void add(Product p){
        products.add(p);
    }

}
