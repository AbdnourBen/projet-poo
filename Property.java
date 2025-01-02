package hjk;

import java.io.Serializable;

public class Property implements Serializable {
    private String type;
    private double price;
    private String location;
    private double size;
    private String description;
    private String assignedAgent;

    public Property(String type, String location, double size, double price, String description, String assignedAgent) {
        this.type = type;
        this.location = location;
        this.size = size;
        this.price = price;
        this.description = description;
        this.assignedAgent = assignedAgent;
    }

    //getters and setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedAgent() {
        return assignedAgent;
    }

    public void setAssignedAgent(String assignedAgent) {
        this.assignedAgent = assignedAgent;
    }

    @Override
    public String toString() {
        if ("Choose Property..".equals(type)) {
            return type;
        }
        return type + " | " + price + " | " + location + " | " + size + "sqm | " + description + " | Agent: " + assignedAgent;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Property property = (Property) obj;
        return Double.compare(property.price, price) == 0 &&
                Double.compare(property.size, size) == 0 &&
                type.equals(property.type) &&
                location.equals(property.location) &&
                description.equals(property.description) &&
                assignedAgent.equals(property.assignedAgent);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type.hashCode();
        result = 31 * result + location.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(size);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

}
