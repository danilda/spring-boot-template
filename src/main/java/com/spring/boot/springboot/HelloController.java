package com.spring.boot.springboot;

import com.spring.boot.springboot.db.Customer;
import com.spring.boot.springboot.db.CustomerRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dach1016 on 13.07.2017.
 *
 */

@RestController
public class HelloController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!" + getAllCustomers();
    }

    private String getAllCustomers(){
        StringBuilder sb = new StringBuilder();
        CustomerRepository customers = (CustomerRepository) Application.getCtx().getBean("customerRepository");
        customers.findAll().forEach(customer-> sb.append("<h2>").append(customer.toString()).append("</h2>\n"));
        return sb.toString();
    }
}
