package com.company.view;

import com.company.board.Board;

public interface Printer {
    void print(Board board);
    void print(String string);
    void println(String string);
    void println();
    void printf(String format, Object ... args);
}
