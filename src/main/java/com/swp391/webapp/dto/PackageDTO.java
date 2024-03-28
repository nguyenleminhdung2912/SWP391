package com.swp391.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageDTO {
    String name;
    Integer slot;
    float discountPercentage = 0;
    String description;
    String picture;
}
