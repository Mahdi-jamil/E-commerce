package com.devesta.ecommerce.orderline.model;

public record OrderLineRequest(Integer orderLineId,
                               Integer orderId,
                               Integer productId,
                               double quantity
) {
}
