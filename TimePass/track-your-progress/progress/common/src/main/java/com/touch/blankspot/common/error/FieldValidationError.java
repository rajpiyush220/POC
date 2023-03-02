package com.touch.blankspot.common.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(onConstructor = @__({@JsonCreator}))
public class FieldValidationError {

  @JsonProperty("fieldName")
  @Schema(example = "firstName")
  private String fieldName;

  @JsonProperty("rejectedValue")
  @Schema(example = "+homas")
  private Object rejectedValue;

  @JsonProperty("message")
  @Schema(
      example = "Invalid character in firstName field.",
      description = "Human readable error message.")
  private String message;

  public static void throwExceptionIfInvalidField(
      Stream<Collection<FieldValidationError>> errorStream) {
    List<FieldValidationError> errors = errorStream.flatMap(Collection::stream).toList();
    if (errors.size() > 0) {
      throw new BusinessException(errors);
    }
  }
}
