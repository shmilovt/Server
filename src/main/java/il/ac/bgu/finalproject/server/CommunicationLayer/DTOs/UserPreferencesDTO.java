package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

public class UserPreferencesDTO {
 private SearchCategoryDTO[] preferences;

    public UserPreferencesDTO(){}
    public UserPreferencesDTO(SearchCategoryDTO[] preferences) {
        this.preferences = preferences;
    }

    public SearchCategoryDTO[] getPreferences() {
        return preferences;
    }

    public void setPreferences(SearchCategoryDTO[] preferences) {
        this.preferences = preferences;
    }
}
