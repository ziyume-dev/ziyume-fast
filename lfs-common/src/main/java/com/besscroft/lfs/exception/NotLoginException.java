package com.besscroft.lfs.exception;

import com.besscroft.lfs.result.IResultCode;
import lombok.Getter;

/**
 * @Description 未登录异常
 * @Author Bess Croft
 * @Time 2022/1/9 15:08
 */
@Getter
public class NotLoginException extends RuntimeException {

    public IResultCode resultCode;

    public NotLoginException() {
        super("用户未登录");
    }

    public NotLoginException(String message){
        super(message);
    }

    public NotLoginException(Throwable cause){
        super(cause);
    }

    public NotLoginException(String message, Throwable cause){
        super(message, cause);
    }

    public NotLoginException(IResultCode resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

}
