package me.nightwarrior.epayconsumes.exception;

import me.nightwarrior.epayconsumes.model.Response;

public class RequestException extends RuntimeException {

    private Response response;

    public RequestException(String message) {
        super(message);
    }

    public RequestException(Response response) {
        this.response = response;
    }

    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestException(String message, Response response) {
        super(message);
        this.response = response;
    }

    public RequestException(Throwable throwable, Response response) {
        super(throwable);
        this.response = response;
    }

    public RequestException(String message, Throwable cause, Response response) {
        super(message, cause);
        this.response = response;
    }

    public Response getStatus() {
        return response;
    }
}
