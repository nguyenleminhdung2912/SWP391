package com.swp391.webapp.dto;

import com.swp391.webapp.Entity.Enum.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private int packageId;

    private List<OrderDetailDTO> orderDetailDTOList;

    private int scheduleId;

    private long totalPrice;

    private Date createAt;

    private OrderStatus status = OrderStatus.ORDERED;

    private String venue;

    private String phoneNumber;

    private String username;

    private String email;

}
