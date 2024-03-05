package com.swp391.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceDTO {
    String name;
    BigDecimal price;
    String description;
    String picture;
}
