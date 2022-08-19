package com.strong.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
@Slf4j
@RestControllerAdvice//拦截所有的Rest风格的异常
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> sqlExceptionHAndler(SQLIntegrityConstraintViolationException exception) {
        log.info(exception.getMessage());
        if(exception.getMessage().contains("Duplicate entry")){
            String[] split = exception.getMessage().split(" ");
            String msg =split[2]+"以存在";
            return R.error(msg);
        }
        return R.error("未知错误");
    }
}
