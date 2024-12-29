package org.omni.bank.customer.exception;

public class DuplicateEntryException extends RuntimeException{

    public DuplicateEntryException(String message){
        super(message);
    }
}
