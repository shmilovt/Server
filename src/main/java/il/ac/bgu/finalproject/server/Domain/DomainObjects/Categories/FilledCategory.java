package il.ac.bgu.finalproject.server.Domain.DomainObjects.Categories;

import il.ac.bgu.finalproject.server.CategoryType;

public abstract class FilledCategory <T> {
    private CategoryType category;
    private T value;

    public FilledCategory(CategoryType category, T value) {
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

}
