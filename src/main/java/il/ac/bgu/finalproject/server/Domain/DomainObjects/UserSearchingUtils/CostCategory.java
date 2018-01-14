package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

public class CostCategory extends SearchCategory<Double> {

    public CostCategory() {super(CategoryType.cost);}
    public CostCategory(Double value) {
        super(CategoryType.cost, value);
    }


    public  void parseValue(String valueString) {
        Double value;
        try {
            value = Double.parseDouble(valueString);
        }
        catch (NumberFormatException e){
            throw e;
        }
            this.setValue(value);

    }
}
