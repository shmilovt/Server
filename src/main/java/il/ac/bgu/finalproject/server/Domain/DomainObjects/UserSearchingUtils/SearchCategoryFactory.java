package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.Exceptions.NoCategoryTypeException;

public class SearchCategoryFactory {
    public SearchCategory createFilledCategory(CategoryType categoryType, String value) throws NoCategoryTypeException {
        if (categoryType == CategoryType.cost) {
            CostCategory costCategory = new CostCategory();
            costCategory.parseValue(value);
            return costCategory;

        }
        throw new NoCategoryTypeException(categoryType.toString());

    }
}
