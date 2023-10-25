package com.edem.productservice.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "product")
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
