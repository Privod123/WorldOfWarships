import org.apache.log4j.Logger;

public class RunningOrder {

    // Инициализация логера
    private static final Logger log = Logger.getLogger(RunningOrder.class);

    public void start(){

        log.info("Игра запущена");
        MainScreen mainScreen = new MainScreen();
        Winner whoWinner = new Winner();
        Computer computer = new Computer();
        PlayerMove playerMove = new PlayerMove(mainScreen.getListCoordinatShipsPlayer2());

        int winner = 0;
        int nameGamer = 1;
        log.info("-------------Начало боя-------------");
        while (winner == 0){
            mainScreen.setCaretPosition(); // данный метод заставляет полосу прокрутки двигаться вниз
            if (nameGamer == 1){
                mainScreen.appendjTextArea(" ----- Ход игрока 1 -----");
                log.info("Ход игрока 1");
                mainScreen.setFinishHit(false);
                mainScreen.setCaretPosition();
                // цикл while ждет когда игрок совершит выстрел
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // if проверяет наличие попадания
                    if ( mainScreen.isSuccesHitPlayer1()){
                        String rezultatHit = playerMove.playerTurnResultat(mainScreen.getFullNameButtonSuccesHit(),
                                                                                    mainScreen.getListButtonGamer2());
                        // if проверяет был затоплен корабль или нет
                        if (!rezultatHit.equals("")) {
                            mainScreen.appendjTextArea(rezultatHit);
                            mainScreen.setListButtonGamer2(playerMove.getListGamer2AfterDiedShip());
                        }
                        if (mainScreen.getListCoordinatShipsPlayer2().isEmpty()) { break; }
                        mainScreen.appendjTextArea(" ----- Ход игрока 1 -----");
                        log.info("Ход игрока 1");
                        mainScreen.setSuccesHitPlayer1(false); // обнуляем удачный выстрел Игрока 1 так как учли его
                        mainScreen.setFinishHit(false); // обнуляем что был сделан выстрел
                        mainScreen.setCaretPosition();
                        continue;
                    }
                    if (mainScreen.isFinishHit()) { break; }
                }
                winner = whoWinner.progress(mainScreen.getListCoordinatShipsPlayer1(),mainScreen.getListCoordinatShipsPlayer2());
                // if посылает сообщение о победители
                if (winner == 1) {
                    mainScreen.appendjTextArea(whoWinner.getMessangeWhoWinner());
                    mainScreen.setCaretPosition();
                } else {
                    nameGamer = 2;
                }
            }
            if (nameGamer == 2){
                log.info("Ход игрока 2 (Компьютер)");
                mainScreen.appendjTextArea(" ----- Ход игрока 2 -----");
                mainScreen.setFinishHit(false);
                mainScreen.setCaretPosition();
                // цикл while ждет когда игрок(компьютер) совершит выстрел
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // метод pressButon из класса Computer делает выстрел и по результату выстрела возврашает
                    // лист игрового поля игрока 1 с учетом того попал или нет.
                    mainScreen.setListButtonGamer1(computer.pressButon(mainScreen.getListButtonGamer1(), mainScreen));// ход компьютера
                    // if оканчивает ход компьютера,так как он произвел выстрел.
                    if (mainScreen.isFinishHit()) break;
                }
                // Метод progress класса Winner определяет кто выиграл или что никто еще не победил
                winner = whoWinner.progress(mainScreen.getListCoordinatShipsPlayer1(),mainScreen.getListCoordinatShipsPlayer2());
                // if посылает сообщение о победители
                if (winner == 2) {
                    mainScreen.appendjTextArea(whoWinner.getMessangeWhoWinner());
                    mainScreen.setCaretPosition();
                } else {
                    nameGamer = 1;
                }
            }
        }
        log.info("-------------Конец боя-------------");
    }
}
