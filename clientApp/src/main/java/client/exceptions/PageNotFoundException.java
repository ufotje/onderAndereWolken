package client.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException() {
        super();
    }
    public PageNotFoundException(String message) {
        super(message);
    }
    public PageNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
    public PageNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
