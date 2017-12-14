package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

import il.ac.bgu.finalproject.server.CategoryType;

public class FilledCategoryDTO {
    private CategoryType categoryType;
    private String value;

    public FilledCategoryDTO(){}
    public FilledCategoryDTO(CategoryType categoryType, String value) {

        this.categoryType = categoryType;
        this.value = value;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
