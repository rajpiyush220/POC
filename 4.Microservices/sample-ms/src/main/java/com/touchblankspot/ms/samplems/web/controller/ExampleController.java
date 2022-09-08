package com.touchblankspot.ms.samplems.web.controller;

import com.touchblankspot.ms.samplems.data.model.Example;
import com.touchblankspot.ms.samplems.service.ExampleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    @NonNull
    private final ExampleService exampleService;

    @GetMapping(value = "/example", produces = "application/json")
    @ResponseBody
    @ResponseStatus
    public Iterable<Example> getAll() {
        return exampleService.findAll();
    }

    @PostMapping(value = "/example", consumes = "application/json", produces = "application/json")
    @ResponseBody
    @ResponseStatus
    public Example createExample(@RequestBody Example example) {
        return exampleService.save(example);
    }


}
