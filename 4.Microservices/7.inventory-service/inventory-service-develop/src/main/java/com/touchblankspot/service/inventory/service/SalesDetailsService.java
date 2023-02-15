package com.touchblankspot.service.inventory.service;

import com.touchblankspot.service.inventory.data.model.SalesDetails;
import com.touchblankspot.service.inventory.data.repository.SalesDetailsRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SalesDetailsService {
  @NonNull private final SalesDetailsRepository salesDetailsRepository;

  public SalesDetails save(SalesDetails salesDetails) {
    return salesDetailsRepository.save(salesDetails);
  }

  public Page<Object[]> getListData(Pageable pageable) {
    return salesDetailsRepository.getListData(pageable);
  }
}
