package com.touchblankspot.service.inventory.type.product.category;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseType {

  private UUID id;

  private String category;

  private String subCategory;
}
