package ru.job4j.game.logic;

import ru.job4j.game.board.Board;
import ru.job4j.game.board.Cell;

import java.util.stream.IntStream;

public class Logic {

    private final Board board;

    public Logic(Board board) {
        this.board = board;
    }

    public boolean isWin() {
        return IntStream
                .range(0, board.size())
                .anyMatch(i -> isWinRow(i) || isWinCol(i)) || isDiagonal();
    }

    public boolean isWinRow(int row) {
        boolean result = true;
        for (int col = 1; col < board.size(); col++) {
            if (board.cellValue(row, col) != board.cellValue(row, 0)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinCol(int col) {
        boolean result = true;
        for (int row = 1; row < board.size(); row++) {
            if (board.cellValue(row, col) != board.cellValue(0, col)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isDiagonal() {
        boolean left = true;
        boolean right = true;
        for (int i = 1; i < board.size(); i++) {
            left = board.cellValue(0, 0) == board.cellValue(i, i);
            right = board.cellValue(0, board.size() - 1) == board.cellValue(i, board.size() - 1 - i);
        }
        return left || right;
    }

    public boolean hasGap() {
        boolean result = false;
        for (int row = 0; row < board.size(); row++) {
            for (int col = 0; col < board.size(); col++) {
                if (board.cellValue(row, col) == Cell.CellValue.EMPTY) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

}
