package com.kgc.easybuy.pojo;

import lombok.Data;

@Data
public class Page {

    private int currentPageNo;
    private int pageSize;
    private int totalPage;
    private Object data;
}
