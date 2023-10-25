package com.edem.productservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
}
