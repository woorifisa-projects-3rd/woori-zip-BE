package fisa.woorizip.backend.common.exception;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;

import lombok.Getter;

@Getter
public class WoorizipDetailException extends WooriZipException {

    private final String[] messages;

    public WoorizipDetailException(ErrorCode errorCode, String[] messages) {
        super(errorCode);
        this.messages = messages;
    }
}
