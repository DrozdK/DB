package Helper;

import enums.Errors;
import lombok.Getter;

@Getter
public class MySQLException extends java.lang.Exception {

    private Errors error;

    public MySQLException(Errors error, Throwable cause) {
        super(cause);
        this.error = error;
    }
}
