package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

public abstract class SearchCategory<T> {
    private CategoryType category;
    private T value;


    public SearchCategory(CategoryType category) {
        this.category = category;
    }
    public SearchCategory(CategoryType category, T value) {
        this.category = category;
        this.value = value;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public abstract  void parseValue(String valueString);

}
