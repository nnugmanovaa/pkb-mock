package kz.codesmith.epay.pkb.connector.controller.error;

import kz.codesmith.epay.pkb.connector.exception.PkbReportRequestFailed;
import kz.codesmith.epay.pkb.connector.exception.ReportForSubjectNotFound;
import kz.codesmith.epay.pkb.connector.exception.UnsupportedReportException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PkbReportRequestFailed.class)
    public ResponseEntity<ApiErrorResponse> pkbRequestFailed(PkbReportRequestFailed exc, WebRequest request) {
        var errorResponse = ApiErrorResponse.builder()
                .path(request.getContextPath())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .message(exc.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnsupportedReportException.class)
    public ResponseEntity<ApiErrorResponse> unsupportedReport(UnsupportedReportException exc, WebRequest request) {
        var errorResponse = ApiErrorResponse.builder()
                .path(request.getContextPath())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .message(exc.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ReportForSubjectNotFound.class)
    public ResponseEntity<ApiErrorResponse> reportNotFound(ReportForSubjectNotFound exc, WebRequest request) {
        var errorResponse = ApiErrorResponse.builder()
                .path(request.getContextPath())
                .status(HttpStatus.NOT_FOUND.value())
                .error("ReportForSubjectNotFound")
                .timestamp(LocalDateTime.now())
                .message(exc.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}