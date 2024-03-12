package com.swp391.webapp.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionDTO {

    private int orderId;

    private int walletId;

    private Date createAt;

    private BigDecimal totalPrice;
}
