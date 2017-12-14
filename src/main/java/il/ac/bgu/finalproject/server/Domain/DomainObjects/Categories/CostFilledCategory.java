package il.ac.bgu.finalproject.server.Domain.DomainObjects.Categories;

import il.ac.bgu.finalproject.server.CategoryType;

public class CostFilledCategory extends FilledCategory<Double> {

    public CostFilledCategory(Double value) {
        super(CategoryType.cost, value);
    }


    public static CostFilledCategory parseValue(String valueString) {
        Double value;
        try {
            value = Double.parseDouble(valueString);
        }
        catch (NumberFormatException e){
            throw e;
        }

       return new CostFilledCategory(value);
    }
}
