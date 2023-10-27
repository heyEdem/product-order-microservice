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
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository repository;
    private final WebClient webClient;
    public void placeOrder (OrderRequest request){
        Order order = new Order(); //create a new order and set an order number
        order.setOrderNumber(UUID.randomUUID().toString());

        List <OrderLineItems> orderLineItemsList = request.getOrderLineItemsDtoList()//pass the order items from the requestDto into a new variable
                .stream()
                .map(this ::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItemsList); //set the order items in that order

        //Collect all skuCodes of items in an orderItemList into a list
        // to be used by findBySkuCodeIn in the inventory service

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                                .map(OrderLineItems::getSkuCode).toList();

        //Check with the inventory service to see if the product is in stock
       Boolean result = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(boolean.class)
                .block();//the block method makes it a synchronous transaction

        if(result){
            repository.save(order);
        }
        else{
            throw new IllegalArgumentException("product is not in stock please try again later");
        }
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