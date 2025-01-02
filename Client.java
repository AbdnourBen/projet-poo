package hjk;

import java.io.Serializable;

public class Client implements Serializable {
    private String name;
    private String contactInfo;
    private String role;
    private String preferences;

    public Client(String name, String contactInfo, String role, String preferences) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.role = role;
        this.preferences = preferences;
    }

    //getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    @Override
    public String toString() {
        return name + " (" + role + ") - " + contactInfo + " | Preferences: " + preferences;
    }
}
