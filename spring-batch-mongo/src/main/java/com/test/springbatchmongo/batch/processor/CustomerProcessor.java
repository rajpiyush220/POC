package com.test.springbatchmongo.batch.processor;

import com.test.springbatchmongo.data.Customer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomerProcessor implements ItemProcessor<Customer,Customer> {
    @Override
    public Customer process(Customer item) throws Exception {
        return item;
    }
}
