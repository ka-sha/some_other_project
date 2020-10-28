package finalRebyataFinal.game;

import java.awt.event.*;
import java.util.*;

public class GameActionListener implements ActionListener {
    private int row;
    private int cell;
    private GameButton button;

    public GameActionListener(int row, int cell, GameButton gButton) {
        this.row = row;
        this.cell = cell;
        this.button = gButton;  // Передаем номер ряда, номер столбца и ссылку на кнопку, к которой привязыаем наш GameActionListener
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();

        if (board.isTurnable(row, cell)) {
            updateByPlayersData(board);

            if (board.isFull()) {
                board.getGame().showMessage("Ничья");
                board.emptyField();
            } else {
                updateByAiData(board);
            }
        } else {
            board.getGame().showMessage("Некорректный ход");
        }

    }

    /**
     * Ход человека
     *
     * @param board GameBoard() - ссылка на игровое поле
     */
    private void updateByPlayersData(GameBoard board) {
        // Обновляем матрицу игры
        board.updateGameField(row, cell);
        // Обновляем содержимое кнопки
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
        // После хода проверим состояние победы
        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Вы выиграли!");
            board.emptyField();
        } else {
            board.getGame().passTurn();
        }
    }

    /**
     * Ход комппьютера
     *
     * @param board GameBoard() - ссылка на игровое поле
     */
    private void updateByAiData(GameBoard board) {
        // Генерация координат хода компьютера
        int x = -1, y = -1;
        boolean flag = false;

        for (int i = 0; i < GameBoard.dimension; i++) {
            for (int j = 0; j < GameBoard.dimension; j++) {
                if (GameBoard.gameField[i][j] == GameBoard.nullSymbol) {
                    // 1. Направление: влево вверх.
                    if(i - 1 >= 0 && j - 1 >= 0 && GameBoard.gameField[i - 1][j - 1] == board.getGame().getCurrentPlayer().getPlayerSign()){
                        y = i;
                        x = j;
                        flag = true;
                    }
                    // 2. Направление: вверх.
                    else if(i - 1 >= 0 && GameBoard.gameField[i - 1][j] == board.getGame().getCurrentPlayer().getPlayerSign()){
                        y = i;
                        x = j;
                        flag = true;
                    }
                    // 3. Направление: вправо вверх.
                    if(i - 1 >= 0 && j + 1 < GameBoard.dimension && GameBoard.gameField[i - 1][j + 1] == board.getGame().getCurrentPlayer().getPlayerSign()){
                        y = i;
                        x = j;
                        flag = true;
                    }
                    // 4. Направление: влево.
                    if(j - 1 >= 0 && GameBoard.gameField[i][j - 1] == board.getGame().getCurrentPlayer().getPlayerSign()){
                        y = i;
                        x = j;
                        flag = true;
                    }
                    // 5. Направление: вправо.
                    if(j + 1 < GameBoard.dimension && GameBoard.gameField[i][j + 1] == board.getGame().getCurrentPlayer().getPlayerSign()){
                        y = i;
                        x = j;
                        flag = true;
                    }
                    // 6. Направление: влево вниз.
                    if(i + 1 < GameBoard.dimension && j - 1 >= 0 && GameBoard.gameField[i + 1][j - 1] == board.getGame().getCurrentPlayer().getPlayerSign()){
                        y = i;
                        x = j;
                        flag = true;
                    }
                    // 7. Направление: вниз.
                    if(i + 1 < GameBoard.dimension && GameBoard.gameField[i + 1][j] == board.getGame().getCurrentPlayer().getPlayerSign()){
                        y = i;
                        x = j;
                        flag = true;
                    }
                    // 8. Направление: вправо вниз.
                    if(i + 1 < GameBoard.dimension && j + 1 < GameBoard.dimension && GameBoard.gameField[i + 1][j + 1] == board.getGame().getCurrentPlayer().getPlayerSign()){
                        y = i;
                        x = j;
                        flag = true;
                    }
                }
                // Выходим из внутреннего цикла, если найдена рядом нужная ячейка.
                if(flag){
                    break;
                }
            }
            // Выходим из внешнего цикла, если найдена рядом нужная ячейка.
            if(flag){
                break;
            }
        }
        if(x == -1){
            do {
                Random rnd = new Random();
                x = rnd.nextInt(GameBoard.dimension);
                y = rnd.nextInt(GameBoard.dimension);
            } while (!board.isTurnable(x, y));
        }

        // Обновим матрицу игры
        board.updateGameField(x, y);

        // Обновим содержимое кнопки
        int cellIndex = GameBoard.dimension * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        // Проверить победу
        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Компьютер выиграл!");
            board.emptyField();
        } else {
            board.getGame().passTurn();
        }

    }
}
