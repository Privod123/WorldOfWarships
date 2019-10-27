import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class DontShutAroundShip {

    private static ImageIcon dontShut = new ImageIcon("src\\Image\\Крест.gif");

    // метод shipDied говорит компьютеру не стрелять в поля вокруг затопленого корабля
    public static List<JButton> shipDied(List<String> succesHitShip, List<JButton> listGamer){
        String[] lettersButton = {"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К"}; //  матрица с буквами полей
        // лист в котором будет искаться буква,куда произведен удачный выстрел.
        List<String> listWhereWillBeShoot = Arrays.asList(lettersButton);
        int j = 0;
        while (!succesHitShip.isEmpty()){
            // Определяем букву куда был сделан удачный выстрел
            String positionLetter = succesHitShip.get(j).substring(0,1);
            // определяем цифры куда был сделать удачный выстрел
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
            // и помечаем эти квадраты крестиком

            for (int i = 0; i < listGamer.size() ; i++) {
                if (nameWestNorthButton.equals(listGamer.get(i).getName()) && listGamer.get(i).isEnabled()) {
                    listGamer.get(i).setEnabled(false);
                    listGamer.get(i).setIcon(dontShut);
                }
                if (nameNorthButton.equals(listGamer.get(i).getName()) && listGamer.get(i).isEnabled()) {
                    listGamer.get(i).setEnabled(false);
                    listGamer.get(i).setIcon(dontShut);
                }
                if (nameEastNorthButton.equals(listGamer.get(i).getName()) && listGamer.get(i).isEnabled()) {
                    listGamer.get(i).setEnabled(false);
                    listGamer.get(i).setIcon(dontShut);
                }
                if (nameWestButton.equals(listGamer.get(i).getName()) && listGamer.get(i).isEnabled()) {
                    listGamer.get(i).setEnabled(false);
                    listGamer.get(i).setIcon(dontShut);
                }
                if (nameCenterButton.equals(listGamer.get(i).getName()) && listGamer.get(i).isEnabled()) {
                    listGamer.get(i).setEnabled(false);
                    listGamer.get(i).setIcon(dontShut);
                }
                if (nameEastButton.equals(listGamer.get(i).getName()) && listGamer.get(i).isEnabled()) {
                    listGamer.get(i).setEnabled(false);
                    listGamer.get(i).setIcon(dontShut);
                }
                if (nameWestSouthButton.equals(listGamer.get(i).getName()) && listGamer.get(i).isEnabled()) {
                    listGamer.get(i).setEnabled(false);
                    listGamer.get(i).setIcon(dontShut);
                }
                if (nameSouthButton.equals(listGamer.get(i).getName()) && listGamer.get(i).isEnabled()) {
                    listGamer.get(i).setEnabled(false);
                    listGamer.get(i).setIcon(dontShut);
                }
                if (nameEastSouthButton.equals(listGamer.get(i).getName()) && listGamer.get(i).isEnabled()) {
                    listGamer.get(i).setEnabled(false);
                    listGamer.get(i).setIcon(dontShut);
                }
            }
            succesHitShip.remove(succesHitShip.get(j));
        }
        return listGamer;
    }
}
