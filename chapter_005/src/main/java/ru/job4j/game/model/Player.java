package ru.job4j.game.model;

import ru.job4j.game.board.Board;
import ru.job4j.game.board.Cell;

public interface Player {
    Cell.CellValue cell();
    String name();
    void play(Board board);
}
