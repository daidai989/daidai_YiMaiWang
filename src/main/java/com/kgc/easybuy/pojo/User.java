package com.kgc.easybuy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String loginName;
    private String userName;
    private String password;
    private int sex;

    private String identityCode;
    private String email;
    private String mobile;
    private int roleId;
    private int isDel;
    private String phone;
    private String address;
    private Integer isDefault;
    private float total_cost;
    private Date createTime;
}