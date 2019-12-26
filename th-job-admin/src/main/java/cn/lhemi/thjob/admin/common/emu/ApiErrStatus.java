package cn.lhemi.thjob.admin.common.emu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

/**
 * @author tutu11
 * @description api错误枚举
 * @date 2019-12-03 08:51:13
 */
@Getter
@AllArgsConstructor
public enum ApiErrStatus {
    /**
     * 签名错误
     */
    ERR_SIGN(10001, "签名错误"),
    ERR_PARAMS(10002, "错误参数"),
    UNREGISTERED_APP(10003, "未注册设备"),
    ERR_TIME_STR(10004, "时间或随机字符串错误"),
    FORBIDDEN(10005, "拒绝访问"),
    RSA_ERR(10006, "数据解密失败");

    private int code;

    private String msg;

    @Nullable
    public static ApiErrStatus resolve(int code) {
        for (ApiErrStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }

}
