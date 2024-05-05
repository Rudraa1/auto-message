package com.rudra.waservicedev.Controllers;


import com.rudra.waservicedev.Models.Customers;
import com.rudra.waservicedev.Models.Product;
import com.rudra.waservicedev.Repository.CustomerRepository;
import com.rudra.waservicedev.Services.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public Customers addNewCustomer(@RequestBody Customers customers){
        return customerService.addNewCustomer(customers);
    }

}
