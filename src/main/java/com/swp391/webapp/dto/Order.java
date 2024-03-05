package com.swp391.webapp.dto;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.PackageEntity;
import com.swp391.webapp.Entity.ScheduleDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer orderID;

    private int quantity;

    private BigDecimal totalPrice;
}
