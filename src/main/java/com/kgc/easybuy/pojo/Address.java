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
public class Address {
    private int id;
    private int userId;
    private String address;
    private Date createTime;
    private int isDefault;
    private  String remark;
    private int isDel;
    private String userName;
    private String email;
    private String mobile;

    private String phone;
    private String recipient;
    private int isSure;
}
