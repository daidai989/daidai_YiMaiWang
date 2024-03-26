package com.kgc.easybuy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author daidai
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {

    private Integer id;
    private String title;
    private String content;
    private Date createTime;
    private Integer isDel;
    private String  shortTitle;
}
