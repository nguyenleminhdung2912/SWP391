package com.swp391.webapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bill_Detail_DTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Bill_Detail_ID")
    private Long billDetailId;

    @ManyToOne
    @JoinColumn(name = "Bill_ID", nullable = false)
    private BillDTO bill;

    @Column(name = "venue", columnDefinition = "text")
    private String venue;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;
}
