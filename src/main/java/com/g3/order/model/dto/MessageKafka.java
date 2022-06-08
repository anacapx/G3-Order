package com.g3.order.model.dto;

import java.sql.Timestamp;

import com.g3.order.model.enums.OrderEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageKafka {
  private Long orderId;
  private String userEmail;
  private OrderEnum status;
  private Timestamp date = new Timestamp(System.currentTimeMillis());
}
