package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.unit.Unit;

public class Casting extends MoveType {
    public Casting(Board board) {
        super(board);
    }

    @Override
    public void verify(Cell from, Cell to, boolean[][] dangerArray) {
        Unit[] units = new Unit[]{board.get(from), board.get(to)};

        for (Unit unit : units) {
            if(unit.isMoved()) {
                String message = String.format("Рокировка не выполнена: %s уже ходил",unit.getRank());
                throw new IllegalArgumentException(message);
            }
        }

        int first = from.column;
        int last = to.column;
        if(from.column > to.column) {
            first = to.column;
            last = from.column;
        }

        for (int i = first + 1; i < last; i++) {
            Cell check = new Cell(i, from.row);
            if(!board.get(check).isNull()) {
                String message = "Рокировка не выполнена: между ладьей и королем есть фигуры";
                throw new IllegalArgumentException(message);
            }
        }
    }

    @Override
    public void execute(Cell from, Cell to) {
        Cell cellKing = board.get(from).isKing() ? from : to;
        Cell cellRock = board.get(from).isRock() ? from : to;
        Unit king = board.remove(cellKing);
        Unit rock = board.remove(cellRock);

        int columnKing = cellKing.column > cellRock.column ?  cellKing.column - 2 : cellKing.column + 2;
        int columnRock = cellKing.column > cellRock.column ?  cellKing.column - 1 : cellKing.column + 1;

        board.insert(king, new Cell(columnKing, cellKing.row));
        board.insert(rock, new Cell(columnRock, cellRock.row));

        king.incMoveCount();
        rock.incMoveCount();
    }

}
