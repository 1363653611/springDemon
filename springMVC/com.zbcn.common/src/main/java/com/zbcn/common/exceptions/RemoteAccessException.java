package com.zbcn.common.exceptions;

import com.zbcn.common.enums.ResultCode;

/**
 * 远程访问异常
 */
public class RemoteAccessException extends BusinessException {

    private static final long serialVersionUID = -6088232414299368237L;

    public RemoteAccessException() {
        super();
    }

    public RemoteAccessException(Object data) {
        super.data = data;
    }

    public RemoteAccessException(ResultCode resultCode) {
        super(resultCode);
    }

    public RemoteAccessException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public RemoteAccessException(String msg) {
        super(msg);
    }

    public RemoteAccessException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
