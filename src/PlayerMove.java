import org.apache.log4j.Logger;

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

       public PlayerMove(ArrayList<String> listCoordinat) {
        for (int i = 0; i < listCoordinat.size(); i++) {
            String coordinatSuccesHit = listCoordinat.get(i);
            int numShip = Integer.parseInt(coordinatSuccesHit.substring(coordinatSuccesHit.length()- 2,
                                                                            coordinatSuccesHit.length() - 1));
            switch (numShip){
                case 0:
                    ship4desc.add(coordinatSuccesHit);
                    break;
                case 1:
                    ship31desc.add(coordinatSuccesHit);
                    break;
                case 2:
                    ship32desc.add(coordinatSuccesHit);
                    break;
                case 3:
                    ship21desc.add(coordinatSuccesHit);
                    break;
                case 4:
                    ship22desc.add(coordinatSuccesHit);
                    break;
                case 5:
                    ship23desc.add(coordinatSuccesHit);
                    break;
                case 6:
                    ship11desc.add(coordinatSuccesHit);
                    break;
                case 7:
                    ship12desc.add(coordinatSuccesHit);
                    break;
                case 8:
                    ship13desc.add(coordinatSuccesHit);
                    break;
                case 9:
                    ship14desc.add(coordinatSuccesHit);
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

    public String playerTurnResultat(String nameButtonPressPlayer1){

        String rezPlayerTurn = "";

        for (int i = 0; i < ships.length; i++) {
            if (ships[i].contains(nameButtonPressPlayer1)) {
                ships[i].remove(nameButtonPressPlayer1);
            }
        }
        if (ship4desc.isEmpty() ){
            rezPlayerTurn = "Игрок 1 затопил четырехпалубный корабль";
            log.info(rezPlayerTurn);
            ship4desc.add("1");
        }
        if (ship31desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил трехпалубный корабль";
            log.info(rezPlayerTurn);
            ship31desc.add("1");
        }
        if (ship32desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил трехпалубный корабль";
            log.info(rezPlayerTurn);
            ship32desc.add("1");
        }
        if (ship21desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил двухпалубный корабль";
            log.info(rezPlayerTurn);
            ship21desc.add("1");
        }
        if (ship22desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил двухпалубный корабль";
            log.info(rezPlayerTurn);
            ship22desc.add("1");
        }
        if (ship23desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил двухпалубный корабль";
            log.info(rezPlayerTurn);
            ship23desc.add("1");
        }
        if (ship11desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил однопалубный корабль";
            log.info(rezPlayerTurn);
            ship11desc.add("1");
        }
        if (ship12desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил однопалубный корабль";
            log.info(rezPlayerTurn);
            ship12desc.add("1");
        }
        if (ship13desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил однопалубный корабль";
            log.info(rezPlayerTurn);
            ship13desc.add("1");
        }
        if (ship14desc.isEmpty()) {
            rezPlayerTurn = "Игрок 1 затопил однопалубный корабль";
            log.info(rezPlayerTurn);
            ship14desc.add("1");
        }
        return rezPlayerTurn;
    }
}
