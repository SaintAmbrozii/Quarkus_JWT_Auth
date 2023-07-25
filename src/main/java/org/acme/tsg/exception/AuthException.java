package org.acme.tsg.exception;



public class AuthException extends RuntimeException {

    public AuthException(String s) {
        super("Пароль не совпадает" + s);
    }

}