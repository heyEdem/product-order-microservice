package com.edem.inventoryservice.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "t_inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String skuCode;
    private Integer quantity;
}