package app.exceptions;

public class NotEnoughPermissionException extends HttpException {
    public NotEnoughPermissionException(int code,String msg) {
        super(code,msg);
    }

}
