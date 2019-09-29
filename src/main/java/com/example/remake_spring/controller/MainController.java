package com.example.remake_spring.controller;

import com.example.remake_spring.domain.ProductEntity;
import com.example.remake_spring.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
       return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<ProductEntity> products = productRepo.findAll();
        model.put("products", products);

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String name,@RequestParam String productType ,Map<String, Object> model){
        ProductEntity pE = new ProductEntity();
        pE.setName(name);
        pE.setProductType(productType);
        pE.setCreated(LocalDateTime.now());
        pE.setStatus("NEW");
        productRepo.save(pE);

        Iterable<ProductEntity> products = productRepo.findAll();
        model.put("products", products);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String,Object> model){
        Iterable<ProductEntity> pE;

        if(filter !=null && !filter.isEmpty()){
            pE = productRepo.findByName(filter);
        } else {
            pE = productRepo.findAll();
        }

        model.put("products", pE);
        return "main";
    }


}
