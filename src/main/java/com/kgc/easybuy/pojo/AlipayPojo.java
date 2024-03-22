package com.kgc.easybuy.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@ConfigurationProperties(prefix = "alipay")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlipayPojo implements Serializable {
    private String gateway;
    private String appId;
    private String publicKey;
    private String privateKey;
    private String alipayPublicKey;
    private String returnUrl;
    private String notifyUrl;


}
