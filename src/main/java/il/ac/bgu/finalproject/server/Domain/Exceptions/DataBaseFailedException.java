package il.ac.bgu.finalproject.server.Domain.Exceptions;

public class DataBaseFailedException extends ProjectException  {
    public DataBaseFailedException(String  dataBaseFailed,int code) {
        super("the query "+ dataBaseFailed + " failed.");
    }


}
