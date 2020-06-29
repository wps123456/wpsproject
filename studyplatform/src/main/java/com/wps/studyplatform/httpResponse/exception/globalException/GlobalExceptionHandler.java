package com.wps.studyplatform.httpResponse.exception.globalException;

import com.wps.studyplatform.httpResponse.exception.licenseException.LicenseException;
import com.wps.studyplatform.utils.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LicenseException.class)
    public ApiResult handleLicenseException(LicenseException e){
        return ApiResult.fail(e.getMessage(),e.getData());
    }
}
