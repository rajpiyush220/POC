package com.touch.blankspot.progress.contract.apitype;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class ApiTaskCategoryRequest {

    @JsonProperty("name")
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @JsonProperty("description")
    @NotNull
    @Size(min = 1, max = 255)
    private String description;

    @JsonProperty("organizationId")
    @NotNull
    private UUID organizationId;
}
