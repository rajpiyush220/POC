package com.test.springbatchmongo.batch.writer;

import com.test.springbatchmongo.data.Customer;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class CustomerWriter implements ItemWriter<Customer> {
    @Override
    public void write(List<? extends Customer> items) throws Exception {
        System.out.println("Customer :" + items.get(0));
    }
}
