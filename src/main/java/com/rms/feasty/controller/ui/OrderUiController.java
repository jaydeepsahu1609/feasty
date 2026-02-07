/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.controller.ui;

import com.rms.feasty.dto.order.DetailedOrderResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            if (e instanceof HttpClientErrorException.NotFound)
                redirectAttributes.addFlashAttribute(
                        "errorMessage",
                        "Order not found"
                );
            else
                redirectAttributes.addFlashAttribute(
                        "errorMessage",
                        "Some error occurred"
                );
            return "redirect:/ui/home";
        }
    }

    // SHOW ADD ITEMS PAGE
    @GetMapping("/{orderId}/items")
    public String showAddItemsPage(
            @PathVariable Integer orderId,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        try {
            ResponseEntity<List> response =
                    restTemplate.getForEntity(
                            "http://localhost:" + port + "/items/",
                            List.class
                    );

            model.addAttribute("items", response.getBody());
            model.addAttribute("orderId", orderId);
            return "orders/add-items";

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Failed to fetch items"
            );
            return "redirect:/ui/home";
        }
    }

    // ADD ITEMS TO ORDER
    @PostMapping("/{orderId}/items")
    public String addItemsToOrder(
            @PathVariable Integer orderId,
            @RequestParam HashMap<String, String> itemToQuantityMap,
            RedirectAttributes redirectAttributes
    ) {
        try {
            List<Map<String, Object>> requestBody = getMaps(orderId, itemToQuantityMap);

            restTemplate.postForEntity(
                    "http://localhost:" + port + "/orders/" + orderId + "/items",
                    requestBody,
                    Void.class
            );

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Items added successfully"
            );

            return "redirect:/ui/orders/" + orderId;

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Failed to add items to order"
            );
            return "redirect:/ui/home";
        }
    }

    private static @NonNull List<Map<String, Object>> getMaps(Integer orderId, HashMap<String, String> itemToQuantityMap) {
        List<Map<String, Object>> requestBody = new ArrayList<>();

        for (Map.Entry<String, String> itemToQuantity : itemToQuantityMap.entrySet()) {
            if (Integer.parseInt(itemToQuantity.getValue()) > 0) {
                Map<String, Object> item = new HashMap<>();
                item.put("orderId", orderId);
                item.put("itemId", itemToQuantity.getKey());
                item.put("quantity", itemToQuantity.getValue());
                requestBody.add(item);
            }
        }
        return requestBody;
    }
}

