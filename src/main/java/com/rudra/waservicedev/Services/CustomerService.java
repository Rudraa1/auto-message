package com.rudra.waservicedev.Services;

import com.rudra.waservicedev.Models.Customers;
import com.rudra.waservicedev.Models.Product;
import com.rudra.waservicedev.Repository.CustomerRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustmerServiceInterface{

    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    public Customers findByPhone(String customerPhone) {
        return customerRepository.findCustomersByCustomerPhone(customerPhone);
    }

    @Override
    public Customers addNewCustomer(Customers customers) {

//
//        Category category = product.getCategory();
//        if(category.getId() == null){
//            Category savedCategory = categoryRepository.save(category);
//        }

        return customerRepository.save(customers);
    }

}
