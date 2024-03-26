package com.kgc.easybuy.pojo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayPojo implements Serializable {

    private String gateway;
    private String appId;
    private String publicKey;
    private String privateKey;
    private String alipayPublicKey;
    private String returnUrl;
    private String notifyUrl;

}
