package com.kgc.easybuy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsSelect {

    private String name;
    private double begin;
    private double end;
    private int brandId;
    private int currentPageNo;
    private int pageSize;
    private boolean sortPrice;
    private int userId;
}
