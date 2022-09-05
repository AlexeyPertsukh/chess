package com.company.model.under_attack;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.piece.Piece;
import com.company.model.piece.figure.Rank;
import com.company.model.piece.figure.Team;
import com.company.model.piece.figure.direction.Direction;
import com.company.model.piece.figure.direction.Distance;
import com.company.model.piece.figure.direction.Offset;

public class UnderAttack {
    private UnderAttack() {
    }

    public static boolean isUnderAttack(Board board, Team team, Cell cell) {
        if (isUnderLongAttackOf(board, team, cell, Direction.ROOK.getOffsetsAttack(), Rank.QUEEN, Rank.ROOK)) {
            return true;
        }
        if (isUnderLongAttackOf(board, team, cell, Direction.BISHOP.getOffsetsAttack(), Rank.QUEEN, Rank.BISHOP)) {
            return true;
        }
        if (isUnderShortAttackOf(board, team, cell, Direction.KNIGHT.getOffsetsAttack(), Rank.KNIGHT)) {
            return true;
        }

        if (isUnderShortAttackOf(board, team, cell, Direction.KING.getOffsetsAttack(), Rank.KING)) {
            return true;
        }

        Offset[] offsets = getPawnOffsets(team);

        return isUnderShortAttackOf(board, team, cell, offsets, Rank.PAWN);
    }

    private static boolean isUnderLongAttackOf(Board board, Team team, Cell cell, Offset[] offsets, Rank... ranks) {
        return isUnderAttackOf(board, team, cell, offsets, Distance.LONG, ranks);
    }

    private static boolean isUnderShortAttackOf(Board board, Team team, Cell cell, Offset[] offsets, Rank... ranks) {
        return isUnderAttackOf(board, team, cell, offsets, Distance.SHORT, ranks);
    }
    private static boolean isUnderAttackOf(Board board, Team team, Cell cell, Offset[] offsets, Distance distance, Rank... ranks) {
        for (Offset o : offsets) {
            Cell check = cell.sum(o);
            while (board.isCorrect(check)) {
                if (board.isOccupied(check)) {
                    Piece piece = board.get(check);
                    if (isEnemy(piece, team) && isContains(ranks, piece.getRank())) {
                        return true;
                    }
                    break;
                }

                if (distance == Distance.SHORT) {
                    break;
                }
                check = check.sum(o);
            }
        }
        return false;
    }

    private static boolean isContains(Rank[] ranks, Rank find) {
        for (Rank r : ranks) {
            if (r == find) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEnemy(Piece piece, Team myTeam) {
        return piece.getTeam() != myTeam;
    }

    private static final Offset[] ENEMY_PAWN_BLACK_OFFSETS =  new Offset[] {new Offset(-1, -1), new Offset(1, -1) };
    private static final Offset[] ENEMY_PAWN_WHITE_OFFSETS =  new Offset[] {new Offset(-1, 1), new Offset(1, 1) };
    private static Offset[] getPawnOffsets(Team myTeam) {
        return myTeam.isWhite() ? ENEMY_PAWN_BLACK_OFFSETS : ENEMY_PAWN_WHITE_OFFSETS;
    }
}
