package com.edem.inventoryservice.service;

import com.edem.inventoryservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository repository;
    @Transactional
    public boolean isInStock(String skuCode){
         log.info("Product {} is in stock", skuCode);
         return repository.findBySkuCode(skuCode).isPresent();
    }
}
