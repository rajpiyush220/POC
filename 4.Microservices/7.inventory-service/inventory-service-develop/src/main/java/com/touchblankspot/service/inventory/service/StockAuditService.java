package com.touchblankspot.service.inventory.service;

import com.touchblankspot.service.inventory.data.model.StockAudit;
import com.touchblankspot.service.inventory.data.repository.StockAuditRepository;
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
public class StockAuditService {
  @NonNull private final StockAuditRepository stockAuditRepository;

  public StockAudit save(StockAudit stockAudit) {
    return stockAuditRepository.save(stockAudit);
  }

  public Page<Object[]> getListData(Pageable pageable) {
    return stockAuditRepository.getListData(pageable);
  }
}
