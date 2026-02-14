/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.dto.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequest {
    private Integer tableId;
    private Integer customerId;
}
