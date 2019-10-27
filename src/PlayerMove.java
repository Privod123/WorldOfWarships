import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerMove {

    // Инициализация логера
    private static final Logger log = Logger.getLogger(PlayerMove.class);

    // Координаты корабля противника
    private List<String> ship4desc = new ArrayList<>();
    private List<String> ship31desc = new ArrayList<>();
    private List<String> ship32desc = new ArrayList<>();
    private List<String> ship21desc = new ArrayList<>();
    private List<String> ship22desc = new ArrayList<>();
    private List<String> ship23desc = new ArrayList<>();
    private List<String> ship11desc = new ArrayList<>();
    private List<String> ship12desc = new ArrayList<>();
    private List<String> ship13desc = new ArrayList<>();
    private List<String> ship14desc = new ArrayList<>();
    private List<String>[] ships = new List[10];

    private List<JButton> listGamer2AfterDiedShip; // хранит поле игрока 2 после того каак затопили корабль

    // Координаты корабля противника,которые используются при затопление корабля.
    // Подсказка игроку куда стрелять не надо
    private List<String> ship4descD = new ArrayList<>();
    private List<String> ship31descD = new ArrayList<>();
    private List<String> ship32descD = new ArrayList<>();
    private List<String> ship21descD = new ArrayList<>();
    private List<String> ship22descD = new ArrayList<>();
    private List<String> ship23descD = new ArrayList<>();
    private List<String> ship11descD = new ArrayList<>();
    private List<String> ship12descD = new ArrayList<>();
    private List<String> ship13descD = new ArrayList<>();
    private List<String> ship14descD = new ArrayList<>();


    public List<JButton> getListGamer2AfterDiedShip() {
        return listGamer2AfterDiedShip;
    }

    public PlayerMove(ArrayList<String> listCoordinat) {
        String nameButton;
        for (int i = 0; i < listCoordinat.size(); i++) {
            String coordinatSuccesHit = listCoordinat.get(i).trim();
            int numShip = Integer.parseInt(coordinatSuccesHit.substring(coordinatSuccesHit.length()- 2,
                                                                            coordinatSuccesHit.length() - 1));
            nameButton = coordinatSuccesHit.substring( 1, coordinatSuccesHit.length()-2);
            switch (numShip){
                case 0:
                    ship4desc.add(coordinatSuccesHit);
                    ship4descD.add(nameButton);
                    break;
                case 1:
                    ship31desc.add(coordinatSuccesHit);
                    ship31descD.add(nameButton);
                    break;
                case 2:
                    ship32desc.add(coordinatSuccesHit);
                    ship32descD.add(nameButton);
                    break;
                case 3:
                    ship21desc.add(coordinatSuccesHit);
                    ship21descD.add(nameButton);
                    break;
                case 4:
                    ship22desc.add(coordinatSuccesHit);
                    ship22descD.add(nameButton);
                    break;
                case 5:
                    ship23desc.add(coordinatSuccesHit);
                    ship23descD.add(nameButton);
                    break;
                case 6:
                    ship11desc.add(coordinatSuccesHit);
                    ship11descD.add(nameButton);
                    break;
                case 7:
                    ship12desc.add(coordinatSuccesHit);
                    ship12descD.add(nameButton);
                    break;
                case 8:
                    ship13desc.add(coordinatSuccesHit);
                    ship13descD.add(nameButton);
                    break;
                case 9:
                    ship14desc.add(coordinatSuccesHit);
                    ship14descD.add(nameButton);
                    break;
            }
        }
        ships[0]=ship4desc;
        ships[1]=ship31desc;
        ships[2]=ship32desc;
        ships[3]=ship21desc;
        ships[4]=ship22desc;
        ships[5]=ship23desc;
        ships[6]=ship11desc;
        ships[7]=ship12desc;
        ships[8]=ship13desc;
        ships[9]=ship14desc;

        }

    public String playerTurnResultat(String nameButtonPressPlayer1,List<JButton> listGamer2){

        String rezPlayerTurn = "";

        for (int i = 0; i < ships.length; i++) {
            if (ships[i].contains(nameButtonPressPlayer1)) {
                ships[i].remove(nameButtonPressPlayer1);
            }
        }
        if (ship4desc.isEmpty() ){
            rezPlayerTurn = "Игрок 1 затопил четырехпалубный корабль";
            listGamer2AfterDiedShip = DontShutAroundShip.shipDied(ship4descD,listGamer2);
            log.info(rezPlayerTurn);
            ship4desc.add("1");
        }
        if (ship31desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил трехпалубный корабль";
            listGamer2AfterDiedShip = DontShutAroundShip.shipDied(ship31descD,listGamer2);
            log.info(rezPlayerTurn);
            ship31desc.add("1");
        }
        if (ship32desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил трехпалубный корабль";
            listGamer2AfterDiedShip = DontShutAroundShip.shipDied(ship32descD,listGamer2);
            log.info(rezPlayerTurn);
            ship32desc.add("1");
        }
        if (ship21desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил двухпалубный корабль";
            listGamer2AfterDiedShip = DontShutAroundShip.shipDied(ship21descD,listGamer2);
            log.info(rezPlayerTurn);
            ship21desc.add("1");
        }
        if (ship22desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил двухпалубный корабль";
            listGamer2AfterDiedShip = DontShutAroundShip.shipDied(ship22descD,listGamer2);
            log.info(rezPlayerTurn);
            ship22desc.add("1");
        }
        if (ship23desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил двухпалубный корабль";
            listGamer2AfterDiedShip = DontShutAroundShip.shipDied(ship23descD,listGamer2);
            log.info(rezPlayerTurn);
            ship23desc.add("1");
        }
        if (ship11desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил однопалубный корабль";
            listGamer2AfterDiedShip = DontShutAroundShip.shipDied(ship11descD,listGamer2);
            log.info(rezPlayerTurn);
            ship11desc.add("1");
        }
        if (ship12desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил однопалубный корабль";
            listGamer2AfterDiedShip = DontShutAroundShip.shipDied(ship12descD,listGamer2);
            log.info(rezPlayerTurn);
            ship12desc.add("1");
        }
        if (ship13desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил однопалубный корабль";
            listGamer2AfterDiedShip = DontShutAroundShip.shipDied(ship13descD,listGamer2);
            log.info(rezPlayerTurn);
            ship13desc.add("1");
        }
        if (ship14desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил однопалубный корабль";
            listGamer2AfterDiedShip = DontShutAroundShip.shipDied(ship14descD,listGamer2);
            log.info(rezPlayerTurn);
            ship14desc.add("1");
        }
        return rezPlayerTurn;
    }
}
