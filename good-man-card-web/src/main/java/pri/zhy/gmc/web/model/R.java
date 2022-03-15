package pri.zhy.gmc.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import pri.zhy.gmc.web.enums.ResultEnum;

/**
 * 响应结果
 *
 * @author zhy
 * @date 2022年03月15日
 */
@Data
public class R<T> {

    private Integer code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;


    public R(ResultEnum result) {
        this.code = result.getCode();
        this.message = result.getMessage();
    }

    public static R<Void> success() {
        return new R<>(ResultEnum.SUCCESS);
    }


    public static <T> R<T> success(T t) {
        R<T> ret = new R<>(ResultEnum.SUCCESS);
        ret.setData(t);
        return ret;
    }

    public static <T> R<T> failed(ResultEnum result) {
        return new R<>(result);
    }

    public static <T> R<T> error(String message) {
        R<T> r = new R<>(ResultEnum.FAILED);
        r.setMessage(message);
        return r;
    }
}
