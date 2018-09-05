package app.exceptions;

public class ExistsException extends HttpException {
    public ExistsException(int errorCode, String msg) {
        super(errorCode, msg);
    }
}
