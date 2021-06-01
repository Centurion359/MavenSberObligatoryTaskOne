package centurion359.sber.workClass;

import centurion359.sber.enums.Moveable;
import centurion359.sber.enums.NameItems;
import centurion359.sber.general.General;
import centurion359.sber.main.Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Inventory implements Serializable {
    private List<Item> items = new ArrayList<>();

    public void generatorItems(){
        Random random = new Random();
        int iMax = random.nextInt(NameItems.values().length);
        NameItems[] nameItems = NameItems.values();
        Moveable[] moveables = Moveable.values();
        for (int i = 0; i < iMax; i++) {
            items.add(new Item(nameItems[i].name(), "Описание", moveables[random.nextInt(moveables.length)]));
        }
    }

    public List add(Item item){
        this.items.add(item);
        return items;
    }

    public List remove(){
        for (int i = 0; i < items.size(); i++) {
            General.inventoryUser.add(items.get(i));
        }
        this.items.removeAll(items);
        return items;
    }

    public List removeName(String name){
        General.inventoryUser.items.removeIf(item -> item.getName() == name );
        return items;
    }

    public void show(){
        for (Item item:
             items) {
            System.out.println(item.getName());
            System.out.println("\tОписание:" + item.getDescription());
        }
    }

    public long count(String name){
        long item = items
                .stream().filter(item_ -> name.equals(item_.getName())).count();
        return item;
    }

    public Item find(String name){
        Item item = items
                .stream().filter(item_ -> name.equals(item_.getName()))
                .findAny()
                .orElse(null);
        return item;
    }

}
