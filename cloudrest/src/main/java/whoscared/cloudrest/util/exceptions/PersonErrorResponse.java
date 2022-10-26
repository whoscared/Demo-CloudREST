package whoscared.cloudrest.util.exceptions;

//object of this class will be sent (in JSON format) to the web-service
public class PersonErrorResponse {
    private final String message;

    public PersonErrorResponse(String message) {
        this.message = message;
    }
}
