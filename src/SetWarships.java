import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SetWarships {

    // Инициализация логера
    private static final Logger log = Logger.getLogger(SetWarships.class);

    private String[] lettersButton = {"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К"};
    private int countShip = 0;
    private int numberOfShipDecks;
    private ArrayList<String> listCoordinatShipsPlayer = new ArrayList<>();

    public ArrayList<String> getListCoordinatShipsPlayer() {
        return listCoordinatShipsPlayer;
    }

    public List<JButton> setShips(List<JButton> list, int numGamer) {
        level:
        while (countShip != 10) {

            if (countShip == 0) numberOfShipDecks = 4;
            else if (countShip > 0 && countShip <= 2) numberOfShipDecks = 3;
            else if (countShip > 2 && countShip <= 5) numberOfShipDecks = 2;
            else if (countShip > 5 && countShip <= 9) numberOfShipDecks = 1;

            int direction = (int) (Math.random() * 3); // направление корабля 0 - вверх, 1-вправо, 2-вниз, 3-влево
            int numletter = (int) (Math.random() * 9); // начальная буква
            int numButton = 1 + (int) (Math.random() * 10); // начальная цифра

            switch (numberOfShipDecks){
                case 4:
                    //--------вверх ----------//
                    if (direction == 0 && numButton > 3) { // проверка условия что попадаем в поле игры
                        // метод if проверяет можно ли построить корабль и не задеть другие корабли
                        if (checkButton(direction,numletter,numButton,list)){
                            log.info("Строим корабль номер " + countShip + ". Колличество палуб -  4");
                            setUpShip(numletter,numButton, list, numGamer);
                        } else {
                            continue level;
                        }
                    }
                    //--------вправо ----------//
                    else if (direction == 1 && numletter < lettersButton.length - numberOfShipDecks) {
                        if (checkButton(direction,numletter,numButton,list)){
                            log.info("Строим корабль номер " + countShip + ". Колличество палуб -  4");
                            setRightShip(numletter,numButton,list, numGamer);
                        }else {
                            continue level;
                        }
                    }
                    //--------вниз ----------//
                    else if (direction == 2 && numButton < 8) {
                        if (checkButton(direction,numletter,numButton,list)) {
                            log.info("Строим корабль номер " + countShip + ". Колличество палуб -  4");
                            setDownShip(numletter, numButton, list, numGamer);
                        }else {
                            continue level;
                        }
                    }
                    //--------влево ----------//
                    else if (direction == 3 && numletter > 3) {
                        if (checkButton(direction,numletter,numButton,list)) {
                            log.info("Строим корабль номер " + countShip + ". Колличество палуб -  4");
                            setLeftShip(numletter, numButton, list, numGamer);
                        }else {
                            continue level;
                        }
                    }else {
                        continue level;
                    }
                    log.info("Корабль номер " + countShip + " построен.");
                    break;
                case 3:
                    if (direction == 0 && numButton > 2) {
                        if (checkButton(direction,numletter,numButton,list)) {
                            log.info("Строим корабль номер " + countShip + ". Колличество палуб -  3");
                            setUpShip(numletter, numButton, list, numGamer);
                        }else {
                            continue level;
                        }
                    }
                    else if (direction == 1 && numletter < lettersButton.length - numberOfShipDecks) {
                        if (checkButton(direction,numletter,numButton,list)) {
                            log.info("Строим корабль номер " + countShip + ". Колличество палуб -  3");
                            setRightShip(numletter, numButton, list, numGamer);
                        }else {
                            continue level;
                        }
                    }
                    else if (direction == 2 && numButton < 9) {
                        if (checkButton(direction,numletter,numButton,list)) {
                            log.info("Строим корабль номер " + countShip + ". Колличество палуб -  3");
                            setDownShip(numletter, numButton, list, numGamer);
                        }else {
                            continue level;
                        }
                    }
                    else if (direction == 3 && numletter > 2) {
                        if (checkButton(direction,numletter,numButton,list)) {
                            log.info("Строим корабль номер " + countShip + ". Колличество палуб -  3");
                            setLeftShip(numletter, numButton, list, numGamer);
                        }else {
                            continue level;
                        }
                    }else {
                        continue level;
                    }
                    log.info("Корабль номер " + countShip + " построен.");
                    break;
                case 2:
                    if (direction == 0 && numButton > 1) {
                        if (checkButton(direction,numletter,numButton,list)) {
                            log.info("Строим корабль номер " + countShip + ". Колличество палуб -  2");
                            setUpShip(numletter, numButton, list, numGamer);
                        } else {
                            continue level;
                        }
                    }
                    else if (direction == 1 && numletter < lettersButton.length - numberOfShipDecks) {
                        if (checkButton(direction,numletter,numButton,list)) {
                            log.info("Строим корабль номер " + countShip + ". Колличество палуб -  2");
                            setRightShip(numletter, numButton, list, numGamer);
                        } else {
                            continue level;
                        }
                    }
                    else if (direction == 2 && numButton < 10) {
                        if (checkButton(direction,numletter,numButton,list)) {
                            log.info("Строим корабль номер " + countShip + ". Колличество палуб -  2");
                            setDownShip(numletter, numButton, list, numGamer);
                        } else {
                            continue level;
                        }
                    }
                    else if (direction == 3 && numletter > 1) {
                        if (checkButton(direction,numletter,numButton,list)) {
                            log.info("Строим корабль номер " + countShip + ". Колличество палуб -  2");
                            setLeftShip(numletter, numButton, list, numGamer);
                        } else {
                            continue level;
                        }
                    } else {
                        continue level;
                    }
                    log.info("Корабль номер " + countShip + " построен.");
                    break;
                case 1:
                    if (checkButton(direction,numletter,numButton,list)) {
                        log.info("Строим корабль номер " + countShip + ". Колличество палуб -  1");
                        setUpShip(numletter, numButton, list, numGamer);
                    } else {
                        continue level;
                    }
                    log.info("Корабль номер " + countShip + " построен.");
                    break;
            }
            countShip++;
        }
        return list;
    }


    //--------вверх ----------//
    private void setUpShip(int numletter,int numButton, List<JButton> list, int numGamer){
        log.info("Координаты корабля:");
        String infoNameDeskShip = " ";
        String nameFirstDeck = lettersButton[numletter] + numButton; // устанавливаем начальную точку строительства корабля
        level1:
        while (numberOfShipDecks > 0){
            for (int i = 0; i < list.size(); i++) {
                JButton jButton = list.get(i); // берем каждую кнопку по очереди из листа
                if (jButton.getName().equals(nameFirstDeck)) { // сравниваем что это кнопка из очереди и выбранная точка есть в списке
                    String nameDeck = numGamer + nameFirstDeck + countShip + numberOfShipDecks; // устанавливаем новое имя кнопки
                    infoNameDeskShip =  infoNameDeskShip + " " + nameDeck + ", "; // координаты корабля для логирования
                    listCoordinatShipsPlayer.add(nameDeck); // записываем координаты корабля в лист координат
                    jButton.setName(nameDeck); //  устанавливаем новое имя кнопки
                    // Данный if окрашивает установленные корабли игрока 1 в зеленый цвет
                    if (numGamer == 1){
                        jButton.setBackground(Color.green);
                    }
                    list.set(i,jButton); // перезаписываем обновленную кнопку в лист кнопок на позицию откуда взяли старую
                    numButton--;
                    nameFirstDeck = lettersButton[numletter] + numButton; // Устанавливаем следующие координаты(палубы) корабля
                    numberOfShipDecks--;
                    continue level1;
                }
            }
        }
        log.info(infoNameDeskShip);
    }
    //--------вправо ----------//
    private void setRightShip(int numletter,int numButton, List<JButton> list, int numGamer){
        log.info("Координаты корабля:");
        String infoNameDeskShip = " ";
        String nameFirstDeck = lettersButton[numletter] + numButton;
        level2:
        while (numberOfShipDecks > 0) {
            for (int i = 0; i < list.size(); i++) {
                JButton jButton = list.get(i);
                if (jButton.getName().equals(nameFirstDeck)) {
                    String nameDeck = numGamer + nameFirstDeck + countShip + numberOfShipDecks;
                    infoNameDeskShip =  infoNameDeskShip + " " + nameDeck + ", "; // координаты корабля
                    listCoordinatShipsPlayer.add(nameDeck);
                    jButton.setName(nameDeck);
                    // Данный if окрашивает установленные корабли игрока 1 в зеленый цвет
                    if (numGamer == 1){
                        jButton.setBackground(Color.GREEN);
                    }
                    list.set(i,jButton);
                    numletter++;
                    nameFirstDeck = lettersButton[numletter] + numButton;
                    numberOfShipDecks--;
                    continue level2;
                }
            }
        }
        log.info(infoNameDeskShip);
    }
    //--------вниз ----------//
    private void setDownShip(int numletter,int numButton, List<JButton> list, int numGamer){
        log.info("Координаты корабля:");
        String infoNameDeskShip = "";
        String nameFirstDeck = lettersButton[numletter] + numButton;
        level3:
        while (numberOfShipDecks > 0){
            for (int i = 0; i < list.size(); i++) {
                JButton jButton = list.get(i);
                if (jButton.getName().equals(nameFirstDeck)) {
                    String nameDeck = numGamer + nameFirstDeck + countShip + numberOfShipDecks;
                    infoNameDeskShip = infoNameDeskShip + " " + nameDeck + ", "; // координаты корабля
                    listCoordinatShipsPlayer.add(nameDeck);
                    jButton.setName(nameDeck);
                    // Данный if окрашивает установленные корабли игрока 1 в зеленый цвет
                    if (numGamer == 1){
                        jButton.setBackground(Color.GREEN);
                    }
                    list.set(i,jButton);
                    numButton++;
                    nameFirstDeck = lettersButton[numletter] + numButton;
                    numberOfShipDecks--;
                    continue level3;
                }
            }
        }
        log.info(infoNameDeskShip);
    }
    //--------влево ----------//
    private void setLeftShip(int numletter,int numButton, List<JButton> list, int numGamer){
        log.info("Координаты корабля:");
        String infoNameDeskShip = " ";
        String nameFirstDeck = lettersButton[numletter] + numButton;
        level4:
        while (numberOfShipDecks > 0) {
            for (int i = 0; i < list.size(); i++) {
                JButton jButton = list.get(i);
                if (jButton.getName().equals(nameFirstDeck)) {
                    String nameDeck = numGamer + nameFirstDeck + countShip + numberOfShipDecks;
                    infoNameDeskShip =  infoNameDeskShip + " " + nameDeck + ", "; // координаты корабля
                    listCoordinatShipsPlayer.add(nameDeck);
                    jButton.setName(nameDeck);
                    // Данный if окрашивает установленные корабли игрока 1 в зеленый цвет
                    if (numGamer == 1){
                        jButton.setBackground(Color.GREEN);
                    }
                    list.set(i,jButton);
                    numletter--;
                    nameFirstDeck = lettersButton[numletter] + numButton;
                    numberOfShipDecks--;
                    continue level4;
                }
            }
        }
        log.info(infoNameDeskShip);
    }
    // проверка,можно ли строить корабль
    private boolean checkButton (int direction, int numletter,int numButton, List<JButton> list){

        int goodButton = 0;
        int checkDecks = 0;

        while (checkDecks < numberOfShipDecks ){

            int[] numLetterB = new int[3];
            int[] numButtonB = new int[3];
            // ------------------------ //
            if (numletter == 0){
                numLetterB[0] = numletter;
                numLetterB[1] = numletter;
                numLetterB[2] = numletter + 1;
            } else if (numletter == 9){
                numLetterB[0] = numletter - 1;
                numLetterB[1] = numletter;
                numLetterB[2] = numletter;
            } else {
                numLetterB[0] = numletter - 1;
                numLetterB[1] = numletter;
                numLetterB[2] = numletter + 1;
            }
            // ------------------------ //
            if (numButton == 1){
                numButtonB[0] = numButton;
                numButtonB[1] = numButton;
                numButtonB[2] = numButton + 1;
            } else if (numButton == 10){
                numButtonB[0] = numButton - 1;
                numButtonB[1] = numButton;
                numButtonB[2] = numButton;
            } else {
                numButtonB[0] = numButton - 1;
                numButtonB[1] = numButton;
                numButtonB[2] = numButton + 1;
            }

            // имена квадратов которые проверяются что они не заняты другим кораблем
            String nameWestNorthButton = lettersButton[numLetterB[0]] + numButtonB[0];
            String nameNorthButton = lettersButton[numLetterB[1]] + numButtonB[0];
            String nameEastNorthButton = lettersButton[numLetterB[2]] + numButtonB[0];
            String nameWestButton = lettersButton[numLetterB[0]] + numButtonB[1];
            String nameCenterButton = lettersButton[numLetterB[1]] + numButtonB[1];
            String nameEastButton = lettersButton[numLetterB[2]] + numButtonB[1];
            String nameWestSouthButton = lettersButton[numLetterB[0]] + numButtonB[2];
            String nameSouthButton = lettersButton[numLetterB[1]] + numButtonB[2];
            String nameEastSouthButton = lettersButton[numLetterB[2]] + numButtonB[2];

            // результат проверки квадратов
            boolean rezWestNorthButton = false;
            boolean rezNorthButton = false;
            boolean rezEastNorthButton = false;
            boolean rezWestButton = false;
            boolean rezCenterButton = false;
            boolean rezEastButton = false;
            boolean rezWestSouthButton = false;
            boolean rezSouthButton = false;
            boolean rezEastSouthButton = false;

            // устанавливаем маркет в true если соседние клетки не заняты
            for (int i = 0; i < list.size(); i++) {
                JButton jButton = list.get(i);
                if (jButton.getName().equals(nameWestNorthButton)) rezWestNorthButton = true;
                if (jButton.getName().equals(nameNorthButton)) rezNorthButton = true;
                if (jButton.getName().equals(nameEastNorthButton)) rezEastNorthButton = true;
                if (jButton.getName().equals(nameWestButton)) rezWestButton = true;
                if (jButton.getName().equals(nameCenterButton)) rezCenterButton = true;
                if (jButton.getName().equals(nameEastButton)) rezEastButton = true;
                if (jButton.getName().equals(nameWestSouthButton)) rezWestSouthButton = true;
                if (jButton.getName().equals(nameSouthButton)) rezSouthButton = true;
                if (jButton.getName().equals(nameEastSouthButton)) rezEastSouthButton = true;
            }

            if (rezWestNorthButton && rezNorthButton && rezEastNorthButton &&
                    rezWestButton && rezCenterButton && rezEastButton &&
                    rezWestSouthButton && rezSouthButton && rezEastSouthButton){
                goodButton++;
            }
            if (direction == 0) numButton--;
            if (direction == 1) numletter++;
            if (direction == 2) numButton++;
            if (direction == 3) numletter--;
            checkDecks++;
        }

        return goodButton == numberOfShipDecks ? true : false;
    }
}
