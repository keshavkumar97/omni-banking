package org.omni.bank.auth.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class DuplicateEntryException extends ErrorResponseException {
    public DuplicateEntryException(HttpStatusCode code, String msg) {
        super(code, ProblemDetail.forStatusAndDetail(code, msg), null);
    }
}
