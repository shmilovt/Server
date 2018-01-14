package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

public class UserPreferences {
    private SearchCategory[]  preferences;

    public UserPreferences(SearchCategory[] preferences) {
        this.preferences = preferences;
    }

    public SearchCategory[] getPreferences() {
        return preferences;
    }

    public void setPreferences(SearchCategory[] preferences) {
        this.preferences = preferences;
    }
}
