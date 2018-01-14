package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

public class SearchCategoryDTO {
    private int categoryType;
    private String value;

    public SearchCategoryDTO(){}
    public SearchCategoryDTO(int categoryType, String value) {

        this.categoryType = categoryType;
        this.value = value;
    }

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
