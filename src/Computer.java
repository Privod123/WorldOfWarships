import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Computer {

    // Инициализация логера
    private static final Logger log = Logger.getLogger(Computer.class);

    private String[] lettersButton = {"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К"}; //  матрица с буквами полей
    private List<String> listWhereWillBeShoot; // лист в котором будет искаться буква,куда произведен удачный выстрел.
    private int countShipDied = 0; // кол-во кораблей удачно потопленных компьютером
    private List<String> listHit = new ArrayList<>();
    private int countSuccesHit = 1; // кол-во удачных попаданий. Нужна для проверке убит ли корабль
    private JButton jButtonRandom;
    private List<String> succesHit = new ArrayList<>(); // лист куда записываем удачные попадания
    private String messangeFromComputer = "";

    public Computer() {
        this.listWhereWillBeShoot = Arrays.asList(lettersButton);
    }

    public String getMessangeFromComputer() {
        return messangeFromComputer;
    }

    public List<JButton> pressButon (List<JButton> listButtonGamer1, MainScreen mainScreen){
        List<JButton> listGamer1 = listButtonGamer1;
        messangeFromComputer = "";
        while (countShipDied != 10){
            // если в предыдущем ходе ранили корабль,то listHit(списка возможных мест удара) не пустой
            // и компьютер будет стрелять по координатам из этого листа
            if (!listHit.isEmpty()){
                // определяем координату куда будет произведен выстрел (случайным образом) для добивания корабля
                int numHit= (int) (Math.random() * listHit.size());
                // забираем клетку куда будет произведен выстрел из игрвого поля
                String nameListButton;
                for (int i = 0; i < listButtonGamer1.size() ; i++) {
                    if (listButtonGamer1.get(i).getName().length() > 3){
                        nameListButton = listButtonGamer1.get(i).getName().trim().substring(1,
                                                                listButtonGamer1.get(i).getName().length()-2);
                    }
                    else {
                        nameListButton = listButtonGamer1.get(i).getName();
                    }
                    if (listHit.get(numHit).equals(nameListButton)){
                        jButtonRandom = listButtonGamer1.get(i) ;
                    }
                }
                // после того как выбрана клетка координаты из listHit(списка возможных мест удара) удаляется
                listHit.remove(numHit);
            }else {
                // Случайный квадрат куда произведем выстрел (если не был ранен ни один корабль)
                int positionButtonInList= (int) (Math.random() * listButtonGamer1.size());
                jButtonRandom = listButtonGamer1.get(positionButtonInList);
            }
            // проверка что кнопка не была ранее уже нажата
            if (jButtonRandom.isEnabled()){
                jButtonRandom.doClick();
                String nameButton = jButtonRandom.getName().trim();
                String[] namePressedButton = nameButton.split("");
                if (namePressedButton.length > 3){
                    // countShip - переменная которая хранит номер корабля.
                    int countShip = Integer.parseInt(namePressedButton[namePressedButton.length - 2]);
                    // Для кораблей от 6 до 9 (это однопалубные) после попадания в них компьютер делает еще выстрел
                    if ( countShip == 9 || countShip == 8 || countShip == 7 || countShip == 6) {
                        log.info("Компьютер попал в корабль номер - " + countShip + ". Корабль затоплен!");
                        mainScreen.appendjTextArea("Игрок 2 затопил однопалубный корабль");
                        // Получаем координаты квадрата с удачнум выстрелом (нужно для алгаритмов компьютера)
                        String newNameButton = jButtonRandom.getName().trim().substring(1,jButtonRandom.getName().length()-2);
                        // Заносим координаты удачного выстрела в лист
                        succesHit.add(newNameButton);
                        listGamer1 = shipDied(succesHit,listButtonGamer1);
                        // очищаем лист удачных выстрелов,так как корабль потоплен
                        succesHit.clear();
                        countShipDied++;
                        log.info("Ход игрока 2 (Компьютер)");
                        mainScreen.appendjTextArea(" ----- Ход игрока 2 -----");
                        mainScreen.setCaretPosition();
                        // время в течение которого компьютер думает куда стрелять
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    // Для кораблей от 3 до 5 (это двухпалубные) после попадания в них компьютер делает еще выстрел
                    if (countShip == 3 || countShip == 4 || countShip == 5){
                        log.info("Компьютер попал в корабль номер - " + countShip + " (двухпалубный).");
                        // Получаем координаты квадрата с удачным выстрелом (нужно для алгаритмов компьютера)
                        String newNameButton = jButtonRandom.getName().trim().substring(1,jButtonRandom.getName().length()-2);
                        // if  проверяет затоплен корабль или ранен
                        if (countSuccesHit == 2){
                            succesHit.add(newNameButton);
                            listHit.clear(); // чистим listHit(списка возможных мест удара) так как корабль потоплен
                            countSuccesHit = 1; // ставим в начальное положение
                            log.info("Компьютер попал в корабль номер - " + countShip + " (двухпалубный). Корабль затоплен!");
                            mainScreen.appendjTextArea("Игрок 2 затопил двухпалубный корабль");
                            // Вызываем метод ктороый скажем компьютеру не стрелять вокруг корабля
                            listGamer1 = shipDied(succesHit,listButtonGamer1);
                            succesHit.clear(); // чистим список удачных попаданий, так как корабль затоплен
                            countShipDied++;
                        }else {
                            caseWillBeHit(newNameButton);
                            succesHit.add(newNameButton);
                            countSuccesHit++;
                        }
                        log.info("Ход игрока 2 (Компьютер)");
                        mainScreen.appendjTextArea(" ----- Ход игрока 2 -----");
                        mainScreen.setCaretPosition();
                        // время в течение которого компьютер думает куда стрелять
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    // Для кораблей от 1 до 2 (это трехпалубные) после попадания в них компьютер делает еще выстрел
                    if (countShip == 1 || countShip == 2){
                        log.info("Компьютер попал в корабль номер - " + countShip + " (трехпалубный).");
                        // Получаем координаты квадрата с удачным выстрелом (нужно для алгаритмов компьютера)
                        String newNameButton = jButtonRandom.getName().trim().substring(1,jButtonRandom.getName().length()-2);
                        if (countSuccesHit == 3){
                            succesHit.add(newNameButton);
                            listHit.clear(); // чистим listHit(списка возможных мест удара) так как корабль потоплен
                            countSuccesHit = 1; // ставим в начальное положение
                            log.info("Компьютер попал в корабль номер - " + countShip + " (трехпалубный). Корабль затоплен!");
                            mainScreen.appendjTextArea("Игрок 2 затопил трехпалубный корабль");
                            // Вызываем метод ктороый скажем компьютеру не стрелять вокруг корабля
                            listGamer1 = shipDied(succesHit,listButtonGamer1);
                            succesHit.clear(); // чистим список удачных попаданий, так как корабль затоплен
                            countShipDied++;
                        }else {
                            caseWillBeHit(newNameButton);
                            succesHit.add(newNameButton);
                            countSuccesHit++;
                        }
                        log.info("Ход игрока 2 (Компьютер)");
                        mainScreen.appendjTextArea(" ----- Ход игрока 2 -----");
                        mainScreen.setCaretPosition();
                        // время в течение которого компьютер думает куда стрелять
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    // Для корабля 0 (это четырехпалубные) после попадания в него компьютер делает еще выстрел
                    if (countShip == 0){
                        log.info("Компьютер попал в корабль номер - 10 (четырехпалубный).");
                        // Получаем координаты квадрата с удачным выстрелом (нужно для алгаритмов компьютера)
                        String newNameButton = jButtonRandom.getName().trim().substring(1,jButtonRandom.getName().length()-2);
                        if (countSuccesHit == 4){
                            succesHit.add(newNameButton);
                            listHit.clear(); // чистим listHit(списка возможных мест удара) так как корабль потоплен
                            countSuccesHit = 1; // ставим в начальное положение
                            log.info("Компьютер попал в корабль номер - " + countShip + " (четырехпалубный). Корабль затоплен!");
                            mainScreen.appendjTextArea("Игрок 2 затопил четырехпалубный корабль");
                            // Вызываем метод ктороый скажем компьютеру не стрелять вокруг корабля
                            listGamer1 = shipDied(succesHit,listButtonGamer1);
                            succesHit.clear(); // чистим список удачных попаданий, так как корабль затоплен
                            countShipDied++;
                        }else {
                            caseWillBeHit(newNameButton);
                            succesHit.add(newNameButton);
                            countSuccesHit++;
                        }
                        log.info("Ход игрока 2 (Компьютер)");
                        mainScreen.appendjTextArea(" ----- Ход игрока 2 -----");
                        mainScreen.setCaretPosition();
                        // время в течение которого компьютер думает куда стрелять
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                }
                break;
            } else {
                continue;
            }
        }
        return listGamer1;
    }

    // метод shipDied говорит компьютеру не стрелять в поля вокруг затопленого корабля
    private List<JButton> shipDied(List<String> succesHitShip, List<JButton> listGamer1){
        int j = 0;
        while (!succesHitShip.isEmpty()){
            // Определяем букву куда был сделан удачный выстрел
            String positionLetter = succesHitShip.get(j).substring(0,1);
            // определяем цифры куда был сделат удачный выстрел
            int numberLetterPosition = Integer.parseInt(succesHitShip.get(j).substring(1));

            int numLetterShipDied = listWhereWillBeShoot.indexOf(positionLetter);

            // создаем матрицы 3х3 для перебора квадратов которые надо исключить из потанциальных для стрельбы
            int[] numLetterB = new int[3];
            int[] numButtonB = new int[3];
            // ------------------------ //
            // расматриваем 3 варианта где стоял корабль по Букве
            if (numLetterShipDied == 0){
                numLetterB[0] = numLetterShipDied;
                numLetterB[1] = numLetterShipDied;
                numLetterB[2] = numLetterShipDied + 1;
            } else if (numLetterShipDied == 9){
                numLetterB[0] = numLetterShipDied - 1;
                numLetterB[1] = numLetterShipDied;
                numLetterB[2] = numLetterShipDied;
            } else {
                numLetterB[0] = numLetterShipDied - 1;
                numLetterB[1] = numLetterShipDied;
                numLetterB[2] = numLetterShipDied + 1;
            }
            // ------------------------ //
            // расматриваем 3 варианта где стоял корабль по Цифре
            if (numberLetterPosition == 1){
                numButtonB[0] = numberLetterPosition;
                numButtonB[1] = numberLetterPosition;
                numButtonB[2] = numberLetterPosition + 1;
            } else if (numberLetterPosition == 10){
                numButtonB[0] = numberLetterPosition - 1;
                numButtonB[1] = numberLetterPosition;
                numButtonB[2] = numberLetterPosition;
            } else {
                numButtonB[0] = numberLetterPosition - 1;
                numButtonB[1] = numberLetterPosition;
                numButtonB[2] = numberLetterPosition + 1;
            }

            // имена квадратов которые компьютер больше стрелять не будет.
            String nameWestNorthButton = lettersButton[numLetterB[0]] + numButtonB[0];
            String nameNorthButton = lettersButton[numLetterB[1]] + numButtonB[0];
            String nameEastNorthButton = lettersButton[numLetterB[2]] + numButtonB[0];
            String nameWestButton = lettersButton[numLetterB[0]] + numButtonB[1];
            String nameCenterButton = lettersButton[numLetterB[1]] + numButtonB[1];
            String nameEastButton = lettersButton[numLetterB[2]] + numButtonB[1];
            String nameWestSouthButton = lettersButton[numLetterB[0]] + numButtonB[2];
            String nameSouthButton = lettersButton[numLetterB[1]] + numButtonB[2];
            String nameEastSouthButton = lettersButton[numLetterB[2]] + numButtonB[2];

            // перебираем все квадраты игрового поля и все совпадающие по названия поля исключает из зоны обстрела компьютера

            for (int i = 0; i < listGamer1.size() ; i++) {
                if (nameWestNorthButton.equals(listGamer1.get(i).getName()) && listGamer1.get(i).isEnabled())
                    listGamer1.get(i).setEnabled(false);
                if (nameNorthButton.equals(listGamer1.get(i).getName()) && listGamer1.get(i).isEnabled())
                    listGamer1.get(i).setEnabled(false);
                if (nameEastNorthButton.equals(listGamer1.get(i).getName()) && listGamer1.get(i).isEnabled())
                    listGamer1.get(i).setEnabled(false);
                if (nameWestButton.equals(listGamer1.get(i).getName()) && listGamer1.get(i).isEnabled())
                    listGamer1.get(i).setEnabled(false);
                if (nameCenterButton.equals(listGamer1.get(i).getName()) && listGamer1.get(i).isEnabled())
                    listGamer1.get(i).setEnabled(false);
                if (nameEastButton.equals(listGamer1.get(i).getName()) && listGamer1.get(i).isEnabled())
                    listGamer1.get(i).setEnabled(false);
                if (nameWestSouthButton.equals(listGamer1.get(i).getName()) && listGamer1.get(i).isEnabled())
                    listGamer1.get(i).setEnabled(false);
                if (nameSouthButton.equals(listGamer1.get(i).getName()) && listGamer1.get(i).isEnabled())
                    listGamer1.get(i).setEnabled(false);
                if (nameEastSouthButton.equals(listGamer1.get(i).getName()) && listGamer1.get(i).isEnabled())
                    listGamer1.get(i).setEnabled(false);
            }
            succesHitShip.remove(succesHitShip.get(j));
        }
        return listGamer1;
    }

    // метод caseWillBeHit добавляет в listHit(список возможных мест удара) координаты новых клеток
    private void caseWillBeHit (String succesButtonHit){

        // Определяем букву куда был сделан удачный выстрел
        String positionLetter = succesButtonHit.substring(0,1);
        // определяем цифры куда был сделат удачный выстрел
        int numberLetterPosition = Integer.parseInt(succesButtonHit.substring(1));

        int numLetterShipDied = listWhereWillBeShoot.indexOf(positionLetter);

        // создаем матрицы 3х3 для перебора квадратов которые надо стрелять (угловые квадраты не будем использовать)
        int[] numLetterB = new int[3];
        int[] numButtonB = new int[3];
        // ------------------------ //
        // расматриваем 3 варианта где стоял корабль по Букве
        if (numLetterShipDied == 0){
            numLetterB[0] = numLetterShipDied;
            numLetterB[1] = numLetterShipDied;
            numLetterB[2] = numLetterShipDied + 1;
        } else if (numLetterShipDied == 9){
            numLetterB[0] = numLetterShipDied - 1;
            numLetterB[1] = numLetterShipDied;
            numLetterB[2] = numLetterShipDied;
        } else {
            numLetterB[0] = numLetterShipDied - 1;
            numLetterB[1] = numLetterShipDied;
            numLetterB[2] = numLetterShipDied + 1;
        }
        // ------------------------ //
        // расматриваем 3 варианта где стоял корабль по Цифре
        if (numberLetterPosition == 1){
            numButtonB[0] = numberLetterPosition;
            numButtonB[1] = numberLetterPosition;
            numButtonB[2] = numberLetterPosition + 1;
        } else if (numberLetterPosition == 10){
            numButtonB[0] = numberLetterPosition - 1;
            numButtonB[1] = numberLetterPosition;
            numButtonB[2] = numberLetterPosition;
        } else {
            numButtonB[0] = numberLetterPosition - 1;
            numButtonB[1] = numberLetterPosition;
            numButtonB[2] = numberLetterPosition + 1;
        }

        // имена квадратов в которые компьютер будет стрелять.
        String nameNorthButton = lettersButton[numLetterB[1]] + numButtonB[0];
        String nameWestButton = lettersButton[numLetterB[0]] + numButtonB[1];
        String nameEastButton = lettersButton[numLetterB[2]] + numButtonB[1];
        String nameSouthButton = lettersButton[numLetterB[1]] + numButtonB[2];

        listHit.add(nameNorthButton);
        listHit.add(nameWestButton);
        listHit.add(nameEastButton);
        listHit.add(nameSouthButton);

    }

}
