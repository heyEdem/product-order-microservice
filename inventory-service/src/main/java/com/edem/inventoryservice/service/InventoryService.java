package com.edem.inventoryservice.service;

import com.edem.inventoryservice.dto.InventoryResponse;
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
    public List<InventoryResponse> isInStock(List<String> skuCode){
         log.info("Product {} is in stock", skuCode);

         //find all items by the list of skuCodes given
         return repository.findBySkuCodeIn(skuCode).stream()
                 // map the returned objects to a list of inventory responses
                 .map(inventory ->
                     InventoryResponse.builder()
                             .skuCode(inventory.getSkuCode())
                             .isInStock(inventory.getQuantity() > 0)
                             .build()
                 ).toList();
    }
}
