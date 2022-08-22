package com.company.view;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.unit.Unit;

public class ConsolePrinter implements Printer {

    private static final char SPACE = '\u2003';
    private static final String TEMPLATE =  " %c ";
    private static final String SEPARATOR =  "|";

    @Override
    public void print(Board board) {
        System.out.println();
        printBoarLetters();

        for (int i = 0; i < Board.SIZE; i++) {
            printBoardLine();
            printBoardNum(i);
            for (int j = 0; j < Board.SIZE; j++) {
                Cell cell = new Cell(j, i);
                Unit figure = board.get(cell);
                String s = SEPARATOR + TEMPLATE;
                char ch = figure.isNull() ? SPACE : figure.getIcon();
                System.out.printf(s, ch);
            }
            System.out.print(SEPARATOR);
//            printBoardNum(i);
            System.out.println();
        }
        printBoardLine();
        printBoarLetters();
        System.out.println();
    }

    @Override
    public void print(String string) {
        System.out.print(string);
    }

    @Override
    public void println(String string) {
        System.out.println(string);
    }

    @Override
    public void println() {
        System.out.println();
    }

    @Override
    public void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    private void printBoardLine() {
        String s = "+%0" + (Board.SIZE * 4 + 4) + "d+";
        s = String.format(TEMPLATE, ' ') + String.format(s, 0).replace('0', '-');
        System.out.println(s);
    }

    private void printBoardNum(int num) {
        System.out.printf(TEMPLATE, (Board.SIZE - num + '0'));
    }

    private void printBoarLetters() {
        System.out.printf(TEMPLATE, ' ');
        char ch = 'A';
        for (int i = 0; i < Board.SIZE; i++) {
            String s = SPACE + TEMPLATE;
            System.out.printf(s, ch++);
        }
        System.out.println();
    }

    private static boolean isEven(int j, int i) {
        return j % 2 == 1 && i % 2 == 1;
    }
}
