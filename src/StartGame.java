import org.apache.log4j.Logger;

public class StartGame {

    // Инициализация логера
    private static final Logger log = Logger.getLogger(StartGame.class);

    public static void main(String[] args) {
        RunningOrder newGame = new RunningOrder();
        newGame.start();
    }
}
