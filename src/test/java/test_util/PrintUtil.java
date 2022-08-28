package test_util;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.danger.Danger;
import com.company.model.danger.DangerMatrix;
import com.company.model.figure.FigureColor;
import com.company.model.unit.Unit;
import com.company.view.ConsolePrinter;
import com.company.view.Printer;

public class PrintUtil {
//    private PrintUtil() {
//    }
//
//    public static void print(List<PossibleMove> list) {
//        int i = 0;
//        for (PossibleMove pm : list) {
//            String string = String.format("%d. %s-%s %d", i++, Board.toPosition(pm.from), Board.toPosition(pm.to), pm.value);
//            System.out.println(string);
//        }
//    }

//    public static void printMoveValuesTable(List<PossibleMove> list) {
//
//        int[][] table = new int[Board.SIZE][Board.SIZE];
//
//        for (PossibleMove pm : list) {
//            int row = pm.to.row;
//            int column = pm.to.column;
//            table[row][column] = pm.value;
//        }
//
//        for (int[] arr : table) {
//            for (int n : arr) {
//                System.out.printf(" %-4d |", n);
//            }
//            System.out.println();
//            System.out.println("--------------------------------------------------------");
//        }
//
//    }

    public static void printBoard(Board board) {
        Printer printer = new ConsolePrinter();
        printer.print(board);
    }

    public static void print(DangerMatrix dangerMatrix) {
        boolean[][] array = dangerMatrix.toArray();
        print(array);
        System.out.println("Check: " + dangerMatrix.isCheck());
        System.out.println("CheckLists.size: " + dangerMatrix.getCheckLists().size());

        if (dangerMatrix.isCheck()) {

            for (Danger.CheckList cl : dangerMatrix.getCheckLists()) {
                for (Cell c : cl) {
                    System.out.println(Board.toPosition(c));
                }

            }

        }
    }

    private static void print(boolean[][] array) {
        for (boolean[] ar : array) {
            for (boolean b : ar) {
                String s = b ? " 1 " : " 0 ";
                System.out.print(s);
            }
            System.out.println();
        }
        System.out.println("---------------");
        System.out.println();
    }


    public static void printPrimitiveBoard(Board board) {

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                Cell cell = new Cell(j, i);
                Unit unit = board.get(cell);
                String s = "  %c%c  |";
                if (unit.isNull()) {
                    s = String.format(s, ' ', ' ');
                } else {
                    s = unit.getColor() == FigureColor.WHITE ?
                            String.format(s, unit.getLetter(), unit.getColor().name().toLowerCase().charAt(0)) :
                            String.format(s, Character.toLowerCase(unit.getLetter()), unit.getColor().name().toUpperCase().charAt(0));
                }

                System.out.print(s);
            }
            System.out.println();
            System.out.println("--------------------------------------------------------");

        }
    }

}
