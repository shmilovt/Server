package il.ac.bgu.finalproject.server.Domain.Exceptions;

public class NoUserNameException extends ProjectException {

    public NoUserNameException(String username) {
        super("The username "+username+" not exist");
    }

}
