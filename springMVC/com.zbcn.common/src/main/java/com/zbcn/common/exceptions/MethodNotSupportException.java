package com.zbcn.common.exceptions;

import com.zbcn.common.enums.ResultCode;

/**
 * 方法不支持异常
 */
public class MethodNotSupportException extends BusinessException {

    private static final long serialVersionUID = -928790556230754862L;

    public MethodNotSupportException() {
        super();
    }

    public MethodNotSupportException(Object data) {
        super.data = data;
    }

    public MethodNotSupportException(ResultCode resultCode) {
        super(resultCode);
    }

    public MethodNotSupportException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public MethodNotSupportException(String msg) {
        super(msg);
    }

    public MethodNotSupportException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
