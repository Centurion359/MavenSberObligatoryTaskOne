package centurion359.sber.threads;

import centurion359.sber.general.General;
import centurion359.sber.main.Main;
import centurion359.sber.workClass.Inventory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;

public class SaveThread extends Thread {
    @Override
    public void run() {
        General.LOGGER.log(Level.INFO, "Запуск потока сохранения");
    }

    public void save(){
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Save.dat"))){
            objectOutputStream.writeObject(General.inventoryUser);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void getSaveFile(){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Save.dat")))
        {
            General.inventoryUser = (Inventory) objectInputStream.readObject();
        }
        catch(Exception exception){
            System.out.println(exception);
        }
    }

}
