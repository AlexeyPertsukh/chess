package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.command.Command;
import com.company.model.danger.Danger;
import com.company.model.figure.FigureColor;
import com.company.model.player.Player;
import com.company.model.unit.Unit;
import com.company.model.board.Way;

public class Castling extends Move {

    private static final String MARKER = "Рокировка не выполнена";

    public Castling(Board board) {
        super(board);
    }

    @Override
    protected void action(Way way) {
        Cell from = way.from;
        Cell to = way.to;

        Cell cellKing = board.get(from).isKing() ? from : to;
        Cell cellRock = board.get(from).isRock() ? from : to;

        int columnKing = cellKing.column > cellRock.column ? cellKing.column - 2 : cellKing.column + 2;
        int columnRock = cellKing.column > cellRock.column ? cellKing.column - 1 : cellKing.column + 1;

        Way kingWay = new Way(cellKing, cellKing.updateColumn(columnKing));
        Unit king = board.transfer(kingWay);

        Way rockWay = new Way(cellRock, cellRock.updateColumn(columnRock));
        Unit rock = board.transfer(rockWay);

        king.incMoveCount();
        rock.incMoveCount();
    }

    @Override
    protected Cell[] commandToCells(Player player, Command command) {
        String string = command.getString();
        int row = player.getColor() == FigureColor.WHITE ? 1 : 8;
        Cell e = Board.toCell("e" + row);

        if (command.isLeftCastling()) {
            Cell a = Board.toCell("a" + row);
            return new Cell[]{e, a};
        }

        if (command.isRightCastling()) {
            Cell h = Board.toCell("h" + row);
            return new Cell[]{e, h};
        }

        String message = String.format("this command is not castling: %s", string);
        throw new IllegalArgumentException(message);
    }

    @Override
    protected void specialVerify(Way way, Danger danger) {
        Cell from = way.from;
        Cell to = way.to;

        Unit[] units = new Unit[]{board.get(from), board.get(to)};

        for (Unit unit : units) {
            if (unit.isMoved()) {
                String message = String.format("%s: %s уже ходил", MARKER, unit.getRank());
                throw new IllegalArgumentException(message);
            }
        }

        Cell kingCell = board.get(from).isKing() ? from : to;
        if (danger.isUnderAttack(kingCell)) {
            String message = String.format("%s: шах королю", MARKER);
            throw new IllegalArgumentException(message);
        }

        int first = from.column;
        int last = to.column;
        if (from.column > to.column) {
            first = to.column;
            last = from.column;
        }

        for (int i = first + 1; i < last; i++) {
            Cell check = new Cell(i, from.row);
            if (!board.get(check).isNull()) {
                String message = String.format("%s: препятствия на пути фигур", MARKER);
                throw new IllegalArgumentException(message);
            }

            if (danger.isUnderAttack(check)) {
                String message = String.format("%s: поле под боем", MARKER);
                throw new IllegalArgumentException(message);
            }
        }
    }

    @Override
    protected String messageNoUnit(Cell cell) {
        return String.format("%s: на клетке %s нет фигуры", MARKER, Board.toPosition(cell));
    }

    @Override
    protected String messageAlienUnit(Cell cell) {
        return String.format("Рокировка не выполнена: фигура на %s принадлежит другому игроку", Board.toPosition(cell));
    }
}
