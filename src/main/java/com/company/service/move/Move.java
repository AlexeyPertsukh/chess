package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.chess_exception.ChessException;
import com.company.model.command.Command;
import com.company.model.danger.Danger;
import com.company.model.piece.figure.Rank;
import com.company.model.piece.figure.Team;
import com.company.model.piece.figure.direction.Distance;
import com.company.model.piece.figure.direction.Offset;
import com.company.model.player.Player;
import com.company.model.piece.Piece;
import com.company.model.board.Way;

public class Move extends Turn {

    private static final String MARKER = "Move failed";

    public Move(Board board) {
        super(board);
    }

    @Override
    protected void action(Way way, Team team) {
        Piece piece = board.transfer(way);
        Danger dangerTest = new Danger(board, team);

        if (isCheck(dangerTest)) {
            board.undo();
            String message = String.format("%s: check to the king", MARKER);
            throw new ChessException(message);
        }

        piece.incMoveCount();
    }

    @Override
    protected Cell[] commandToCells(Player player, Command command) {
        String[] strings = command.getString().split("-");
        return new Cell[]{Board.toCell(strings[0]), Board.toCell(strings[1])};
    }

    @Override
    protected void specialVerify(Way way, Danger danger) {
        Piece pieceFrom = board.get(way.from);
        Piece pieceTo = board.get(way.to);

        if (!isCorrectDirection(board, way)) {
            String message = String.format("%s: illegal move %s", MARKER, wayToString(way));
            throw new ChessException(message);
        }

        if (!isWayWithoutObstacles(way)) {
            String message = String.format("%s: obstacles in the way of pieces", MARKER);
            throw new ChessException(message);
        }

        if (!pieceTo.isNull() && pieceFrom.getTeam() == pieceTo.getTeam()) {
            String message = String.format("%s: you can't attack your piece %s", MARKER, Board.toPosition(way.to));
            throw new ChessException(message);
        }

    }

    @Override
    protected String messageNoUnit(Cell cell) {
        return String.format("%s: cell %s has no piece", MARKER, Board.toPosition(cell));
    }

    @Override
    protected String messageAlienUnit(Cell cell) {
        return String.format("%s: %s another player's piece", MARKER, Board.toPosition(cell));
    }

    private boolean isCorrectDirection(Board board, Way way) {
        Cell from = way.from;
        Cell to = way.to;

        Piece piece = board.get(from);
        boolean attack = !board.get(to).isNull();
        Offset[] offsets = attack ? piece.getOffsetsAttack() : piece.getOffsetsMove();
        Distance distance = piece.getDistance();

        int row = to.row - from.row;
        int column = to.column - from.column;

        if (!attack && piece.isPawn() && from.column == to.column &&
                ((from.row == 1 && to.row == 3) || (from.row == Board.SIZE - 2 && to.row == Board.SIZE - 4))) {
            return true;
        }

        if (distance == Distance.UNLIM) {
            if (row == 0) {
                column = sign(column);
            } else if (column == 0) {
                row = sign(row);
            } else if (Math.abs(column) == Math.abs(row)) {
                column = sign(column);
                row = sign(row);
            }
        }

        Offset check = new Offset(column, row);

        for (Offset o : offsets) {
            if (check.equals(o)) {
                return true;
            }
        }

        return false;
    }

    protected boolean isWayWithoutObstacles(Way way) {
        Cell from = way.from;
        Cell to = way.to;

        Piece piece = board.get(from);

        if (piece.getDistance() == Distance.ONE) {
            Piece other = board.get(to);
            return other.isNull() || other.getTeam() != piece.getTeam();
        }

        int offsetRow = sign(to.row - from.row);
        int offsetColumn = sign(to.column - from.column);

        Cell cell = from;
        while (true) {
            cell = cell.sum(offsetColumn, offsetRow);
            if (cell.equals(to)) {
                break;
            }
            if (!board.get(cell).isNull()) {
                return false;
            }
        }

        return true;
    }

    private static String wayToString(Way way) {
        return Board.toPosition(way.from) + "-" + Board.toPosition(way.to);
    }

}
