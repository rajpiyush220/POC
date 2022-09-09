package com.test.springbatchmongo.batch.reader;

import com.test.springbatchmongo.batch.util.TaskConstants;
import com.test.springbatchmongo.data.Customer;
import com.test.springbatchmongo.repository.CustomerRepository;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.*;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class CustomerReader implements ItemReader<Customer> {
    private CustomerRepository customerRepository;

    public CustomerReader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    private ExecutionContext executionContext;
    private JobParameters jobParameter;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        executionContext = stepExecution.getJobExecution().getExecutionContext();
        executionContext.put(TaskConstants.READ_STATUS, TaskConstants.STARTED);
        jobParameter = stepExecution.getJobParameters();

    }

    @Override
    public Customer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Customer customer = Objects.nonNull(executionContext.get(TaskConstants.READ_STATUS)) && executionContext.get(TaskConstants.READ_STATUS).equals(TaskConstants.STARTED) ?
        customerRepository.findAll().get(0) : null;
        executionContext.put(TaskConstants.READ_STATUS, TaskConstants.COMPLETED);
        return customer;
    }
}
