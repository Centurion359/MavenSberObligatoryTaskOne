import centurion359.sber.main.Main;
import centurion359.sber.workClass.Location;
import centurion359.sber.workClass.Player;
import org.junit.Test;

public class MainTest {

    @Test
    public void getMainLocation() {
        Player player = new Player();
        player.setName("Тестовый помощник");
        player.setLocation(new Location("Тестовое пространство"));
        Main.getMainLocation(player);
    }
}