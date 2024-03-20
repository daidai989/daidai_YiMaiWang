package com.kgc.easybuy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "easybuy_product")
public class Product {
    @Id
    private int id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String description;
    private float price;
    @Field(type = FieldType.Integer,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private int stock;
    @Field(type = FieldType.Integer,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private int categoryLevelId;
    @Field(type = FieldType.Integer,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private int brandId;
    @Field(type = FieldType.Integer,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private int fileId;
    @Field(type = FieldType.Integer,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private int isDelete;
    private Date createTime;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String filePath;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String categoryName;
    @Field(type = FieldType.Integer,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private int parentId;
    @Field(type = FieldType.Integer,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private int type;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String brandName;
}
