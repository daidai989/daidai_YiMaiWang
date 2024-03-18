package com.kgc.easybuy.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private String description;
    private float price;
    private int stock;
    private int categoryLevelId;
    private int fileId;
    private int isDelete;
    private Date createTime;
    private String filePath;
    private String categoryName;
    private int parentId;
    private int type;
}
