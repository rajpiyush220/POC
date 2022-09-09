package com.test.springbatchmongo.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "CUSTOMER")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {
    @Id
    private String id;
    private String name;
    private String address;

}
