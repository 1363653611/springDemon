package com.zbcn.common.exceptions;

public class UserNotLoginException extends BusinessException {

    private static final long serialVersionUID = 5062683020856479750L;

    public UserNotLoginException() {
        super();
    }

    public UserNotLoginException(String msg) {
        super(msg);
    }

    public UserNotLoginException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
