package com.company.model.board;

import com.company.model.chess_exception.ChessException;
import com.company.model.piece.figure.Team;
import com.company.model.piece.figure.Rank;
import com.company.model.piece.Piece;
import com.company.model.piece.PieceNull;

public class Board {
    public static final int SIZE = 8;
    private final Piece[][] array = new Piece[SIZE][SIZE];

    public Board() {
        clear();
    }

    private void clear() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = PieceNull.getInstance();
            }
        }
    }

    public void insert(Piece piece, String position) {
        Cell cell = toCell(position);
        insert(piece, cell);
    }

    private void insert(Piece piece, Cell cell) {
        insert(piece, cell.row, cell.column);
    }

    private void insert(Piece piece, int row, int column) {
        array[row][column] = piece;
    }

    public static Cell toCell(String position) {
        if (position.length() != 2) {
            throw new ChessException(String.format("incorrect length string position: %d, may be 2", position.length()));
        }
        int column = position.toLowerCase().charAt(0) - 'a';
        int row = SIZE - (position.charAt(1) - '0');
        verifyCoordinateLimit(column, row);
        return new Cell(column, row);
    }

    public static String toPosition(Cell cell) {
        int column = cell.column;
        int row = cell.row;

        verifyCoordinateLimit(column, row);
        char[] arr = new char[]{'a', '0'};
        arr[0] += column;
        arr[1] += SIZE - row;
        return String.valueOf(arr);
    }

    private static void verifyCoordinateLimit(int... coordinates) {
        for (int n : coordinates) {
            if (n < 0 || n >= SIZE) {
                throw new ChessException(String.format("incorrect coordinate number: %d, may be 0... %d ", n, SIZE - 1));
            }
        }
    }

    public Piece get(int row, int column) {
        return array[row][column];
    }

    public Piece get(Cell cell) {
        return get(cell.row, cell.column);
    }

    public boolean isCorrect(Cell cell) {
        return cell.column >= 0 && cell.column < SIZE && cell.row >= 0 && cell.row < SIZE;
    }

    public Piece transfer(Way way) {
        Piece piece = remove(way.from);
        insert(piece, way.to);
        return piece;
    }

    private Piece remove(Cell cell) {
        Piece out = array[cell.row][cell.column];
        array[cell.row][cell.column] = PieceNull.getInstance();
        return out;
    }

    public Cell find(Rank rank, Team team) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Piece piece = get(i, j);
                if (!piece.isNull() && piece.getRank() == rank && piece.getTeam() == team) {
                    return new Cell(j, i);
                }

            }
        }

        String message = String.format("%s %s not found on board", team.name().toLowerCase(), rank.name().toLowerCase());
        throw new ChessException(message);
    }


    @Override
    public Board clone() throws CloneNotSupportedException {
        Board board = new Board();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Piece piece = (Piece) get(i, j).clone();
                board.insert(piece, i, j);
            }
        }
        return board;
    }
}
