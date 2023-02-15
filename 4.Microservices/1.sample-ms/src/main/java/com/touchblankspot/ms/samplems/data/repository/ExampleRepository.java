package com.touchblankspot.ms.samplems.data.repository;

import com.touchblankspot.ms.samplems.data.model.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends CrudRepository<Example, Long> {
}
