public class Client {
    private String name;
    private String contactInfo;
    private String preferences;

    public Client(String name, String contactInfo, String preferences) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.preferences = preferences;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getPreferences() {
        return preferences;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
}
