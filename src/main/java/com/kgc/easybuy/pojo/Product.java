package com.kgc.easybuy.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "prou")
public class Product {

    @Id
    private int id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String description;
    @Field(type = FieldType.Double)
    private float price;
    @Field(type = FieldType.Integer)
    private int stock;
    @Field(type = FieldType.Integer)
    private int categoryLevelId;
    @Field(type = FieldType.Integer)
    private int fileId;
    @Field(type = FieldType.Integer)
    private int isDel;
    @Field(type=FieldType.Date,format = DateFormat.basic_date)
    private Date createTime;
    @Field(type=FieldType.Keyword)
    private String filePath;
    @Field(type=FieldType.Keyword)
    private String categoryName;
    @Field(type = FieldType.Integer)
    private int   brandId;
    @Field(type=FieldType.Keyword)
    private String brandName;
    @Field(type = FieldType.Integer)
    private int parentId;
    @Field(type = FieldType.Integer)
    private int type;
    @Field(type = FieldType.Integer)
    private int count;
    @Field(type = FieldType.Integer)
    private int catId;
    @Field(type = FieldType.Integer)
    private int userId;

    private boolean isCollection;

}
