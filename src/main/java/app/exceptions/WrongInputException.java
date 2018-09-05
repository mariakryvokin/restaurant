package app.exceptions;

public class WrongInputException extends HttpException {
    public WrongInputException(int errorCode, String msg) {
        super(errorCode, msg);
    }
}
