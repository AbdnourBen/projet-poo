public class Property {
    private String type;
    private double size;
    private double price;
    private String location;
    private String description;

    public Property(String type, double size, double price, String location, String description) {
        this.type = type;
        this.size = size;
        this.price = price;
        this.location = location;
        this.description = description;
    }

    // Getters
    public String getType() {
        return type;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setType(String type) {
        this.type = type;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
