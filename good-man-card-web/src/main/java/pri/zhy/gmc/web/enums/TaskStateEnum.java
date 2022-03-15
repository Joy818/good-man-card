package pri.zhy.gmc.web.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 任务状态枚举
 *
 * @author zhy
 * @date 2022年03月15日
 */
@Getter
@ToString
public enum TaskStateEnum {
    CREATED(0, "创建成功"),
    ON_DOING(1, "正在进行"),
    SUCCESS(2, "执行成功"),
    FAILED(3, "执行失败"),
    ;

    private final int code;
    private final String label;

    TaskStateEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }
}
