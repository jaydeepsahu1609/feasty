/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.controller.ui;

import com.rms.feasty.dto.order.DetailedOrderResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ui/orders")
public class OrderUiController {

    @Value("${server.port:9090}")
    private String port;

    private final RestTemplate restTemplate;

    public OrderUiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{orderId}")
    public String viewOrder(@PathVariable Integer orderId, Model model, RedirectAttributes redirectAttributes) {

        try {
            DetailedOrderResponse response =
                    restTemplate.getForObject(
                            "http://localhost:" + port + "/orders/" + orderId,
                            DetailedOrderResponse.class
                    );

            model.addAttribute("order", response);
            return "orders/order-detail";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    e.getMessage()
            );
            return "redirect:/ui/home";
        }
    }
}

