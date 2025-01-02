package hjk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Agent implements Serializable {
    private String name;
    private String id;
    private String contactInfo;
    private List<Property> properties;

    public Agent(String name, String id, String contactInfo) {
        this.name = name;
        this.id = id;
        this.contactInfo = contactInfo;
        this.properties = new ArrayList<>();
    }

    //getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public void deleteProperty(Property property) {
        properties.remove(property);
    }

    @Override
    public String toString() {
        return name + " | " + id + " | " + contactInfo;
    }
}
