package cn.lhemi.thjob.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tutu11
 * @date 2019-12-27 08:56:11
 */
@Data
@ConfigurationProperties("th-job.server")
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
     * 心跳超时时间
     */
    private int timeOut = 10000;
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 应用名称
     */
    private String appName = "default";
    /**
     * 分组
     */
    private String group = "default";
    /**
     * app key
     */
    private String appKey;
    /**
     * 密钥
     */
    private String secretKey;
}
