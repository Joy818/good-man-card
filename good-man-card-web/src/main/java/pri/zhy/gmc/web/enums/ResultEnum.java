package pri.zhy.gmc.web.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 结果枚举
 *
 * @author zhy
 * @date 2022年03月15日
 */
@Getter
@ToString
public enum ResultEnum {
    SUCCESS(0, "成功"),
    FAILED(1, "失败"),
    DATA_NOT_FOUND(2, "数据不存在"),
    ;
    private final int code;
    private final String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
