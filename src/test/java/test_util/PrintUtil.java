package test_util;

import com.company.model.board.Board;
import com.company.model.player.Bot;
import com.company.view.ConsolePrinter;
import com.company.view.Printer;

import java.util.List;

public class PrintUtil {
    private PrintUtil() {
    }

    public static void print(List<Bot.PossibleMove> list) {
        int i = 0;
        for (Bot.PossibleMove pm : list) {
            String string = String.format("%d. %s-%s %d", i++, Board.toPosition(pm.from), Board.toPosition(pm.to), pm.value);
            System.out.println(string);
        }
    }

    public static void printMoveValuesTable(List<Bot.PossibleMove> list) {

        int[][] table = new int[Board.SIZE][Board.SIZE];

        for (Bot.PossibleMove pm : list) {
            int row = pm.to.row;
            int column = pm.to.column;
            table[row][column] = pm.value;
        }

        for (int[] arr : table) {
            for (int n : arr) {
                System.out.printf(" %-3d |", n);
            }
            System.out.println();
        }

    }

    public static void printBoard(Board board) {
        Printer printer = new ConsolePrinter();
        printer.print(board);
    }


}
