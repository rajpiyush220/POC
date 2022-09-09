package com.test.springbatchmongo.batch.config;

import com.test.springbatchmongo.batch.processor.CustomerProcessor;
import com.test.springbatchmongo.batch.reader.CustomerReader;
import com.test.springbatchmongo.batch.writer.CustomerWriter;
import com.test.springbatchmongo.data.Customer;
import com.test.springbatchmongo.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashMap;

@Configuration
@AllArgsConstructor
@EnableBatchProcessing
public class BatchConfiguration {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private CustomerRepository customerRepository;
    private MongoTemplate mongoTemplate;

    //Customer Reader with Mongo Repository
    public CustomerReader reader() {
        return new CustomerReader(customerRepository);
    }

    //Reader with Mongo Templated
/*    @Bean
    public MongoItemReader<Customer> reader() {
        MongoItemReader<Customer> reader = new MongoItemReader<>();
        reader.setTemplate(mongoTemplate);
        reader.setSort(new HashMap<String, Sort.Direction>() {{
            put("_id", Sort.Direction.DESC);
        }});
        reader.setTargetType(Customer.class);
        reader.setQuery("{}");
        return reader;
    }*/

    public CustomerProcessor processor() {
        return new CustomerProcessor();
    }

    public CustomerWriter writer() {
        return new CustomerWriter();
    }

    @Bean
    public Step step() {
       return stepBuilderFactory.get("step")
                .<Customer,Customer>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step())
                .build();
    }


}
