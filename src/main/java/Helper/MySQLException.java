package Helper;

import enums.Error;
import lombok.Getter;

@Getter
public class MySQLException extends java.lang.Exception {

    private final Error error;

    public MySQLException(Error error, Throwable cause) {
        super(cause);
        this.error = error;
    }
}
