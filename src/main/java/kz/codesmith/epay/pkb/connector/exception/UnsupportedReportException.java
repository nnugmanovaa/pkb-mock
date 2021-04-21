package kz.codesmith.epay.pkb.connector.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnsupportedReportException extends RuntimeException  {
    public UnsupportedReportException(String message) {
        super(message);
    }
}

