package com.zbcn.common.response;

import com.zbcn.common.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用平台返回接口封装
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlatformResult<T> implements Result {

    private static final long serialVersionUID = -3768426191571879565L;

    private Integer code;

    private String msg;

    private T data;

    public static PlatformResult success() {
        PlatformResult result = new PlatformResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    private void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }

    public static <T> PlatformResult success(T data) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static PlatformResult failure(ResultCode resultCode) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(resultCode);
        return result;
    }

    public static <T> PlatformResult failure(ResultCode resultCode, T data) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static PlatformResult failure(String message) {
        PlatformResult result = new PlatformResult();
        result.setCode(ResultCode.PARAM_IS_INVALID.code());
        result.setMsg(message);
        return result;
    }



}
