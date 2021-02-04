package com.serviceonepavilion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Integer itemId;
    private Integer quantity;
    private Integer customerId;
}
