package cn.lhemi.thjob.core.dto;

import lombok.Data;

/**
 * @author tutu11
 * @date 2019-12-27 10:24:55
 */
@Data
public class RegisterDto {
    /**
     * app名称
     */
    private String appName;
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 分组
     */
    private String group;
    /**
     * app key
     */
    private String appKey;
    /**
     * 密钥
     */
    private String secretKey;
}
