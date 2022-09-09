package com.test.springbatchmongo.repository;

import com.test.springbatchmongo.data.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
}
