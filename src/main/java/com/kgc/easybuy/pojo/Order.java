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
public class Order {
      private  Integer id;
      private  Integer userId;
      private  String  loginName;
      private  String  userAddress;
      private  Date    createTime;
      private  double  cost;
      private  String  serialNumber;
      private  Integer isDel;
}
