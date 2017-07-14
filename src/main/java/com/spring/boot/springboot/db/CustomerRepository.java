package com.spring.boot.springboot.db;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dach1016 on 14.07.2017.
 *
 */

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
}
