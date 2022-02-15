package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangmeiliang
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result<T> implements Serializable {

    public static final String SUCCESS_CODE = "success";

    private String message;
    private String code;
    @JsonInclude
    private T data;

    public static <T> Result<T> success() {
        return new Result<T>().setCode(SUCCESS_CODE).setMessage("");
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>().setCode(SUCCESS_CODE).setData(data).setMessage("");
    }

    public static Result<Map<String, Object>> success(String key, Object vale) {
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put(key, vale);
        return new Result<Map<String, Object>>().setCode(SUCCESS_CODE).setData(resultMap).setMessage("");
    }

    public static <T> Result<T> error(String code, String msg) {
        return new Result<T>().setCode(code).setMessage(msg);
    }

    public static <T> Result<T> error(String msg) {
        return error("TD0300000000", msg);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }

    @JsonIgnore
    public boolean isSuccessAndNotNull() {
        return SUCCESS_CODE.equals(code) && Objects.nonNull(data);
    }
}
