package com.edem.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name="t_order")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List <OrderLineItems> orderLineItemsList;

}
