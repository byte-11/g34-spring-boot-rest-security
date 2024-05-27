package uz.pdp.g34springbootsecurity.util;

public enum ErrorCode {
    INTERNAL_ERROR("INTERNAL_SERVER_ERROR"),
    DATA_NOT_FOUND("DATA_NOT_FOUND"),
    UNAUTHORIZED("UNAUTHORIZED");
    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
