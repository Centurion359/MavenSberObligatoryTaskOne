package centurion359.sber.general;

import centurion359.sber.enums.Direction;
import centurion359.sber.enums.Moveable;
import centurion359.sber.enums.NameItems;
import centurion359.sber.enums.NameLocation;
import centurion359.sber.main.Main;
import centurion359.sber.threads.SaveThread;
import centurion359.sber.workClass.Inventory;
import centurion359.sber.workClass.Item;
import centurion359.sber.workClass.Location;
import centurion359.sber.workClass.Player;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class General {

    public static Logger LOGGER;

    static {
        try (FileInputStream ins = new FileInputStream("log/log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(Main.class.getName());
        } catch (Exception exception) {

        }
    }

    private static String newLocationName = "";
    private static String oldLocationName = "";
    private static String directionsNameLocation = "";
    public static Location location;
    private static Inventory inventoryLocation;
    public static Inventory inventoryUser;
    private static Location location1;
    private static String directionsName;

    public static void startGeneral() {
        LOGGER.log(Level.INFO, "Начало квеста");
        SaveThread saveThread = new SaveThread();
        saveThread.start();
        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        location = new Location();
        inventoryUser = new Inventory();
        inventoryUser.add(new Item(NameItems.Топор.name(), "Старый ржавый топор. Похоже он очень давно здесь лежал", Moveable.MOBILE));
        Random random = new Random();
        helloPlayer(player, scanner);
        getMainLocation(player);
        startGame(scanner, location, random, saveThread);
    }

    public static void showTooltip() {
        System.out.println("##Обучение## \n Q - осмотреться \t I - инвентарь  \t F - взять предметы \t Z - сохранить игру \t X - загрузить последнее сохранение" +
                "\n\tНаправление - R - варианты направлений: \n\t\t идти \u2191 - W \n\t\t идти \u2193 - S \n\t\t идти \u2192 - D \n\t\t идти \u2190 - A");
        System.out.println("Вы всегда можете отобразить ##Обучение## - H");
        System.out.println("Внимание! Для дополнительной сложности, при выходе и загрузке сохраения локация сбросится!");
        System.out.println("Внимание! При выходе может потребовать повторная инициализация");
        System.out.println("Для выхода из игры введите - QW");
        return;
    }

    public static void startGame(Scanner scanner, Location location, Random random, SaveThread saveThread) {
        try {
            NameLocation[] nameLocations = NameLocation.values();
            Direction[] directions = Direction.values();
            location.setName(nameLocations[random.nextInt(nameLocations.length)].name());
            String scannerString = "";
            clearConsole();
            showTooltip();
            Thread.sleep(1000);
            clearConsole();
            do {
                System.out.println("Вы находитесь на локации:" + location.getName());
                if (location.getName().equals("Хижина") && inventoryUser.find("Талисман") != null) {
                    System.out.println("Вы можете активировать талисман! Активировать ? \n 1 - да \t 2 - нет");
                    scannerString = scanner.next();
                    switch (scannerString) {
                        case "1":
                            try {
                                System.out.println("Вы выиграли!");
                                Thread.sleep(1000);
                                System.out.println("До новых встреч!");
                                LOGGER.log(Level.INFO, "Конец квеста");
                                System.exit(0);
                            } catch (Exception exception) {

                            }
                            break;
                        case "2":
                            System.out.println("Вы всегда сможете сюда вернутся:)");
                            break;
                    }
                }
                System.out.println("Что вы хотите сделать?");
                scannerString = scanner.next();
                switch (scannerString) {
                    case "QW":
                        System.exit(0);
                        break;
                    case "Z":
                        saveThread.save();
                        break;
                    case "X":
                        saveThread.getSaveFile();
                        break;
                    case "H":
                        showTooltip();
                        break;
                    case "Q":
                        look(random, nameLocations, directions);
                        break;
                    case "R":
                        System.out.println("Ваше направление ?:");
                        scannerString = scanner.next();
                        switch (scannerString) {
                            case "W":
                                if (directionsNameLocation.equals(scannerString)) {
                                    System.out.println("Вы пошли \u2191");
                                    oldLocationName = newLocationName;
                                    location.setName(oldLocationName);
                                } else {
                                    System.out.println("Нет пути!");
                                }
                                break;
                            case "S":
                                if (directionsNameLocation.equals(scannerString)) {
                                    System.out.println("Вы пошли \u2193");
                                    oldLocationName = newLocationName;
                                    location.setName(oldLocationName);
                                } else {
                                    System.out.println("Нет пути!");
                                }
                                break;
                            case "A":
                                if (directionsNameLocation.equals(scannerString)) {
                                    System.out.println("Вы пошли \u2190");
                                    oldLocationName = newLocationName;
                                    location.setName(oldLocationName);
                                } else {
                                    System.out.println("Нет пути!");
                                }
                                break;
                            case "D":
                                if (directionsNameLocation.equals(scannerString)) {
                                    System.out.println("Вы пошли \u2192 ");
                                    oldLocationName = newLocationName;
                                    location.setName(oldLocationName);
                                } else {
                                    System.out.println("Нет пути!");
                                }
                                break;
                        }
                        break;
                    case "I":
                        System.out.println("1 - отобразить все\t 2 - поиск по названию");
                        scannerString = scanner.next();
                        switch (scannerString) {
                            case "1":
                                System.out.println("Роюсь в снаряжении! \n Снаряжение:");
                                inventoryUser.show();
                                break;
                            case "2":
                                System.out.println("Название предмета (например, «Топор»)");
                                scannerString = scanner.next();
                                System.out.println("Роюсь в снаряжении! Совпадение...:");
                                System.out.println(inventoryUser.find(scannerString) != null ? inventoryUser.find(scannerString).getName()
                                        : "...нет совпадений");
                        }
                        break;
                    case "F":
                        System.out.println("Подбираем снаряжение....");
                        try {
                            Thread.sleep(1000);
                            System.out.println("Было подобрано: ");
                            inventoryLocation.show();
                            inventoryLocation.remove();
                            System.out.println(inventoryUser.count(NameItems.Топор.name()) >= 10 ? "......«Вы можете собрать талисман! Собрав талисман вы завершите квест! Собрать ?" : "");
                            if (inventoryUser.count(NameItems.Топор.name()) >= 10) {
                                System.out.println("1 - да \t 2 - нет");
                                scannerString = scanner.next();
                                switch (scannerString) {
                                    case "1":
                                        System.out.println("Собираем талисман...");
                                        try {
                                            Thread.sleep(1000);
                                            System.out.println("Талисман собран! Отправляйтесь в хижину и активируйте его!");
                                            inventoryUser.add(new Item("Талисман", "Мистическое сокровище", Moveable.MOBILE));
                                            for (int i = 0; i < 10; i++) {
                                                inventoryUser.removeName(NameItems.Топор.name());
                                            }
                                        } catch (Exception exception) {

                                        }
                                        break;
                                    case "2":
                                        System.out.println("Вы всегда сможете сюда вернутся:)");
                                        break;
                                }
                            }
                        } catch (Exception exception) {

                        }
                        break;
                }
            } while (scannerString != "X");
        } catch (Exception exception) {

        }
    }

    public static void look(Random random, NameLocation[] nameLocations, Direction[] directions) {
        if (newLocationName == oldLocationName) {
            Map<Direction, Location> directionLocationMap = new HashMap<>();
            location1 = new Location();
            directionsName = directions[random.nextInt(directions.length)].name();
            location1.setName(nameLocations[random.nextInt(nameLocations.length)].name());
            directionLocationMap.put(Direction.valueOf(directionsName), location1);
            location1.setDirectionLocationMap(directionLocationMap);
            location1.setInventory(new Inventory());
            System.out.println("Вам доступна локация:" + location1.getName() + " \tнаправление:" + directionsName);
            inventoryLocation = new Inventory();
            System.out.println("Доступные предметы на локации:");
            inventoryLocation.generatorItems();
            inventoryLocation.show();
            directionsNameLocation = directionsName;
            newLocationName = location1.getName();
        } else {
            System.out.println("Вам доступна локация:" + location1.getName() + " \tнаправление:" + directionsName);
            System.out.println("Доступные предметы на локации:");
            inventoryLocation.show();
        }
    }

    public static void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.print("\u26A0");
        }
        System.out.println();
    }

    public static void getMainLocation(Player player) {
        clearConsole();
        System.out.println("Ну что же " + player.getName() + ". Начнем нашу игру...");
        try {
            Thread.sleep(100);
            clearConsole();
            System.out.println("Вы появились на загадачной поляне... Хорошенько осмотревшись вы увидели рядом с собой письмо... \nВ письме было сказано:" +
                    "«Дорогой, " + player.getName() + " случайность приведшая тебя сюда, как бы это ни казалось случайным, \n совершенно не случайна...{испачкана жидкой субстанцией, черного цвета}" +
                    "....твоя цель собрать 10 или больше топоров и добраться до спрятанного портала....{испачкана жидкой субстанцией, черного цвета}..." +
                    "\n.....P/S Возьми первый топор под кустом малины...» Вы взяли первый топор, осталось еще 9");
        } catch (Exception exception) {

        }
    }

    public static void helloPlayer(Player player, Scanner scanner) {
        System.out.println("Добро пожаловать в ##Текстовый квест##");
        System.out.println("Пожалуйста, введите свое имя:");
        player.setName(scanner.nextLine());
        System.out.println("Подходящее имя! \n Начнем игру, " + player.getName() + "?");
        System.out.println("Подсказка! Нажимайте на клавиатуру, чтобы взаимодействовать с программой!");
        System.out.println("1 - Да, 2 - В другой раз");
        switch (scanner.nextInt()) {
            case 1:
                return;
            case 2:
                try {
                    System.out.println("До встречи!");
                    Thread.sleep(100);
                    System.exit(0);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
        }
    }

}
