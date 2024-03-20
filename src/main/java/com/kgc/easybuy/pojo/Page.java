package com.kgc.easybuy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private int currentPageNo;
    private int pageSize;
    private int totalPage;
    private int pageNum;
    private Object data;
}
