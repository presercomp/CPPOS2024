package controllers;

public class Products {
    private int id;
    private String name;
    private boolean active;

    public Products() {
    }

    public Products(int id) {
        this.id = id;
    }

    public Products(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Products(int id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
    
    
}
