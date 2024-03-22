package com.kgc.easybuy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlipayDBPojo implements Serializable {
    private String out_trade_no;//订单号
    private int total_amount;//总金额
    private int status;//状态


}
