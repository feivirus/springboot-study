package com.feivirus.common.utils.entity;

import com.feivirus.common.utils.enums.BaseIntegerEnum;
import com.feivirus.common.utils.enums.ErrorEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author feivirus
 */
@Data
public class Result<T extends Object> implements Serializable {

    private static final long serialVersionUID = -2338064620188562573L;

    private int code;

    private String msg;

    private T data;

    public Result() {

    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> newSuccess() {
        return new Result<T>(ErrorEnum.SUCCESS.getKey(), "success", null);
    }

    public static <T> Result<T> newSuccess(T data) {
        return new Result<>(ErrorEnum.SUCCESS.getKey(), "success", data);
    }

    public static <T> Result<T> newFailure(BaseIntegerEnum error) {
        return new Result<T>(error.getKey(), error.getValue(), null);
    }

    public static <T> Result<T> newFailure(int errorCode, String errorMsg) {
        return new Result<>(errorCode, errorMsg, null);
    }

    public static <T> Result<T> newFailure(String errorMsg) {
        return new Result<>(ErrorEnum.FAILURE.getKey(), errorMsg, null);
    }

}
