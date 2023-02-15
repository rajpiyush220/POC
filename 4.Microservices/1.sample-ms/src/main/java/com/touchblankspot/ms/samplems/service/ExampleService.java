package com.touchblankspot.ms.samplems.service;

import com.touchblankspot.ms.samplems.data.model.Example;
import com.touchblankspot.ms.samplems.data.repository.ExampleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExampleService {

    @NonNull
    private final ExampleRepository exampleRepository;

    public Iterable<Example> findAll(){
        return exampleRepository.findAll();
    }

    public Optional<Example> findById(Long id){
        return exampleRepository.findById(id);
    }

    public Example save(Example example){
        return exampleRepository.save(example);
    }
}
