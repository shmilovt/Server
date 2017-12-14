package il.ac.bgu.finalproject.server.Domain.Exceptions;

public class ProjectException extends Exception {


    public ProjectException(String message){
        super(message);
    }

    public String getMessage(){
        return super.getMessage();
    }


}
