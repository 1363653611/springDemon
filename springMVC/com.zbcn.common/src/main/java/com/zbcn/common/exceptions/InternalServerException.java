package com.zbcn.common.exceptions;

public class InternalServerException extends BusinessException {
    private static final long serialVersionUID = -6590814538233245304L;

    public InternalServerException() {
        super();
    }

    public InternalServerException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InternalServerException(String msg, Throwable cause, Object... objects) {
        super(msg, cause, objects);
    }

    public InternalServerException(String msg) {
        super(msg);
    }

    public InternalServerException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
