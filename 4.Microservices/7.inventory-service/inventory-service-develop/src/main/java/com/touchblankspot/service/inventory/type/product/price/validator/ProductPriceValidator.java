package com.touchblankspot.service.inventory.type.product.price.validator;

import com.touchblankspot.service.inventory.service.ProductPriceService;
import com.touchblankspot.service.inventory.type.product.price.ProductPriceRequestType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ProductPriceValidator implements Validator {

  @NonNull private final ProductPriceService productPriceService;

  @Override
  public boolean supports(Class<?> clazz) {
    return ProductPriceRequestType.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ProductPriceRequestType requestType = (ProductPriceRequestType) target;
    if (requestType.getProductId() != null
        && requestType.getProductSize() != null
        && requestType.getProductSize() != ""
        && productPriceService.isUniqueProductPrice(
            requestType.getProductId(), requestType.getProductSize())) {
      errors.rejectValue(
          "productSize", "", "Product price is already available for selected Product and Size");
    }
  }
}
