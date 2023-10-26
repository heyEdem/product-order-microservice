package com.edem.inventoryservice.service;

import com.edem.inventoryservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository repository;
    @Transactional
    public boolean isInStock(List<String> skuCode){
         log.info("Product {} is in stock", skuCode);
         return repository.findBySkuCodeIn(skuCode).isPresent();
    }
}
