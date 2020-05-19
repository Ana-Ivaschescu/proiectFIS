package exceptions;

public class UsernameAlreadyExistsException extends  Exception{
    public UsernameAlreadyExistsException(String username)
    {
        super(String.format("User %s already exists.\nPlease choose another username", username));
    }
}
