package com.touchblankspot.faker.web.controller;

import net.datafaker.Faker;
import net.datafaker.providers.base.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakerRestController {

    private final Faker faker = new Faker();

    @GetMapping("/fake/name")
    public ResponseEntity<String> generateFakeName() {
        return ResponseEntity.ok(faker.name().fullName());
    }

    @GetMapping("/fake/address")
    @ResponseBody
    public ResponseEntity<String> generateFakeAddress() {
        return ResponseEntity.ok(faker.address().fullAddress());
    }
}
