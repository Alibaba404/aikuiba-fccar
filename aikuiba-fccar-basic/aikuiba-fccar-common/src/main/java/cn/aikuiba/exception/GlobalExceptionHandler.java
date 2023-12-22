package cn.aikuiba.exception;

import cn.aikuiba.result.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

//全局异常捕获
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕捉Exception异常，返回统一的错误提示(属于兜底的异常处理器)
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public JSONResult<Void> exceptionErrorMethod(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        log.error("发生异常错误，请求地址：{}，异常信息{}", request.getRequestURI(), e.getMessage());
        return JSONResult.error("服务器内部异常，请联系管理员");
    }

    /**
     * 捕捉GlobalException异常，把错误日志抛给用户
     * 主要拦截业务异常:程序员主动抛出的异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = GlobalException.class)
    public JSONResult<Void> globalCustomMethod(GlobalException e, HttpServletRequest request) {
        e.printStackTrace();
        log.error("发生异常错误，请求地址：{}，异常信息：{}", request.getRequestURI(), e.getMessage());
        return JSONResult.error(e.getCode(), e.getMessage());
    }
}

