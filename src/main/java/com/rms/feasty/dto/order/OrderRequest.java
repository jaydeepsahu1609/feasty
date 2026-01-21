/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.dto.order;

import com.rms.feasty.constants.OrderStatusEnum;
import lombok.Data;

@Data
public class OrderRequest {
    private Integer tableId;
    private Integer customerId;
}
