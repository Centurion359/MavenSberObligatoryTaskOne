package centurion359.sber.workClass;

import centurion359.sber.enums.Direction;

import java.util.Map;

public class Location {
    private String name;
    private String description;
    private Inventory inventory;
    Map<Direction,Location> directionLocationMap;

    public Location(String name) {
        this.name = name;
    }

    public Location() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Map<Direction, Location> getDirectionLocationMap() {
        return directionLocationMap;
    }

    public void setDirectionLocationMap(Map<Direction, Location> directionLocationMap) {
        this.directionLocationMap = directionLocationMap;
    }
}
