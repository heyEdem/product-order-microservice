package com.edem.orderservice.service;

import com.edem.orderservice.dto.OrderLineItemsDto;
import com.edem.orderservice.dto.OrderRequest;
import com.edem.orderservice.model.Order;
import com.edem.orderservice.model.OrderLineItems;
import com.edem.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository repository;
    public void placeOrder (OrderRequest request){
        Order order = new Order(); //create a new order and set an order number
        order.setOrderNumber(UUID.randomUUID().toString());

        List <OrderLineItems> orderLineItemsList = request.getOrderLineItemsDtoList()//pass the order items from the requestDto into a new variable
                .stream()
                .map(this ::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItemsList); //set the order items in that order
        repository.save(order);
        log.info("Order {} placed successfully", order.getOrderNumber());

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems ;
    }
}
// TODO: 10/25/23 Miss Lilly's work