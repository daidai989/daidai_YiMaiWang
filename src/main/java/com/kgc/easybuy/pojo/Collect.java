package com.kgc.easybuy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collect{
    private int id;
    private int userId;
    private int productId;
    private int isDel;
    private Date createTime;
}
