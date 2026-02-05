/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.controller.ui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/ui")
public class HomePageController {
    @Value("${server.port:9090}")
    private String port;

    private final RestTemplate restTemplate;

    public HomePageController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("tables",
                IntStream.rangeClosed(1, 10).boxed().toList());
        return "home";
    }

    @PostMapping("/orders")
    public String startOrder(
            @RequestParam(required = false) Integer tableId,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Map<String, Object> request = new HashMap<>();
            request.put("tableId", tableId); // null for takeaway

            ResponseEntity<Map> response =
                    restTemplate.postForEntity(
                            "http://localhost:"+port+"/orders",
                            request,
                            Map.class
                    );

            if (response.getStatusCode().is2xxSuccessful()) {
                assert response.getBody() != null;
                redirectAttributes.addFlashAttribute(
                        "successMessage",
                        "Order created successfully (Order ID: "
                                + response.getBody().get("orderId") + ")"
                );
            } else {
                redirectAttributes.addFlashAttribute(
                        "errorMessage",
                        "Failed to create order"
                );
            }

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Error creating order: " + ex.getMessage()
            );
        }

        return "redirect:/ui/home";
    }

    @GetMapping("/orders/open")
    public String viewOpenOrders(Model model) {

        ResponseEntity<List> response =
                restTemplate.getForEntity(
                        "http://localhost:"+port+"/orders/open",
                        List.class
                );

        model.addAttribute("orders", response.getBody());
        return "orders/open-orders";
    }
}
