package centurion359.sber.workClass;

import centurion359.sber.enums.Moveable;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private String description;
    private Moveable moveable;

    public Item(String name, String description, Moveable moveable) {
        this.name = name;
        this.description = description;
        this.moveable = moveable;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Moveable getMoveable() {
        return moveable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMoveable(Moveable moveable) {
        this.moveable = moveable;
    }
}
