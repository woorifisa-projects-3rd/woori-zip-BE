package fisa.woorizip.backend.common.exception;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

public class WooriZipException extends RuntimeException {

    private final ErrorCode errorCode;

    public WooriZipException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
