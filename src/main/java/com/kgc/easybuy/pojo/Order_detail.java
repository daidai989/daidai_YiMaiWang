package com.kgc.easybuy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author daidai
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order_detail {
      private   Integer orderId;
      private   Integer id;
      private   Integer productId;
      private   Integer quantity;
      private   double cost;
      private   Date createTime;
}
