package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.board.BoardHelper;
import com.company.model.board.Cell;
import com.company.model.chess_exception.ChessException;
import com.company.model.command.Command;
import com.company.model.danger.Danger;
import com.company.model.piece.figure.Team;
import com.company.model.player.Player;
import com.company.model.piece.Piece;
import com.company.model.board.Way;

public class Castling extends Turn {

    private static final String MARKER = "Castling failed";

    public Castling(Board board) {
        super(board);
    }

    @Override
    protected void action(Way way, Team team) {
        Cell from = way.from;
        Cell to = way.to;

        Cell cellKing = board.get(from).isKing() ? from : to;
        Cell cellRock = board.get(from).isRock() ? from : to;

        int columnKing = cellKing.column > cellRock.column ? cellKing.column - 2 : cellKing.column + 2;
        int columnRock = cellKing.column > cellRock.column ? cellKing.column - 1 : cellKing.column + 1;

        Way kingWay = new Way(cellKing, cellKing.updateColumn(columnKing));
        Piece king = board.transfer(kingWay);

        Way rockWay = new Way(cellRock, cellRock.updateColumn(columnRock));
        Piece rock = board.transfer(rockWay);

        king.incMoveCount();
        rock.incMoveCount();
    }

    @Override
    protected Cell[] commandToCells(Player player, Command command) {
        String string = command.getString();
        int row = player.getTeam() == Team.WHITE ? 1 : 8;
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
        throw new ChessException(message);
    }

    @Override
    protected void specialVerify(Way way, Danger danger) {

        if (danger.isCheck()) {
            String message = String.format("%s: check to the king", MARKER);
            throw new ChessException(message);
        }

        Cell from = way.from;
        Cell to = way.to;

        Piece[] pieces = new Piece[]{board.get(from), board.get(to)};

        for (Piece piece : pieces) {
            if (piece.isMoved()) {
                String message = String.format("%s: %s already walked", MARKER, piece.getRank().name().toLowerCase());
                throw new ChessException(message);
            }
        }

        int step = sign(to.column - from.column);

        if (BoardHelper.isObstacleLine(board, from, to)) {
            String message = String.format("%s: obstacles in the way of chess pieces", MARKER);
            throw new ChessException(message);
        }

        for (int i = from.column; i != to.column; i+=step) {
            Cell check = new Cell(i, from.row);

            if (danger.isUnderAttack(check)) {
                String message = String.format("%s: %s under attack", MARKER, Board.toPosition(check));
                throw new ChessException(message);
            }
        }
    }

    @Override
    protected String messageNoUnit(Cell cell) {
        return String.format("%s: square %s has no piece", MARKER, Board.toPosition(cell));
    }

    @Override
    protected String messageAlienUnit(Cell cell) {
        return String.format("%s: %s another player's piece", MARKER, Board.toPosition(cell));
    }
}
