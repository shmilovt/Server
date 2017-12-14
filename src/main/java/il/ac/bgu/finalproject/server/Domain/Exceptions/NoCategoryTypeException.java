package il.ac.bgu.finalproject.server.Domain.Exceptions;

public class NoCategoryTypeException  extends ProjectException {
    public NoCategoryTypeException(String  notCategoryType) {
        super("the category "+ notCategoryType+ " not exist");
    }
}
