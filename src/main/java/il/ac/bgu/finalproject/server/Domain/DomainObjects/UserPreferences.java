package il.ac.bgu.finalproject.server.Domain.DomainObjects;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.Categories.FilledCategory;

public class UserPreferences {
    private FilledCategory[]  preferences;

    public UserPreferences(FilledCategory[] preferences) {
        this.preferences = preferences;
    }

    public FilledCategory[] getPreferences() {
        return preferences;
    }

    public void setPreferences(FilledCategory[] preferences) {
        this.preferences = preferences;
    }
}
