package com.edem.orderservice.controller;

import com.edem.orderservice.dto.OrderRequest;
import com.edem.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest request ){
        orderService.placeOrder(request);
        return "Order placed successfully";
    }
}
