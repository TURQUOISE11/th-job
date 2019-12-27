package cn.lhemi.thjob.core.dto;

import com.sun.istack.internal.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author tutu11
 * @description
 * @date 2019-12-27 09:54:53
 */
@Data
public class Message<T> {

    /**
     * 类型
     */
    private MsgType type;

    /**
     * 流水号
     */
    private String outId;

    /**
     * 时间戳
     */
    private long ts;

    /**
     * 数据
     */
    private T data;


    @Getter
    @AllArgsConstructor
    public enum MsgType {
        /**
         * 注销
         */
        LOGOUT(0, "注销"),
        /**
         * 注册
         */
        REGISTER(1, "注册"),
        /**
         * 回复
         */
        REPLY(2, "回复"),
        /**
         * 执行
         */
        EXECUTE(3, "执行"),
        /**
         * 添加
         */
        ADD(4, "添加"),
        ;
        private int code;
        private String msg;

        @Nullable
        public static MsgType resolve(int code) {
            for (MsgType type : values()) {
                if (type.code == code) {
                    return type;
                }
            }
            return null;
        }
    }
}
