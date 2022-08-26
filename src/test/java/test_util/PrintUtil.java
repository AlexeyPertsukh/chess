package test_util;

import com.company.model.artificial_intelligence.Ai;
import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.unit.Unit;
import com.company.view.ConsolePrinter;
import com.company.view.Printer;

import java.util.List;

public class PrintUtil {
    private PrintUtil() {
    }

    public static void print(List<Ai.PossibleMove> list) {
        int i = 0;
        for (Ai.PossibleMove pm : list) {
            String string = String.format("%d. %s-%s %d", i++, Board.toPosition(pm.from), Board.toPosition(pm.to), pm.value);
            System.out.println(string);
        }
    }

    public static void printMoveValuesTable(List<Ai.PossibleMove> list) {

        int[][] table = new int[Board.SIZE][Board.SIZE];

        for (Ai.PossibleMove pm : list) {
            int row = pm.to.row;
            int column = pm.to.column;
            table[row][column] = pm.value;
        }

        for (int[] arr : table) {
            for (int n : arr) {
                System.out.printf(" %-4d |", n);
            }
            System.out.println();
            System.out.println("--------------------------------------------------------");
        }

    }

    public static void printBoard(Board board) {
        Printer printer = new ConsolePrinter();
        printer.print(board);
    }

    public static void printPrimitiveBoard(Board board) {

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                Cell cell = new Cell(j, i);
                Unit unit = board.get(cell);
                String s = "  %c%c  |";
                if(unit.isNull()) {
                    s = String.format(s, ' ', ' ');
                } else {
                    s = String.format(s, unit.getLetter(), unit.getColor().name().toLowerCase().charAt(0));
                }

                System.out.print(s);
            }
            System.out.println();
            System.out.println("--------------------------------------------------------");

        }
    }

}
