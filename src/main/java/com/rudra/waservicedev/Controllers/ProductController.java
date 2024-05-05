package com.rudra.waservicedev.Controllers;

import com.rudra.waservicedev.Models.Product;
import com.rudra.waservicedev.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("allProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/add")
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }

    // Other endpoints for product-related functionalities
}