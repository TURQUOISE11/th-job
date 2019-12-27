package cn.lhemi.thjob.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tutu11
 * @date 2019-12-27 08:56:11
 */
@Data
@ConfigurationProperties("jax.netty")
public class ThJobProperties {
    /**
     * 地址
     */
    private String host = "localhost";
    /**
     * 端口
     */
    private int port = 4567;
    /**
     * 应用名称
     */
    private String appName = "default";
    /**
     * app key
     */
    private String appKey;
    /**
     * 密钥
     */
    private String secretKey;
}
