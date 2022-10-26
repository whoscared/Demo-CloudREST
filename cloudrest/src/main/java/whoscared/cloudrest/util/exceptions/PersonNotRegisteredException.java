package whoscared.cloudrest.util.exceptions;

public class PersonNotRegisteredException extends RuntimeException{

    public PersonNotRegisteredException(String errorMessage){
        super(errorMessage);
    }
}
