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
public class File {
    private int id;
    private String filePath;
    private int productId;
    private int userId;
    private Date createTime;
    private int isDelete;
}
