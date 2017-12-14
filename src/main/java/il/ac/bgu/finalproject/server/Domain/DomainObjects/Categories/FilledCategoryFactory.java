package il.ac.bgu.finalproject.server.Domain.DomainObjects.Categories;

import il.ac.bgu.finalproject.server.CategoryType;
import il.ac.bgu.finalproject.server.Domain.Exceptions.NoCategoryTypeException;

public class FilledCategoryFactory {
    public FilledCategory createFilledCategory(CategoryType categoryType, String value) throws NoCategoryTypeException {
        if (categoryType == CategoryType.cost)
            return CostFilledCategory.parseValue(value);
        throw new NoCategoryTypeException(categoryType.toString());

    }
}
