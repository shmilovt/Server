package il.ac.bgu.finalproject.server.CommunicationLayer.DTOs;

public class UserPreferencesDTO {
 private FilledCategoryDTO [] preferences;

    public UserPreferencesDTO(){}
    public UserPreferencesDTO(FilledCategoryDTO[] preferences) {
        this.preferences = preferences;
    }

    public FilledCategoryDTO[] getPreferences() {
        return preferences;
    }

    public void setPreferences(FilledCategoryDTO[] preferences) {
        this.preferences = preferences;
    }
}
