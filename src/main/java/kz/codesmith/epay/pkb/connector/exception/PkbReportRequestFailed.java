package kz.codesmith.epay.pkb.connector.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class PkbReportRequestFailed extends RuntimeException{
    public PkbReportRequestFailed(String msg) {
        super(msg);
    }
}
