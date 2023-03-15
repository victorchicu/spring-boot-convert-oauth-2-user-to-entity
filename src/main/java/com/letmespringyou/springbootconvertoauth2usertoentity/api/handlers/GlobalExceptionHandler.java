package com.letmespringyou.springbootconvertoauth2usertoentity.api.handlers;

import com.letmespringyou.springbootconvertoauth2usertoentity.api.dto.ErrorDto;
import com.letmespringyou.springbootconvertoauth2usertoentity.api.exceptions.OopsSomethingWentWrong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(Logger.class);

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ErrorDto> handleException(Exception e) {
        logger.error(e.getMessage(),
                e);
        return new ResponseEntity<>(
                ErrorDto.builder()
                        .withMessage("An unhandled exception occurred, please try again later.")
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = OopsSomethingWentWrong.class)
    protected ResponseEntity<ErrorDto> handleOopsSomethingWentWrong(OopsSomethingWentWrong e) {
        logger.error(e.getMessage(),
                e);
        return new ResponseEntity<>(
                ErrorDto.builder()
                        .withMessage("Oops, something went wrong.")
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
}