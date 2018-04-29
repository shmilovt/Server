package il.ac.bgu.finalproject.server.Domain.Exceptions;

public class ProjectException extends Exception {


    private  int code;
    public ProjectException(String message){
        super(message);
    }

    public ProjectException(String message,int code){
        super(message);
        this.code=code;
    }

    public String getMessage(){
        return super.getMessage();
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
