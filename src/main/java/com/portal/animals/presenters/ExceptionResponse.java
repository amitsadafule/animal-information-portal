package com.portal.animals.presenters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionResponse {

    private String code;
    private String description;
}
