package com.example.xumama.exception;

import cn.dev33.satoken.exception.NotLoginException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler
 *
 * @author zhangshun
 * @date 2022/8/9
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public String handlerException(Model model,Exception e) {
        if(e instanceof NotLoginException){
            return "redirect:/";
        }
        model.addAttribute("err_msg",e.getMessage());
        e.printStackTrace();
        return "error";
    }
}
