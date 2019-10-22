import org.apache.log4j.Logger;

import java.util.List;

public class Winner {

    // Инициализация логера
    private static final Logger log = Logger.getLogger(Winner.class);
    private String messangeWhoWinner = "";

    public String getMessangeWhoWinner() {
        return messangeWhoWinner;
    }

    public int progress(List listCoordinatShipsPlayer1, List listCoordinatShipsPlayer2){
        int winner = 0;
        if (listCoordinatShipsPlayer1.isEmpty()){
            log.info(" ------------- Победил игрок № 2 -------------");
            messangeWhoWinner = "Победил игрок № 2";
            winner = 2;
        }
        if (listCoordinatShipsPlayer2.isEmpty()){
            log.info("------------- Победил игрок № 1 -------------");
            messangeWhoWinner = "Победил игрок № 1";
            winner = 1;
        }
        return winner;
    }
}
