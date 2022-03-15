package pri.zhy.gmc.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pri.zhy.gmc.web.model.R;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用错误处理
 *
 * @author zhy
 * @date 2022年03月15日
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public R<Void> handleRuntimeException(RuntimeException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return R.error(e.getMessage());
    }


    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public R<Void> handleException(Exception e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return R.error(e.getMessage());
    }
}
