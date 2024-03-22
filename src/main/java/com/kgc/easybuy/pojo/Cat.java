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
public class Cat {
   private  Integer userId;
   private  Integer id;
   private  Integer productId;
   private  Date createTime;
   private  Integer count;
   private  Integer isDel;
}
