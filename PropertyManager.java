import java.util.ArrayList;
import java.util.List;

public class PropertyManager {
    private List<Property> properties = new ArrayList<>();

    public void addProperty(Property property) {
        properties.add(property);
    }


    public void modifyProperty(int index, Property newProperty) {
        if (index >= 0 && index < properties.size()) {
            properties.set(index, newProperty);
        } else {
            System.out.println("Invalid index");
        }
    }

    public void deleteProperty(int index) {
        if (index >= 0 && index < properties.size()) {
            properties.remove(index);
        } else {
            System.out.println("Invalid index");
        }
    }

    public Property searchProperty(String propertyType) {
        for (Property property : properties) {
            if (property.getType().equals(propertyType)) {
                return property;
            }
        }
        return null; // Return null if not found
}
    
}
