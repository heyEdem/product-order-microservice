package com.edem.inventoryservice.controller;

import com.edem.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService service;
    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-code") String  skuCode){
        return service.isInStock(skuCode);
    }
}
