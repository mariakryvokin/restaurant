package app.exceptions;

import javax.servlet.ServletException;

public abstract class HttpException extends ServletException {
    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    public HttpException(int errorCode, String msg) {
        super(msg);
        this.statusCode = errorCode;
    }
}
