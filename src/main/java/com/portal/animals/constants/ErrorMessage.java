package com.portal.animals.constants;

import java.util.HashMap;
import java.util.Map;

public enum ErrorMessage {

    TYPE_SIZE_NOT_VALID(Constants.TYPE_SIZE_NOT_VALID_CODE, "type parameter should have min 3 characters"),
    BREED_SIZE_NOT_VALID(Constants.BREED_SIZE_NOT_VALID_CODE, "breed parameter should have min 3 characters"),
    ANIMAL_NOT_FOUND(Constants.ANIMAL_NOT_FOUND_CODE, "Animal not found in the system");

    private String code;
    private String description;

    ErrorMessage(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static class Constants {
        public static final String TYPE_SIZE_NOT_VALID_CODE = "ERR1001";
        public static final String BREED_SIZE_NOT_VALID_CODE = "ERR1002";

        public static final String ANIMAL_NOT_FOUND_CODE = "ERR2002";
    }

    private static final Map<String, ErrorMessage> codeToEnum = new HashMap<>();
    static {
        for (ErrorMessage value : values()) {
            codeToEnum.put(value.code, value);
        }
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ErrorMessage getMessage(String code) {
        return codeToEnum.get(code);
    }
}
