import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainScreen {

    // Инициализация логера
    private static final Logger log = Logger.getLogger(MainScreen.class);

    private List<JButton> listButtonGamer1 = new ArrayList<>(); // Список кнопок игрового поля игрока 1
    private List<JButton> listButtonGamer2 = new ArrayList<>(); // Список кнопок игрового поля игрока 2
    private ArrayList<String> listCoordinatShipsPlayer1 ;
    private ArrayList<String> listCoordinatShipsPlayer2 ;
    private JTextArea jTextArea;
    private boolean finishHit = false; // переменная отвечает за проверку был ли совершен выстрел
    private boolean succesHitPlayer1 = false; //флаг удачного выстрела Игрока 1
    private String fullNameButtonSuccesHit;

    public boolean isFinishHit() { return finishHit; }

    public void setFinishHit(boolean finishHit) {
        this.finishHit = finishHit;
    }

    public List<JButton> getListButtonGamer1() { return listButtonGamer1; }

    public void setListButtonGamer1(List<JButton> listButtonGamer1) { this.listButtonGamer1 = listButtonGamer1; }

    public ArrayList<String> getListCoordinatShipsPlayer1() {
        return listCoordinatShipsPlayer1;
    }

    public ArrayList<String> getListCoordinatShipsPlayer2() {
        return listCoordinatShipsPlayer2;
    }

    public void appendjTextArea(String text) { jTextArea.append(text + "\n"); }

    public void setCaretPosition(){ jTextArea.setCaretPosition(jTextArea.getText().length()); }

    public String getFullNameButtonSuccesHit() { return fullNameButtonSuccesHit; }

    public boolean isSuccesHitPlayer1() { return succesHitPlayer1; }

    public void setSuccesHitPlayer1(boolean succesHitPlayer1) { this.succesHitPlayer1 = succesHitPlayer1; }

     public MainScreen() {
        log.info("Создали экран");
        /* Главный экран */
        JFrame jMainScreen = new JFrame("Морской бой");
        jMainScreen.setLayout(new GridLayout(1,3));
        jMainScreen.setSize(900,300);
        jMainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Поле растановки кораблей игрока 1 */
        JPanel jPanelGamer1 = new JPanel();
        jPanelGamer1.setPreferredSize(new Dimension(250,250));
        jPanelGamer1.setBorder(BorderFactory.createLineBorder(Color.black, 3)); // рамка
        jPanelGamer1.setLayout(new FlowLayout());
        createdField(jPanelGamer1, 1);
        jPanelGamer1.setOpaque(true);

        /* Поле растановки кораблей игрока 2 */
        JPanel jPanelGamer2 = new JPanel();
        jPanelGamer2.setPreferredSize(new Dimension(250,250));
        jPanelGamer2.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        jPanelGamer2.setLayout(new FlowLayout());
        createdField(jPanelGamer2, 2);
        jPanelGamer2.setOpaque(true);

        /* Поле игровой активности*/
        JPanel jPanelChat = new JPanel();
        jPanelChat.setPreferredSize(new Dimension(230,250));
        jPanelChat.setBorder(BorderFactory.createLineBorder(Color.black, 3)); // рамка
        jPanelChat.setLayout(new FlowLayout());
        jTextArea = new JTextArea();
        jTextArea.setEditable(false); // запрещает редактирование поля jTextArea(только ввод текста)
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setPreferredSize(new Dimension(280,230));
        jPanelChat.add(jScrollPane);

        jMainScreen.add(jPanelGamer1);
        jMainScreen.add(jPanelGamer2);
        jMainScreen.add(jPanelChat);

        jMainScreen.setVisible(true);


    }

    /* Создание поля, где будут раставлены корабли и растановка кораблей */

    public void createdField(JPanel jPanel, int numGamer){
        /* Заполняем буквы сверху поля боя */
        JPanel jPanelTopLetterrs = new JPanel();
        jPanelTopLetterrs.setLayout(new GridLayout(1,11));
        String[] letters = {" ","А","Б","В","Г","Д","Е","Ж","З","И","К"};
        jPanelTopLetterrs.setPreferredSize(new Dimension(220,10));
        for (int i = 0; i < letters.length; i++) {
            JLabel jLabel = new JLabel(letters[i]);
            jLabel.setHorizontalAlignment(JLabel.CENTER); // выравнивание надписи по центру по горизонтали
            jPanelTopLetterrs.add(jLabel);
        }
        /* Общая панель для цифр и поля размещения квадратов */
        JPanel jPanelCommonNumberAndSqure = new JPanel();
        jPanelCommonNumberAndSqure.setLayout(new FlowLayout());
        /* Заполняем цифры слева от поля боя */
        JPanel jPanelNumber = new JPanel();
        jPanelNumber.setPreferredSize(new Dimension(15,200));
        jPanelNumber.setLayout(new GridLayout(10,1));
        for (int i = 1; i < 11; i++) {
            JLabel jLabel = new JLabel(String.valueOf(i));
            jLabel.setHorizontalAlignment(JLabel.CENTER);
            jPanelNumber.add(jLabel);
        }
        /* Заполняем квадратами поле боя */
        JPanel jPanelSqure = new JPanel();
        jPanelSqure.setPreferredSize(new Dimension(200,200));
        jPanelSqure.setLayout(new GridLayout(10,10));
        String[] lettersButton = {"А","Б","В","Г","Д","Е","Ж","З","И","К"};
        for (int i = 1; i < 11 ; i++) {
            for (int j = 0; j < lettersButton.length; j++) {
                String nameButton = lettersButton[j] + i;
                JButton jButton = new JButton();
                jButton.setName(nameButton);
                jButton.setEnabled(true);
                // это слушатель, который выполняет следующие действия по нажатию кнопки
                jButton.addActionListener((e) -> {
                    // Получаем полное имя клетки с успешныи выстрелом
                    fullNameButtonSuccesHit = jButton.getName();
                    // Метод if определяет стоит ли в данной клетке корабль
                    if (fullNameButtonSuccesHit.length() > 3) {
                        jButton.setBackground(Color.RED);
                        finishHit = true;
                        log.info("Выстрел в квадрат : " + jButton.getName()+ ". Ранил");
                        jButton.setEnabled(false);
                        jButton.setOpaque(true);
                        // получаем только координаты успешного выстрела
                        String succesButtonHit = fullNameButtonSuccesHit.substring(1,fullNameButtonSuccesHit.length()-2);
                        if ( numGamer == 1) {
                            jTextArea.append("Игрок 2 выстрелил в квадрат - " + succesButtonHit + ". Ранил" + "\n");
                            listCoordinatShipsPlayer1.remove(fullNameButtonSuccesHit);
                        }
                        if ( numGamer == 2) {
                            succesHitPlayer1 = true;
                            jTextArea.append("Игрок 1 выстрелил в квадрат - " + succesButtonHit + ". Ранил" + "\n");
                            listCoordinatShipsPlayer2.remove(fullNameButtonSuccesHit);
                        }
                    }else {
                        jButton.setBackground(Color.BLUE);
                        finishHit = true;
                        log.info("Выстрел в квадрат : " + jButton.getName() + ". Мимо");
                        jButton.setEnabled(false);
                        jButton.setOpaque(true);
                        if ( numGamer == 1) {
                            jTextArea.append("Игрок 2 выстрелил в квадрат - " + jButton.getName() + ". Мимо" + "\n");
                        }
                        if ( numGamer == 2) {
                            jTextArea.append("Игрок 1 выстрелил в квадрат - " + jButton.getName() + ". Мимо" + "\n");
                        }
                    }
                });
                if ( numGamer == 1) listButtonGamer1.add(jButton);
                if ( numGamer == 2) listButtonGamer2.add(jButton);
            }
        }

        /* Раставляем корабли на поле игрока 1*/
        if (numGamer == 1) {
            log.info("------------- Строим корабли игрока 1 -------------");
            SetWarships setWarships = new SetWarships();
            listButtonGamer1 = setWarships.setShips(listButtonGamer1, 1);
            listCoordinatShipsPlayer1 = setWarships.getListCoordinatShipsPlayer();
            for (int i = 0; i < listButtonGamer1.size(); i++) {
                JButton jButton = listButtonGamer1.get(i);
                jPanelSqure.add(jButton);
            }
            log.info("------------- Корабли Игрока 1 раставлены -------------");
        }
        /* Раставляем корабли на поле игрока 2*/
        if (numGamer == 2) {
            log.info("------------- Строим корабли игрока 2 -------------");
            SetWarships setWarships = new SetWarships();
            listButtonGamer2 = setWarships.setShips(listButtonGamer2, 2);
            listCoordinatShipsPlayer2 = setWarships.getListCoordinatShipsPlayer();
            for (int i = 0; i < listButtonGamer2.size(); i++) {
                JButton jButton = listButtonGamer2.get(i);
                jPanelSqure.add(jButton);
            }
            log.info("------------- Корабли Игрока 2 раставлены -------------");
        }

        jPanelCommonNumberAndSqure.add(jPanelNumber);
        jPanelCommonNumberAndSqure.add(jPanelSqure);
        jPanel.add(jPanelTopLetterrs);
        jPanel.add(jPanelCommonNumberAndSqure);
    }

}
