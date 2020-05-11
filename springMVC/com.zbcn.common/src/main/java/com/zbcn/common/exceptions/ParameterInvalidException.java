package com.zbcn.common.exceptions;

import com.zbcn.common.enums.ResultCode;

/**
 * 参数无效异常类
 */
public class ParameterInvalidException extends BusinessException{

    private static final long serialVersionUID = 8897953855656558309L;

    public ParameterInvalidException() {
        super();
    }

    public ParameterInvalidException(Object data) {
        super();
        super.data = data;
    }

    public ParameterInvalidException(ResultCode resultCode) {
        super(resultCode);
    }

    public ParameterInvalidException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public ParameterInvalidException(String msg) {
        super(msg);
    }

    public ParameterInvalidException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
